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
    void mSubscribe(@Param("fromUserId") Long fromUserId,
                    @Param("toUserId") Long toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE from_user_id=:fromUserId AND to_user_id=:toUserId", nativeQuery = true)
    void mUnSubscribe(@Param("fromUserId") Long fromUserId,
                      @Param("toUserId") Long toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id=:principalId AND to_user_id=:pageUserId", nativeQuery = true)
    int mSubscribeState(@Param("principalId") Long principalId,
                        @Param("pageUserId") Long pageUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id=:pageUserId", nativeQuery = true)
    int mSubscribeCount(@Param("pageUserId") Long pageUserId);

}

