package com.example.domneu.myapplication.viewmodel;

import android.app.Application;

import com.example.domneu.myapplication.room.Product;
import com.example.domneu.myapplication.room.ProductRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
    }

    public void insert(Product product) {
        repository.insert(product);
    }

    public void update(Product product) {
        repository.update(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

  //  public void deleteAllProducts() {
  //      repository.deleteAllProducts();
  //  }
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }
}