package com.example.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewsContentActivity extends AppCompatActivity {

    public  void actionStart(Context context,String newsTitle,String newsContent){

        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent); //context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");

//        TextView newsTitleText = (TextView) findViewById(R.id.news_title);
//        TextView newsContentText = (TextView) findViewById(R.id.news_content);
//        newsTitleText.setText(newstTitle);
//        newsContentText.setText(newsContent);

        NewsContentFragment newsContentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsContent,newsTitle);

    }
}
