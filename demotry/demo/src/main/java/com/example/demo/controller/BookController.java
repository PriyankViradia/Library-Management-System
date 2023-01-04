package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.model.Context;
import com.example.demo.service.*;
import javassist.NotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.example.demo.controller.MainController.idglobal;

// The controller is kind of a data link layer where all the api's are linked and it talks with the service layer to get the required data
@Controller
public class BookController {
    private final BookService bookService;
    private final StudentService studentService;

    public BookController(BookService bookService, StudentService studentService) {
        this.bookService = bookService;
        this.studentService = studentService;
    }

    // Each function below is using MVC pattern to interact with front-end and the service layer's in the project
    @RequestMapping(value="/books")
    public String getAllBooks(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
       // long idk  =idglobal;
        //Student updateStudent=students.get(students.size()-1);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }
    @RequestMapping("/books/author")
    public String getAllBooksByAuthor(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByAuthor();
        context.setContext(search);

        Student updateStudent=studentService.findStudentById(idglobal);
        books = context.executeSearch(books);
        model.addAttribute("updateStudent",updateStudent);
        model.addAttribute("books", books);
        return "books";
    }


    @RequestMapping("/books/title")
    public String getAllBooksByTitle(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByTittle();
        context.setContext(search);
        books = context.executeSearch(books);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }

    @RequestMapping("/books/price")
    public String getAllBooksByPrice(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByPrice();
        context.setContext(search);
        books = context.executeSearch(books);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model)throws NotFoundException{
        final Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "list-book";
    }
    // This is the search function which searches book via author and book via name in the database
    @RequestMapping("/search")
    public String searchBook(@Param("keyword") String keyword, Model model) throws NotFoundException {
        final List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        Student updateStudent=studentService.findStudentById(idglobal);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }




}
