package com.example.demo.services;

import com.example.demo.exceptions.LecturerExceptions;
import com.example.demo.models.Lecturer;
import com.example.demo.repositories.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;


@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    public Iterable<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerById(String id) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        if (lecturer.isPresent()) {
            return lecturer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecturer not found with id: " + id);
        }
    }

    public Lecturer addLecturer(Lecturer lecturer) throws LecturerExceptions {
        if (lecturer.getLid() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lecturer's lid must be provided");
        }
        if (lecturer.getId() != null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lecturer's id must not be provided");
        }
        if (lecturer.getName() == null || lecturer.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is mandatory");
        }
        Optional<Lecturer> existingLecturer = lecturerRepository.findByLid(lecturer.getLid());
        if (existingLecturer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lecturer with lid " + lecturer.getLid() + " already exists");
        }
        return lecturerRepository.save(lecturer);
    }


    public Lecturer updateLecturer(Lecturer lecturer) {
        if (lecturer.getId() != null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Lecturer's id must not be provided");
        }
        if (lecturer.getLid() != null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Lecturer's Lid must not be provided");
        }
        if (lecturer.getName() == null || lecturer.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is mandatory");
        }
        Optional<Lecturer> lecturerOptional = lecturerRepository.findByName(lecturer.getName());
        if (lecturerOptional.isPresent()) {
            Lecturer existingLecturer = lecturerOptional.get();
//            existingLecturer.setName(lecturer.getName());
            existingLecturer.setTaxBand(lecturer.getTaxBand());
            existingLecturer.setSalaryScale(lecturer.getSalaryScale());
            return lecturerRepository.save(existingLecturer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecturer not found with name: " + lecturer.getName());
        }
        
    }
    
    public Iterable<Lecturer> displayLecturers(String taxBand, int salaryScale) {
        return lecturerRepository.findLecturersByNameAndSalaryScale(taxBand, salaryScale);
    }

    public void deleteLecturerById(String id) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        if (lecturer.isPresent()) {
            lecturerRepository.delete(lecturer.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecturer not found with id: " + id);
        }
    }
}