package com.photogram.core.repository;

import com.photogram.core.domain.entity.image.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "SELECT * FROM image WHERE user_id IN (SELECT to_user_id FROM subscribe WHERE from_user_id=:principalId) ORDER BY id DESC", nativeQuery = true)
    Page<Image> mStory(@Param("principalId") Long principalId, Pageable pageable);
}
