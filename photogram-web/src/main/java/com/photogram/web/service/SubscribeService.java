package com.photogram.web.service;

import com.photogram.core.repository.SubscribeRepository;
import com.photogram.web.exception.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void subscribe(long fromUserId, long toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("구독 실");
        }
    }

    @Transactional
    public void unSubscribe(long fromUserId, long toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
