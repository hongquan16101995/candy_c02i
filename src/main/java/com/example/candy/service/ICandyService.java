package com.example.candy.service;

import com.example.candy.model.Candy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICandyService extends IGenerateService<Candy, Long> {
    Page<Candy> findAllPage(Pageable pageable);

    Page<Candy> filter(Long min, Long max, String name, Pageable pageable);
}
