package fr.iut.monpotager.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.squareup.picasso.Picasso;

import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.manager.UserManager;


public class SettingFragment extends Fragment implements Validator.ValidationListener {
    private static final String TAG = "Setting";
    private static final int REQUEST = 1;
    private final UserManager userManager = UserManager.getInstance();
    private ImageView profile;
    private TextView firstName;
    private TextView lastName;
    private Validator validator;
    private boolean isValid = false;
    private String nameUser;
    @NotEmpty
    @Email
    private TextView eMail;
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private TextView passWord;
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private TextView pwActual;
    @NotEmpty
    @Email
    private TextView eMailActual;
    private Button validate;
    private ActivityResultLauncher<Intent> mLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);
        validator = new Validator(this);
        validator.setValidationListener(this);
        eMailActual = root.findViewById(R.id.emailActual2);
        eMailActual.setText(userManager.getCurrentUser().getEmail());
        pwActual = root.findViewById(R.id.mdpActual2);
        profile = root.findViewById(R.id.imageProfile);
        firstName = root.findViewById(R.id.nom2);
        firstName.setText(userManager.getFirstName());
        lastName = root.findViewById(R.id.prenom2);
        lastName.setText(userManager.getCurrentUser().getDisplayName().split(" ")[1]);
        eMail = root.findViewById(R.id.email2);
        passWord = root.findViewById(R.id.mdp2);


        profile.setOnClickListener(view -> {
            choosePicture();
        });

        mLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri mainImageURI = result.getData().getData();
                        userManager.uploadImageProfile(mainImageURI);
                        Picasso.get().load(mainImageURI).into(profile);
                    } else {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });

        validate = root.findViewById(R.id.passwordButton);
        handleListener();
        return root;
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
            String message = error.getCollatedErrorMessage(this.getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleListener() {
        validate.setOnClickListener(view -> {
            if (eMailActual.getText().toString().isEmpty()) {
                eMailActual.setError(getResources().getString(R.string.email_error));
            } else if (!Patterns.EMAIL_ADDRESS.matcher(eMailActual.getText().toString()).matches()) {
                eMailActual.setError(getResources().getString(R.string.error_invalid_email));
            }

//            validator.validate();
//            if (isValid){
            if (!(firstName.getText().length() == 0)) {
                nameUser = firstName.getText() + " " + lastName.getText();
                userManager.changeName(nameUser);
            }
            if (eMailActual.getText().length() != 0 && pwActual.getText().length() != 0) {
                if (eMail.getText().length() != 0) {
                    if (!Patterns.EMAIL_ADDRESS.matcher(eMail.getText().toString()).matches()) {
                        eMail.setError(getResources().getString(R.string.error_invalid_email));
                    } else {
                        userManager.changeEmail(eMail.getText().toString(), eMailActual.getText().toString(), pwActual.getText().toString());
                    }
                }
                if (passWord.getText().length() != 0) {
                    userManager.changePassword(passWord.getText().toString(), eMailActual.getText().toString(), pwActual.getText().toString());
                }
            } else {
                if (eMail.getText().length() != 0 || passWord.getText().length() != 0) {
                    Toast.makeText(getContext(), "Erreur: impossible de modifier email ou mot de passe sans email et le mot de passe actuel", Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(getContext(), "Les modifications se feront après le relancement de l'application", Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
            this.getActivity().overridePendingTransition(0, 0);
            this.getActivity().startActivity(this.getActivity().getIntent());
            this.getActivity().overridePendingTransition(0, 0);
            //}
        });

        pwActual.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (pwActual.getRight() - pwActual.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 20)) {
                    pwActual.clearFocus();
                    Drawable right;

                    if (pwActual.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        pwActual.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        right = getResources().getDrawable(R.drawable.ic_auth_eye);
                    } else {
                        pwActual.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        right = getResources().getDrawable(R.drawable.ic_auth_eye_open);
                    }

                    pwActual.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
                    pwActual.setTypeface(eMailActual.getTypeface());
                    return true;
                }
            }
            return false;
        });

        passWord.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passWord.getRight() - passWord.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 20)) {
                    passWord.clearFocus();
                    Drawable right;

                    if (passWord.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        right = getResources().getDrawable(R.drawable.ic_auth_eye);
                    } else {
                        passWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        right = getResources().getDrawable(R.drawable.ic_auth_eye_open);
                    }

                    passWord.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
                    passWord.setTypeface(eMail.getTypeface());
                    return true;
                }
            }

            return false;
        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        mLauncher.launch(Intent.createChooser(intent, "Choisir une image de profile"));
    }


}