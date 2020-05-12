package com.example.domneu.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.domneu.myapplication.adapter.ProductAdapter;
import com.example.domneu.myapplication.room.Product;
import com.example.domneu.myapplication.viewmodel.ProductViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Observer;

public class MainActivity extends AppCompatActivity /*implements DatePickerDialog.OnDateSetListener*/ {
    private ProductViewModel productViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
            RecyclerView recyclerView = findViewById(R.id.recycler_view); //kupa?
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            final ProductAdapter adapter = new ProductAdapter();
            recyclerView.setAdapter(adapter);
            productViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(ProductViewModel.class);
            //productViewModel =  new ViewModelProvider(this).get(ProductViewModel.class);
            productViewModel.getAllProducts().observe(this, new androidx.lifecycle.Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                Toast.makeText(MainActivity.this,"dzialaaa" ,Toast.LENGTH_SHORT ).show();
                    adapter.setProducts(products);
                }


            });

        }
    }

