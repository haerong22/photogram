package com.photogram.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespSubscribe {

    private BigInteger id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState;
}
