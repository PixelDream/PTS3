package fr.iut.monpotager.manager;

import java.util.Date;

import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.repository.GardenRepository;
import fr.iut.monpotager.repository.UserRepository;
import fr.iut.monpotager.repository.VegetableRepository;


/***
 * Le manager lui, va s'occuper de traiter et formater la data si besoin.
 * Il va donc faire appel au Repository pour récupérer la data et
 * éventuellement, la traiter afin qu'elle soit dans un format exploitable par nos vues.
 */
public class VegetableManager {

    private static volatile VegetableManager instance;
    private VegetableRepository vegetableRepository;

    private VegetableManager() {
        vegetableRepository = vegetableRepository.getInstance();
    }

    public static VegetableManager getInstance() {
        VegetableManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserRepository.class) {
            if (instance == null) {
                instance = new VegetableManager();
            }
            return instance;
        }
    }

    public void getVegetableById(String uid, Callback callback) {
        vegetableRepository.getVegetableById(uid, callback);
    }

}