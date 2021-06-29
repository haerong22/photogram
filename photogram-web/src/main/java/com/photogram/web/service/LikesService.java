package com.photogram.web.service;

import com.photogram.core.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;


    @Transactional
    public void likes(Long imageId, Long principalId) {
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void unLikes(Long imageId, Long principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }
}
