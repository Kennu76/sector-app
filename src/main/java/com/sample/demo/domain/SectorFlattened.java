package com.sample.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorFlattened {
	@NonNull String name;
    private int childrenId;
    private int level;
    private boolean expandable;
}
