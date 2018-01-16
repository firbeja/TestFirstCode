package com.example.litepaltest;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by john on 2018/1/12.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext() {
        return context;
    }
}
