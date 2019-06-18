package com.example.auth.entity.crunch;

public class MyPlantNamesExp {

    private Long id;

    private String name;

    public MyPlantNamesExp() {
    }

    public MyPlantNamesExp(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
