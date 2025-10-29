package com.pgc.exguides.service;

import com.pgc.exguides.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("학생을 생성하고 ID로 조회할 수 이썽야 한다.")
    void createAndFindStudent(){
//        1. Given(준비)
        Student newStudent = new Student();
        newStudent.setName("테스트학생");
        newStudent.setEmail("test@example.com");
        newStudent.setAge(25);

//        2. When (실행)
        Student createdStudent = studentService.createStudent(newStudent);
        Student foundStudent = studentService.getStudentById(createdStudent.getId());

//        3. Then (검증)
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getName()).isEqualTo("테스트학생");
        assertThat(foundStudent.getId()).isEqualTo(createdStudent.getId());

    }
}
