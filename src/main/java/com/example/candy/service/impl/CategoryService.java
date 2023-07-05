package com.example.candy.service.impl;

import com.example.candy.model.Category;
import com.example.candy.model.CategoryDTO;
import com.example.candy.repository.ICategoryRepository;
import com.example.candy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findOne(Long aLong) {
        return iCategoryRepository.findById(aLong);
    }

    @Override
    public void save(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public void delete(Long aLong) {
        iCategoryRepository.deleteById(aLong);
    }

    @Override
    public List<CategoryDTO> totalMoney() {
        return iCategoryRepository.totalMoney();
    }
}
