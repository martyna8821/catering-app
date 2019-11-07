package com.martyna.catering.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DietDTO {
    private String name;

    private String description;

    private int price;
}
