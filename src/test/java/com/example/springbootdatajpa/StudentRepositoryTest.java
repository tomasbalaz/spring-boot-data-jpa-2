package com.example.springbootdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldFindStudentByEmail() {
        // Given
        Student student = new Student(
                "Tomas",
                "Tomas",
                "tomas.tomas@edu.edu",
                20
        );

        // When
        underTest.save(student);

        // Then
        Optional<Student> optionalStudent = underTest.findStudentByEmail(student.getEmail());
        assertThat(optionalStudent)
                .isPresent()
                .hasValueSatisfying(s -> {
                    assertThat(s.getId()).isEqualTo(student.getId());
                    assertThat(s.getFirstName()).isEqualTo(student.getFirstName());
                    assertThat(s.getLastName()).isEqualTo(student.getLastName());
                    assertThat(s.getEmail()).isEqualTo(student.getEmail());
                });
    }

    @Test
    void itShouldNotFindStudentWhenEmailIsIncorrect() {
        // Given
        Student student = new Student(
                "Tomas",
                "Tomas",
                "tomas.tomas@edu.edu",
                20
        );

        // When
        underTest.save(student);

        // Then
        Optional<Student> optionalStudent = underTest.findStudentByEmail("invalidEmail");
        assertThat(optionalStudent).isNotPresent();
    }
}