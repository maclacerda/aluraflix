package com.maclacerda.aluraflix.controllers;

import java.net.URI;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.maclacerda.aluraflix.dtos.CategoryDTO;
import com.maclacerda.aluraflix.dtos.CategoryDeletedDTO;
import com.maclacerda.aluraflix.dtos.CategoryDetailDTO;
import com.maclacerda.aluraflix.forms.CategoryForm;
import com.maclacerda.aluraflix.forms.CategoryVideoForm;
import com.maclacerda.aluraflix.models.Category;
import com.maclacerda.aluraflix.repositories.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;

	@GetMapping
	@Cacheable(value = "categoriesList")
	public Page<CategoryDTO> list(
			@PageableDefault(direction = Direction.ASC, sort = "title", page = 0, size = 10) Pageable pagination) {
		Page<Category> categories;

		categories = repository.findAll(pagination);

		return CategoryDTO.parse(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDetailDTO> detail(@PathVariable Long id) throws EntityNotFoundException {
		try {
			Category category = repository.getOne(id);

			return ResponseEntity.ok(new CategoryDetailDTO(category));
		} catch (EntityNotFoundException exception) {
			throw new EntityNotFoundException("Category not found");
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "categoriesList", allEntries = true)
	public ResponseEntity<CategoryDTO> add(@RequestBody @Valid CategoryForm form, UriComponentsBuilder builder) {
		Category category = form.parse();
		repository.save(category);

		URI uri = builder.path("/categorias/{id}").buildAndExpand(category.getId()).toUri();

		return ResponseEntity.created(uri).body(new CategoryDTO(category));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "categoriesList", allEntries = true)
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryVideoForm form) {
		Category category = form.update(id, repository);

		return ResponseEntity.ok(new CategoryDTO(category));
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "categoriesList", allEntries = true)
	public ResponseEntity<CategoryDeletedDTO> delete(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);

			return ResponseEntity.ok(new CategoryDeletedDTO("Category deleted successfull"));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
