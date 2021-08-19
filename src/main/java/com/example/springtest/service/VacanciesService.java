package com.example.springtest.service;


import com.example.springtest.entity.Area;
import com.example.springtest.entity.Experience;
import org.hibernate.validator.constraints.LuhnCheck;

import java.util.List;

public interface VacanciesService {

    List<Area> findAllAreas();

    List<Experience> findAllExperiences();
}
