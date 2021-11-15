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

    public Vegetable() {
        this.name = "";
        this.water = 0;
        this.sowingMonth = new int[0];
        this.adviseMaintenance = new String[0];
        this.adviseRecolt = new String[0];
        this.harvestMonth = new int[0];
        this.perpetual = false;
        this.plantingMonth = new int[0];
        this.sunshine = 0;
        this.temperature = "";
        this.tips = new String[0];
        this.vegetablesCompatibilities = new Vegetable[0];
        this.weather = "";
        this.duration = 0;
        this.picture = "";
    }

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

    public void setWater(int water) {
        this.water = water;
    }

    public int[] getSowingMonth() {
        return sowingMonth;
    }

    public void setSowingMonth(int[] sowingMonth) {
        this.sowingMonth = sowingMonth;
    }

    public String[] getAdviseMaintenance() {
        return adviseMaintenance;
    }

    public void setAdviseMaintenance(String[] adviseMaintenance) {
        this.adviseMaintenance = adviseMaintenance;
    }

    public String[] getAdviseRecolt() {
        return adviseRecolt;
    }

    public void setAdviseRecolt(String[] adviseRecolt) {
        this.adviseRecolt = adviseRecolt;
    }

    public int[] getHarvestMonth() {
        return harvestMonth;
    }

    public void setHarvestMonth(int[] harvestMonth) {
        this.harvestMonth = harvestMonth;
    }

    public boolean isPerpetual() {
        return perpetual;
    }

    public void setPerpetual(boolean perpetual) {
        this.perpetual = perpetual;
    }

    public int[] getPlantingMonth() {
        return plantingMonth;
    }

    public void setPlantingMonth(int[] plantingMonth) {
        this.plantingMonth = plantingMonth;
    }

    public int getSunshine() {
        return sunshine;
    }

    public void setSunshine(int sunshine) {
        this.sunshine = sunshine;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String[] getTips() {
        return tips;
    }

    public void setTips(String[] tips) {
        this.tips = tips;
    }

    public Vegetable[] getVegetablesCompatibilities() {
        return vegetablesCompatibilities;
    }

    public void setVegetablesCompatibilities(Vegetable[] vegetablesCompatibilities) {
        this.vegetablesCompatibilities = vegetablesCompatibilities;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
