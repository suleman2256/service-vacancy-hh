package com.example.springtest.service;

import com.example.springtest.entity.Area;
import com.example.springtest.entity.City;
import com.example.springtest.entity.Currency;
import com.example.springtest.entity.Experience;
import com.example.springtest.repositories.AreaRepository;
import com.example.springtest.repositories.CityRepository;
import com.example.springtest.repositories.CurrencyRepository;
import com.example.springtest.repositories.ExperienceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class DictionariesServiceImpl implements DictionariesService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    VacanciesServiceImpl vacanciesServiceImpl;

    private static final String hhApiDictionaries = "https://api.hh.ru/dictionaries";
    private static final String hhApiAreas = "https://api.hh.ru/areas";

    public void getCurrentRate() {
        ResponseEntity<JsonNode> response = restTemplate.exchange(hhApiDictionaries, HttpMethod.GET, null, JsonNode.class);
        JsonNode currency = response.getBody().get("currency");
        for (int i = 0; i < currency.size(); i++) {
            currencyRepository.save(new Currency(
                    currency.get(i).get("code").asText(),
                    currency.get(i).get("rate").asDouble(),
                    currency.get(i).get("name").asText()));
        }
    }

    public void getAreas() {
        vacanciesServiceImpl.truncateTable();
        ResponseEntity<JsonNode> response = restTemplate.exchange(hhApiAreas, HttpMethod.GET, null, JsonNode.class);
        JsonNode areas = Objects.requireNonNull(response.getBody()).get(0).get("areas");
        for (int i = 0; i < areas.size(); i++) {
            areaRepository.save(new Area(
                    areas.get(i).get("id").asInt(),
                    areas.get(i).get("name").asText()));
        }
    }

    public void getCities() {
        ResponseEntity<JsonNode> response = restTemplate.exchange(hhApiAreas, HttpMethod.GET, null, JsonNode.class);
        JsonNode russiaAreas = Objects.requireNonNull(response.getBody()).get(0).get("areas");
        for (int i = 0; i < russiaAreas.size(); i++) {
            JsonNode currentArea = russiaAreas.get(i).get("areas");
            for (int j = 0; j < currentArea.size(); j++) {
                cityRepository.save(new City(
                        currentArea.get(j).get("id").asInt(),
                        currentArea.get(j).get("parent_id").asInt(),
                        currentArea.get(j).get("name").asText()));
            }
        }
        cityRepository.save(new City(1,1,"Москва"));
        cityRepository.save(new City(2,2,"Санкт-Петербург"));
    }

    public void getExperience() {
        ResponseEntity<JsonNode> response = restTemplate.exchange(hhApiDictionaries, HttpMethod.GET, null, JsonNode.class);
        JsonNode experience = response.getBody().get("experience");
        for (int i = 0; i < experience.size(); i++) {
            experienceRepository.save(new Experience(
                    experience.get(i).get("id").asInt(),
                    experience.get(i).get("name").asText()));
        }
    }

    @Override
    public List<Area> findAllAreas() {
        return null;
    }
}
