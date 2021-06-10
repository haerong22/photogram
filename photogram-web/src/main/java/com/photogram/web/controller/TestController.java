package com.photogram.web.controller;

import com.photogram.core.domain.entity.Member;
import com.photogram.core.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("member")
    public List<Member> findAll() {
        List<Member> all = memberRepository.findAll();
        all.forEach(v -> {
            System.out.println(v.getUserName());
        });
        return all;
    }
}
