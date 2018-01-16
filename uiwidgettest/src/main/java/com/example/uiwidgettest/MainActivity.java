package com.example.uiwidgettest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        Button btChangeImage = (Button) findViewById(R.id.bt_change_image);
        Button btProgessbarChange = (Button) findViewById(R.id.bt_progessbar_change);
        Button btProgessbarProgress = (Button) findViewById(R.id.bt_progressbar_progress_change);
        Button btAlterDialog = (Button) findViewById(R.id.bt_alterdialog);
        Button btProgressDialog = (Button) findViewById(R.id.bt_progressdialog);

        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);progressBar.setProgress(11);

        button.setOnClickListener(this);
        btChangeImage.setOnClickListener(this);
        btProgessbarChange.setOnClickListener(this);
        btProgessbarProgress.setOnClickListener(this);
        btAlterDialog.setOnClickListener(this);
        btProgressDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String inputString = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputString, Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_change_image:
                imageView.setImageResource(R.mipmap.img_2);
                break;
            case R.id.bt_progessbar_change:
                if (progressBar.getVisibility() == View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_progressbar_progress_change:
                int progress = progressBar.getProgress();
                progress = progress +10;
                progressBar.setProgress(progress);
                break;
            case R.id.bt_alterdialog:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("这是一个AlertDialog*****");
                dialog.setMessage("一些重要的事情##################");
                dialog.setCancelable(false);
                dialog.setIcon(R.mipmap.img_2);
                dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"点击了\"好的\"",Toast.LENGTH_SHORT).show();
                        progressBar.setProgress(3);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.bt_progressdialog:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("进度对话框 标题");
                progressDialog.setMessage("内容。。。。。。。。。");
                progressDialog.setCancelable(false);
                progressDialog.show();
                break;
            default:
            break;
        }
    }
}
