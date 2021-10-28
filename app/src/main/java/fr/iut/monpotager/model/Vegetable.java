package fr.iut.monpotager.model;

import java.io.Serializable;

public class Vegetable implements Serializable {

    private String name;
    private int water;
    private int[] sowingMonth;
    private String[] adviseMaintenance;
    private String[] adviseRecolt;
    private int[] harvestMonth;
    private boolean perpetual;
    private int[] plantingMonth;
    private int sunshine;
    private String temperature;
    private String[] tips;
    private Vegetable[] vegetablesCompatibilities;
    private String weather;


    /**
     * In Day
     */
    private int duration;
    private String picture;

    public Vegetable(String name, int duration, String image) {
        this.name = name;
        this.duration = duration;
        this.picture = image;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "name='" + name + '\'' +
                ", water=" + water +
                ", duration=" + duration +
                ", picture='" + picture + '\'' +
                '}';
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
