package com.pgc.exguides.mapper;

// 2단계 DB와 통신하여 SQL을 실행시키는 인터페이스

import com.pgc.exguides.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // Mybatis 매퍼임을 지정
public interface StudentMapper {

    @Insert("INSERT INTO student (name, email, age, created_at, updated_at) " +
            "VALUES (#{name}, #{email}, #{age}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findById(Long id);

    @Select("SELECT * FROM student ORDER BY id DESC")
    List<Student> findAll();

    @Update("UPDATE student " +
            "SET name = #{name}, email = #{email}, age = #{age}, updated_at = #{updatedAt} " +
            "WHERE id = #{id}")
    void update(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteById(Long id);
}
