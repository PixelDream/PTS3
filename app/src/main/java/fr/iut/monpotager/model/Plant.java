package fr.iut.monpotager.model;

import android.os.Parcel;

public class Plant {
    private final String name;
    private final int sunNeed;
    private final Period plantationPeriod;
    private final Period sowingPeriod;
    private final int idealTemp;
    private final int waterNeed;
    private final int fruitingTime;
    private final String maintenanceInstructions;
    private final Season preferSeason;
    private final Period floweringDate;
    private final Species species;
    private final int lifeTime;

    public Plant(String name, int sunNeed, Period plantationPeriod, Period sowingPeriod, int idealTemp, int waterNeed, int fruitingTime, String maintenanceInstructions, Season preferSeason, Period floweringDate, Species species, int lifeTime) {
        this.name = name;
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

    public Plant(Parcel in) {
        this.name = in.readString();
        this.sunNeed = in.readInt();
        this.plantationPeriod = Period.AOUT.toPeriod(in.readString()); //TODO: refaire ca c'est pas beau
        this.sowingPeriod = Period.AOUT.toPeriod(in.readString()); //TODO: refaire ca c'est pas beau
        this.idealTemp = in.readInt();
        this.waterNeed = in.readInt();
        this.fruitingTime = in.readInt();
        this.maintenanceInstructions = in.readString();
        this.preferSeason = Season.AUTOMNE.toSeason(in.readString()); //TODO: refaire ca c'est pas beau
        this.floweringDate = Period.AOUT.toPeriod(in.readString()); //TODO: refaire ca c'est pas beau
        this.species = Species.ESPECE1.toSpecies(in.readString()); //TODO: refaire ca c'est pas beau
        this.lifeTime = in.readInt();
    }

    public String getName() {
        return name;
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
