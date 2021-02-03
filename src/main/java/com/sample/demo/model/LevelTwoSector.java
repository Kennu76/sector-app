package com.sample.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class LevelTwoSector {

    @Id
    private int levelTwoSectorId;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "main_sector_id")
    @JsonIgnore
    private MainSector mainSector;

    @OneToMany(mappedBy = "levelTwoSector")
    private Set<LevelThreeSector> levelThreeSector;

}
