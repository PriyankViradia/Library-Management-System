package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.model.Context;
import com.example.demo.service.*;
import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {

    private final BookService bookService;


    public static Long idglobal;

    private final StudentService studentService;

    public MainController(BookService bookService, StudentService studentService) {
        this.bookService = bookService;
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("student") Student student) throws ChangeSetPersister.NotFoundException {

        try {
        if(studentService.logincred(student.getEmail(),student.getPassword()))
        {
              idglobal = studentService.getIdByEmail(student.getEmail());
            System.out.println(student.toString());
            if(idglobal ==1){
                return "redirect:/booksadmin";
            }else return "redirect:/books";
        }

        }catch (Exception e){
            return "redirect:/?fail";

        }
        return "redirect:/?fail";}

    @GetMapping("/booksadmin")
    public String booksadmin(Model model) throws NotFoundException {
        List<Book> books = bookService.findAllBooks();
        List<Student> students=studentService.findAllStudents();
        // long idk  =idglobal;
        //Student updateStudent=students.get(students.size()-1);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "/booksadmin";
    }
    @RequestMapping("/booksadmin/author")
    public String getAllBooksByAuthor(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByAuthor();
        context.setContext(search);
        Student updateStudent=studentService.findStudentById(idglobal);
        books = context.executeSearch(books);
        model.addAttribute("updateStudent",updateStudent);
        model.addAttribute("books", books);
        return "booksadmin";
    }
    @RequestMapping ("/delete/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model)throws NotFoundException{
        System.out.println(id);
        bookService.deleteBook(id);
        List<Book> books = bookService.findAllBooks();
        // long idk  =idglobal;
        //Student updateStudent=students.get(students.size()-1);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "/booksadmin";
    }


    @GetMapping("/addbooks")
    public String addbook(Model model){
        model.addAttribute("books",new Book());
        return "/addbooks";
    }


    @PostMapping ("/booksadd")
    public String addbooks(@ModelAttribute("books")Book book, Model model) throws NotFoundException{

        bookService.addBook(book);

        List<Book> books = bookService.findAllBooks();
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "/booksadmin";
    }
    @RequestMapping("/searcha")
    public String searchBook(@Param("keyword") String keyword, Model model) throws NotFoundException {
        final List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("updateStudent",updateStudent);
        return "booksadmin";
    }

    @RequestMapping("/booksadmin/title")
    public String getAllBooksByTitle(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByTittle();
        context.setContext(search);
        books = context.executeSearch(books);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "booksadmin";
    }

    @RequestMapping("/booksadmin/price")
    public String getAllBooksByPrice(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByPrice();
        context.setContext(search);
        books = context.executeSearch(books);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "booksadmin";
    }

}
