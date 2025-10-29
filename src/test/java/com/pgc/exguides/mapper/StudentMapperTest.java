package com.pgc.exguides.mapper;

import com.pgc.exguides.domain.StudentTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapperTest {
    @Select("SELECT * FROM student ORDER BY id DESC")
    List<StudentTest> findAll();

    @Select("SELECT * FROM student WHERE id = #{id}")
    StudentTest findById(Long id);

    //	@Insert("INSERT INTO student(NAME, email, age) \r\n"
//			+ "  	VALUES (#{name}, #{email}, #{age})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	void insert(Student student);
//
//	@Update("UPDATE student\r\n"
//			+ "	SET NAME = #{name}, email = #{email}, age = #{age}\r\n"
//			+ "	WHERE id = #{id}")
//	void update(Student student);
//
//	@Delete("DELETE FROM student WHERE id = #{id}")
//	void delete(Long id);
//
    @Insert("INSERT INTO student(NAME, email, age) \r\n"
            + "  	VALUES (#{name}, #{email}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(StudentTest student);
}
