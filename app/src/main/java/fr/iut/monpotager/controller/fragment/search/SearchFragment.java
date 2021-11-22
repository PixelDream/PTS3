package fr.iut.monpotager.controller.fragment.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.plant.PlantFragment;
import fr.iut.monpotager.controller.fragment.search.adapter.CustomVegetableListAdapter;
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

        //TODO: Migrer la partie du code suivante dans un PlantRepository comme pour UserRepository

        root = (ViewGroup) inflater.inflate(R.layout.search_page, container, false);
        vegetableListFirebase = new ArrayList();


        mDatabase = FirebaseFirestore.getInstance();


        vegetables = mDatabase.collection("vegetables");

        vegetables.get().addOnSuccessListener(queryDocumentSnapshots -> {

            for (DocumentSnapshot document : queryDocumentSnapshots) {
                try {
                    Vegetable v = new Vegetable();
                    v.setName(document.get("name").toString());
                    v.setDuration(Integer.parseInt(document.get("duration").toString()));
                    v.setPicture(document.get("picture").toString());
                    v.setTemperature(document.get("temperature").toString());
                    v.setSunshine(Integer.parseInt(document.get("sunshine").toString()));
                    v.setAdviseMaintenance((List<String>) document.get("advise_maintenance"));
                    v.setAdviseRecolt((List<String>) document.get("advise_recolt"));
                    v.setHarvestMonth((List<Long>) document.get("harvest_month"));
                    v.setPerpetual(Boolean.parseBoolean(document.get("perpetual").toString()));
                    v.setPlantingMonth((List<Long>) document.get("planting_month"));
                    v.setSowingMonth((List<Long>) document.get("sowing_month"));
                    v.setWater(Integer.parseInt(document.get("water").toString()));
                    v.setWeather(document.get("weather").toString());
                    vegetableListFirebase.add(v);
                } catch (Exception e) {
                    Log.e("load-vege-search", e.getMessage());
                }
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
            public void afterTextChanged(Editable editable) {
            }
        });
    }


}
