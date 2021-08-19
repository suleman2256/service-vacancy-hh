package com.example.springtest.entity;




import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@ToString
@NoArgsConstructor
@Data
@Table(schema = "springhh")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "areaName")
    private String areaName;

    public Area(Integer id, String areaName) {
        this.id = id;
        this.areaName = areaName;
    }
}

