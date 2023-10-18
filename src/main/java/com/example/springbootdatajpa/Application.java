package com.example.springbootdatajpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {

			Student maria = new Student(
					"Maria",
					"Jones",
					"maria.jones@edu.edu",
					21
			);

			Student maria2 = new Student(
					"Maria",
					"Jones",
					"maria2.jones@edu.edu",
					25
			);

			Student ahmed = new Student(
					"Ahmed",
					"Ali",
					"ahmed.ali@edu.edu",
					21
			);

			studentRepository.saveAll(List.of(maria, ahmed, maria2));

			studentRepository.findStudentByEmail("ahmed.ali@edu.edu")
					.ifPresentOrElse(System.out::println,
							() -> System.out.println("Student with email ahmed.ali@edu.edu not found"));

			studentRepository.findStudentByEmailNative("ahmed.ali@edu.edu")
					.ifPresentOrElse(System.out::println,
							() -> System.out.println("Student with email ahmed.ali@edu.edu not found"));

			List<Student> students = studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThan(
					"Maria", 21
			);
			students.forEach(System.out::println);
		};
	}

}
