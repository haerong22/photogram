package com.photogram.web.dto.comment;

import lombok.Data;

@Data
public class ReqComment {

    private Long imageId;
    private String content;
}
