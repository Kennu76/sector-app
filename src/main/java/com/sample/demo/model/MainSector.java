package com.sample.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class MainSector {

    @Id
    private int id;

    @NonNull
    private String name;

    @OneToMany
    @JoinColumn(name = "level_2_sector_id")
    private Set<Level2Sector> level2Sectors;
    
}
