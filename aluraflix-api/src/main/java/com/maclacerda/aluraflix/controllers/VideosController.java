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

import com.maclacerda.aluraflix.dtos.VideoDTO;
import com.maclacerda.aluraflix.dtos.VideoDeletedDTO;
import com.maclacerda.aluraflix.dtos.VideoDetailDTO;
import com.maclacerda.aluraflix.forms.UpdateVideoForm;
import com.maclacerda.aluraflix.forms.VideoForm;
import com.maclacerda.aluraflix.models.Video;
import com.maclacerda.aluraflix.repositories.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideosController {

	@Autowired
	private VideoRepository repository;

	@GetMapping
	@Cacheable(value = "videosList")
	public Page<VideoDTO> list(
			@PageableDefault(direction = Direction.DESC, sort = "id", page = 0, size = 10) Pageable pagination) {
		Page<Video> videos;

		videos = repository.findAll(pagination);

		return VideoDTO.parse(videos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideoDetailDTO> detail(@PathVariable Long id) throws EntityNotFoundException {
		try {
			Video video = repository.getOne(id);

			return ResponseEntity.ok(new VideoDetailDTO(video));
		} catch (EntityNotFoundException exception) {
			throw new EntityNotFoundException("Video not found");
		}

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "videosList", allEntries = true)
	public ResponseEntity<VideoDTO> add(@RequestBody @Valid VideoForm form, UriComponentsBuilder builder) {
		Video video = form.parse();
		repository.save(video);

		URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();

		return ResponseEntity.created(uri).body(new VideoDTO(video));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "videosList", allEntries = true)
	public ResponseEntity<VideoDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateVideoForm form) {
		Video video = form.update(id, repository);

		return ResponseEntity.ok(new VideoDTO(video));
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "videosList", allEntries = true)
	public ResponseEntity<VideoDeletedDTO> delete(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);

			return ResponseEntity.ok(new VideoDeletedDTO("Video deleted successfull"));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
