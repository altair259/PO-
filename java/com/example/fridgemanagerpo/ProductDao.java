package com.example.fridgemanagerpo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
//bo room generuje kod metod
@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product_table")
    void deleteAllProducts();

    @Query("SELECT * FROM product_table ORDER BY expDate ASC")
    LiveData<List<Product>> getAllProducts();


}
