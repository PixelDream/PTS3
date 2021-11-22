package fr.iut.monpotager.repository;

<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Context;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
=======
import androidx.annotation.Nullable;

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
import androidx.annotation.Nullable;

>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public final class UserRepository {

    private static volatile UserRepository instance;

<<<<<<< HEAD
<<<<<<< HEAD
    private UserRepository() { }
=======
    private UserRepository() {
    }
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
    private UserRepository() {
    }
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3

    public static UserRepository getInstance() {
        UserRepository result = instance;

        if (result != null) {
            return result;
        }

<<<<<<< HEAD
<<<<<<< HEAD
        synchronized(UserRepository.class) {
=======
        synchronized (UserRepository.class) {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
        synchronized (UserRepository.class) {
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }
    }

    @Nullable
<<<<<<< HEAD
<<<<<<< HEAD
    public FirebaseUser getCurrentUser(){
=======
    public FirebaseUser getCurrentUser() {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
    public FirebaseUser getCurrentUser() {
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

}