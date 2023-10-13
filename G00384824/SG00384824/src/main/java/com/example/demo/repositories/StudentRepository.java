package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findBySid(String sid);
}
