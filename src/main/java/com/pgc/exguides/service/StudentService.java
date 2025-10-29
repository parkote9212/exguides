package com.pgc.exguides.service;

import com.pgc.exguides.domain.Student;
import com.pgc.exguides.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentMapper studentMapper;

    /**
     * CREATE (생성)
     *
     * @Transactional: 이 메소드 전체를 하나의 트랜잭션으로 묶음
     * 중간에 실패하면 모든 DB 작업을 롤백(Rollback)함
     */
    @Transactional
    public Student createStudent(Student student) {
//        (비즈니스 로직) 생성 시각과 수정시각을 현재로 설정
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        studentMapper.insert(student);
        return student;
    }

    public Student getStudentById(Long id) {
//        (AOP 테스트용 임시코드)
        if (id == 999L) {
            throw new RuntimeException("AOP 예외 테스트용 강제 에러!");
        }
        return studentMapper.findById(id);
    }

//    READ ALL (전체 조회)
    public List<Student> getAlllStudents(){
        return studentMapper.findAll();
    }

//    UPDATE(수정)
    @Transactional
    public Student updateStudent(Long id, Student studentDetails){
        Student existingStudent = studentMapper.findById(id);
        if (existingStudent == null){
            return null;
        }
//        전달받은 정보로 기존 학생 정보 업데이트
        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setAge(studentDetails.getAge());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        studentMapper.update(existingStudent);
        return existingStudent;
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentMapper.deleteById(id);
    }

}
