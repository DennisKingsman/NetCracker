package com.example.auth.repo;

import com.example.auth.entity.GrowBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowBoxRepo extends JpaRepository<GrowBox, Long> {

    List<GrowBox> findByResponsibleUserId(Long userId);
}
