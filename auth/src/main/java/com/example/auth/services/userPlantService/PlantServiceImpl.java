package com.example.auth.services.userPlantService;

import com.example.auth.entity.plants.Plant;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.repo.PlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepo plantRepo;

    @Override
    public List<Plant> findByBoxId(Long boxId) {
        return plantRepo.findByResponsibleGrowBoxId(boxId);
    }

    @Override
    public Plant findById(Long id) throws ResourceNotFoundException {
        return plantRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
    }

    @Override
    public Plant savePlant(Plant plant) {
        return plantRepo.save(plant);
    }

    @Override
    public void deletePlant(Plant plant) {
        plantRepo.delete(plant);
    }

    @Override
    public void deletePlantById(Long plantId) {
        plantRepo.deleteById(plantId);
    }
}
