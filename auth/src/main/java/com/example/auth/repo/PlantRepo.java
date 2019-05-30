package com.example.auth.repo;

import com.example.auth.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepo extends JpaRepository<Plant, Long> {

}
