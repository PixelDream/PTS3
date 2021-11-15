package fr.iut.monpotager.controller.fragment.plant;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.search.SearchFragment;
import fr.iut.monpotager.manager.UserManager;
import fr.iut.monpotager.model.Period;
import fr.iut.monpotager.model.Plant;
import fr.iut.monpotager.model.Vegetable;

public class PlantFragment extends Fragment {
    private static final String DESCRIBABLE_KEY = "vegetable";
    private List<Button> btnList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_plant, container, false);


        Vegetable vegetable = (Vegetable) getArguments().getSerializable(DESCRIBABLE_KEY);

        insertPlantSpec(root, vegetable);

        final Button infoBtn = root.findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(v -> changePlantView(root,0, infoBtn));

        final Button entertainBtn = root.findViewById(R.id.entertainBtn);
        entertainBtn.setOnClickListener(v -> changePlantView(root,1, entertainBtn));

        final Button harvestBtn = root.findViewById(R.id.harvestBtn);
        harvestBtn.setOnClickListener(v -> changePlantView(root,2, harvestBtn));

        btnList = new ArrayList(Arrays.asList(infoBtn, entertainBtn, harvestBtn));

        ImageView plantImage = root.findViewById(R.id.plantImage);
        Picasso.get().load(vegetable.getPicture()).into(plantImage);

        return root;
    }

    public static PlantFragment newInstance(Vegetable vegetable) {
        PlantFragment fragment = new PlantFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, vegetable);
        fragment.setArguments(bundle);

        return fragment;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changePlantView(ViewGroup container, int viewNb, Button btn){
        ViewFlipper vf = container.findViewById(R.id.vf);
        vf.setDisplayedChild(viewNb);
        btn.setClickable(false);
        btn.setBackgroundResource(R.drawable.input);
        for (Button o : btnList) {
            if(!o.equals(btn)){
                o.setClickable(true);
                o.setBackgroundResource(R.drawable.input_light);
            }
        }
    }

    public void insertPlantSpec(ViewGroup container, Vegetable vegetable){
        final TextView plantName = container.findViewById(R.id.plantName);
        plantName.setText(vegetable.getName());
        final TextView humidity = container.findViewById(R.id.humidity);
        humidity.setText(vegetable.getWater() +"%");
        final TextView sun = container.findViewById(R.id.sunText);
        sun.setText(vegetable.getSunshine());
        final TextView lifeTime = container.findViewById(R.id.lifeTime);
        lifeTime.setText(Math.round(vegetable.getDuration() / 7) + " semaines");
        final TextView climate = container.findViewById(R.id.climateText);
        climate.setText(vegetable.getTemperature() + "°C");
    }

    public void updateGraph(ImageView g, Period p){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) g.getLayoutParams();
        params.setMargins(60,0,60,0);
    }
}
