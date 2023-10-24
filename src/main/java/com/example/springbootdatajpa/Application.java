package com.example.springbootdatajpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

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

			studentIdCardRepository.save(studentIdCard);

			studentRepository.findById(1L)
							.ifPresent(System.out::println);

			studentIdCardRepository.findById(1L)
					.ifPresent(System.out::println);
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
