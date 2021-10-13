package fr.iut.monpotager.controller;

import java.io.Serializable;

public class Vegetable implements Serializable {

    private String name;

    private int water;

    private int duration;

    private String image;

    public Vegetable(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
