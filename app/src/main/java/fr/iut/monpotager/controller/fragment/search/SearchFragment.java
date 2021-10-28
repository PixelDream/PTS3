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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.MainActivity;
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
     * @param savedInstanceState
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.search_page, container, false);
        vegetableListFirebase = new ArrayList();


        mDatabase = FirebaseFirestore.getInstance();



        vegetables = mDatabase.collection("vegetables");

        vegetables.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        String name = document.get("name").toString();
                        int duration = Integer.parseInt(document.get("duration").toString());
                        String picture = document.get("picture").toString();
                        vegetableListFirebase.add(new Vegetable(name, duration, picture));
                    }

                    onSuccessData();

            }
        });


        return root;
    }

    public void onSuccessData(){
        origList = (ArrayList) vegetableListFirebase.clone();

        adapter = new CustomVegetableListAdapter(getContext(), vegetableListFirebase);
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
    }


}
