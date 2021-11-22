package fr.iut.monpotager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Vegetable implements Serializable {

    private String name;
    private int water;
    private List<Long> sowingMonth;
    private List<String> adviseMaintenance;
    private List<String> adviseRecolt;
    private List<Long> harvestMonth;
    private boolean perpetual;
    private List<Long> plantingMonth;
    private int sunshine;
    private String temperature;
    private List<String> tips;
    private List<Vegetable> vegetablesCompatibilities;
    private String weather;


    /**
     * In Day
     */
    private int duration;
    private String picture;

    public Vegetable() {
        this.name = "";
        this.water = 0;
        this.sowingMonth = new ArrayList<Long>(Collections.singletonList(1L));
        this.plantingMonth = new ArrayList<Long>(Collections.singletonList(1L));
        this.harvestMonth = new ArrayList<Long>(Collections.singletonList(1L));
        this.adviseMaintenance = new ArrayList<String>(Collections.singletonList(""));
        this.adviseRecolt = new ArrayList<String>(Collections.singletonList(""));
        this.perpetual = false;
        this.sunshine = 0;
        this.temperature = "";
        this.tips = new ArrayList<String>(Collections.singletonList(""));
        this.vegetablesCompatibilities =  new ArrayList<Vegetable>(Collections.singletonList(null));
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

    public List<Long> getSowingMonth() {
        return sowingMonth;
    }

    public void setSowingMonth(List<Long> sowingMonth) {
        this.sowingMonth = sowingMonth;
    }

    public void setHarvestMonth(List<Long> harvestMonth) {
        this.harvestMonth = harvestMonth;
    }

    public void setPlantingMonth(List<Long> plantingMonth) {
        this.plantingMonth = plantingMonth;
    }

    public void setVegetablesCompatibilities(List<Vegetable> vegetablesCompatibilities) {
        this.vegetablesCompatibilities = vegetablesCompatibilities;
    }

    public List<Long> getHarvestMonth() {
        return harvestMonth;
    }

    public List<Long> getPlantingMonth() {
        return plantingMonth;
    }

    public List<Vegetable> getVegetablesCompatibilities() {
        return vegetablesCompatibilities;
    }

    public List<String> getAdviseMaintenance() {
        return adviseMaintenance;
    }

    public void setAdviseMaintenance(List<String> adviseMaintenance) {
        this.adviseMaintenance = adviseMaintenance;
    }

    public List<String> getAdviseRecolt() {
        return adviseRecolt;
    }

    public void setAdviseRecolt(List<String> adviseRecolt) {
        this.adviseRecolt = adviseRecolt;
    }

    public boolean isPerpetual() {
        return perpetual;
    }

    public void setPerpetual(boolean perpetual) {
        this.perpetual = perpetual;
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

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
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
