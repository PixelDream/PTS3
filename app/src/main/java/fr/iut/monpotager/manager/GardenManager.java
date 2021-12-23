package fr.iut.monpotager.manager;

import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

import fr.iut.monpotager.model.Garden;
import fr.iut.monpotager.repository.GardenRepository;
import fr.iut.monpotager.repository.UserRepository;


/***
 * Le manager lui, va s'occuper de traiter et formater la data si besoin.
 * Il va donc faire appel au Repository pour récupérer la data et
 * éventuellement, la traiter afin qu'elle soit dans un format exploitable par nos vues.
 */
public class GardenManager {

    private static volatile GardenManager instance;
    private GardenRepository gardenRepository;

    private GardenManager() {
        gardenRepository = GardenRepository.getInstance();
    }

    public static GardenManager getInstance() {
        GardenManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserRepository.class) {
            if (instance == null) {
                instance = new GardenManager();
            }
            return instance;
        }
    }

    public void addVegetableToGarden(String vegetableId, int quantity, Date date, Runnable callback) {
        gardenRepository.addVegetableToGarden(vegetableId, quantity, date, callback);
    }



}