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
public class LevelThreeSector {

    @Id
    private int levelThreeSectorId;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_two_sector_id")
    @JsonIgnore
    private LevelTwoSector levelTwoSector;

    @OneToMany(mappedBy = "levelThreeSector")
    private Set<LevelFourSector> levelFourSector;

}
