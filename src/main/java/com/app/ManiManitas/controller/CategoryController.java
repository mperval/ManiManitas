package com.app.ManiManitas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ManiManitas.entity.Category;
import com.app.ManiManitas.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public List<Category> findAll(){
		return categoryService.findAll();
	}
	@GetMapping("/{id}")
	public Category findById(@PathVariable Integer id) {
		Category res = null;
		Optional<Category> categoryOptional = categoryService.findById(id);
		if(categoryOptional.isPresent()) {
			res = categoryOptional.get();
		}
		return res;
	}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		categoryService.deleteById(id);
		System.out.println("ha sido borrado...");
	}
	@PostMapping("")
	public void save(@RequestBody Category c) {
		categoryService.save(c);
	}
	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody Category c) {
		Optional<Category> categoryOptional = categoryService.findById(id);
		if(categoryOptional.isPresent()) {
			Category res = categoryOptional.get();
			res.setName(c.getName());
			categoryService.save(res);
		}
	}
}
