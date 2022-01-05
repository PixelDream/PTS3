package fr.iut.monpotager.manager;

import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.repository.TipRepository;

public class TipManager {

    private static volatile TipManager instance;
    private final TipRepository tipRepository;

    private TipManager() {
        tipRepository = TipRepository.getInstance();
    }

    public static TipManager getInstance() {
        TipManager result = instance;

        if (result != null) {
            return result;
        }

        synchronized (TipManager.class) {
            if (instance == null) {
                instance = new TipManager();
            }
            return instance;
        }
    }

    public void getRandomTip(Callback callback) {
        tipRepository.getRandomTip(callback);
    }

}