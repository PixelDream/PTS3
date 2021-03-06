package fr.iut.monpotager.controller.fragment.garden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.search.adapter.RoundedCornersTransformation;
import fr.iut.monpotager.manager.GardenManager;
import fr.iut.monpotager.model.Garden;

public class CharacteristicFragment extends Fragment {

    private static final String TAG = "Fragment";
    private static final String DESCRIBABLE_KEY = "Garden";
    private GardenManager gardenManager;

    private ViewGroup root;
    private TextView plantName, gardenDate, gardenQuantity;
    private ImageView plantImage;

    public static CharacteristicFragment newInstance(Garden garden) {
        CharacteristicFragment fragment = new CharacteristicFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, garden);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gardenManager = GardenManager.getInstance();

        root = (ViewGroup) inflater.inflate(R.layout.caracteristics_plant, container, false);


        Garden garden = (Garden) getArguments().getSerializable(DESCRIBABLE_KEY);

        plantName = root.findViewById(R.id.plantName);
        plantName.setText(garden.getVegetable().getName());

        plantImage = root.findViewById(R.id.plantImage);
        Picasso.get().load(garden.getVegetable().getPicture()).transform(new RoundedCornersTransformation(128, 5)).resize(2048, 2048).centerCrop().into(plantImage);

        gardenDate = root.findViewById(R.id.gardenDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy");
        gardenDate.setText("Plant?? le " + simpleDateFormat.format(garden.getDate()));

        gardenQuantity = root.findViewById(R.id.gardenQuantity);
        gardenQuantity.setText(garden.getQuantity() + " " + garden.getUnit());


        return root;
    }

}
