package com.example.springtest.service;

import com.example.springtest.entity.SearchParametersVacancy;
import com.example.springtest.entity.Vacancy;
import com.example.springtest.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    AreaRepository areaRepository;

    public String getSearchURL(SearchParametersVacancy params) {

        String vacanciesUrl = "https://api.hh.ru/vacancies";
        StringBuilder sb = new StringBuilder(vacanciesUrl);

        if (params.getArea() != null) {
            sb.append("&area=").append(areaRepository.findByAreaName(params.getArea()).getId());
        }

        if (params.getSalaryFrom() != null)
            sb.append("&salaryFrom=").append(params.getSalaryFrom());

        if (params.getSalaryTo() != null)
            sb.append("&salaryTo=").append(params.getSalaryTo());

        return sb.toString();
    }
}
