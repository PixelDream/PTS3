package fr.iut.monpotager.manager;

import com.google.firebase.auth.FirebaseUser;

import fr.iut.monpotager.repository.UserRepository;

public class UserManager {

    private static volatile UserManager instance;
<<<<<<< HEAD
    private UserRepository userRepository;
=======
    private final UserRepository userRepository;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

    private UserManager() {
        userRepository = UserRepository.getInstance();
    }

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result != null) {
            return result;
        }
<<<<<<< HEAD
        synchronized(UserRepository.class) {
=======
        synchronized (UserRepository.class) {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
            if (instance == null) {
                instance = new UserManager();
            }
            return instance;
        }
    }

    public FirebaseUser getCurrentUser() {
        return userRepository.getCurrentUser();
    }

<<<<<<< HEAD
    public String getFirstName() {
        String firstName = userRepository.getCurrentUser().getDisplayName().split(" ")[0];
        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }


    public Boolean isCurrentUserLogged(){
=======
    public Boolean isCurrentUserLogged() {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        return (this.getCurrentUser() != null);
    }

    public void signOut() {
        userRepository.signOut();
    }


}