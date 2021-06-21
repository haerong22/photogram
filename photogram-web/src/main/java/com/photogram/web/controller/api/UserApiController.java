package com.photogram.web.controller.api;

import com.photogram.core.domain.dto.CommonResponse;
import com.photogram.core.domain.dto.user.ReqUserUpdate;
import com.photogram.core.domain.entity.user.User;
import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.exception.ex.CustomValidationApiException;
import com.photogram.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CommonResponse<?> update(@PathVariable long id,
                                    @Valid ReqUserUpdate reqUserUpdate,
                                    BindingResult bindingResult,
                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (bindingResult.hasFieldErrors()) {
            throw new CustomValidationApiException("유효성 검사 실패", bindingResult);
        }

        User userEntity = userService.updateUser(id, reqUserUpdate.toEntity());

        principalDetails.setUser(userEntity);
        return CommonResponse.builder()
                .code(1)
                .result("회원 수정 완료")
                .data(userEntity)
                .build();
    }
}
