package com.example.springtest.entity;

import lombok.*;


@ToString
@NoArgsConstructor
@Data
public class SearchParametersVacancy {

    private String area;
    private String salaryFrom;
    private String salaryTo;

    public String getArea() {
        return area;
    }

    public SearchParametersVacancy(String area, String salaryFrom, String salaryTo) {
        this.area = area;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }
}
