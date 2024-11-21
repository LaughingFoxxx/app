package com.sortofclub.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BicycleDto {
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
}
