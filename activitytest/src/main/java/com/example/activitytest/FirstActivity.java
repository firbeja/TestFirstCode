package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity111";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"add按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,"remove按钮",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ------------->"+FirstActivity.this);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivityForResult(intent,1);
//                Intent intent1 = new Intent(FirstActivity.this, ThirdActivity.class);
//                startActivityForResult(intent1,2);

                //通过SecondActivity内的actionStart启动Activity
                SecondActivity.actionStart(FirstActivity.this,"第一个数据","第二个数据");

            }
        });

        //standare启动模式demo，跳转到FirstActivity
        Button buttonStandare = (Button) findViewById(R.id.standare_mode);
        buttonStandare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        //singleTop启动模式demo,跳转到SecondActivity
        Button buttonSingleTop = (Button) findViewById(R.id.singleTop_mode);
        buttonSingleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1 :
                if (resultCode == RESULT_OK){
                    String data_return = data.getStringExtra("data_return");
                    Log.d(TAG, "在FirstActivity中获得返回的数据：" + data_return);
                }
                break;
            case 2 :
                if (resultCode == RESULT_CANCELED){
                    String third_return = data.getStringExtra("third_return");
                    Log.d(TAG, "第三个ThirdAcitivity返回的数据： "+ third_return);
                }
        }
    }
}
