package fr.iut.monpotager.controller;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.WindowManager;
import android.widget.EditText;
=======
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
<<<<<<< HEAD

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.auth.LoginActivity;
import fr.iut.monpotager.controller.fragment.HomeFragment;
import fr.iut.monpotager.controller.fragment.search.SearchFragment;
import fr.iut.monpotager.controller.sidemenu.DrawerAdapter;
import fr.iut.monpotager.controller.sidemenu.DrawerItem;
import fr.iut.monpotager.controller.sidemenu.SimpleItem;
import fr.iut.monpotager.controller.sidemenu.SpaceItem;
import fr.iut.monpotager.manager.UserManager;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private UserManager userManager = UserManager.getInstance();

    /*private static final int POS_CLOSE = 0;*/
    private static final int POS_PERSO = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_RES = 3;
    private static final int POS_SETTINGS = 4;
    private static final int POS_LOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
=======

import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.auth.LoginActivity;
import fr.iut.monpotager.controller.fragment.DashBoardFragment;
import fr.iut.monpotager.controller.sidemenu.DrawerAdapter;
import fr.iut.monpotager.controller.sidemenu.DrawerItem;
import fr.iut.monpotager.controller.sidemenu.SimpleItem;
import fr.iut.monpotager.controller.sidemenu.SpaceItem;
import fr.iut.monpotager.manager.UserManager;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    /*private static final int POS_CLOSE = 0;*/
    //private static final int POS_PERSO = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_RES = 3;
    private static final int POS_SETTINGS = 4;
    private static final int POS_LOGOUT = 6;
    boolean inDrag;
    TextView profil;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

    private final UserManager userManager = UserManager.getInstance();
    private FirebaseAuth mAuth;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slidingRootNav;
    /*
    @Override
<<<<<<< HEAD
=======
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (slidingRootNav != null && slidingRootNav.isMenuOpened()) {
            boolean menuTouched = findViewById(R.id.container).dispatchTouchEvent(ev) ;
            if(menuTouched) {
                slidingRootNav.closeMenu();
            }
            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }
    */
    @Override
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!userManager.isCurrentUserLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

<<<<<<< HEAD
=======
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

=======
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
<<<<<<< HEAD
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
=======
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .addDragStateListener(new DragStateListener() {
                    @Override
                    public void onDragStart() {
                        inDrag = true;
                    }

                    @Override
                    public void onDragEnd(boolean isMenuOpened) {
                        inDrag = false;
                    }
                })
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
<<<<<<< HEAD
                createItemFor(POS_PERSO),
=======
                new SpaceItem(100),
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBY_RES),
                createItemFor(POS_SETTINGS),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

<<<<<<< HEAD
        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
=======

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
<<<<<<< HEAD
=======

        FrameLayout container = findViewById(R.id.container);
        container.setOnClickListener(view -> slidingRootNav.closeMenu());

        ImageView image = findViewById(R.id.icon);
        Log.d("+++++", userManager.getCurrentUser().getPhotoUrl().toString());
        //image.setImageDrawable();
        TextView profil = findViewById(R.id.nameUser);
        profil.setText(userManager.getCurrentUser().getDisplayName());

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_DASHBOARD) {
<<<<<<< HEAD
            HomeFragment homeFragment = new HomeFragment();
            transaction.replace(R.id.container, homeFragment);
        } else if (position == POS_MY_PROFILE) {

        } else if (position == POS_NEARBY_RES) {

        } else if (position == POS_SETTINGS) {

=======
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        } else if (position == POS_MY_PROFILE) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        } else if (position == POS_NEARBY_RES) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
        } else if (position == POS_SETTINGS) {
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container, dashBoardFragment);
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        } else if (position == POS_LOGOUT) {
            userManager.signOut();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

<<<<<<< HEAD
=======

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

<<<<<<< HEAD
=======
    @SuppressWarnings("rawtypes")
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
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