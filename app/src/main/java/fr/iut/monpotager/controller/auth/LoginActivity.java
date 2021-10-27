package fr.iut.monpotager.controller.auth;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.Arrays;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.MainActivity;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private Validator validator;

    @Email
    @NotEmpty
    private EditText emailInput;

    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText passwordInput;

    private TextView signup;
    private TextView forgotPassword;
    private ImageButton googleButton;
    private ImageButton facebookButton;
    private Button loginButton;
    private LoginButton facebook_btn;
    private GoogleSignInClient googleSignInClient;
    private CallbackManager mcallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_login);

        validator = new Validator(this);
        validator.setValidationListener(this);

        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_clinent_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        mcallbackManager = CallbackManager.Factory.create();

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginButton = findViewById(R.id.loginButton);
        googleButton = findViewById(R.id.googleButton);
        facebookButton = findViewById(R.id.facebookButton);
        facebook_btn = findViewById(R.id.facebook_btn);

        forgotPassword = findViewById(R.id.forgotPassword);
        signup = findViewById(R.id.signup);


        loginFacebook();
        handleListener();
    }

    /**
<<<<<<< HEAD
     *  Create listeners on buttons
=======
     * Create listeners on buttons
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
     */

    private void handleListener() {
        forgotPassword.setOnClickListener(v -> startActivity(new Intent(this, PasswordActivity.class)));
        signup.setOnClickListener(v -> startActivity(new Intent(this, SignupActivity.class)));
        googleButton.setOnClickListener(v -> loginGoogle());
        facebookButton.setOnClickListener(v -> facebook_btn.performClick());
        loginButton.setOnClickListener(view -> {
<<<<<<< HEAD
=======

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            validator.validate();

            if (validator.isValidating()) login(email, password);
        });
    }

    /**
     * Login with Facebook service
     */
    private void loginFacebook() {
        List<String> permissionNeeds = Arrays.asList("email", "public_profile");

        facebook_btn.setReadPermissions(permissionNeeds);

        facebook_btn.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });

        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) mAuth.signOut();
            }
        };
    }

    /**
     * Call Google Servie
     */
    public void loginGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        redirectUser();
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();

                        if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                            Toast.makeText(this, "Account Created...\n" + email, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "Existing user...\n" + email, Toast.LENGTH_LONG).show();
                        }

                        redirectUser();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }

                });
    }

    public void firebaseAuthWithFacebook(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                    Toast.makeText(this, "Account Created...\n", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Existing user...\n", Toast.LENGTH_LONG).show();
                }

                redirectUser();
            } else {
                try {
                    throw task.getException();
                } catch (FirebaseAuthUserCollisionException e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("You have already an account with other service...");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.show();
                    Log.w(TAG, e.getMessage());
                } catch (Exception e) {
                    Log.w(TAG, e.getMessage());
                }
            }
        });

    }

    public void redirectUser() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
<<<<<<< HEAD
    public void onValidationSucceeded() {}
=======
    public void onValidationSucceeded() {
    }
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
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