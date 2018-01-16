package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //CREATE TABLE Book (id integer primary key autoincrement, author text, price real, pages integer, name text);

                values.put("author","Davi");
                values.put("price",16.96);
                values.put("pages",454);
                values.put("name","The 1 First Book");
                db.insert("Book",null,values);
                values.clear();
                values.put("author","Davi");
                values.put("price",19.95);
                values.put("pages",510);
                values.put("name","The Davi Second Book");
                db.insert("Book",null,values);

//                db.execSQL("insert into Book (name , author , pages , price) values(? , ? , ? , ?)" ,
//                        new String[]{"3 Copy of The 1 First Book" , "Davi" , "4541" , "16.96"});
//                db.execSQL("insert into Book (name , author , pages , price) values(? , ? , ? , ?)" ,
//                        new String[]{"3 Copy of The Davi Second Book" , "Davi" , "5101" , "19.95"});
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("price",10.99);
//                db.update("Book",values,"name = ?",new String[]{"The 1 First Book"});

                db.execSQL("update Book set price = ? where name = ?",new String[]{"101.99","The 1 First Book"});
            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("price",10.99);
                //CREATE TABLE Book (id integer primary key autoincrement, author text, price real, pages integer, name text);
//                db.delete("Book","pages >?",new String[]{"500"});

                db.execSQL("delete from Book where pages>?",new String[]{"500"});
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

//                Cursor cursor = db.query("Book", null, null, null, null, null, null, null);

                Cursor cursor = db.rawQuery("select * from Book", null);

                if (cursor.moveToFirst()){
                    do {
                        //CREATE TABLE Book (id integer primary key autoincrement,
                        // author text, price real, pages integer, name text);
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d(TAG, "书的名字是： "+ name);
                        Log.d(TAG, "书的作者是： "+ author);
                        Log.d(TAG, "书的页数是： "+ pages);
                        Log.d(TAG, "书的价格是： "+ price);
                    }while (cursor.moveToNext());
                }
                cursor.close();


            }
        });

    }
}
