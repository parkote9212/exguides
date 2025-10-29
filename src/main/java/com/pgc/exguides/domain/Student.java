package com.pgc.exguides.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

//1단계 도메인 생성

@Getter
@Setter
@ToString
public class Student {
    private long id;

//   유효성 검사 "정의"를 미리 한다

    @NotBlank(message = "이름은 필수 입력 항복입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 항목 입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotNull(message = "나이는 필수 입력 항목입니다.")
    @Min(value = 1, message = "나이는 1 이상의 값이어야 합니다.")
    private Integer age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
