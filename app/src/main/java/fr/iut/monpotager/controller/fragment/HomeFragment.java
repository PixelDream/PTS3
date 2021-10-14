package fr.iut.monpotager.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.search.SearchFragment;
import fr.iut.monpotager.manager.UserManager;

public class HomeFragment extends Fragment {
    private UserManager userManager = UserManager.getInstance();

    EditText searchBar;
    TextView helloUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);

        searchBar = root.findViewById(R.id.searchBar);
        helloUser = root.findViewById(R.id.helloUser);

        helloUser.setText("Bonjour " + userManager.getFirstName());

        searchBar.setOnFocusChangeListener((view, focus) -> {
            if (focus) {
                SearchFragment searchFragment = new SearchFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
            }
        });

        return root;
    }
}
