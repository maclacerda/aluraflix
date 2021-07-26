package com.maclacerda.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maclacerda.aluraflix.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
