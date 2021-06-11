package com.photogram.web.controller;

import com.photogram.core.domain.entity.Member;
import com.photogram.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestApiController {

    private final MemberRepository memberRepository;

    @PostMapping("/member")
    public String saveMember() {
        Member member = new Member();
        memberRepository.save(member);
        return "success";
    }

    @GetMapping("member")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
