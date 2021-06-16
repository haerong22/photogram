package com.photogram.web.controller;

import com.photogram.core.domain.dto.auth.ReqSignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AuthController {

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(ReqSignupDto reqSignupDto) {
        log.info("ReqSignupDto ==> {}", reqSignupDto);
        return "auth/signin";
    }
}
