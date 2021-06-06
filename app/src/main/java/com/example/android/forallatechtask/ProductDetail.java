package com.example.android.forallatechtask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetail extends AppCompatActivity {
    ImageView product_image;
    TextView title,description,category,price;
    Button addcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initview();
        getintentvalues();
    }

    private void initview() {

        product_image = findViewById(R.id.product_image);
        title = findViewById(R.id.pname);
        price=findViewById(R.id.price);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        addcart=findViewById(R.id.addcart);

    }
    private void getintentvalues() {

        try {

            title.setText(getIntent().getExtras().getString("title"));
            description.setText(getIntent().getExtras().getString("description"));
            category.setText(getIntent().getExtras().getString("category"));
            price.setText(getIntent().getExtras().getString("price"));
            Glide.with(ProductDetail.this).load(getIntent().getExtras().getString("image")).placeholder(R.drawable.ic_loading_image).into(product_image);

        }

        catch (Exception e)
        {

        }



    }

}