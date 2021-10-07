package fr.iut.monpotager.controller;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.adapter.CustomVegetableListAdapter;

public class SearchActivity extends AppCompatActivity {


    private ArrayList origList;
    private EditText searchVegetable;
    private ListView listView;
    /**
     * Create app
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        Vegetable vegetable1 = new Vegetable("Tomate",5);
        Vegetable vegetable2 = new Vegetable("Poire",5);
        Vegetable vegetable3 = new Vegetable("Pomme",5);
        Vegetable vegetable4 = new Vegetable("Cerise",5);
        ArrayList<Vegetable> vegetableArrayList = new ArrayList<>();
        vegetableArrayList.add(vegetable1);
        vegetableArrayList.add(vegetable2);
        vegetableArrayList.add(vegetable3);
        vegetableArrayList.add(vegetable4);

        origList = (ArrayList) vegetableArrayList.clone();

        CustomVegetableListAdapter adapter = new CustomVegetableListAdapter(this, vegetableArrayList);
        searchVegetable = (EditText) findViewById(R.id.searchVegetable);
        listView = (ListView) findViewById(R.id.search_list);
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
