package com.example.android.forallatechtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.forallatechtask.Adapter.ProductAdapter;
import com.example.android.forallatechtask.Model.ProductModel;
import com.example.android.forallatechtask.Utils.EndPoints;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    ImageView cart_btn;
    ProductAdapter productAdapter;
    List<ProductModel> productlist = new ArrayList<>();
    ProgressBar progressbar;
    RequestQueue mRequestQue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         initView();
         productAdapter = new ProductAdapter(productlist,MainActivity.this);
         RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
         recycler_view.setLayoutManager(mLayoutManager);
         recycler_view.setItemAnimator(new DefaultItemAnimator());
         recycler_view.setAdapter(productAdapter);
         getProducts();


    }
    private void initView() {
        recycler_view = findViewById(R.id.recycler_view);
        cart_btn = findViewById(R.id.cart_btn);
        progressbar = findViewById(R.id.progressbar);

        mRequestQue = Volley.newRequestQueue(MainActivity.this);

    }

    private void getProducts() {
        progressbar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.GET, EndPoints.GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressbar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray =new JSONArray(response);
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            ProductModel productModel = new ProductModel();
                            productModel.setTitle(object.getString("title"));
                            productModel.setId(object.getString("id"));
                            productModel.setImgae(object.getString("image"));
                            productModel.setCategory(object.getString("category"));
                            productModel.setPrice(object.getString("price"));
                            productModel.setDesciption(object.getString("description"));

                            productlist.add(productModel);
                        }
                        productAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        mRequestQue.add(request);

    }
}