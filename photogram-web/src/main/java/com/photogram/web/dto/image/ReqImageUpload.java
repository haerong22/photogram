package com.photogram.web.dto.image;

import com.photogram.core.domain.entity.image.Image;
import com.photogram.core.domain.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqImageUpload {

    private MultipartFile file;

    private String caption;

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
