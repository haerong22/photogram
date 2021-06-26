package com.photogram.web.dto.user;

import com.photogram.core.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespUserProfile {

    private boolean pageOwnerState;
    private int imageCount;
    private User user;
}
