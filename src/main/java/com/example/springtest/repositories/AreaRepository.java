package com.example.springtest.repositories;

import com.example.springtest.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area findByAreaName(String areaName);
    @Query("SELECT a FROM Area a ORDER BY name ASC")
    List<Area> findAll();


}

