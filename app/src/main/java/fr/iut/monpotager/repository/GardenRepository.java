package fr.iut.monpotager.repository;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Date;

import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.UserManager;
import fr.iut.monpotager.manager.VegetableManager;
import fr.iut.monpotager.model.Garden;
import fr.iut.monpotager.model.Vegetable;

/**
 * Leurs objectif est de récupérer la data
 */

public final class GardenRepository {

    private static final String TAG = "GardenRepository";
    private static final String GARDEN_COLLECTION = "Garden";
    private static volatile GardenRepository instance;
    private final UserManager userManager;
    private final VegetableManager vegetableManager;

    private GardenRepository() {
        this.userManager = UserManager.getInstance();
        this.vegetableManager = VegetableManager.getInstance();
    }

    public static GardenRepository getInstance() {
        GardenRepository result = instance;

        if (result != null) {
            return result;
        }

        synchronized (GardenRepository.class) {
            if (instance == null) {
                instance = new GardenRepository();
            }
            return instance;
        }
    }

    public CollectionReference getGardenCollection() {
        return FirebaseFirestore.getInstance().collection(GARDEN_COLLECTION);
    }

    public DocumentReference getGardenDocument(String uid) {
        return this.getGardenCollection().document(uid);
    }

    public void addVegetableToGarden(Vegetable vegetable, int quantity, Date date, String unit, Runnable callback) {
        DocumentReference gardenRef = getGardenCollection().document();
        Garden garden = new Garden(vegetable, quantity, date, userManager.getCurrentUser().getUid(), unit);

        // Store Vegetable to Firestore garden
        gardenRef.set(garden.toMap()).addOnSuccessListener(unused -> {
            garden.setId(gardenRef.getId());
            callback.run();
        });
    }

    public void getCurrentGarden(Callback callback) {

        getGardenCollection().whereEqualTo("userId", userManager.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Garden g = new Garden();
                    g.setId(document.getId());
                    g.setDate(document.getDate("date"));
                    g.setQuantity(document.get("quantity", Integer.class));
                    g.setUnit(document.getString("unit"));
                    g.setUserId(document.getString("userId"));

                    vegetableManager.getVegetableById(document.getString("vegetableId"), new Callback() {
                        @Override
                        public void onSuccessResult(Object result) {
                            g.setVegetable((Vegetable) result);
                            callback.onSuccessResult(g);
                            Log.e(TAG, "add " + g.getVegetable().getName());
                        }

                        @Override
                        public void onErrorResult(Exception e) {
                            callback.onErrorResult(task.getException());
                        }
                    });

                }

            } else {
                callback.onErrorResult(task.getException());
            }
        });
    }

}