package com.photogram.core.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.photogram.core.domain.entity.image.Image;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phone;
    private String gender;

    private String profileImageUrl;
    private String role;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();


    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
