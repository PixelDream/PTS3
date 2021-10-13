package fr.iut.monpotager.controller;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.model.Period;
import fr.iut.monpotager.model.Plant;

public class PlantSpecActivity extends AppCompatActivity {

    private List<Button> btnList;

    /**
     * Create app
     * @param savedInstanceState
     */

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_spec);

        Bundle b = getIntent().getExtras();

        Plant p = b.getParcelable("plant");
        insertPlantSpec(p);

        final Button infoBtn = findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(v -> changePlantView(0, infoBtn));

        final Button entertainBtn = findViewById(R.id.entertainBtn);
        entertainBtn.setOnClickListener(v -> changePlantView(1, entertainBtn));

        final Button harvestBtn = findViewById(R.id.harvestBtn);
        harvestBtn.setOnClickListener(v -> changePlantView(2, harvestBtn));

        btnList = new ArrayList(Arrays.asList(infoBtn, entertainBtn, harvestBtn));

        updateGraph(findViewById(R.id.calendarGraphImg1), Period.AOUT);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changePlantView(int viewNb, Button btn){
        ViewFlipper vf = findViewById(R.id.vf);
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

    public void insertPlantSpec(Plant p){
        final TextView plantName = findViewById(R.id.plantName);
        plantName.setText(p.getName());
        final TextView humidity = findViewById(R.id.humidity);
        humidity.setText(p.getWaterNeed() +"%");
        final TextView sun = findViewById(R.id.sunText);
        sun.setText(p.getSunNeed() +"%");
        final TextView lifeTime = findViewById(R.id.lifeTime);
        lifeTime.setText(p.getLifeTime()+" semaines");
        final TextView climate = findViewById(R.id.climateText);
        climate.setText(p.getIdealTemp()+"°C");
    }

    public void updateGraph(ImageView g, Period p){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) g.getLayoutParams();
        params.setMargins(60,0,60,0);
    }

}
