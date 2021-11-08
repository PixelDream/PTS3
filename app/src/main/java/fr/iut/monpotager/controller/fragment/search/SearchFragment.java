package fr.iut.monpotager.controller.fragment.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.HomeFragment;
import fr.iut.monpotager.controller.fragment.plant.PlantFragment;
import fr.iut.monpotager.controller.fragment.search.adapter.CustomVegetableListAdapter;
import fr.iut.monpotager.model.Period;
import fr.iut.monpotager.model.Plant;
import fr.iut.monpotager.model.Season;
import fr.iut.monpotager.model.Species;
import fr.iut.monpotager.model.Vegetable;

public class SearchFragment extends Fragment {


    private ArrayList origList;
    private ArrayList<Vegetable> vegetableListFirebase;
    private CollectionReference vegetables;
    private EditText searchVegetable;
    private ListView listView;
    private FirebaseFirestore mDatabase;
    private CustomVegetableListAdapter adapter;
    private ViewGroup root;

    /**
     * Create app
     *
     * @param savedInstanceState
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.search_page, container, false);
        vegetableListFirebase = new ArrayList();


        mDatabase = FirebaseFirestore.getInstance();


        vegetables = mDatabase.collection("vegetables");

        vegetables.get().addOnSuccessListener(queryDocumentSnapshots -> {

            for (DocumentSnapshot document : queryDocumentSnapshots) {
                String name = document.get("name").toString();
                int duration = Integer.parseInt(document.get("duration").toString());
                String picture = document.get("picture").toString();
                String temperature = document.get("temperature").toString();

                vegetableListFirebase.add(new Vegetable(name, duration, picture, temperature));
            }

            onSuccessData();
        });


        return root;
    }

    public void onSuccessData() {
        origList = (ArrayList) vegetableListFirebase.clone();

        adapter = new CustomVegetableListAdapter(getContext(), vegetableListFirebase);
        searchVegetable = root.findViewById(R.id.searchVegetable);
        listView = root.findViewById(R.id.search_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Vegetable vegetable = (Vegetable) parent.getItemAtPosition(position);

            searchVegetable.clearFocus();

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            PlantFragment plantFragment = PlantFragment.newInstance(vegetable);

            transaction.replace(R.id.container, plantFragment);
            transaction.commit();
        });


        searchVegetable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.setListData(origList);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }


}
