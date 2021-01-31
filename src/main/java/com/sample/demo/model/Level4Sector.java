package com.sample.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Level4Sector {

    @Id
    private int id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_3_sector_id")
    private Level3Sector level3Sector;
    
}
