package com.example.springtest.service;


import com.example.springtest.entity.Area;
import com.example.springtest.entity.Experience;
import com.example.springtest.entity.Vacancy;
import com.example.springtest.repositories.AreaRepository;
import com.example.springtest.repositories.CityRepository;
import com.example.springtest.repositories.ExperienceRepository;
import com.example.springtest.repositories.VacancyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VacanciesServiceImpl implements VacanciesService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    CityRepository cityRepository;


    public void getVacancies(int requiredVacanciesNumber, String url) {
        int count = 0;
        int pages = restTemplate.exchange(url, HttpMethod.GET
                , null, JsonNode.class).getBody().get("pages").asInt();
        int found = restTemplate.exchange(url, HttpMethod.GET
                , null, JsonNode.class).getBody().get("found").asInt();
        for (int i = 0; i < pages; i++) {
            JsonNode currentVacanciesList = restTemplate.exchange(url + "&page=" + i, HttpMethod.GET
                    , null, JsonNode.class).getBody();
            for (int j = 0; j < 20; j++) {
                Vacancy vacancy = new Vacancy();
                writeData(currentVacanciesList.get("items").get(j), vacancy);
                vacancyRepository.save(vacancy);
                count++;
                if(count == requiredVacanciesNumber || count == found) break;
            }
            if(count == requiredVacanciesNumber || count == found) break;
        }
    }

    private void writeData(JsonNode givenHHVacancy, Vacancy vacancy) {

        if (!givenHHVacancy.get("salary").isNull()) {
            if (!givenHHVacancy.get("salary").get("from").isNull()) {
                vacancy.setSalaryFrom(givenHHVacancy.get("salary").get("from").asLong());
            }
            if (!givenHHVacancy.get("salary").get("to").isNull()) {
                vacancy.setSalaryTo(givenHHVacancy.get("salary").get("to").asLong());
            }
            /*if (!givenHHVacancy.get("salary").get("currency").isNull()) {
               vacancy.setSalaryCurrency(givenHHVacancy.get("salary").get("currency").asText());
            }*/
        }

        /*if (!givenHHVacancy.get("id").isNull())*/
        /*    vacancy.setId(givenHHVacancy.get("id").asInt());*/

        /*if (!givenHHVacancy.get("name").isNull())*/
        /*    vacancy.setName(givenHHVacancy.get("name").asText());*/

        if (!givenHHVacancy.get("area").isNull()) {
            //if (!givenHHVacancy.get("area").get("id").isNull()) {
            //    vacancy.setAreaId(givenHHVacancy.get("area").get("id").asInt());
            //}
            if (!givenHHVacancy.get("area").get("name").isNull()) {
                vacancy.setAreaName(givenHHVacancy.get("area").get("name").asText());
            }
        }

        /*if (!givenHHVacancy.get("employer").isNull()) {*/
        /*    if (!givenHHVacancy.get("employer").get("name").isNull()) {*/
        /*        vacancy.setCompanyName(givenHHVacancy.get("employer").get("name").asText());*/
        /*    }*/
        /*}*/

        /*if (!givenHHVacancy.get("experience").isNull()) {
            if (!givenHHVacancy.get("experience").get("id").isNull()) {
                vacancy.setExperienceId(givenHHVacancy.get("experience").get("id").asText());
            }
            if (givenHHVacancy.get("experience").get("name").isNull()) {
                vacancy.setExperienceName(givenHHVacancy.get("experience").get("name").asText());
            }
        }*/
    }

    public void truncateTable() {
        vacancyRepository.deleteAll();
        areaRepository.deleteAll();
        experienceRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @Override
    public List<Area> findAllAreas() {
        return (List<Area>) areaRepository.findAll();
    }

    @Override
    public List<Experience> findAllExperiences() {
        return (List<Experience>) experienceRepository.findAll();
    }

}
