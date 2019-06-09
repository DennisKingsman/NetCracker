package com.example.auth.repo;

import com.example.auth.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepo extends JpaRepository<Plant, Long> {

    List<Plant> findByResponsibleGrowBoxId(Long boxId);

}
