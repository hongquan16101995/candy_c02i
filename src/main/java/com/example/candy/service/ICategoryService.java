package com.example.candy.service;

import com.example.candy.model.Category;
import com.example.candy.model.CategoryDTO;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category, Long> {
    List<CategoryDTO> totalMoney();
}
