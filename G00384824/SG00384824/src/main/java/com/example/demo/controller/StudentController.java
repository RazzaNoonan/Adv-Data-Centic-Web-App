package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Lecturer;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.services.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StudentService studentService;


    @GetMapping
    public List<Map<String, Object>> getStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (Student student : students) {
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("id", student.getId());
            studentMap.put("sid", student.getSid());
            studentMap.put("name", student.getName());
            
            List<Map<String, Object>> moduleList = new ArrayList<>();
            for (com.example.demo.models.Module module : student.getModules()) {
                Map<String, Object> moduleMap = new HashMap<>();
                moduleMap.put("id", module.getId());
                moduleMap.put("mid", module.getMid());
                moduleMap.put("name", module.getName());
                moduleMap.put("credits", module.getCredits());
                moduleMap.put("level", module.getLevel());
                
                Map<String, Object> lecturerMap = new HashMap<>();
                Lecturer lecturer = module.getLecturer();
                lecturerMap.put("id", lecturer.getId());
                lecturerMap.put("name", lecturer.getName());
                lecturerMap.put("taxBand", lecturer.getTaxBand());
                lecturerMap.put("salaryScale", lecturer.getSalaryScale());
                moduleMap.put("lecturer", lecturerMap);
                
                moduleList.add(moduleMap);
            }
            
            studentMap.put("modules", moduleList);
            result.add(studentMap);
        }
        
        return result;
    }
    @DeleteMapping("/{sid}")
    public ResponseEntity<String> studentDelete(@PathVariable String sid) {
        try {
            studentService.studentDelete(sid);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}