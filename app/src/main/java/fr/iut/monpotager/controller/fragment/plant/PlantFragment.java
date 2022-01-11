package fr.iut.monpotager.controller.fragment.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.search.adapter.RoundedCornersTransformation;
import fr.iut.monpotager.model.Vegetable;

public class PlantFragment extends Fragment {
    private static final String DESCRIBABLE_KEY = "vegetable";
    private List<Button> btnList;

    private Button addToGarden;
    private LinearLayout advise_maintenance, advise_recolt;

    public static PlantFragment newInstance(Vegetable vegetable) {
        PlantFragment fragment = new PlantFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, vegetable);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_plant, container, false);


        Vegetable vegetable = (Vegetable) getArguments().getSerializable(DESCRIBABLE_KEY);

        insertPlantSpec(root, vegetable);

        final Button infoBtn = root.findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(v -> changePlantView(root, 0, infoBtn));

        final Button entertainBtn = root.findViewById(R.id.entertainBtn);
        entertainBtn.setOnClickListener(v -> changePlantView(root, 1, entertainBtn));

        final Button harvestBtn = root.findViewById(R.id.harvestBtn);
        harvestBtn.setOnClickListener(v -> changePlantView(root, 2, harvestBtn));

        btnList = new ArrayList(Arrays.asList(infoBtn, entertainBtn, harvestBtn));

        ImageView plantImage = root.findViewById(R.id.plantImage);
        Picasso.get().load(vegetable.getPicture()).transform(new RoundedCornersTransformation(128, 5)).resize(2048, 2048).centerCrop().into(plantImage);

        addToGarden = root.findViewById(R.id.addToGarden);
        addToGarden.setOnClickListener(v -> addToGarden(vegetable));

        advise_maintenance = root.findViewById(R.id.advise_maintenance);
        adviseMaintenance(vegetable);

        advise_recolt = root.findViewById(R.id.advise_recolt);
        adviseRecolt(vegetable);

        updateGraph(root.findViewById(R.id.firstLine), root.findViewById(R.id.secondeLine), root.findViewById(R.id.thirdLine), vegetable);

        return root;
    }

    public void changePlantView(ViewGroup container, int viewNb, Button btn) {
        ViewFlipper vf = container.findViewById(R.id.vf);
        vf.setDisplayedChild(viewNb);
        btn.setClickable(false);
        btn.setBackgroundResource(R.drawable.input);
        for (Button o : btnList) {
            if (!o.equals(btn)) {
                o.setClickable(true);
                o.setBackgroundResource(R.drawable.input_light);
            }
        }
    }

    public void updateGraph(ImageView firstLine, ImageView secondLine, ImageView thirdLine, Vegetable vegetable) {

        // Firstline of the graph => Sowing Month

        int mSize = vegetable.getSowingMonth().size();
        int firstM = vegetable.getSowingMonth().get(0).intValue();

        TableRow.LayoutParams params = (TableRow.LayoutParams) firstLine.getLayoutParams();
        params.span = (firstM == -1) ? 0 : mSize;
        params.column = firstM - 1;
        firstLine.setLayoutParams(params);

        // Seconde line of the graph => Planting Month

        mSize = vegetable.getPlantingMonth().size();
        firstM = vegetable.getPlantingMonth().get(0).intValue();

        params = (TableRow.LayoutParams) secondLine.getLayoutParams();
        params.span = (firstM == -1) ? 0 : mSize;
        params.column = firstM - 1;
        secondLine.setLayoutParams(params);

        // Third line of the graph => Harvest Month

        mSize = vegetable.getHarvestMonth().size();
        firstM = vegetable.getHarvestMonth().get(0).intValue();

        params = (TableRow.LayoutParams) thirdLine.getLayoutParams();
        params.span = (firstM == -1) ? 0 : mSize;
        params.column = firstM - 1;
        thirdLine.setLayoutParams(params);

    }

    private void addToGarden(Vegetable vegetable) {
        DialogFragment dialogFragment = new AddToGardenDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("vegetable", vegetable);

        dialogFragment.setArguments(bundle);
        dialogFragment.show(getParentFragmentManager(), "addToGarden");
    }

    public void insertPlantSpec(ViewGroup container, Vegetable vegetable) {
        final TextView plantName = container.findViewById(R.id.plantName);
        plantName.setText(vegetable.getName());

        final TextView humidity = container.findViewById(R.id.humidity);
        humidity.setText(vegetable.getWater() + "%");

        final TextView sun = container.findViewById(R.id.sunText);
        sun.setText(Integer.toString(vegetable.getSunshine()));

        final TextView lifeTime = container.findViewById(R.id.lifeTime);
        lifeTime.setText(Math.round(vegetable.getDuration() / 7) + " semaines");

        final TextView climate = container.findViewById(R.id.climateText);
        climate.setText(vegetable.getTemperature() + "°C");
    }

    private void adviseMaintenance(Vegetable vegetable) {
        for (String advise : vegetable.getAdviseMaintenance()) {
            TextView textView = new TextView(getContext());
            textView.setText(advise);
            textView.setPadding(0, 0, 0, 25);
            advise_maintenance.addView(textView);
        }
    }

    private void adviseRecolt(Vegetable vegetable) {
        for (String advise : vegetable.getAdviseRecolt()) {
            TextView textView = new TextView(getContext());
            textView.setText(advise);
            textView.setPadding(0, 0, 0, 25);
            advise_recolt.addView(textView);
        }
    }
}
