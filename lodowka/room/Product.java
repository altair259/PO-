package com.example.domneu.myapplication.room;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String expDate;

    //private boolean Singleton;

    private String description;

    private int priority;

    public Product( String name, String expDate, String description, int priority) {

        this.name = name;
        this.expDate = expDate;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
