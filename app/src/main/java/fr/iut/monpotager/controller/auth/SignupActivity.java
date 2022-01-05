package fr.iut.monpotager.controller.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import fr.iut.monpotager.R;

public class SignupActivity extends AppCompatActivity implements Validator.ValidationListener {

    private static final String TAG = "SignupActivity";

    private FirebaseAuth mAuth;
    private Validator validator;
    private boolean isValid = false;

    @Length(min = 3)
    private EditText firstNameInput;

    @Length(min = 3)
    private EditText lastNameInput;

    @NotEmpty
    @Email
    private EditText emailInput;

    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText passwordInput;

    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_signup);

        mAuth = FirebaseAuth.getInstance();

        validator = new Validator(this);
        validator.setValidationListener(this);

        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signupButton = findViewById(R.id.signupButton);

        handleListener();
    }

    private void handleListener() {
        signupButton.setOnClickListener(view -> {
            String firtName = firstNameInput.getText().toString().trim();
            String lastName = lastNameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            validator.validate();
            if (isValid) signup(firtName, lastName, email, password);
        });
        passwordInput.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordInput.getRight() - passwordInput.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 20)) {
                    passwordInput.clearFocus();
                    Drawable right, left = getResources().getDrawable(R.drawable.ic_auth_password);

                    if (passwordInput.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        right = getResources().getDrawable(R.drawable.ic_auth_eye);
                    } else {
                        passwordInput.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        right = getResources().getDrawable(R.drawable.ic_auth_eye_open);
                    }

                    passwordInput.setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
                    passwordInput.setTypeface(emailInput.getTypeface());
                    return true;
                }
            }
            return false;
        });
    }

    public void signup(String firstName, String lastName, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                                .Builder()
                                .setDisplayName(firstName + " " + lastName)
                                .build();
                        user.updateProfile(profileUpdates);
                        finish();
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
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