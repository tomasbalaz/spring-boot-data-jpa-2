package com.example.springbootdatajpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			generateRandomStudents(studentRepository);

			//Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
			Sort sort = Sort.by("firstName").ascending()
					.and(Sort.by("age")).descending();

			studentRepository.findAll(sort)
					.forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));
		};
	}

	private void generateRandomStudents(StudentRepository studentRepository) {
		IntStream.rangeClosed(1 ,20)
				.forEach(value -> {
					Faker faker = new Faker();
					String firstName = faker.name().firstName();
					String lastName = faker.name().lastName();
					Student student = new Student(
							firstName,
							lastName,
							String.format("%s.%s@edu.edu", firstName, lastName),
							faker.number().numberBetween(17, 55)
					);

					studentRepository.save(student);
				});
	}

}
