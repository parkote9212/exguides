package com.pgc.exguides.service;

import com.pgc.exguides.domain.StudentTest;
import com.pgc.exguides.mapper.StudentMapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class StudentServiceTest2 {
    @Autowired
    private StudentMapperTest studentMapperTest;

    @Test
    @DisplayName("학생 등록후 조회 확인(TDD)")
    void testRegisterAndFind() {
//		given
        StudentTest studentTest = StudentTest.builder().name("홍길동").email("hhh@jjj.com").age(22).build();

//		기본 생성자방식
//		new StudentTest(null, "홍길동", "hhh@jjj.com", 22, null, null);
//		new Student("홍길동", "hong@test.com", 20);

//		when
        int result = studentMapperTest.insert(studentTest);

//		then
        assertEquals(1, result, "등록은 1건 성공해야 한다");
        assertNotNull(studentTest.getId(), "등록 후 id가 자동 생성되어야 한다");

        StudentTest findStudent = studentMapperTest.findById(studentTest.getId());
        assertEquals("홍길동", findStudent.getName());
//		assertEquals("hong@test.com", findStudent.getEmail());
        assertEquals("hhh@jjj.com", findStudent.getEmail());

    }

    @Test
    @DisplayName("학생 전체 조회 (TDD)")
    void testFindAll() {
        // given
        StudentTest s3 = StudentTest.builder().name("홍길동3").email("hong3@test.com").age(23).build();
        StudentTest s4 = StudentTest.builder().name("홍길동4").email("hong4@test.com").age(24).build();
        studentMapperTest.insert(s3);
        studentMapperTest.insert(s4);

        // when
        List<StudentTest> students = studentMapperTest.findAll();

        // then
        assertTrue(students.size() >= 2);
//        assertEquals(2, students.size());
        // 스스로 수정해보자
        // assertEquals("김철수", students.get(0).getName()); // 최신순
        assertEquals("홍길동4", students.get(0).getName()); // 최신순
    }
}
