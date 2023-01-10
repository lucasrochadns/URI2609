package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2609.entities.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value="SELECT categories.name, SUM(products.amount)" +
            " FROM categories INNER JOIN products on products.id_categories = categories.id GROUP BY categories.name")
    List<CategorySumProjection> searchAllBySumCategories();

    @Query(value="SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(p.category.name, SUM(p.amount)) " +
            "FROM Product p GROUP BY p.category.name")
    List<CategorySumDTO> searchAllBySumCategoriesTotal();


}
