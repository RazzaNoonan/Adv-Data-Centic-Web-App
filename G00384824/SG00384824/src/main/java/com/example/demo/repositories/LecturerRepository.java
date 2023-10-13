package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Lecturer;

public interface LecturerRepository extends CrudRepository<Lecturer, String> {

    Optional<Lecturer> deleteByLid(String lid);

    Optional<Lecturer> findByLid(String lid);
    
    Optional<Lecturer> findByName(String Name);

    @Query(nativeQuery = true, value = "SELECT * FROM lecturer WHERE tax_band = ?1  AND salary_scale = ?2")
    List<Lecturer> findLecturersByNameAndSalaryScale(String taxBand, int salaryScale);
}

