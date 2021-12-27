package fr.iut.monpotager.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.UserManager;
import fr.iut.monpotager.model.Garden;
import fr.iut.monpotager.model.Vegetable;

/**
 * Leurs objectif est de récupérer la data
 */

public final class VegetableRepository {

    private static final String TAG = "VegetableRepository";
    private static final String GARDEN_COLLECTION = "vegetables";

    private UserManager userManager;

    private static volatile VegetableRepository instance;

    private VegetableRepository() {
        this.userManager = UserManager.getInstance();
    }

    public static VegetableRepository getInstance() {
        VegetableRepository result = instance;

        if (result != null) {
            return result;
        }

        synchronized (VegetableRepository.class) {
            if (instance == null) {
                instance = new VegetableRepository();
            }
            return instance;
        }
    }

    public CollectionReference getVegetableCollection() {
        return FirebaseFirestore.getInstance().collection(GARDEN_COLLECTION);
    }

    public DocumentReference getVegetableDocument(String uid) {
        return this.getVegetableCollection().document(uid);
    }

//    public void addVegetable(String vegetableId, int quantity, Date date, Runnable callback) {
//        DocumentReference gardenRef = getGardenCollection().document();
//        Garden garden = new Garden(vegetableId, quantity, date, userManager.getCurrentUser().getUid());
//
//        // Store Vegetable to Firestore garden
//        gardenRef.set(garden).addOnSuccessListener(unused -> {
//            garden.setId(gardenRef.getId());
//            callback.run();
//        });
//    }

    public void getVegetableById(String uid, Callback callback) {

        getVegetableDocument(uid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                DocumentSnapshot document = task.getResult();

                Vegetable v = new Vegetable();
                v.setId(document.getId());
                v.setName(document.getString("name"));
                v.setDuration(document.get("duration", Integer.class));
                v.setPicture(document.getString("picture"));
                v.setTemperature(document.getString("temperature"));
                v.setSunshine(document.get("sunshine", Integer.class));
                v.setAdviseMaintenance((List<String>) document.get("advise_maintenance"));
                v.setAdviseRecolt((List<String>) document.get("advise_recolt"));
                v.setHarvestMonth((List<Long>) document.get("harvest_month"));
                v.setPerpetual(document.getBoolean("perpetual"));
                v.setPlantingMonth((List<Long>) document.get("planting_month"));
                v.setSowingMonth((List<Long>) document.get("sowing_month"));
                v.setWater(document.get("water", Integer.class));
                v.setWeather(document.getString("weather"));

                callback.onSuccessResult(v);
            } else {
                callback.onErrorResult(task.getException());
            }
        });
    }

}