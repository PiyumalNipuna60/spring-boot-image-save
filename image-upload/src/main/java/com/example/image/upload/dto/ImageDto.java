package com.example.image.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ImageDto {
    private Long id;
    private MultipartFile imageName;
}
