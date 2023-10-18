package com.example.springbootdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    @Query(value = "SELECT * FROM student WHERE email = ?1", nativeQuery = true)
    Optional<Student> findStudentByEmailNative(String email);
    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.age >= :age ")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(
            @Param("firstName") String firstName, @Param("age") Integer age);
}
