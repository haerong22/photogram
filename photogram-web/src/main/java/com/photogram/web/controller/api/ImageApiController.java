package com.photogram.web.controller.api;

import com.photogram.core.domain.entity.image.Image;
import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.dto.CommonResponse;
import com.photogram.web.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;

    @GetMapping("/api/images")
    public ResponseEntity<?> getStoryList(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<Image> imageList = imageService.getStoryList(principalDetails.getUser().getId());

        return ResponseEntity.ok().body(CommonResponse.builder()
                .code(1)
                .result("success")
                .data(imageList)
                .build());
    }
}
