package com.pgc.exguides.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentTest {
    private Long id;
    @NotBlank(message = "이름은 필수항목")
    private String name;
    @Email(message = "이메일 형식@ 입력필수")
    private String email;
    @Min(value = 1, message = "나이는 1세 이상")
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
