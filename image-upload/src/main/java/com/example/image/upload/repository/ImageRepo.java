package com.example.image.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
