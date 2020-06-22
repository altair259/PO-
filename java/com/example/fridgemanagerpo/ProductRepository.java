package com.example.fridgemanagerpo;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;
    public String category = "";

    public ProductRepository(Application application) {
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.productDao();//przez builder w Database Room generuje ciało do tej metody
        allProducts = productDao.getAllProducts();
    }

    //Viewmodel ma korzysta tylko z tych metod
    public void insert(Product product) {
        new InsertProductAsyncTask(productDao).execute(product);
    }

    public void update(Product product) {
        new UpdateProductAsyncTask(productDao).execute(product);

    }

    public void delete(Product product) {
        new DeleteProductAsyncTask(productDao).execute(product);

    }

    public void deleteAllProducts(Product product) {
        new DeleteAllProductAsyncTask(productDao).execute();

    }

    public LiveData<List<Product>> getProductsByCategory(String selected_category) {
        Log.i("repo", "getProductsByCategory");
        return productDao.getProductsByCategory(selected_category);
    }

    public LiveData<List<Product>> getAllProducts() {
        // return allProducts;
        return productDao.getProductsByCategory(category);
    }


    private static class InsertProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;
        private InsertProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }
    private static class UpdateProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;
        private UpdateProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }// przez konstruktor
        @Override
        protected Void doInBackground(Product... products) {
            productDao.update(products[0]);
            return null;
        }
    }
    private static class DeleteProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;
        private DeleteProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDao.delete(products[0]);
            return null;
        }
    }
    private static class DeleteAllProductAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDao productDao;
        private DeleteAllProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllProducts();
            return null;
        }
    }
}