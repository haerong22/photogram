package com.photogram.web.service;

import com.photogram.web.dto.auth.ReqSignupDto;
import com.photogram.core.domain.entity.user.User;
import com.photogram.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User createUser(ReqSignupDto reqSignupDto) {
        User user = reqSignupDto.toEntity();
        user.setPassword(bCryptPasswordEncoder.encode(reqSignupDto.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }
}
