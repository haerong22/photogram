package com.photogram.web.controller;

import com.photogram.web.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable long id) {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable long id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails,
                         Model model) {
        model.addAttribute("information", principalDetails);
        return "user/update";
    }
}
