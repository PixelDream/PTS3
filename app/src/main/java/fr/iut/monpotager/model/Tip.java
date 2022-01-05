package fr.iut.monpotager.model;

import java.io.Serializable;

public class Tip implements Serializable {

    private String tip;
    private String title;

    public Tip() {
    }

    public Tip(String tip, String title) {
        this.tip = tip;
        this.title = title;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

