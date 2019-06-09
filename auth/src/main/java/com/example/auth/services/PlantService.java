package com.example.auth.services;

import com.example.auth.entity.Plant;
import com.example.auth.exception.ResourceNotFoundException;

import java.util.List;

public interface PlantService {

    List<Plant> findByBoxId(Long boxId);

    Plant findById(Long id) throws ResourceNotFoundException;

    Plant savePlant(Plant plant);

    void deletePlant(Plant plant);

    void deletePlantById(Long plantId);
}
