package com.example.litepaltest;

import org.litepal.crud.DataSupport;

/**
 * Created by john on 2018/1/6.
 */

public class Book extends DataSupport {

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

    //CREATE TABLE book (id integer primary key autoincrement,author text, name text, price real, pages integer, press text);

    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;
    private String press;

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
