package com.example.android.forallatechtask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.forallatechtask.MainActivity;
import com.example.android.forallatechtask.Model.ProductModel;
import com.example.android.forallatechtask.ProductDetail;
import com.example.android.forallatechtask.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<ProductModel> list;
     Context context;

    public ProductAdapter(List<ProductModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        if (list.get(position).getImgae() != null) {
            Glide.with(context).load(list.get(position).getImgae()).placeholder(R.drawable.ic_loading_image).into(holder.imageView);
        }
        if (list.get(position).getTitle() != null) {

            holder.nameTv.setText(list.get(position).getTitle());
        }


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("description",list.get(position).getDesciption());
                intent.putExtra("category",list.get(position).getCategory());
                intent.putExtra("image",list.get(position).getImgae());
                intent.putExtra("price","₹ "+list.get(position).getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      ImageView imageView;
       TextView nameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTv = itemView.findViewById(R.id.nameTv);
        }
    }
}
