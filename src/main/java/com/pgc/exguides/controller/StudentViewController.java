package com.pgc.exguides.controller;

import com.pgc.exguides.domain.Student;
import com.pgc.exguides.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentViewController {

    private final StudentService studentService;

    /**
     * READ: 학생 전체 목록 페이지 (index.html)
     */
    @GetMapping(value = {"/", "/students"})
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students); // 3. Model에 데이터를 담아 뷰로 전달
        return "index"; // 4. "index.html" 템플릿을 반환
    }

    /**
     * CREATE: 학생 생성 폼 페이지 (new.html)
     */
    @GetMapping("/students/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student()); // 폼 바인딩을 위한 빈 객체
        return "new"; // "new.html" 템플릿을 반환
    }

    /**
     * CREATE: 학생 생성 처리 (가이드 3: Validation 적용)
     */
    @PostMapping("/students")
    public String createStudent(@Valid @ModelAttribute Student student,
                                BindingResult bindingResult) { // 5. @Valid 바로 뒤에 BindingResult

        // 6. (가이드 3) 유효성 검사 실패 시
        if (bindingResult.hasErrors()) {
            return "new"; // "new.html" 폼으로 다시 돌아감 (에러 메시지 표시)
        }

        studentService.createStudent(student);
        return "redirect:/students"; // 성공 시 목록 페이지로 리다이렉트
    }

    /**
     * UPDATE: 학생 수정 폼 페이지 (edit.html)
     */
    @GetMapping("/students/{id}/edit")
    public String editStudentForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students"; // 학생이 없으면 목록으로
        }
        model.addAttribute("student", student);
        return "edit"; // "edit.html" 템플릿을 반환
    }

    /**
     * UPDATE: 학생 수정 처리 (가이드 3: Validation 적용)
     */
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @Valid @ModelAttribute Student studentDetails,
                                BindingResult bindingResult) { // 7. @Valid 바로 뒤에 BindingResult

        // 8. (가이드 3) 유효성 검사 실패 시
        if (bindingResult.hasErrors()) {
            return "edit"; // "edit.html" 폼으로 다시 돌아감
        }

        studentService.updateStudent(id, studentDetails);
        return "redirect:/students";
    }

    /**
     * DELETE: 학생 삭제 처리
     */
    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}

