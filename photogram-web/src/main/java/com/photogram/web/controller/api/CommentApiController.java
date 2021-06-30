package com.photogram.web.controller.api;

import com.photogram.core.domain.entity.comment.Comment;
import com.photogram.web.config.auth.PrincipalDetails;
import com.photogram.web.dto.CommonResponse;
import com.photogram.web.dto.comment.ReqComment;
import com.photogram.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> createComment(@RequestBody ReqComment reqComment,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Comment comment = commentService.saveComment(reqComment.getContent(), reqComment.getImageId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(CommonResponse.builder()
                .code(1)
                .result("success")
                .data(comment)
                .build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        return null;
    }
}
