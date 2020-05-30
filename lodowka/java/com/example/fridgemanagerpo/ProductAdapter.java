package com.example.fridgemanagerpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                .inflate(R.layout.product_item,parent,false);
        return new ProductHolder(productView);


    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewpriority.setText(String.valueOf(currentProduct.getPriority()));
        holder.textViewDate.setText(String.valueOf(currentProduct.getDate()));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void setProducts(List<Product>products){
       this.products = products;
       notifyDataSetChanged();
    }


    class ProductHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewpriority;
        private TextView textViewDate;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewpriority = itemView.findViewById(R.id.text_view_priority);
            textViewDate = itemView.findViewById(R.id.text_view_date);
        }
    }
}
