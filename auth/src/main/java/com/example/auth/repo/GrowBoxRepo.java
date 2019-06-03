package com.example.auth.repo;

import com.example.auth.entity.GrowBox;
import com.example.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrowBoxRepo extends JpaRepository<GrowBox, Long> {
    List<GrowBox> findByResponsibleUserId(Long userId);
}
