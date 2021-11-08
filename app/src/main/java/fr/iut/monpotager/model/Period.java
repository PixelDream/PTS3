package fr.iut.monpotager.model;

public enum Period {
    JANVIER(""), FEVRIER(""), MARS(""), AVRIL(""), MAI(""), JUIN(""), JUILLET(""), AOUT(""), SEPTEMBRE(""), OCTOBRE(""), NOVEMBRE(""), DECEMBRE("");

    private String name;

    Period (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Period toPeriod(String str){
        switch(str){
            case "JANVIER":
                return JANVIER;
            case "FEVRIER":
                return FEVRIER;
            case "MARS":
                return MARS;
            case "AVRIL":
                return AVRIL;
            case "MAI":
                return MAI;
            case "JUIN":
                return JUIN;
            case "JUILLET":
                return JUILLET;
            case "AOUT":
                return AOUT;
            case "SEPTEMBRE":
                return SEPTEMBRE;
            case "OCTOBRE":
                return OCTOBRE;
            case "NOVEMBRE":
                return NOVEMBRE;
            case "DECEMBRE":
                return DECEMBRE;
            default:
                return null;
        }
    }
}
