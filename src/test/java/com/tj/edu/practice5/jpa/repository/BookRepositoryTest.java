package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void bookTest() {
        Book book = givenBook();

        bookRepository.findAll();
    }
    @Test
    @Transactional
    void relationTest1() {
        givenBookAndReview();       // 테스트 기초 데이터 생성

        Member member = memberRepository.findByEmail("amsun@thejoeun.com");
        System.out.println(">>> member: " + member);
//        System.out.println(">>> review: " + member.getReviews());
        Book book = member.getReviews().get(0).getBook();
        System.out.println(">>> book: " + book);
        Publisher publisher = book.getPublisher();
        System.out.println(">>> publisher: " + publisher);
    }
    private Book givenBook(Publisher publisher) {
        Book book = Book.builder()
                .name("React와 스프링 부트로 만들기 프로젝트")
                .publisher(publisher)
                .build();
        return bookRepository.save(book);
    }
    private Book givenBook() {
        Book book = Book.builder()
                .name("React와 스프링 부트로 만들기 프로젝트2")
                .build();
        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = Publisher.builder()
                .name("조은출판사")
                .build();
        return publisherRepository.save(publisher);
    }
    private void givenBookAndReview() {
        givenReview(givenMember(), givenBook(givenPublisher()));
    }
    private Review givenReview(Member member, Book book) {
        Review review = Review.builder()
                .title("너무 재미있는 책")
                .member(member)
                .book(book)
                .build();
        return reviewRepository.save(review);
    }
    private Member givenMember() {
        return memberRepository.findByEmail("amsun@thejoeun.com");
    }


    @Test
    void jpqlTest1() {
        // 성공
        List<Book> bookList = bookRepository.findByMyBooks("재미있는 자바책");
        bookList.forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------1");

        // 성공
        List<Book> bookList2 = bookRepository.findByMyBooksAndMyId("재미있는 자바책",2L);
        bookList2.forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------2");

        // 성공
        List<String> bookList3 = bookRepository.findNameByMyBooks("재미있는 자바책");
        bookList3.forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------3");

        // 에러
//        List<Book> bookList4 = bookRepository.findNameIdByMyBooks("재미있는 자바책");
//        bookList4.forEach(System.out::println);
        // 성공
        List<Map<String, Object>> listMap1 = bookRepository.findNameIdByMyBooks("재미있는 자바책");
        listMap1.forEach(x -> System.out.println(x.entrySet()));
//        listMap1.forEach(x -> System.out.println(x.values()));

        System.out.println("--------------------------------------------------------------------4");

        List<Map<String, Object>> listMap2 = bookRepository.findByNamedNameIdByMyBooks("재미있는 자바책");
        listMap2.forEach(x -> System.out.println(x.entrySet()));

        System.out.println("--------------------------------------------------------------------5");

        List<Book> bookListByNamed = bookRepository.findByNamedByMyBooksAndMyId( 1L, "재미있는 자바책");
        bookListByNamed.forEach(System.out::println);

    }


    @Test
    void nativeSqlTest1() {
        List<Book> bookListByNative = bookRepository.findByNativeByMyBooks( "재미있는 자바책");
        bookListByNative.forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------nativeSqlTest1-1");

        List<Book> bookListByNativeName = bookRepository.findByNativeNameByMyBooks( "재미있는 자바책");
        bookListByNativeName.forEach(System.out::println);
    }


    @Test
    void customModelJpaTest1() {
        System.out.println("-------------------------------------------------------------------customModelJpaTest1-1");

        List<BookAndId> bookAndIdList = bookRepository.findByCustomNamedNameIdByMyBooks( "재미있는 자바책");
        bookAndIdList.forEach(s -> System.out.println(s.getAbc() + " : " + s.getName2()));
    }

//    업데이트
    @Test
//    @Transactional
    void updateJpaTest1() {
//        int isUpdate = bookRepository.updateSpecificName(2L);
//        System.out.println("2번 id를 가진 book의 이름 " + (isUpdate > 0 ? " 바뀜" : " 바뀌지 않음"));

        int isUpdate = bookRepository.updateSpecificNameByJPQL(2L);
        System.out.println("2번 id를 가진 book의 이름 " + (isUpdate > 0 ? " 바뀜" : " 바뀌지 않음"));
    }
    
    @Test
    void deleteJpaTest1(){
        int isDelete = bookRepository.deleteSpecificNameByJPQL(2L);
        System.out.println("2번 id를 가진 book의 이름 삭제" + (isDelete > 0 ? " 삭제" : " 삭제대상없음"));
    }
    @Test
    void insertJpaTest1(){
        int isInsert = bookRepository.insertSpecificNameByJPQL(null,"어려운 데이터베이스",1L);
        System.out.println("2번 id를 가진 book 생성" + (isInsert > 0 ? " 성공" : " 실패"));
    }
}
