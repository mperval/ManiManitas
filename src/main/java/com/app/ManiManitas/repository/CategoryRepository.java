package com.app.ManiManitas.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.app.ManiManitas.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

	List<Category> findAll();
}
