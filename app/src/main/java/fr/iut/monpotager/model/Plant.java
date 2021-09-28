package fr.iut.monpotager.model;

import java.util.Date;

public class Plant {
    private int sunNeed;
    private Period plantationPeriod;
    private Period sowingPeriod;
    private int idealTemp;
    private int waterNeed;
    private int fruitingTime;
    private String maintenanceInstructions;
    private Season preferSeason;
    private Period floweringDate;
    private Species species;
    private int lifeTime;

    public Plant(int sunNeed, Period plantationPeriod, Period sowingPeriod, int idealTemp, int waterNeed, int fruitingTime, String maintenanceInstructions, Season preferSeason, Period floweringDate, Species species, int lifeTime) {
        this.sunNeed = sunNeed;
        this.plantationPeriod = plantationPeriod;
        this.sowingPeriod = sowingPeriod;
        this.idealTemp = idealTemp;
        this.waterNeed = waterNeed;
        this.fruitingTime = fruitingTime;
        this.maintenanceInstructions = maintenanceInstructions;
        this.preferSeason = preferSeason;
        this.floweringDate = floweringDate;
        this.species = species;
        this.lifeTime = lifeTime;
    }

    public int getSunNeed() {
        return sunNeed;
    }

    public Period getPlantationPeriod() {
        return plantationPeriod;
    }

    public Period getSowingPeriod() {
        return sowingPeriod;
    }

    public int getIdealTemp() {
        return idealTemp;
    }

    public int getWaterNeed() {
        return waterNeed;
    }

    public int getFruitingTime() {
        return fruitingTime;
    }

    public String getMaintenanceInstructions() {
        return maintenanceInstructions;
    }

    public Season getPreferSeason() {
        return preferSeason;
    }

    public Period getFloweringDate() {
        return floweringDate;
    }

    public Species getSpecies() {
        return species;
    }

    public int getLifeTime() {
        return lifeTime;
    }
}
