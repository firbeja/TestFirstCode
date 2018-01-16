package com.example.shardperferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button savaData = (Button) findViewById(R.id.save_data);
        savaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("datam", MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age4",98);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });

        Button restoreData = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("datam", MODE_PRIVATE);
                String name = pref.getString("name", "no name");
                int age = pref.getInt("age9", 000000);
                boolean married = pref.getBoolean("married", false);
                Log.d("tag", " 名字是： "+name);
                Log.d("tag", " 年龄是： "+age);
                Log.d("tag", " 是否结婚： "+married);
            }
        });


    }
}
