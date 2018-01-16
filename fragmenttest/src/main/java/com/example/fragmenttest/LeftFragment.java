package com.example.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by john on 2017/12/19.
 */

public class LeftFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment,container,false);
        return view;


    }

//    public void i(){
//        MainActivity activity = (MainActivity) getActivity();
//        activity.init222();
//        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
//        RightFragment rightFragment = (RightFragment) supportFragmentManager.findFragmentById(R.id.right_fragment);
//        rightFragment.init111();
//    }
}
