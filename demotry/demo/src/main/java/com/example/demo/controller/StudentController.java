package com.example.demo.controller;

import com.example.demo.UserAlreadyExistException;
import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String viewWelcomePage(@ModelAttribute("student") Student student){

        return"Welcome";
    }
//
//    @ModelAttribute("student")
//    public Student register(){
//        return new Student();
//    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("student",new Student());
        return "register";
    }


    @PostMapping("/process")
    public String registerStudentAccount(@Valid @ModelAttribute("student") Student student, BindingResult result) {

        if(result.hasErrors())
        {
            System.out.println(result);
            return "register";
        }
        try {
            studentService.register(student);
            return "redirect:/register?success";
        }catch (UserAlreadyExistException e){
            return "redirect:/register?fail";

        }

    }

    @GetMapping("/update/{id}")
    public String UpdateForm(@PathVariable("id") Long id, Model model)throws NotFoundException {
        final Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);

        return "update-student";
    }
    // This function helps to push the updates to the database
    @PostMapping("/update/{id}")
    public String updateStudent(final @Valid Student student,@PathVariable("id") Long id, final BindingResult result,final Model model, Book book)throws NotFoundException {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
        studentService.upadateStudent(student);
        return "redirect:/books";
    }

    @RequestMapping("/student/{id}")
    public String findStudentById(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final List<Student> student = studentService.getAllStudentsExcept(id);
        model.addAttribute("student", student);
        return "list-student";
    }
    @RequestMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) throws NotFoundException {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
//    @GetMapping(value = "/books")
//    public String login()

}
