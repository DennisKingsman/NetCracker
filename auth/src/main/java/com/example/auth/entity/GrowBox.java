package com.example.auth.entity;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "growBoxes")
public class GrowBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Integer length;

    private Integer width;

    private Integer height;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responsibleGrowBox", cascade = CascadeType.ALL)
    private List<Plant> plants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activeGrowBox", cascade = CascadeType.ALL)
    private List<Sensor> sensors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsibleUser;



    public GrowBox() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }
}
