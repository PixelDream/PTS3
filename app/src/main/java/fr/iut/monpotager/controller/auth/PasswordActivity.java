package fr.iut.monpotager.controller.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import fr.iut.monpotager.R;

public class PasswordActivity extends AppCompatActivity {

    private static final String TAG = "PasswordActivity";

    private FirebaseAuth mAuth;
    private EditText emailInput;
    private Button passwordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_password);

        mAuth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.emailInput);
        passwordButton = findViewById(R.id.passwordButton);

        handleListener();
    }

    private void handleListener() {
        passwordButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();

            if (email.isEmpty()) {
                return;
            }

            resetPassword(emailInput.getText().toString());
        });
    }

    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Email envoyé", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this, "Erreur d'email", Toast.LENGTH_LONG);
            }
        });
    }


}