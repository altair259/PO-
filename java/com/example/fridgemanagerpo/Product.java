package com.example.fridgemanagerpo;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

//@Entity(tableName = "product_table",foreignKeys = @ForeignKey(entity = Item.class,
//        parentColumns = "uid",
//        childColumns = "id",
//        onDelete = CASCADE,
//        onUpdate = CASCADE),
//        indices = @Index("id"))
@Entity(tableName = "product_table")
public class Product {



    @PrimaryKey(autoGenerate = true)
    private int id;
    //private int priority;
    private String name;
    // private String date;
    private Date expDate;
    private String description;
    private String category;
  //  private int timeleft;

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

    public void setCategory(String category){this.category = category;}


    public void setId(int id) {
        this.id = id;

    }
 //   public void setTimeleft( int timeleft) {
 //       this.timeleft = timeleft;

//    }



    public Product(String name,Date expDate,String description,String category) {
        // this.priority = priority;
        this.name = name;
        //  this.date = date;
        this.category = category;
        this.expDate = expDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }
  //  public int getTimeleft() {
 //       return timeleft;
 //   }
    //public int getPriority() {
    // return priority;
    // }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
   }
    public String getDescription() {
        return description;
    }

    public Date getExpDate() {
        return expDate;
    }
}

