package com.sample.demo.model;

import org.springframework.data.annotation.Id;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
public class MainSector {

    @Id
    private long id;

    @NonNull
    private String name;
    
}
