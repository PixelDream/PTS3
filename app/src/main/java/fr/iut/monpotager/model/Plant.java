package fr.iut.monpotager.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Plant implements Parcelable {
    private String name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.sunNeed);
        dest.writeString(String.valueOf(this.plantationPeriod));
        dest.writeString(String.valueOf(this.sowingPeriod));
        dest.writeInt(this.idealTemp);
        dest.writeInt(this.waterNeed);
        dest.writeInt(this.fruitingTime);
        dest.writeString(this.maintenanceInstructions);
        dest.writeString(String.valueOf(this.preferSeason));
        dest.writeString(String.valueOf(this.floweringDate));
        dest.writeString(String.valueOf(this.species));
        dest.writeInt(this.lifeTime);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };
}
