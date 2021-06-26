package com.photogram.web.controller.auth;

import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.dto.image.ReqImageUpload;
import com.photogram.web.exception.ex.CustomValidationException;
import com.photogram.web.service.ImageService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story(Model model) {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular(Model model) {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload(Model model) {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ReqImageUpload reqImageUpload,
                              @AuthenticationPrincipal PrincipalDetails principalDetails,
                              Model model) {
        if (reqImageUpload.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        imageService.uploadImage(reqImageUpload, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

}
