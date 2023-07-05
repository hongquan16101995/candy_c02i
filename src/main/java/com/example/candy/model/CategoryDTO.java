package com.example.candy.model;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Long money;

    public CategoryDTO(Long id, String name, Long money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
}
