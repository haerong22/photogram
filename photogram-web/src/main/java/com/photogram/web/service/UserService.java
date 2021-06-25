package com.photogram.web.service;

import com.photogram.core.domain.entity.image.Image;
import com.photogram.core.domain.entity.user.User;
import com.photogram.core.repository.UserRepository;
import com.photogram.web.exception.ex.CustomException;
import com.photogram.web.exception.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User getUserProfile(Long userId) {
        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("Not Found User"));
        userEntity.getImages().get(0);

        return userEntity;
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
}
