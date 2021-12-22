package fr.iut.monpotager.controller.fragment.garden;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.plant.PlantFragment;
import fr.iut.monpotager.controller.fragment.search.adapter.CustomVegetableListAdapter;
import fr.iut.monpotager.model.Vegetable;

public class GardenFragment extends Fragment {


    private ViewGroup root;

    /**
     * Create app
     *
     * @param savedInstanceState
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.garden_page, container, false);
        return root;
    }



}
