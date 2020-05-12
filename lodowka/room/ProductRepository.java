package com.example.domneu.myapplication.room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application){
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.productDao();
        allProducts = productDao.getAllProducts();

    }
    public void insert(Product product){
        new InsertProductAsyncTask(productDao).execute(product);
    }
    public void update(Product product){
        new UpdateProductAsyncTask(productDao).execute(product);
    }
    public void delete(Product product){
        new DeleteProductAsyncTask(productDao).execute(product);
    }
    public void deleteAllProducts(Product product){
        new DeleteAllProductsProductAsyncTask(productDao).execute();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
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


        }

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
    private static class DeleteAllProductsProductAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDao productDao;
        private DeleteAllProductsProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;


        }

        @Override
        protected Void doInBackground(Void... voidss) {
            productDao.deleteAllProducts();
            return null;
        }
    }
}
