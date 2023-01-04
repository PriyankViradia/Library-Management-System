package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.service.BookService;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.StudentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.controller.MainController.idglobal;
@Controller
public class EmailController {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentService studentService;
    private LocalDateTime issuedAt;
    @Autowired
    private EmailSenderService senderService;
//
//    @GetMapping("/adding/{id}")
//    public String redure(@PathVariable("id") Long id, Model model) throws NotFoundException{
//        Student student=studentService.findStudentById(idglobal);
//        Book b = bookService.findBookById(id);
//        model.addAttribute("books", b);
//        model.addAttribute("updateStudent",student);
//        return "list-book";
//    }

    @RequestMapping("/adding/{id}")
    public String createTransaction(@PathVariable("id") Long bid, Model model) throws NotFoundException {

        final Book book= bookService.findBookById(bid);
        Student student=studentService.findStudentById(idglobal);
        String subject ="Thank you "+ student.getFirstName() +" for the purchase.";
        String body = "Summary of your purchase \n" + book.getBookName()+ " by " + book.getBookAuthor()+ " \n price:  "
                +book.getBookPrice()+"";
        senderService.sendEmail(student.getEmail(),subject, body);
        bookService.deleteBook(bid);
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",student);
        return "redirect:/books";
          }



}
