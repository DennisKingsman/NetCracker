package com.example.auth.services;

import com.example.auth.entity.GrowBox;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.repo.GrowBoxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrowBoxServiceImpl implements GrowBoxService {

    @Autowired
    private GrowBoxRepo growBoxRepo;

    @Override
    public List<GrowBox> findByUser(Long userId) {
        return growBoxRepo.findByResponsibleUserId(userId);
    }

    @Override
    public GrowBox findById(Long id) throws ResourceNotFoundException {
        return growBoxRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
    }

    @Override
    public GrowBox saveBox(GrowBox box) {
        return growBoxRepo.save(box);
    }

    @Override
    public void deleteBox(Long id) {
        growBoxRepo.deleteById(id);
    }
}
