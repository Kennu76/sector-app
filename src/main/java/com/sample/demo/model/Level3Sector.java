package com.sample.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
public class Level3Sector {

    @Id
    private int id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "main_sector_id")
    private MainSector mainSector;

    @OneToMany
    private Set<Level4Sector> level4Sectors;
    
}
