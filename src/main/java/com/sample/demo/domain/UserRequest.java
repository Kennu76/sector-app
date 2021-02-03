package com.sample.demo.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private String name;
    private List<SectorFlattened> sectors;
    private boolean agreeToTerms;
    
}
