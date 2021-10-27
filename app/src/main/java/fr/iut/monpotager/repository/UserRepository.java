package fr.iut.monpotager.repository;

<<<<<<< HEAD
import android.content.Context;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
=======
import androidx.annotation.Nullable;

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public final class UserRepository {

    private static volatile UserRepository instance;

<<<<<<< HEAD
    private UserRepository() { }
=======
    private UserRepository() {
    }
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

    public static UserRepository getInstance() {
        UserRepository result = instance;

        if (result != null) {
            return result;
        }

<<<<<<< HEAD
        synchronized(UserRepository.class) {
=======
        synchronized (UserRepository.class) {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }
    }

    @Nullable
<<<<<<< HEAD
    public FirebaseUser getCurrentUser(){
=======
    public FirebaseUser getCurrentUser() {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

}