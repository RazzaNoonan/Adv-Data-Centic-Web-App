package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void studentDelete(String sid) {
        Optional<Student> studentOpt = studentRepository.findBySid(sid);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (!student.getModules().isEmpty()) {
                throw new IllegalStateException("Student with sid: " + sid + " cannot be deleted because they have associated modules.");
            }
            studentRepository.delete(student);
        } else {
            throw new IllegalArgumentException("Student not found with sid: " + sid);
        }
    }
}