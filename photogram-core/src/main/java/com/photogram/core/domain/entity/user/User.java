package com.photogram.core.domain.entity.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
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

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
