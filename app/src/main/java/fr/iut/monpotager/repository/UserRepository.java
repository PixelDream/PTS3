package fr.iut.monpotager.repository;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public final class UserRepository {

    private static volatile UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        UserRepository result = instance;

        if (result != null) {
            return result;
        }

        synchronized (UserRepository.class) {
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

}