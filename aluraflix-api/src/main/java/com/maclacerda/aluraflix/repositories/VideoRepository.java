package com.maclacerda.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maclacerda.aluraflix.models.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {}
