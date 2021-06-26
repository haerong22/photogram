package com.photogram.web.controller;

import com.photogram.core.domain.entity.user.User;
import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.dto.user.RespUserProfile;
import com.photogram.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable Long pageUserId,
                          @AuthenticationPrincipal PrincipalDetails principalDetails,
                          Model model) {

        RespUserProfile userProfile = userService.getUserProfile(pageUserId, principalDetails.getUser().getId());

        model.addAttribute("dto", userProfile);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails,
                         Model model) {
        model.addAttribute("information", principalDetails);
        return "user/update";
    }
}
