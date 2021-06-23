package com.photogram.core.repository;

import com.photogram.core.domain.entity.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying
    @Query(value = "INSERT INTO subscribe(from_user_id, to_user_id, create_date) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(@Param("fromUserId") long fromUserId,
                    @Param("toUserId") long toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE from_user_id=:fromUserId AND to_user_id=:toUSerId", nativeQuery = true)
    void mUnSubscribe(long fromUserId, long toUserId);

}

