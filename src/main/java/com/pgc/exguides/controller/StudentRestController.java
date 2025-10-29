package com.pgc.exguides.controller;

import com.pgc.exguides.domain.Student;
import com.pgc.exguides.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    /**
     * READ (전체 조회)
     * @GetMapping
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    /**
     * CREATE (생성)
     *
     * @PostMapping
     * @Valid: Student 객체의 유효성 검사 실행
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
//        201 Create (생성성공) 상태 코드와 객체 반환
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    /**
     * READ (ID로 1건 조회)
     *
     * @GetMapping("/{id}")
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
//            200 OK(조회 성공)
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
//            404 Not Found (데잍 없음)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * UPDATE (수정)
     *
     * @PutMapping("/{id}")
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        if (updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE (삭제)
     *
     * @DeleteMapping("/{id}")
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
//         204 No Content (삭제 성공, 반환 데이터 없음)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
