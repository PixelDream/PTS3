package fr.iut.monpotager.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import fr.iut.monpotager.R;

public class MainActivity extends AppCompatActivity {

    /**
     * Create app
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_login);
    }
}