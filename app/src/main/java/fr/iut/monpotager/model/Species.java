package fr.iut.monpotager.model;

public enum Species {
    ESPECE1, ESPECE2, ESPECE3;

    public Species toSpecies(String str){
        switch (str){
            case "ESEPCE1":
                return ESPECE1;
            case "ESEPCE2":
                return ESPECE2;
            case "ESEPCE3":
                return ESPECE3;
            default:
                return null;
        }
    }
}
