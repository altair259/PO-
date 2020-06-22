package com.example.fridgemanagerpo;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private List<Product> products = new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override  //pojedynczy produkt
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item,parent,false);
        return new ProductHolder(productView);


    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        long timeNow = Instant.now().toEpochMilli();
        //  long milisec = expDate.getTime();

        Product currentProduct = products.get(position);
      //  holder.textViewTimeLeft.setText(DateUtils.getRelativeTimeSpanString(currentProduct.getExpDate().getTime(),timeNow,DateUtils.DAY_IN_MILLIS));
        holder.textViewCategory.setText(currentProduct.getCategory());
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewDescription.setText(currentProduct.getDescription());
        //holder.textViewpriority.setText(String.valueOf(currentProduct.getPriority()));

        Date date = currentProduct.getExpDate();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Warsaw"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String parseDate = day + "-" + month + "-" + year;

        holder.textViewDate.setText(parseDate);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void setProducts(List<Product>products){
        this.products = products;
        notifyDataSetChanged();
    }

    public  Product getProductAt(int position){
        return products.get(position);
    }
    //public void setOnItemClickListener(OnItemClickListener listener){mlistener = listener; }
    class ProductHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewDate;
        private ImageView deleteProduct;
        private TextView textViewTimeLeft;
        private TextView textViewCategory;


        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);

            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            deleteProduct = itemView.findViewById(R.id.delete_product);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewCategory = itemView.findViewById(R.id.text_view_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(products.get(position));
                }
            });
            //   deleteProduct.setOnClickListener(new View.OnClickListener() {
            //   @Override
            //   public void onClick(View view) {
            //   int position = getAdapterPosition();
            //   if(listener != null && position != RecyclerView.NO_POSITION)
            //       listener.onDeleteClick(position);

            //    }
            // });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Product product);
        //  void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
