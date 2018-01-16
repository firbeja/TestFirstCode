package com.example.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class NormalActivity extends AppCompatActivity {

    private static final String TAG = "NormalActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        int a = bundle.getInt("bundle_int");
        Log.d(TAG, "onCreate: ------------->" + a);

    }
}
