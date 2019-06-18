package com.example.auth.dao;

import com.example.auth.entity.crunch.MyPlantNamesExp;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MyPlantNameExpDAO {

    private static final List<MyPlantNamesExp> NAMES = new ArrayList<>();

    static {
        initData();
    }

    private static void initData() {
        MyPlantNamesExp tomato = new MyPlantNamesExp(1L,"TOMATO");
        MyPlantNamesExp papaya = new MyPlantNamesExp(2L, "PAPAYA");

        NAMES.add(tomato);
        NAMES.add(papaya);
    }

    public List<MyPlantNamesExp> getNames(){
        return NAMES;
    }
}
