package com.example.springtest.controller;

import com.example.springtest.entity.*;
import com.example.springtest.service.DictionariesServiceImpl;
import com.example.springtest.service.SearchServiceImpl;
import com.example.springtest.service.VacanciesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/*@CrossOrigin(origins="http://group09-front.apps.ocp4.trainee.ru.com")*/
@RestController
@RequestMapping(value = "/", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class VacanciesController {

    static String url = "https://api.hh.ru/vacancies";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DictionariesServiceImpl dictionariesServiceImpl;

    @Autowired
    VacanciesServiceImpl vacanciesServiceImpl;

    @Autowired
    SearchServiceImpl searchService;

    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    public void getRate() {
        dictionariesServiceImpl.getCurrentRate();
        dictionariesServiceImpl.getAreas();
        dictionariesServiceImpl.getCities();
        dictionariesServiceImpl.getExperience();
    }

    /*@PostMapping(value = "items", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getItems(@RequestBody ItemsParams params) {

    }*/


    @GetMapping("regions")
    public List<Area> readAllregions() {
        return vacanciesServiceImpl.findAllAreas();
    }

    @GetMapping("experiences")
    public List<Experience> readAllexperiences() {
        return vacanciesServiceImpl.findAllExperiences();
    }

    /*@PostMapping("allVacancies")
    public String getAllVacancies(@RequestBody Vacancy vacancy) {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @PostMapping("javaVacancies")
    public String getJavaVac() {
        ResponseEntity<String> response = restTemplate.exchange(url + "?text=java", HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @PostMapping(value = "items", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getVacancies(@RequestBody SearchParametersVacancy params) {
        String searchURL = searchService.getSearchURL(params);
        vacanciesServiceImpl.getVacancies(100, searchURL);
        return "Got it.";
    }*/

    @PostMapping(value = "JavaVacancies", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String  getVacancies() {
        vacanciesServiceImpl.getVacancies(30, url + "?text=java");
        return "Поиск завершён";
    }

}






