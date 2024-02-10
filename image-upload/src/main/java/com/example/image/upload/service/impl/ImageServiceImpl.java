package com.example.image.upload.service.impl;

import com.example.image.upload.dto.ImageDetailsGetDto;
import com.example.image.upload.dto.ImageDto;
import com.example.image.upload.model.Image;
import com.example.image.upload.repository.ImageRepo;
import com.example.image.upload.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;
    private final ModelMapper modelMapper;

    public ImageServiceImpl(ImageRepo imageRepo, ModelMapper modelMapper) {
        this.imageRepo = imageRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ImageDetailsGetDto saveImage(ImageDto imageDto) {
        Image image = dtoToImageEntity(imageDto);

        try {

            //create image save path
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadDir = new File(projectPath + "/uploads");
            uploadDir.mkdir();

            imageDto.getImageName().transferTo(new File(uploadDir.getAbsolutePath() + "/" + imageDto.getImageName().getOriginalFilename()));

            //set image path and name
            image.setImageName("uploads/" + imageDto.getImageName().getOriginalFilename());

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        Image save = this.imageRepo.save(image);
        return entityToImageDto(save);
    }

    @Override
    public List<ImageDetailsGetDto> getAllImage() {
        List<Image> all = imageRepo.findAll();
        List<ImageDetailsGetDto> list = new ArrayList<>();
        for (Image image : all) {
            ImageDetailsGetDto dto = entityToImageDto(image);
            list.add(dto);
        }
        return list;
    }


    private ImageDetailsGetDto entityToImageDto(Image save) {
        if (save == null) {
            return null;
        } else {
            return modelMapper.map(save, ImageDetailsGetDto.class);
        }
    }

    private Image dtoToImageEntity(ImageDto imageDto) {
        return modelMapper.map(imageDto, Image.class);
    }
}
