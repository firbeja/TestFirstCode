package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;


public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    public static void actionStart(Context context,String data1,String data2){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1 = "secondactivity say hei";

                Intent intent = new Intent();
                intent.putExtra("data_return",data1);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        //测试singleTop启动模式，点击跳转到FirstActivity
        Button singleTopToFirstActivity = (Button) findViewById(R.id.singleTop_to_firstActivity);
        singleTopToFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        //通过actionStart的方式，启动activity
        Intent intent = getIntent();
        String param1 = intent.getStringExtra("param1");
        String param2 = intent.getStringExtra("param2");
        Log.d(TAG, "onCreate: **************" + param1);
        Log.d(TAG, "onCreate: **************" + param2);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return","在onBackPressed中返回的数据 ");
        setResult(RESULT_OK,intent);
        finish();
    }
}
