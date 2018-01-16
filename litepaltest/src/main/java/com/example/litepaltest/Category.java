package com.example.litepaltest;

/**
 * Created by john on 2018/1/6.
 */

public class Category {

//    public String CREATE_BOOK = "create table Book ("
//            +"id integer primary key autoincrement, "
//            +"author text, "
//            +"price real, "
//            +"pages integer, "
//            +"name text)";
//
//    public String CREATE_CATEGORY = "create table Category ("
//            +"id integer primary key autoincrement, "
//            +"category_name text, "
//            + "category_code integer)";

    private int id;
    private String categoryName;
    private int categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
