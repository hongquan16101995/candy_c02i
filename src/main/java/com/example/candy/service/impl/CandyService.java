package com.example.candy.service.impl;

import com.example.candy.model.Candy;
import com.example.candy.repository.ICandyRepository;
import com.example.candy.service.ICandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandyService implements ICandyService {
    @Autowired
    private ICandyRepository iCandyRepository;

    @Override
    public Page<Candy> findAllPage(Pageable pageable) {
        return iCandyRepository.findAll(pageable);
    }

    @Override
    public List<Candy> findAll() {
        return iCandyRepository.findAll();
    }

    @Override
    public Optional<Candy> findOne(Long aLong) {
        return iCandyRepository.findById(aLong);
    }

    @Override
    public void save(Candy candy) {
        iCandyRepository.save(candy);
    }

    @Override
    public void delete(Long aLong) {
        iCandyRepository.deleteById(aLong);
    }

    @Override
    public Page<Candy> filter(Long min, Long max, String name, Pageable pageable) {
        return iCandyRepository.filter(min, max,"%" + name + "%", pageable);
    }
}
