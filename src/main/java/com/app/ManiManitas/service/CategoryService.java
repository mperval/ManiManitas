package com.app.ManiManitas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ManiManitas.entity.Category;
import com.app.ManiManitas.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll(){
		return categoryRepository.findAll();	
	}
	
	public Optional<Category> findById(Integer id){
		return this.categoryRepository.findById(id);
	}
	
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	public void save(Category c) {
		this.categoryRepository.save(c);
	}
	
}
