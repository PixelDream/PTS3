package fr.iut.monpotager.model;

public enum Season {
    PRINTEMPS("Printemps"), ETE("Ete"), AUTOMNE("Automne"), HIVER("Hiver");

    private final String name;

    Season(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Season toSeason(String str) {
        switch (str) {
            case "PRINTEMPS":
                return PRINTEMPS;
            case "ETE":
                return ETE;
            case "AUTOMNE":
                return AUTOMNE;
            case "HIVER":
                return HIVER;
            default:
                return null;
        }
    }
}
