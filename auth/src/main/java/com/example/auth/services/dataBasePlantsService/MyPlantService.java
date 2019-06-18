package com.example.auth.services.dataBasePlantsService;

import com.example.auth.entity.plants.MyPlants;
import com.example.auth.exception.ResourceNotFoundException;

import java.util.List;


public interface MyPlantService {

    List<MyPlants> findAllPlants();

    MyPlants findByName(String plantName);

    MyPlants findById(Long id) throws ResourceNotFoundException;

}
