package com.example.springbootdatajpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			Student student = new Student(
					firstName,
					lastName,
					String.format("%s.%s@edu.edu", firstName, lastName),
					faker.number().numberBetween(17, 55)
			);
			StudentIdCard studentIdCard = new StudentIdCard("123457890", student);

			student.addBook(new Book("Clean Code", LocalDateTime.now()));
			student.addBook(new Book("Java introduction", LocalDateTime.now()));
			student.addBook(new Book("Spring data JPA", LocalDateTime.now()));

			student.addEnrollments(
					new Enrollment(
							new EnrollmentId(1l,1L),
							student,
							new Course("Computer Science", "IT")));

			student.addEnrollments(
					new Enrollment(new EnrollmentId(1L, 2L),
							student,
							new Course("Spring Data JPA", "IT")));

			student.setStudentIdCard(studentIdCard);
			studentRepository.save(student);

			studentRepository.findById(1L)
					.ifPresent(s -> {
						System.out.println(" fetch books lazy ...");
						List<Book> books = student.getBooks();
						books.forEach(book -> System.out.println(s.getFirstName() + " borrowed " + book.getBookName()));
					});
		};
	}
}
