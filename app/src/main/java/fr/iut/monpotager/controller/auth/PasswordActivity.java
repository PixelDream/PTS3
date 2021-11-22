package fr.iut.monpotager.controller.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import fr.iut.monpotager.R;

public class PasswordActivity extends AppCompatActivity implements Validator.ValidationListener {

    private static final String TAG = "PasswordActivity";

    private FirebaseAuth mAuth;
    private Validator validator;
    private boolean isValid = false;

    @NotEmpty
    @Email
    private EditText emailInput;

    private Button passwordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_password);

        mAuth = FirebaseAuth.getInstance();

        validator = new Validator(this);
        validator.setValidationListener(this);

        emailInput = findViewById(R.id.emailInputRecover);
        passwordButton = findViewById(R.id.passwordButton);

        handleListener();
    }

    private void handleListener() {
        passwordButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();

            validator.validate();

            if (isValid) resetPassword(email);
        });
    }

    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Email envoyé", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erreur d'email", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onValidationSucceeded() {
        isValid = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isValid = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}