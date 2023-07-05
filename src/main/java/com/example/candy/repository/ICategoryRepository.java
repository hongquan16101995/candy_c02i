package com.example.candy.repository;

import com.example.candy.model.Category;
import com.example.candy.model.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select new com.example.candy.model.CategoryDTO(c.id, c.name, sum(ca.price * ca.quantity))" +
            "from Category c join Candy ca on  c.id = ca.category.id " +
            "group by c.name")
    List<CategoryDTO> totalMoney();
}
