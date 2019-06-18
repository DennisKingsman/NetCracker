package com.example.auth.repo;

import com.example.auth.entity.plants.MyPlants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPlantRepo extends JpaRepository<MyPlants, Long> {

    MyPlants findByPlantName(String plantName);
}
