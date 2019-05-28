package com.example.auth.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "growBoxes")
public class GrowBox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private int length;

    private int width;

    private int height;

    private Set<String> plants;

    private Set<String> sensors;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<String> getPlants() {
        return plants;
    }

    public void setPlants(Set<String> plants) {
        this.plants = plants;
    }

    public Set<String> getSensors() {
        return sensors;
    }

    public void setSensors(Set<String> sensors) {
        this.sensors = sensors;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }
}
