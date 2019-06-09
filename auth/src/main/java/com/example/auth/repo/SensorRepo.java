package com.example.auth.repo;

import com.example.auth.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Long> {

    List<Sensor> findByActiveGrowBoxId(Long boxId);
}
