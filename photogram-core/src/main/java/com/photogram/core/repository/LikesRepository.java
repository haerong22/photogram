package com.photogram.core.repository;

import com.photogram.core.domain.entity.likes.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(image_id, user_id, create_date) VALUES(:imageId, :principalId, now())", nativeQuery = true)
    int mLikes(@Param("imageId") Long imageId,
               @Param("principalId") Long principalId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE image_id=:imageId AND user_id=:principalId", nativeQuery = true)
    int mUnLikes(@Param("imageId") Long imageId,
                 @Param("principalId") Long principalId);
}
