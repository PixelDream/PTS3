package fr.iut.monpotager.repository;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.util.HashMap;
import java.util.Map;

import fr.iut.monpotager.controller.fragment.search.adapter.RoundedCornersTransformation;

public final class UserRepository {

    private static volatile UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        UserRepository result = instance;

        if (result != null) {
            return result;
        }

        synchronized (UserRepository.class) {
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void changeName(String name) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Display name: ", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                }
            }
        });

    }

    public void uploadImageProfile(Uri url) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();

        final StorageReference ImagesPath = mStorageReference.child("profile_images").child(user.getUid() + ".jpg");


        ImagesPath.putFile(url).continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }
            return ImagesPath.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downUri = task.getResult();
                downUri.toString();


                Map<String, Object> userMap = new HashMap<>();
                userMap.put("image", downUri.toString());
                userMap.put("name", user.getDisplayName());

                mFirestore.collection("Users").document(user.getUid()).set(userMap).addOnSuccessListener(aVoid -> {
                });
            }
        });

    }

    public void getImage(ImageView imageView, boolean rounded) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("Users").document(getCurrentUser().getUid()).get().addOnCompleteListener(task -> {

            Transformation transform = new RoundedCornersTransformation(90, 5, RoundedCornersTransformation.CornerType.ALL);

            if (task.isSuccessful()) {
                String img = task.getResult().get("image", String.class);

                RequestCreator rq = Picasso.get().load(img).resize(150, 150).centerCrop();
                if (rounded) rq.transform(transform);

                rq.into(imageView);

            } else {
                String img = user.getPhotoUrl().toString();

                RequestCreator rq = Picasso.get().load(img).resize(150, 150).centerCrop();
                if (rounded) rq.transform(transform);

                rq.into(imageView);
            }
        });
    }

    public void changeEmail(String eMail, String eMailActual, String pw) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider.getCredential(eMailActual, pw); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("email", "User re-authenticated.");
                        //Now change your email address \\
                        //----------------Code for Changing Email Address----------\\
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updateEmail(eMail)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("email", "User email address updated.");
                                        }
                                    }
                                });
                        //----------------------------------------------------------\\
                    }
                });

        //user.updateEmail(eMail);
    }

    public void changePass(String pass, String eMailActual, String pw) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(eMailActual, pw);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("pw", "Password updated");
                                    } else {
                                        Log.d("pw", "Error password not updated");
                                    }
                                }
                            });
                        } else {
                            Log.d("pw", "Error auth failed");
                        }
                    }
                });
    }
}