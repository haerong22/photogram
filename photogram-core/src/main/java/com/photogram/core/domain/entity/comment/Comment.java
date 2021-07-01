package com.photogram.core.domain.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.photogram.core.domain.entity.image.Image;
import com.photogram.core.domain.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String content;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "image_id")
    @ManyToOne
    private Image image;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
