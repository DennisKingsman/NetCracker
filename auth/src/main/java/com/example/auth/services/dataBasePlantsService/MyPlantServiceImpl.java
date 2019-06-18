package com.example.auth.services.dataBasePlantsService;

import com.example.auth.entity.plants.MyPlants;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.repo.MyPlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPlantServiceImpl implements MyPlantService {

    @Autowired
    MyPlantRepo myPlantRepo;

    @Override
    public List<MyPlants> findAllPlants() {
        return myPlantRepo.findAll();
    }

    @Override
    public MyPlants findByName(String plantName) {
        return myPlantRepo.findByPlantName(plantName);
    }

    @Override
    public MyPlants findById(Long id) throws ResourceNotFoundException {
        return myPlantRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
    }
}
