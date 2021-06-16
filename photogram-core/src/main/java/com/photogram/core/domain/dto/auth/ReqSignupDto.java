package com.photogram.core.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqSignupDto {

    private String username;
    private String password;
    private String email;
    private String name;
}
