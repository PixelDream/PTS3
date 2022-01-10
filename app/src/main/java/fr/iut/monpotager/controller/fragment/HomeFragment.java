package fr.iut.monpotager.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.search.SearchFragment;
import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.TipManager;
import fr.iut.monpotager.manager.UserManager;
import fr.iut.monpotager.model.Tip;

public class HomeFragment extends Fragment {
    private final UserManager userManager = UserManager.getInstance();
    private final TipManager tipManager = TipManager.getInstance();
//    LinearLayout seasonPlant;
    EditText searchBar;
    TextView helloUser, titleTip, textTip;
//    private final VegetableManager vegetableManager = VegetableManager.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);

        searchBar = root.findViewById(R.id.searchBar);
        helloUser = root.findViewById(R.id.helloUser);
        titleTip = root.findViewById(R.id.titleTip);
        textTip = root.findViewById(R.id.textTip);
//        seasonPlant = root.findViewById(R.id.seasonPlant);

        helloUser.setText("Bonjour " + userManager.getFirstName());

        searchBar.setOnFocusChangeListener((view, focus) -> {
            if (focus) {
                SearchFragment searchFragment = new SearchFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
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
            }
        });

/*        vegetableManager.getVegetables(new Callback() {
            @Override
            public void onSuccessResult(Object result) {
                Vegetable vegetable = (Vegetable) result;
                Calendar calendar = Calendar.getInstance();

                if (vegetable.getAdviseRecolt().contains(calendar.get(Calendar.MONTH))) {
                    ImageView plantImage = new ImageView(getContext());
                    Picasso.get().load(vegetable.getPicture()).transform(new RoundedCornersTransformation(128, 5)).resize(1024, 1024).centerCrop().into(plantImage);
                }
            }

            @Override
            public void onErrorResult(Exception e) {}
        });*/


        return root;
    }
}
