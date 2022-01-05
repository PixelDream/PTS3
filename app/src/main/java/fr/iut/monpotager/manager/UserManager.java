package fr.iut.monpotager.manager;

import android.net.Uri;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;

import fr.iut.monpotager.repository.UserRepository;

public class UserManager {

    private static volatile UserManager instance;
    private final UserRepository userRepository;

    private UserManager() {
        userRepository = UserRepository.getInstance();
    }

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserRepository.class) {
            if (instance == null) {
                instance = new UserManager();
            }
            return instance;
        }
    }

    public FirebaseUser getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public String getFirstName() {
        String firstName = userRepository.getCurrentUser().getDisplayName().split(" ")[0];
        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }

    public void changeName(String name) {
        userRepository.changeName(name);
    }

    public void changeEmail(String eMail, String eMailActual, String pw) {
        userRepository.changeEmail(eMail, eMailActual, pw);
    }

    public void changePassword(String pass, String eMailActual, String pw) {
        userRepository.changePass(pass, eMailActual, pw);
    }

    public Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() != null);
    }

    public void signOut() {
        userRepository.signOut();
    }

    public void uploadImageProfile(Uri url) {
        userRepository.uploadImageProfile(url);
    }

    public void imageProfileIntoImage(ImageView imageView, boolean rounded) {
        userRepository.getImage(imageView, rounded);
    }
}