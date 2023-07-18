package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import com.tj.edu.practice5.jpa.model.BookAndId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);

    // JPQL
    @Query(value = "select b from Book b where name = ?1")
    List<Book> findByMyBooks(String name);

    @Query(value = "select b from Book b where name = ?1 and id = ?2")
    List<Book> findByMyBooksAndMyId(String name, Long id);

//    @Query(value = "select b from Book b where name = :name and id = :id")
//    List<Book> findByNamedByMyBooksAndMyId(@Param("id") Long id, @Param("name") String name);
    @Query(value = "select b from Book b where name like %:name% and id = :id")
    List<Book> findByNamedByMyBooksAndMyId(@Param("id") Long id, @Param("name") String name);


    @Query(value = "select b.name from Book b where name = ?1")
    List<String> findNameByMyBooks(String name);

    // return 해주는 book객체를 convert가 안되서 안되는 메소드
    @Query(value = "select b.id id, b.name name from Book b where name = ?1")
//    List<Book> findNameIdByMyBooks(String name);
    List<Map<String, Object>> findNameIdByMyBooks(String name);

    @Query(value = "select b.id id, b.name name from Book b where name = :name")
    List<Map<String, Object>> findByNamedNameIdByMyBooks(@Param("name") String name);

    @Query(value = "select * from book where name = ?1", nativeQuery = true)
    List<Book> findByNativeByMyBooks(String name);

    @Query(value = "select * from book where name = :name", nativeQuery = true)
    List<Book> findByNativeNameByMyBooks(String name);

    List<Book> findByNameIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtLessThan(String name, LocalDateTime createAt, LocalDateTime updateAt);

    @Query(value = "select b.id abc, b.name name2 from Book b where name = :name")
    List<BookAndId> findByCustomNamedNameIdByMyBooks(@Param("name") String name);

//    업데이트는 네이티브가 낫다
    @Transactional
    @Modifying  //수정 삭제 시 필수 임
    @Query(value = "update book set name = '이상한자바책' where id = :id", nativeQuery = true)
    int updateSpecificName(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Book b set b.name = '이상한자바책' where b.id = :id")
    int updateSpecificNameByJPQL(@Param("id") Long id);

    @Transactional
    @Modifying
//    @Query(value = "Delete from book where id = :id", nativeQuery = true)
    @Query(value = "Delete from Book b where id = :id")
    int deleteSpecificNameByJPQL(@Param("id") Long id);

    @Transactional
    @Modifying
//    @Query(value = "insert into book(`id`, `name`, `publisher_id`, `create_at`, `update_at`) values (:id, :name, :pid, now(), now())", nativeQuery = true)
    @Query(value = "insert into Book b(`id`, `name`, `publisher_id`, `create_at`, `update_at`) values (:id, :name, :pid, now(), now())")
    int insertSpecificNameByJPQL(@Param("id") Long id, String name, Long pid);


}
