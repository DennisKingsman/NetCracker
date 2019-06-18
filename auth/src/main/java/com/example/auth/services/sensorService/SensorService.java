package com.example.auth.services.sensorService;

import com.example.auth.entity.sensor.Sensor;

import java.util.List;

public interface SensorService {

    List<Sensor> findByBoxId(Long id);

    void deleteSensor(Long id);

    Sensor saveSansor(Sensor sensor);
}
