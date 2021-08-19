package com.example.springtest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Data
@ToString
@Table(schema = "springhh", name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String areaName;
    private Long salaryFrom;
    private Long salaryTo;
    /*private String companyName;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Experience_Id")
    private Experience experience;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Area_Id")
    private Area area;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Currency_Id")
    private Currency currency;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "City_Id")
    private City city;

    public Vacancy(Integer id, String name, String areaName, Long salaryFrom, Long salaryTo, Experience experience, Area area, Currency currency, City city) {
        this.id = id;
        this.name = name;
        this.areaName = areaName;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.experience = experience;
        this.area = area;
        this.currency = currency;
        this.city = city;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public Long getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Long salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Long getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Long salaryTo) {
        this.salaryTo = salaryTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
