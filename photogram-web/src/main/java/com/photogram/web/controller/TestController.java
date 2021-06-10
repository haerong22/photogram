package com.photogram.web.controller;

import com.photogram.core.domain.entity.Member;
import com.photogram.core.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/member")
    public String saveMember() {
        Member member = new Member();
        memberRepository.save(member);
        return "success";
    }
}
