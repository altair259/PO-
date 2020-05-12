package com.example.domneu.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.domneu.myapplication.R;
import com.example.domneu.myapplication.room.Product;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private List<Product> products = new ArrayList<>();
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row,parent,false);
        return new ProductHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewDescription.setText(currentProduct.getDescription());
        holder.textViewExpDate.setText(String.valueOf(currentProduct.getExpDate()));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products){
            this.products = products;
            notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDescription;
        private  TextView textViewExpDate;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.row_name);
            textViewDescription = itemView.findViewById(R.id.row_desc);
            textViewExpDate = itemView.findViewById(R.id.row_date);
        }
    }
}
