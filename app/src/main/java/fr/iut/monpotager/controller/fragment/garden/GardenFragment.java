package fr.iut.monpotager.controller.fragment.garden;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.garden.adapter.CustomGardenListAdapter;
import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.GardenManager;
import fr.iut.monpotager.model.Garden;

public class GardenFragment extends Fragment {

    private static final String TAG = "GardenFragment";
    private GardenManager gardenManager;

    private ArrayList<Garden> vegetableListFirebase;
    private ListView listView;
    private CustomGardenListAdapter adapter;
    private ViewGroup root;


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
            Garden garden = (Garden) parent.getItemAtPosition(position);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            CharacteristicFragment characteristicFragment = CharacteristicFragment.newInstance(garden);

            transaction.replace(R.id.container, characteristicFragment);
            transaction.addToBackStack(null);
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


        return root;
    }
}
