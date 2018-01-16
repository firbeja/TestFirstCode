package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by john on 2018/1/5.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public String CREATE_BOOK = "create table Book ("
            +"id integer primary key autoincrement, "
            +"author text, "
            +"price real, "
            +"pages integer, "
            +"name text)";

    public String CREATE_CATEGORY = "create table Category ("
            +"id integer primary key autoincrement, "
            +"category_name text, "
            + "category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);

        //1|Davi|10.99|454|The 1 First Book
        //2|Davi|19.95|510|The Davi Second Book
//        db.execSQL("insert into Book (name , author , pages , price) values(? , ? , ? , ?)" ,
//                new String[]{"Copy of The 1 First Book" , "Davi" , "4541" , "16.96"});
//        db.execSQL("insert into Book (name , author , pages , price) values(? , ? , ? , ?)" ,
//                new String[]{"Copy of The Davi Second Book" , "Davi" , "5101" , "19.95"});
//        Toast.makeText(mContext,"创建成功",Toast.LENGTH_SHORT).show();//在内容提供者中，也就是在跨程序访问时不能用Toast.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

    }
}
