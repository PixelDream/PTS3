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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.search.adapter.CustomVegetableListAdapter;
import fr.iut.monpotager.model.Vegetable;

public class SearchFragment extends Fragment {


    private ArrayList origList;
    private EditText searchVegetable;
    private ListView listView;
    private FirebaseFirestore mDatabase;

    /**
     * Create app
     * @param savedInstanceState
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.search_page, container, false);
        mDatabase = FirebaseFirestore.getInstance();

       CollectionReference vegetables =      mDatabase.collection("vegetables");

        Vegetable vegetable1 = new Vegetable("Tomate",5,"https://img.passeportsante.net/1000x526/2021-05-03/i102192-tomate-nu.jpg");
        Vegetable vegetable2 = new Vegetable("Poire",5,"https://img.passeportsante.net/1000x526/2021-05-03/i102192-tomate-nu.jpg");
        Vegetable vegetable3 = new Vegetable("Pomme",5,"https://img.passeportsante.net/1000x526/2021-05-03/i102192-tomate-nu.jpg");
        Vegetable vegetable4 = new Vegetable("Cerise",5,"https://img.passeportsante.net/1000x526/2021-05-03/i102192-tomate-nu.jpg");
        ArrayList<Vegetable> vegetableArrayList = new ArrayList<>();
        vegetableArrayList.add(vegetable1);
        vegetableArrayList.add(vegetable2);
        vegetableArrayList.add(vegetable3);
        vegetableArrayList.add(vegetable4);

        origList = (ArrayList) vegetableArrayList.clone();

        CustomVegetableListAdapter adapter = new CustomVegetableListAdapter(getContext(), vegetableArrayList);
        searchVegetable = root.findViewById(R.id.searchVegetable);
        listView = root.findViewById(R.id.search_list);
        listView.setAdapter(adapter);


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


        return root;
    }


}
