package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepository2Test {
    @Autowired
    private MemberRepository2 memberRepository2;

    @Test
    void memberJpqlTest1(){
        System.out.println("--------------------memberJpqlTest1-1----------------------");
        List<Member> member2List = memberRepository2.findAll();
        member2List.forEach(System.out::println);

        System.out.println("--------------------memberJpqlTest1-2----------------------");
        List<Member> member2GetId = memberRepository2.member2findById(1L);
        member2GetId.forEach(System.out::println);

        System.out.println("--------------------memberJpqlTest1-3----------------------");
        List<Member> member2GetId2 = memberRepository2.member2findById2(1L);
        member2GetId2.forEach(System.out::println);
    }

}