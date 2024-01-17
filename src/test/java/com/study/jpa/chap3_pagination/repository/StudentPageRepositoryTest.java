package com.study.jpa.chap3_pagination.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class StudentPageRepositoryTest {
    @Autowired
    StudentPageRepository studentPageRepository;

    @BeforeEach
    void bulkInsert(){
        //학생 147명 저장
        for (int i = 1; i <=147; i++) {
            Student s = Student.builder()
                    .name("김딱팍" + i)
                    .city("도시시" + i)
                    .major("전공공공" + i)
                    .build();
            studentPageRepository.save(s);
        }
    }

    @Test
    @DisplayName("기본적인 페이징 테스트" )
    void basicPagetest () {
        //given
        int pageNo=1;
        int amount=10;
        //페이지 정보 객체 생성(Pageable)
        //여기서는 페이지 정보가 zero-based :1페이지는 0이다
        Pageable pageInfo= PageRequest.of(
                pageNo-1,
                amount,
//                Sort.by("name").descending()
                Sort.by(
                        Sort.Order.desc("name"),
                        Sort.Order.asc("city")

                )
//
//
                ); //엔터티 필드명
        //when
        Page<Student> studentPage = studentPageRepository.findAll(pageInfo);
        ///실질적인 조회데이터 꺼내기
        List<Student> studentList = studentPage.getContent();

        //총페이지수
        int totalPages = studentPage.getTotalPages();

        //총학생수
        long totalElements = studentPage.getTotalElements();

        //then
        System.out.println("\n\n\n");
        studentList.forEach(System.out::println);
        System.out.println("\n\n\n");
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);

    }
    @Test
    @DisplayName("이름검색 + 페이징")
    void testSearchAndPagination() {
        //given
        int pageNo = 1;
        int size = 10;
        Pageable pageInfo = PageRequest.of(pageNo - 1, size);
        //when
        Page<Student> students
                = studentPageRepository.findByNameContaining("3", pageInfo);

        //then
        System.out.println("\n\n\n");
        students.getContent().forEach(System.out::println);
        System.out.println("\n\n\n");
    }


}