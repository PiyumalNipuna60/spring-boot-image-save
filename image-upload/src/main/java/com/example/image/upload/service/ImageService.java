package com.example.image.upload.service;

import com.example.image.upload.dto.ImageDetailsGetDto;
import com.example.image.upload.dto.ImageDto;

import java.util.List;

public interface ImageService {
    ImageDetailsGetDto saveImage(ImageDto imageDto);

    List<ImageDetailsGetDto> getAllImage();

}
