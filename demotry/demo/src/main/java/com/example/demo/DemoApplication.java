package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	CommandLineRunner commandLineRunner() {
//		return args -> {
//			// Random fake data is generated to load the database
//
//			String firstName = "Priyank";
//			String lastName = "Viradia";
//			String email = "priyankviradia@gmail.com";
//			String password="admin";
//
//			Student student = new Student(
//					firstName,
//					lastName,
//					email,
//					password,
//					25,
//					Arrays.asList( new Role("ROLE_ADMIN")));
//			Book book3=new Book( "War and Peace","Leo", 50.0);
//
//			Book book4=new Book( "Think and Grow Rich","Aayush",50.0 );
//
//			Book book5=new Book( "The Adventures of Huckleberry Finn"," Mark",100.0);
//
//
//			Book book6=new Book( "Hamlet"," William",10.0);
//
//
//			Book book7=new Book( "The Great Gatsby","Scott",50.0);
//
//
//			Book abc = new Book( " Lolita", "Vladimi ", 5.0);
//
//		};
//	}
@Component
public class DataLoader implements ApplicationRunner {
		@Autowired
		private StudentRepository userRepository;

		@Autowired
		private BookRepository bookRepository;

	@Autowired
	public DataLoader(StudentRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		String firstName = "Priyank";
			String lastName = "Viradia";
			String email = "priyank@gmail.com";
			String password="admin";

			Student student = new Student(
					firstName,
					lastName,
					email,
					password,
					25);
		userRepository.save(student);

		Student dummy = new Student(
				"Priyank_testing",
				"dummy",
				"priyankviradia@gmail.com",
				"12",
				22);
		userRepository.save(dummy);
//
		Book book4=new Book( "Think and Grow Rich","Aayush",50 );
		bookRepository.save(book4);
	Book book5=new Book( "The Adventures of Huckleberry Finn"," Mark",100);
	bookRepository.save(book5);
	Book book6=new Book( "Hamlet"," William",10);
		bookRepository.save(book6);
	Book book7=new Book( "The Great Gatsby","Scott",50);
	bookRepository.save(book7);
	Book abc = new Book( " Lolita", "Vladimi ", 5);
bookRepository.save(abc);
	}


}

}
