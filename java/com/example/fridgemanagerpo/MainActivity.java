package com.example.fridgemanagerpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProductViewModel  productViewModel;
    public static final int ADD_PRODUCT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        FloatingActionButton buttonAddProduct = findViewById(R.id.button_add_product);
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivityForResult(intent, ADD_PRODUCT_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ProductAdapter adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);


        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override // wywołuje sie zawsze gdy dane w livedata zmienia sie
            public void onChanged(List<Product> products) {

                adapter.setProducts(products);

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                productViewModel.delete(adapter.getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"usunieto",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                productViewModel.delete(product);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddProductActivity.EXTRA_NAME);
         //   int priority = data.getIntExtra(AddProductActivity.EXTRA_PRIORITY, 1);
            String description = data.getStringExtra(AddProductActivity.EXTRA_DESCRIPTION);
            int day = data.getIntExtra(AddProductActivity.EXTRA_DAY, 1);
            int month = data.getIntExtra(AddProductActivity.EXTRA_MONTH, 1);
            int year = data.getIntExtra(AddProductActivity.EXTRA_YEAR, 2020);

              //  Calendar currentTime = Calendar.getInstance();
              //  currentTime.set(2020, month, day);
             //   String test = currentTime.getTime().toString();
              //  String test = day +"." + month+"." + year+"r" ;
            Calendar expDate = Calendar.getInstance();


            expDate.set( year,month,day);
           // expDate.set(Calendar.MONTH, month);
          //  expDate.set(Calendar.DAY_OF_MONTH, day);
            Log.i("czas",expDate.getTime().toString());


            Product product = new Product(name,expDate.getTime(),description);
            productViewModel.insert(product);

            Toast.makeText(this, "zapisano", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "nie zapisano", Toast.LENGTH_SHORT).show();

        }
    }
}