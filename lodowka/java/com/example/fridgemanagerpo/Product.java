package com.example.fridgemanagerpo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private int priority;
    private String name;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }




    public void setId(int id) {
        this.id = id;
    }

    public Product(int priority, String name,String date) {
        this.priority = priority;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }


}

