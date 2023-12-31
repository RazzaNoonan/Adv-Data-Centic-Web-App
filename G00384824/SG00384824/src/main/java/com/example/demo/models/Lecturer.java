package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String lid;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "lecturer")
    @JsonIgnore
    private List<Module> modules = new ArrayList<Module>();
    private String taxBand;
    private Integer salaryScale;
    public String getTaxBand() {
        return taxBand;
    }
    public void setTaxBand(String taxBand) {
        this.taxBand = taxBand;
    }
    public Integer getSalaryScale() {
        return salaryScale;
    }
    public void setSalaryScale(Integer salaryScale) {
        this.salaryScale = salaryScale;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLid() {
        return lid;
    }
    public void setLid(String lid) {
        this.lid = lid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Module> getModules() {
        return modules;
    }
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }



}