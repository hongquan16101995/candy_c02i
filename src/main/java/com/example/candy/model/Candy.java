package com.example.candy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Candy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imagePath;
    private Long price;
    private Long quantity;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "int default 0")
    private Integer sale;

    @ManyToOne
    private Category category;
}
