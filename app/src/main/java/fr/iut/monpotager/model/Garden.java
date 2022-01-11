package fr.iut.monpotager.model;

import android.os.Build;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Garden implements Serializable {

    private String id;
    private Vegetable vegetable;
    private String userId;
    private int quantity;
    private Date date;
    private String unit;

    public Garden(Vegetable vegetable, int quantity, Date date, String userId, String unit) {
        this.userId = userId;
        this.vegetable = vegetable;
        this.quantity = quantity;
        this.date = date;
        this.unit = unit;
    }

    public Garden() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("vegetableId", vegetable.getId());
        objectMap.put("userId", userId);
        objectMap.put("quantity", quantity);
        objectMap.put("date", date);
        objectMap.put("unit", unit);

        return objectMap;
    }

    @Exclude
    public long getDayDuration() {
        Calendar calendar = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return vegetable.getDuration() - Duration.between(date.toInstant(), calendar.toInstant()).toDays();
        }

        return -1;
    }

    @Exclude
    public Date getDateRecolt() {
        Calendar calendar = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar.add(Calendar.DATE, Math.toIntExact(getDayDuration()));
            return calendar.getTime();
        }

        return null;
    }

    @Exclude
    public boolean isFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return getDayDuration() <= 0L;
        }

        return false;
    }

}
