package com.photogram.web.service;

import com.photogram.core.repository.SubscribeRepository;
import com.photogram.web.dto.subscribe.RespSubscribe;
import com.photogram.web.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    public List<RespSubscribe> getSubscribeList(long principalId, long pageUserId) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profile_image_url profileImageUrl, ");
        sb.append("if((SELECT 1 FROM subscribe WHERE from_user_id = ? AND to_user_id = u.id), 1, 0) subscribeState, ");
        sb.append("if((?=u.id), 1, 0) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("on u.id = s.to_user_id ");
        sb.append("WHERE s.from_user_id=?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        System.out.println("여기");
        List<RespSubscribe> list = result.list(query, RespSubscribe.class);
        list.forEach(System.out::println);


        return list;
    }

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("subscribe fail");
        }
    }

    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
