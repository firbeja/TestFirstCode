package com.example.listviewtest;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        ListView listview = (ListView) findViewById(R.id.list_view);
        FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        listview.setAdapter(fruitAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i=0;i<=100;i++){
            Fruit flower = new Fruit("Flower", R.mipmap.flower);
            fruitList.add(flower);
            Fruit mood = new Fruit("mood", R.mipmap.mood);
            fruitList.add(mood);
        }
    }

    public class FruitAdapter extends ArrayAdapter<Fruit> {

        private int resourceId;

        public FruitAdapter(Context context, int textViewResourceId,  List<Fruit> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Fruit fruit = getItem(position);
            View view;
            ViewHolder viewHolder;
            if (convertView==null){
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
                viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_iamge);
                view.setTag(viewHolder);
            }else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.fruitImage.setImageResource(fruit.getImageId());
            viewHolder.fruitName.setText(fruit.getName());
            return view;
        }

        class ViewHolder {
            ImageView fruitImage;
            TextView fruitName;
        }

    }



}
