package com.example.auth.repo;

import com.example.auth.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SensorRepo extends JpaRepository<Sensor, Long> {

}
