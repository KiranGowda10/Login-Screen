package com.example.android.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Vehicle_Details extends AppCompatActivity {

    public TextView make_model;
    public TextView location;
    public TextView veh_price;
    public TextView created_at;
    public ImageView image_url;
    String make, model, veh_desc, make_price,image,page_created_at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        make_model = (TextView) findViewById(R.id.textView1);
        location = (TextView) findViewById(R.id.textView2);
        veh_price = (TextView) findViewById(R.id.textView3);
        created_at = (TextView) findViewById(R.id.textView4);
        image_url = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();

        //getting the data by using getStringExtra.
        make = intent.getStringExtra("name");
        model= intent.getStringExtra("model");
        veh_desc= intent.getStringExtra("vehicle_description");
        make_price = intent.getStringExtra("price");
        image = intent.getStringExtra("image_url");
        page_created_at = intent.getStringExtra("created_at");

        //setting the textviews and imageview to the respective data.
        make_model.setText(make + " - " + model);
        location.setText(veh_desc);
        veh_price.setText("Price: $" + make_price);
        Picasso.with(getApplicationContext()).load(image).into(image_url);
        created_at.setText("Created At: " + page_created_at);

    }


}

