package com.sample.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
    @NonNull private String name;
    private List<SectorFlattened> sectors;
    private boolean agreeToTerms;
    
}
