package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.LecturerExceptions;
import com.example.demo.models.Lecturer;
import com.example.demo.models.Student;
import com.example.demo.services.LecturerService;
import com.example.demo.services.StudentService;
import com.example.demo.validations.LecturersPOSTValidations;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "")
public class LecturerController {

    @Autowired
    LecturerService lecturerService;

    @GetMapping(path = "/lecturer")
    public Iterable<Lecturer> getLecturer() {
        return lecturerService.getAllLecturers();
    }

    @Validated(LecturersPOSTValidations.class)
    @PostMapping(path = "/lecturer", consumes = {"application/json"})
    public void addEmployee(@Valid @RequestBody Lecturer lecturer)throws LecturerExceptions {
        try {
            lecturerService.addLecturer(lecturer);
        } catch (LecturerExceptions e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @PutMapping(path = "/lecturer/update")
    public void updateLecturer( @Valid @RequestBody Lecturer l)
    {
        lecturerService.updateLecturer(l);
    }
    
    @GetMapping(path = "/lecturer/query")
    public Iterable<Lecturer> displayLecturer(@RequestParam String taxBand, @RequestParam int salaryScale) {
        return lecturerService.displayLecturers(taxBand, salaryScale);
    }
}
