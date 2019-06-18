package com.example.auth.entity.plants;

import com.example.auth.entity.GrowBox;

import javax.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "my_plant_id", nullable = false)
    private Long myPlantId;

    @Column(nullable = false)
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "gb_id")
    private GrowBox responsibleGrowBox;

    public Plant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMyPlantId() {
        return myPlantId;
    }

    public void setMyPlantId(Long myPlantId) {
        this.myPlantId = myPlantId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public GrowBox getResponsibleGrowBox() {
        return responsibleGrowBox;
    }

    public void setResponsibleGrowBox(GrowBox responsibleGrowBox) {
        this.responsibleGrowBox = responsibleGrowBox;
    }

    @Override
    public String toString() {
        return "id : " + id + " myPlantId : " + myPlantId;
    }
}
