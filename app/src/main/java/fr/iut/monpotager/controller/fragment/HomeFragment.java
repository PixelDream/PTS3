package fr.iut.monpotager.controller.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.plant.PlantFragment;
import fr.iut.monpotager.controller.fragment.search.SearchFragment;
import fr.iut.monpotager.controller.fragment.search.adapter.RoundedCornersTransformation;
import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.TipManager;
import fr.iut.monpotager.manager.UserManager;
import fr.iut.monpotager.manager.VegetableManager;
import fr.iut.monpotager.model.Tip;
import fr.iut.monpotager.model.Vegetable;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private final UserManager userManager = UserManager.getInstance();
    private final TipManager tipManager = TipManager.getInstance();
    private final VegetableManager vegetableManager = VegetableManager.getInstance();
    LinearLayout seasonPlant, seasonPanel, recentSearch;
    EditText searchBar;
    TextView helloUser, titleTip, textTip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);

        searchBar = root.findViewById(R.id.searchBar);
        helloUser = root.findViewById(R.id.helloUser);
        titleTip = root.findViewById(R.id.titleTip);
        textTip = root.findViewById(R.id.textTip);

        seasonPlant = root.findViewById(R.id.seasonPlant);
        seasonPanel = root.findViewById(R.id.seasonPanel);
        recentSearch = root.findViewById(R.id.recentSearch);

        helloUser.setText("Bonjour " + userManager.getFirstName());

        searchBar.setOnFocusChangeListener((view, focus) -> {
            if (focus) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                SearchFragment searchFragment = new SearchFragment();
                transaction.replace(R.id.container, searchFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tipManager.getRandomTip(new Callback() {
            @Override
            public void onSuccessResult(Object result) {
                Tip tip = (Tip) result;

                textTip.setText(tip.getTip());
                titleTip.setText(tip.getTitle());
            }

            @Override
            public void onErrorResult(Exception e) {
                Log.e(TAG, "Une erreur s'est produite");
            }
        });

        vegetableManager.getVegetables(new Callback() {
            @Override
            public void onSuccessResult(Object result) {
                List<Vegetable> vegetableList = (List<Vegetable>) result;
                Calendar calendar = Calendar.getInstance();

                for (Vegetable vegetable : vegetableList) {

                    if (vegetable.getHarvestMonth().contains(calendar.get(Calendar.MONTH) + 1L)) {
                        ImageView plantImage = new ImageView(getContext());
                        plantImage.setPadding(0, 0, 35, 0);
                        Picasso.get().load(vegetable.getPicture()).transform(new RoundedCornersTransformation(60, 5)).resize(350, 350).centerCrop().into(plantImage);
                        seasonPlant.addView(plantImage);

                        seasonPlant.setOnClickListener(v -> goToVegetable(vegetable));
                    }

                    // Search History
                    SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);

                    if (settings.getAll().containsKey(vegetable.getId())) {
                        View child = getLayoutInflater().inflate(R.layout.recent_search_item, null);

                        ImageView searchImage = child.findViewById(R.id.searchImage);
                        Picasso.get().load(vegetable.getPicture()).transform(new RoundedCornersTransformation(10, 5)).resize(60, 60).centerCrop().into(searchImage);

                        TextView searchText = child.findViewById(R.id.searchText);
                        searchText.setText(vegetable.getName());

                        child.setOnClickListener(v -> goToVegetable(vegetable));

                        recentSearch.addView(child);
                    }
                }

                if (seasonPlant.getChildCount() > 0) seasonPanel.setVisibility(View.VISIBLE);
            }

            @Override
            public void onErrorResult(Exception e) {
                Log.e(TAG, "Une erreur s'est produite");
            }
        });

        return root;
    }

    private void goToVegetable(Vegetable vegetable) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        PlantFragment plantFragment = PlantFragment.newInstance(vegetable);

        transaction.replace(R.id.container, plantFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
