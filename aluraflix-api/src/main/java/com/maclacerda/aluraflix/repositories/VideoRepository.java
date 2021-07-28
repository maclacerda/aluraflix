package com.maclacerda.aluraflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.maclacerda.aluraflix.models.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	
	Page<Video> findByCategoryId(Long id, Pageable pagination);
	
}
