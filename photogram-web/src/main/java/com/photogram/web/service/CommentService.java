package com.photogram.web.service;

import com.photogram.core.domain.entity.comment.Comment;
import com.photogram.core.domain.entity.image.Image;
import com.photogram.core.domain.entity.user.User;
import com.photogram.core.repository.CommentRepository;
import com.photogram.core.repository.UserRepository;
import com.photogram.web.exception.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment saveComment(String content, Long imageId, Long userId) {
        Image image = Image.builder().id(imageId).build();
        User userEntity= userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("user not found"));

        Comment comment = Comment.builder()
                .content(content)
                .image(image)
                .user(userEntity)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
