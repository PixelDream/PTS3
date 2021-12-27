package fr.iut.monpotager.controller.fragment.garden;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.common.collect.Lists;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.garden.adapter.CustomGardenListAdapter;
import fr.iut.monpotager.controller.fragment.plant.PlantFragment;
import fr.iut.monpotager.controller.fragment.search.adapter.CustomVegetableListAdapter;
import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.GardenManager;
import fr.iut.monpotager.model.Garden;
import fr.iut.monpotager.model.Vegetable;

public class GardenFragment extends Fragment {

    private static final String TAG = "GardenFragment";
    private GardenManager gardenManager;

    private ArrayList origList;
    private ArrayList<Garden> vegetableListFirebase;
    private ListView listView;
    private CustomGardenListAdapter adapter;
    private ViewGroup root;

    /**
     * Create app
     *
     * @param savedInstanceState
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gardenManager = GardenManager.getInstance();
        vegetableListFirebase = new ArrayList();

        root = (ViewGroup) inflater.inflate(R.layout.garden_page, container, false);
        listView = root.findViewById(R.id.search_list);

        adapter = new CustomGardenListAdapter(getContext(), vegetableListFirebase);
        listView.setEmptyView(root.findViewById(R.id.search_list_no_item));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Vegetable vegetable = (Vegetable) parent.getItemAtPosition(position);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            PlantFragment plantFragment = PlantFragment.newInstance(vegetable);

            transaction.replace(R.id.container, plantFragment);
            transaction.commit();
        });


        gardenManager.getCurrentGarden(new Callback() {
            @Override
            public void onSuccessResult(Object garden) {
                vegetableListFirebase.add((Garden) garden);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorResult(Exception e) {
                Log.e(TAG, "Une erreur s'est produite");
            }
        });


        //origList = (ArrayList) vegetableListFirebase.clone();

        return root;
    }
}
