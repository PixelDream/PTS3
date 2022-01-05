package fr.iut.monpotager.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.model.Tip;

public class TipRepository {

    private static final String TAG = "TipRepository";
    private static final String TIP_COLLECTION = "Tips";

    private static volatile TipRepository instance;

    private TipRepository() {
    }

    public static TipRepository getInstance() {
        TipRepository result = instance;

        if (result != null) {
            return result;
        }

        synchronized (TipRepository.class) {
            if (instance == null) {
                instance = new TipRepository();
            }
            return instance;
        }
    }

    public CollectionReference getTipCollection() {
        return FirebaseFirestore.getInstance().collection(TIP_COLLECTION);
    }

    public void getRandomTip(Callback callback) {
        getTipCollection().get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Tip> tips = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Tip t = new Tip();

                    t.setTitle(document.getString("title"));
                    t.setTip(document.getString("tip"));

                    tips.add(t);
                }

                Collections.shuffle(tips);

                callback.onSuccessResult(tips.get(0));
            } else {
                callback.onErrorResult(task.getException());
            }
        });
    }

}