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

    public Vegetable(String name, int duration, String image, String temperature) {
        this.name = name;
        this.duration = duration;
        this.picture = image;
        this.temperature = temperature;
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


    public int getDuration() {
        return duration;
    }

    public String getPicture() {
        return picture;
    }

    public String getTemperature() {
        return temperature;
    }
}
