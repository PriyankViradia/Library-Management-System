package com.example.demo.service.impl;

import com.example.demo.UserAlreadyExistException;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {



    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getALlStudent() {
        return studentRepository.findAll();
    }
    @Override
    public String checkIfUserExist(String email)  {
        System.out.println(email);
        String sd= studentRepository.findByEmail(email);
        return sd;
    }

    @Override
    public boolean logincred(String email, String password) throws ChangeSetPersister.NotFoundException {
        if (checkIfUserExist(email) ==null) {
            System.out.println("Email not exists");
            throw new ChangeSetPersister.NotFoundException();
        }
        else {
            Student student = studentRepository.findStudentByEmail(email);
            if(student.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Long getIdByEmail(String email) {
        return studentRepository.findIdByEmail(email);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public void register(Student student)  throws UserAlreadyExistException {
        System.out.println(student.getEmail());
        if (checkIfUserExist(student.getEmail()) !=null) {
            System.out.println("Email already exists");
            throw  new UserAlreadyExistException("Email already exists");
        }
        Student student1 = new Student(student.getFirstName(),student.getLastName()
                ,student.getEmail(),
               student.getPassword(),
                student.getAge());
        studentRepository.save(student1);

    }
    @Override
    public void upadateStudent(Student student){
        studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with ID %d", id)));
    }
    @Override
    public List<Student> getAllStudentsExcept(Long id) throws NotFoundException{
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("student not found with ID %d", id)));
        return  studentRepository.getAllStudentExcept(id);

    }
    @Override
    public void deleteStudent(Long id) throws NotFoundException {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("student not found with ID %d", id)));

        studentRepository.deleteById(student.getId());
    }

}
