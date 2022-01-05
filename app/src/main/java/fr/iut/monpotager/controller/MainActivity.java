package fr.iut.monpotager.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.iut.monpotager.R;

public class MainActivity extends AppCompatActivity {

    /**
     * Create app
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("Firebase", "Is user anonymous:" + firebaseAuth.isAnonymous());
        setContentView(R.layout.activity_main);
    }
}