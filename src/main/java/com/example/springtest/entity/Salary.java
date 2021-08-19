package com.example.springtest.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
public class Salary {


    private Integer id;
    private Integer salaryTo;
    private Integer salaryFrom;
    private boolean onlyWithSalary = true;


    public Salary(Integer id, Integer salaryTo, Integer salaryFrom) {
        this.id = id;
        this.salaryTo = salaryTo;
        this.salaryFrom = salaryFrom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public Integer getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public boolean isOnlyWithSalary() {
        return onlyWithSalary;
    }

    public void setOnlyWithSalary(boolean onlyWithSalary) {
        this.onlyWithSalary = onlyWithSalary;
    }
}
