package com.example.springtest.repositories;


import com.example.springtest.entity.Area;
import com.example.springtest.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    List<Experience> findAll();
}
