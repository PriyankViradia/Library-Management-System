package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(
         value = "SELECT s.email FROM student s where s.email=:email",
         nativeQuery = true)
    String findByEmail(@Param("email") String email);

    @Query(
            value = "SELECT * FROM student s where s.email=:email",
            nativeQuery = true)
    Student findStudentByEmail(@Param("email") String email);


    @Query(
            value = "SELECT s.id FROM student s where s.email=:email",
            nativeQuery = true)
    Long findIdByEmail(@Param("email") String email);

 // boolean getConfirmation(String email);

    @Query(
            value = "SELECT * FROM student  where id !=?1",
            nativeQuery = true)
    List<Student> getAllStudentExcept(Long id);



    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int  deleteStudentById(Long id);

}
