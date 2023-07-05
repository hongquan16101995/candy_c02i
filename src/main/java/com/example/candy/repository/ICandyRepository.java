package com.example.candy.repository;

import com.example.candy.model.Candy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandyRepository extends JpaRepository<Candy, Long> {
    @Query(value = "select ca from Candy ca join Category c " +
            "on ca.category.id = c.id " +
            "where (ca.name like :name and ca.price between :min and :max) " +
            "or (c.name like :name and ca.price between :min and :max)")
    Page<Candy> filter(@Param("min") Long minPrice,
                       @Param("max") Long maxPrice,
                       @Param("name") String name,
                       Pageable pageable);
}
