package com.example.auth.entity;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorName;

    private Integer value;

    @ManyToOne
    @JoinColumn(name = "agb_id")
    private GrowBox activeGrowBox;

    public Sensor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public GrowBox getActiveGrowBox() {
        return activeGrowBox;
    }

    public void setActiveGrowBox(GrowBox activeGrowBox) {
        this.activeGrowBox = activeGrowBox;
    }
}
