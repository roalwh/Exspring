package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository2 extends JpaRepository<Member, Long> {

    @Query(value = "select * from member where id=:id",nativeQuery = true)
    List<Member> member2findById(Long id);

    @Query(value = "select m from Member m where id=:id")
    List<Member> member2findById2(Long id);

}
