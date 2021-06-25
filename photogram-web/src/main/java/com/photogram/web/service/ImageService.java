package com.photogram.web.service;

import com.photogram.core.domain.entity.image.Image;
import com.photogram.core.repository.ImageRepository;
import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.dto.image.ReqImageUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadDirectory;

    @Transactional
    public void uploadImage(ReqImageUpload reqImageUpload, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + reqImageUpload.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadDirectory + imageFileName);

        try {
            Files.write(imageFilePath, reqImageUpload.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image image = reqImageUpload.toEntity(imageFileName, principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

        log.info("saved image entity ====> {}", imageEntity);
    }
}
