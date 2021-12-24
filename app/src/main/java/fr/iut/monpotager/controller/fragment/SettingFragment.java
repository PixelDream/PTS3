package fr.iut.monpotager.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.squareup.picasso.Picasso;

import fr.iut.monpotager.R;
import fr.iut.monpotager.manager.UserManager;


public class SettingFragment extends Fragment {
    private static final int REQUEST = 1;
    private ImageView profile;
    private TextView firstName;
    private TextView lastName;

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
    private final UserManager userManager = UserManager.getInstance();
    private ActivityResultLauncher<Intent> mLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);
        // Inflate the layout for this fragment
        eMailActual = root.findViewById(R.id.emailActual2);
        eMailActual.setText(userManager.getCurrentUser().getEmail());
        pwActual= root.findViewById(R.id.mdpActual2);
        profile = root.findViewById(R.id.imageProfile);
        firstName = root.findViewById(R.id.nom2);
        firstName.setText(userManager.getFirstName());
        lastName = root.findViewById(R.id.prenom2);
        lastName.setText(userManager.getCurrentUser().getDisplayName().split(" ")[1]);
        eMail = root.findViewById(R.id.email2);
        passWord = root.findViewById(R.id.mdp2);
        profile.setOnClickListener(view -> {
            CHOOSEFOTO();
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



                    /*if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Picasso.get().load(data.getData()).into(profile);
                        try {
                            InputStream io = getActivity().getContentResolver().openInputStream(data.getData());
                            userManager.uploadImageProfile(new File(io.));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }*/
                });


        validate = root.findViewById(R.id.passwordButton);
        validate.setOnClickListener(view -> {
            if(!(firstName.getText().length()==0)) {
                nameUser = firstName.getText() + " " + lastName.getText();
                userManager.changeName(nameUser);
            }

            if(eMailActual.getText().length()!=0 && pwActual.getText().length()!=0){
                if(eMail.getText().length() != 0){
                    userManager.changeEmail(eMail.getText().toString(), eMailActual.getText().toString(), pwActual.getText().toString());
                }
                if(passWord.getText().length() != 0){
                    userManager.changePassword(passWord.getText().toString(), eMailActual.getText().toString(), pwActual.getText().toString());
                }
            }else{
                if(eMail.getText().length() != 0 || passWord.getText().length() != 0){
                    Toast.makeText(getContext(), "Erreur: impossible de modifier email ou mot de passe sans email et le mot de passe actuel", Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(getContext(), "Les modifications se feront après le relancement de l'application", Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
            this.getActivity().overridePendingTransition(0, 0);
            this.getActivity().startActivity(this.getActivity().getIntent());
            this.getActivity().overridePendingTransition(0, 0);
        });

        return root;
    }

    private void triggerChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        mLauncher.launch(Intent.createChooser(intent, ""));
    }

    private void CHOOSEFOTO() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        mLauncher.launch(Intent.createChooser(intent, "Choisir une image de profile"));
    }


}