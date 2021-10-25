package fr.iut.monpotager.model;

import java.io.Serializable;

public class Vegetable implements Serializable {

    private String name;

    private int water;

    private int duration;

    private String picture;

    public Vegetable(String name, int duration, String image) {
        this.name = name;
        this.duration = duration;
        this.picture = image;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
