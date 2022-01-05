package fr.iut.monpotager.controller.fragment.search;

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

import java.util.ArrayList;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.plant.PlantFragment;
import fr.iut.monpotager.controller.fragment.search.adapter.CustomVegetableListAdapter;
import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.VegetableManager;
import fr.iut.monpotager.model.Vegetable;

public class SearchFragment extends Fragment {

    private final VegetableManager vegetableManager = VegetableManager.getInstance();

    private ArrayList origList;
    private ArrayList<Vegetable> vegetableListFirebase;
    private EditText searchVegetable;
    private ListView listView;
    private CustomVegetableListAdapter adapter;
    private ViewGroup root;

    /**
     * Create app
     *
     * @param savedInstanceState
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vegetableListFirebase = new ArrayList<>();

        root = (ViewGroup) inflater.inflate(R.layout.search_page, container, false);

        vegetableManager.getVegetables(new Callback() {
            @Override
            public void onSuccessResult(Object result) {
                vegetableListFirebase.addAll((List<Vegetable>) result);
                onSuccessData();
            }

            @Override
            public void onErrorResult(Exception e) {
                // Todo: Manage Errors
            }
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
