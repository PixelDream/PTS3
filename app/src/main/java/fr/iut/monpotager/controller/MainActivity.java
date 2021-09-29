package fr.iut.monpotager.controller;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.DashBoardFragment;
import fr.iut.monpotager.controller.sidemenu.DrawerAdapter;
import fr.iut.monpotager.controller.sidemenu.DrawerItem;
import fr.iut.monpotager.controller.sidemenu.SimpleItem;
import fr.iut.monpotager.controller.sidemenu.SpaceItem;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {


    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_RES = 3;
    private static final int POS_SETTINGS = 4;
    private static final int POS_ABOUT_US = 5;
    private static final int POS_LOGOUT = 7;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBY_RES),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_DASHBOARD) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        }

        else if (position == POS_MY_PROFILE) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        }

        else if (position == POS_NEARBY_RES) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        }

        else if (position == POS_SETTINGS) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        }

        else if (position == POS_ABOUT_US) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        }

        else if (position == POS_LOGOUT) {
            finish();
        }


        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.purple_200))
                .withTextTint(color(R.color.purple_500))
                .withSelectedIconTint(color(R.color.purple_700))
                .withSelectedTextTint(color(R.color.purple_700));
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