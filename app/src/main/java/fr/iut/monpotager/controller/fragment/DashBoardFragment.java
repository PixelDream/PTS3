package fr.iut.monpotager.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import fr.iut.monpotager.R;

public class DashBoardFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup)  inflater.inflate(R.layout.dashboard_fragment, container, false);
        return root;
    }
}
