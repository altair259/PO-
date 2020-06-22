package com.example.fridgemanagerpo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Product.class},version = 16)
@TypeConverters({Converters.class})
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;

    public abstract ProductDao productDao(); //to acess DAO

    public static  synchronized  ProductDatabase getInstance(Context context){
        if(instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class,"product_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback) // kiedy powstaje instancja wywoluje sie metoda onCreate
                    .build();


        }
        return instance;
    }//zeby nie powstały 2 instancje

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db); // która
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
            // productDao.insert(new Product(1, "xD","21.03.2020"));
            // productDao.insert(new Product(2, "xDD","22.03.2020"));
            // productDao.insert(new Product(3, "xDDD","22.03.2004"));
            return null;
        }
    }

}
