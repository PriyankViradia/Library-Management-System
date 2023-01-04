package com.example.demo.service;

import com.example.demo.UserAlreadyExistException;

import com.example.demo.entity.Student;
import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;


import java.util.List;

public interface StudentService{

    public List<Student> getALlStudent();
    public void register(Student student) throws UserAlreadyExistException;
    public String checkIfUserExist(String email);

    public boolean logincred(String email , String password) throws ChangeSetPersister.NotFoundException;

    public Long getIdByEmail(String email);
    public List<Student> findAllStudents();
    public void upadateStudent(Student student);
    public Student findStudentById(Long id) throws NotFoundException;
    public List<Student> getAllStudentsExcept(Long id) throws NotFoundException;

    public void deleteStudent(Long id) throws NotFoundException;

}
