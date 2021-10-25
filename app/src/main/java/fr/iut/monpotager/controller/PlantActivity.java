package fr.iut.monpotager.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.iut.monpotager.R;
import fr.iut.monpotager.model.Period;
import fr.iut.monpotager.model.Plant;
import fr.iut.monpotager.model.Season;
import fr.iut.monpotager.model.Species;

public class PlantActivity extends AppCompatActivity {

    /**
     * Create app
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        final Button tomate = findViewById(R.id.tomate);
        tomate.setOnClickListener(v -> {
            final Plant plant = new Plant("Tomate",45, Period.FEVRIER, Period.MARS, 15, 31, 14, "Il faut faire attention aux larves.", Season.HIVER, Period.FEVRIER, Species.ESPECE1, 24);
            changeView(plant);
        });
        final Button melon = findViewById(R.id.melon);
        melon.setOnClickListener(v -> {
            final Plant plant = new Plant("Melon",37, Period.JANVIER, Period.FEVRIER, 12, 39, 10, "Arroser généreusement.", Season.HIVER, Period.JANVIER, Species.ESPECE2, 30);
            changeView(plant);
        });
        final Button pasteque = findViewById(R.id.pasteque);
        pasteque.setOnClickListener(v -> {
            final Plant plant = new Plant("Pasteque",71, Period.JUILLET, Period.AOUT, 24, 70, 11, "Ne pas exposer en plein soleil.", Season.ETE, Period.AOUT, Species.ESPECE3, 18);
            changeView(plant);
        });
        final Button cornichon = findViewById(R.id.cornichon);
        cornichon.setOnClickListener(v -> {
            final Plant plant = new Plant("Cornichon",55, Period.MAI, Period.JUIN, 21, 63, 12, "Couper les feuilles dès qu'elles jaunissent.", Season.PRINTEMPS, Period.MAI, Species.ESPECE2, 22);
            changeView(plant);
        });
    }
    private void changeView(Plant plant) {
        Intent intent = new Intent(this, PlantSpecActivity.class);
        Bundle b = new Bundle();
        b.putParcelable("plant", plant);
        intent.putExtras(b);
        startActivity(intent);
    }
}