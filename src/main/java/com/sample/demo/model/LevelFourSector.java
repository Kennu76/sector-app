package com.sample.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class LevelFourSector {

    @Id
    private int levelFourSectorId;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_three_sector_id")
    @JsonIgnore
    private LevelThreeSector levelThreeSector;

}
