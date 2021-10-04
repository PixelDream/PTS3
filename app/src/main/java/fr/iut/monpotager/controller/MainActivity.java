package fr.iut.monpotager.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.auth.LoginActivity;
import fr.iut.monpotager.controller.auth.SignupActivity;
import fr.iut.monpotager.manager.UserManager;

public class MainActivity extends AppCompatActivity {

    private UserManager userManager = UserManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!userManager.isCurrentUserLogged()) {
            //Toast.makeText(this, "test", Toast.LENGTH_LONG);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}