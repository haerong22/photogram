package com.photogram.web.service;

import com.photogram.core.domain.entity.user.User;
import com.photogram.core.repository.SubscribeRepository;
import com.photogram.core.repository.UserRepository;
import com.photogram.web.dto.user.RespUserProfile;
import com.photogram.web.handler.ex.CustomApiException;
import com.photogram.web.handler.ex.CustomException;
import com.photogram.web.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadDirectory;

    @Transactional
    public RespUserProfile getUserProfile(Long pageUserId, Long userId) {
        User userEntity = userRepository.findById(pageUserId)
                .orElseThrow(() -> new CustomException("Not Found User"));

        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        int subscribeState = subscribeRepository.mSubscribeState(userId, pageUserId);

        userEntity.getImages().forEach(image -> {
            image.setLikeCount(image.getLikes().size());
        });

        return RespUserProfile.builder()
                .user(userEntity)
                .imageCount(userEntity.getImages().size())
                .pageOwnerState(pageUserId.equals(userId))
                .subScribeCount(subscribeCount)
                .subScribeState(subscribeState == 1)
                .build();
    }

    @Transactional
    public User updateUser(Long id, User user){
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomValidationApiException("Not found id"));
        userEntity.setName(user.getName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }

    @Transactional
    public User updateProfileImageUrl(Long principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadDirectory + imageFileName);

        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId)
                .orElseThrow(() -> new CustomApiException("user not found"));

        userEntity.setProfileImageUrl(imageFileName);
        return userEntity;
    }
}
