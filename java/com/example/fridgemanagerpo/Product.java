package com.example.fridgemanagerpo;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {



    @PrimaryKey(autoGenerate = true)
    private int id;
    //private int priority;
    private String name;
   // private String date;
    private Date expDate;
    private String description;

   // public void setDate(String date) {
   //  //      this.date = date;
   //  //  }


    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

   // public String getDate() {
  //      return date;
  //  }
    public void setDescription(String description){this.description = description;}




    public void setId(int id) {
        this.id = id;
    }

    public Product(String name,Date expDate,String description) {
       // this.priority = priority;
        this.name = name;
      //  this.date = date;
        this.expDate = expDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    //public int getPriority() {
       // return priority;
   // }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Date getExpDate() {
        return expDate;
    }
}

