package com.example.domneu.myapplication.room;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Product.class, version = 1)
    public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance;

    public abstract ProductDao productDao();


    public static synchronized ProductDatabase getInstance(Context context) {
        if (instance == null) {
            instance =  Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class, "product_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }
    //deleteInstanceOfDatabase(){
       // instance = null
   // }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDao productDao;
        private PopulateDbAsyncTask(ProductDatabase db){
            productDao = db.productDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insert(new Product("japko","xddsdfsdddd","21:3ff7",1));
            productDao.insert(new Product("jajko","xddsdfsdfddd","21:3ff7",1));
            productDao.insert(new Product("gówno","xddddfsdfdd","21:37ff",1));
            return null;
        }
    }
}