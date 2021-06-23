package com.photogram.web.controller.api;

import com.photogram.core.domain.dto.CommonResponse;
import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable long toUserId) {
        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);

        return ResponseEntity.ok().body(CommonResponse.builder()
                .code(1)
                .result("구독하기 성공")
                .build());
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @PathVariable long toUserId) {

        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .code(1)
                .result("구독취소 성공")
                .build());
    }
}
