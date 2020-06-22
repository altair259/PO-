package com.example.fridgemanagerpo;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String name;
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
