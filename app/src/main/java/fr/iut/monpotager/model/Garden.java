package fr.iut.monpotager.model;

import java.util.Date;

public class Garden {

    private String id;
    private String vegetableId;
    private String userId;
    private int quantity;
    private Date date;

    public Garden(String vegetableId, int quantity, Date date, String userId) {
        this.userId = userId;
        this.vegetableId = vegetableId;
        this.quantity = quantity;
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(String vegetableId) {
        this.vegetableId = vegetableId;
    }
}
