package com.example.fridgemanagerpo;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProductViewModel extends AndroidViewModel {
    private  ProductRepository repository;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Product>> searchByLiveData;
    private MutableLiveData<String> filterLiveData = new MutableLiveData<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
        filterLiveData.setValue("%");
        searchByLiveData = Transformations.switchMap(filterLiveData,
                v -> repository.getProductsByCategory(v));
    }
    public void insert(Product product){
        repository.insert(product);
    }
    public void update(Product product){
        repository.update(product);
    }
    public void delete(Product product){
        repository.delete(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        Log.i("viewModel", "getAllProducts");
        return searchByLiveData;
    }

    public void setCategory(String category) {
        Log.i("viewModel", "setCategory");
        filterLiveData.setValue(category);
    }
}