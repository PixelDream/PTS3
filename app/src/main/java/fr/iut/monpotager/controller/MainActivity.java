package fr.iut.monpotager.controller;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.auth.LoginActivity;
import fr.iut.monpotager.controller.fragment.HomeFragment;
import fr.iut.monpotager.controller.fragment.SettingFragment;
import fr.iut.monpotager.controller.fragment.garden.GardenFragment;
import fr.iut.monpotager.controller.sidemenu.DrawerAdapter;
import fr.iut.monpotager.controller.sidemenu.DrawerItem;
import fr.iut.monpotager.controller.sidemenu.SimpleItem;
import fr.iut.monpotager.controller.sidemenu.SpaceItem;
import fr.iut.monpotager.manager.UserManager;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private static final int POS_PERSO = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_GARDEN = 2;
    private static final int POS_MY_PROFILE = 3;
    private static final int POS_LOGOUT = 5;
    private final UserManager userManager = UserManager.getInstance();
    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!userManager.isCurrentUserLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_PERSO),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_GARDEN),
                createItemFor(POS_MY_PROFILE),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);

        if (userManager.isCurrentUserLogged()) {
            TextView profile = findViewById(R.id.nameUser);
            profile.setText(userManager.getCurrentUser().getDisplayName());

            ImageView aImageprofie = findViewById(R.id.avatar);
            userManager.imageProfileIntoImage(aImageprofie, true);
        }
    }


    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_DASHBOARD) {
            HomeFragment homeFragment = new HomeFragment();
            transaction.replace(R.id.container, homeFragment);
        } else if (position == POS_GARDEN) {
            GardenFragment gardenFragment = new GardenFragment();
            transaction.replace(R.id.container, gardenFragment);
        } else if (position == POS_MY_PROFILE) {
            SettingFragment settingFragment = new SettingFragment();
            transaction.replace(R.id.container, settingFragment);
        } else if (position == POS_LOGOUT) {
            userManager.signOut();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }


        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withTextTint(color(R.color.black))
                .withSelectedTextTint(color(R.color.green))
                .withSelectedTextBold();
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcon);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}