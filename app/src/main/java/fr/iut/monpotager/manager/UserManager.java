package fr.iut.monpotager.manager;

import com.google.firebase.auth.FirebaseUser;

import fr.iut.monpotager.repository.UserRepository;

public class UserManager {

    private static volatile UserManager instance;
    private UserRepository userRepository;

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


    public Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() != null);
    }

    public void signOut() {
        userRepository.signOut();
    }


}