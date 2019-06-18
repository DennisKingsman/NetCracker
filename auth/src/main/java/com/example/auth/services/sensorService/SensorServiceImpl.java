package com.example.auth.services.sensorService;

import com.example.auth.entity.sensor.Sensor;
import com.example.auth.repo.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepo sensorRepo;

    @Override
    public List<Sensor> findByBoxId(Long id) {
        return sensorRepo.findByActiveGrowBoxId(id);
    }

    @Override
    public void deleteSensor(Long id) {
        sensorRepo.deleteById(id);
    }

    @Override
    public Sensor saveSansor(Sensor sensor) {
        return sensorRepo.save(sensor);
    }
}
