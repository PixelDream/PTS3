package fr.iut.monpotager.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

import fr.iut.monpotager.manager.UserManager;
import fr.iut.monpotager.model.Garden;

/**
 * Leurs objectif est de récupérer la data
 */

public final class GardenRepository {

    private static final String TAG = "GardenRepository";
    private static final String GARDEN_COLLECTION = "Garden";

    private UserManager userManager;

    private static volatile GardenRepository instance;

    private GardenRepository() {
        this.userManager = UserManager.getInstance();
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

    public CollectionReference getGardenCollection(){
        return FirebaseFirestore.getInstance().collection(GARDEN_COLLECTION);
    }

    public DocumentReference getGardenDocument(String uid){
        return this.getGardenCollection().document(uid);
    }

    public void addVegetableToGarden(String vegetableId, int quantity, Date date, Runnable callback) {
        DocumentReference gardenRef = getGardenCollection().document();
        Garden garden = new Garden(vegetableId, quantity, date, userManager.getCurrentUser().getUid());

        // Store Vegetable to Firestore garden
        gardenRef.set(garden).addOnSuccessListener(unused -> {
            garden.setId(gardenRef.getId());
            callback.run();
        });
    }

}