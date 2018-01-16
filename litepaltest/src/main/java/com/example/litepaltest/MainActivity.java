package com.example.litepaltest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //1|Davi|10.99|454|The 1 First Book
    //2|Davi|19.95|510|The Davi Second Book
    //CREATE TABLE book (id integer primary key autoincrement,author text, name text, price real, pages integer, press text);

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();

                //!!!!!使用了全局获取的技巧！MyApplication.getContext()
                Toast.makeText(MyApplication.getContext(), "!!!!!使用了全局获取的技巧！MyApplication.getContext()", Toast.LENGTH_LONG).show();
            }
        });

        //添加数据 add_data按钮点击事件
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                //1|Davi|10.99|454|The 1 First Book
                //2|Davi|19.95|510|The Davi Second Book
                //CREATE TABLE book (id integer primary key autoincrement,author text, name text, price real, pages integer, press text);
                book.setAuthor("Davi");
                book.setPrice(16.96);
                book.setPages(454);
                book.setName("The 1 First Book");
                book.setPress("Unknow");
                book.save();
            }
        });

        //更新数据 update_data按钮点击事件
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                //1|Davi|10.99|454|The 1 First Book
                //2|Davi|19.95|510|The Davi Second Book
                //CREATE TABLE book (id integer primary key autoincrement,author text, name text, price real, pages integer, press text);

                book.setAuthor("Davi");
                book.setPrice(19.95);
                book.setPages(510);
                book.setName("The Davi Second Book");
                book.setPress("Unknow");
                book.save();

                // 更新price
//                book.setPrice(10.99);
//                book.save();

                // updataAll()更新
//                book.setPrice(14.95);
//                book.setPress("Anchor");
//                book.updateAll("name = ? and author = ?" , "The Davi Second Book" , "Davi");

                // setToDefault()设置默认值
//                book.setToDefault("pages");
//                book.updateAll();

            }
        });

        //删除数据 delete_data按钮     DataSupport.deleteAll()
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price > ?", "16");
            }
        });

        //查询数据 query_data按钮
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                List<Book> books = DataSupport.findAll(Book.class);

//                Book firstBook = DataSupport.findFirst(Book.class);
//                Log.d(TAG, "书的名字是： "+ firstBook.getName());
//                    Log.d(TAG, "书的作者是： "+ firstBook.getAuthor());
//                    Log.d(TAG, "书的页数是： "+ firstBook.getPages());
//                    Log.d(TAG, "书的价格是： "+ firstBook.getPrice());
//                    Log.d(TAG, "书的出版社是： "+ firstBook.getPress());

//                Book lastBook = DataSupport.findLast(Book.class);
//                Log.d(TAG, "书的名字是： " + lastBook.getName());
//                Log.d(TAG, "书的作者是： " + lastBook.getAuthor());
//                Log.d(TAG, "书的页数是： " + lastBook.getPages());
//                Log.d(TAG, "书的价格是： " + lastBook.getPrice());
//                Log.d(TAG, "书的出版社是： " + lastBook.getPress());


//                List<Book> books = DataSupport.where("pages < ?" , "500")
//                        .order("pages desc")
//                        .limit(2)
//                        .offset(2)
//                        .find(Book.class);
//
//                for (Book book:books){
//
////                    1|Davi|The 1 First Book|16.96|0|Unknow
////                    2|Davi|The Davi Second Book|14.95|0|Anchor
//                    Log.d(TAG, "书的名字是： "+ book.getName());
//                    Log.d(TAG, "书的作者是： "+ book.getAuthor());
//                    Log.d(TAG, "书的页数是： "+ book.getPages());
//                    Log.d(TAG, "书的价格是： "+ book.getPrice());
//                    Log.d(TAG, "书的出版社是： "+ book.getPress());
//
//                }

                Cursor cursor = DataSupport.findBySQL("select * from Book where price > ? and pages < ?", "15", "500");
                if (cursor.moveToFirst()) {
                    do {

//                        1|Davi|The 1 First Book|16.96|0|Unknow
//                        3|Davi|The 1 First Book|16.96|454|Unknow
//                        4|Davi|The 1 First Book|16.96|454|Unknow
//                        5|Davi|The 1 First Book|16.96|454|Unknow
//                        6|Davi|The Davi Second Book|19.95|510|Unknow

                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        String pages = cursor.getString(cursor.getColumnIndex("pages"));
                        String press = cursor.getString(cursor.getColumnIndex("press"));
                        Log.d(TAG, "书的名字是： " + name);
                        Log.d(TAG, "书的作者是： " + author);
                        Log.d(TAG, "书的页数是： " + pages);
                        Log.d(TAG, "书的价格是： " + price);
                        Log.d(TAG, "书的出版社是： " + press);

                    } while (cursor.moveToNext());
                }

//                if (cursor != null && cursor.moveToFirst()) {
//                    while (cursor.isAfterLast()) {
//
//                    }
//                }

            }
        });

    }
}
