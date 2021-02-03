package com.sample.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MainSector {

    @Id
    private int mainSectorId;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "mainSector")
    private Set<LevelTwoSector> levelTwoSector;

}
