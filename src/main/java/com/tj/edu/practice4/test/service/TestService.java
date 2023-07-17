package com.tj.edu.practice4.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import com.tj.edu.practice4.test.model.Member;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public List<String> getAllStringVal() {
        return Arrays.asList("abc", "xyz");
    }

    public List<Member> getAllMembers() {
        Member member = new Member();
        member.setId(1L);
        member.setName("홍길동");

        List<Member> memberList = Arrays.asList(member);
        return memberList;
    }
}