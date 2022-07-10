package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByPersonId(Long id);

    Optional<ImageModel> findByPostId(Long id);
}
