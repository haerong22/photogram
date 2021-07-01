package com.photogram.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReqComment {

    @NotNull
    private Long imageId;

    @NotBlank
    private String content;
}
