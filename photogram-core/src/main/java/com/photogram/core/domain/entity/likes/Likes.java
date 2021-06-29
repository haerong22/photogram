package com.photogram.core.domain.entity.likes;

import com.photogram.core.domain.entity.image.Image;
import com.photogram.core.domain.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"image_id", "user_id"}
                )
        }
)
public class Likes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "image_id")
    @ManyToOne
    private Image image;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
