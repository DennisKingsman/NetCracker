package com.example.auth.services.boxService;

import com.example.auth.entity.GrowBox;
import com.example.auth.exception.ResourceNotFoundException;

import java.util.List;

public interface GrowBoxService {

    List<GrowBox> findByUser(Long userId);

    GrowBox findById(Long id) throws ResourceNotFoundException;

    GrowBox saveBox(GrowBox box);

    void deleteBox(Long id);
}
