package com.phuocthuy.repository;

import com.phuocthuy.repository.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByName(String name);
    List<Image> findByNameContainingIgnoreCase(String keyword);
    List<Image> findByIsPublicTrue();
    List<Image> findByOwner(String owner);


}
