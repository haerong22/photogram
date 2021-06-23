package com.photogram.web.controller;

import com.photogram.web.dto.auth.ReqSignupDto;
import com.photogram.core.domain.entity.user.User;
import com.photogram.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid ReqSignupDto reqSignupDto) {
        log.info("ReqSignupDto ==> {}", reqSignupDto);
        User userEntity = authService.createUser(reqSignupDto);
        return "auth/signin";
    }
}
