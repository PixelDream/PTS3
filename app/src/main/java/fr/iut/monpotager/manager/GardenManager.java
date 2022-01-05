package fr.iut.monpotager.manager;

import java.util.Date;

import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.model.Vegetable;
import fr.iut.monpotager.repository.GardenRepository;
import fr.iut.monpotager.repository.UserRepository;


/***
 * Le manager lui, va s'occuper de traiter et formater la data si besoin.
 * Il va donc faire appel au Repository pour récupérer la data et
 * éventuellement, la traiter afin qu'elle soit dans un format exploitable par nos vues.
 */
public class GardenManager {

    private static volatile GardenManager instance;
    private final GardenRepository gardenRepository;

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

    public void addVegetableToGarden(Vegetable vegetable, int quantity, Date date, String unit, Runnable callback) {
        gardenRepository.addVegetableToGarden(vegetable, quantity, date, unit, callback);
    }

    public void getCurrentGarden(Callback callback) {
        gardenRepository.getCurrentGarden(callback);
    }


}