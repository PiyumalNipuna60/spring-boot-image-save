package com.example.image.upload.controller;

import com.example.image.upload.dto.ImageDetailsGetDto;
import com.example.image.upload.dto.ImageDto;
import com.example.image.upload.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image_upload")
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Object> saveImage(@ModelAttribute ImageDto imageDto) {
        ImageDetailsGetDto dto = imageService.saveImage(imageDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAllImage() {
        List<ImageDetailsGetDto> dto = imageService.getAllImage();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
