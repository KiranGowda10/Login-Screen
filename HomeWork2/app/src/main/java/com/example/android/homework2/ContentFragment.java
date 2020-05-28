package com.example.android.homework2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

//creating the ContentFragnet class for the tablet mode.
public class ContentFragment extends Fragment {

    public ContentFragment(String selected_make,String selected_model,String selected_price,String selected_veh_desc,String selected_created_on,String selected_image_url)
    {
        make = selected_make;
        model = selected_model;
        veh_desc = selected_veh_desc;
        make_price = selected_price;
        image = selected_image_url;
        page_created_at = selected_created_on;
    }
    public TextView make_model;
    public TextView location;
    public TextView veh_price;
    public TextView created_at;
    public ImageView image_url;
    String make, model, veh_desc, make_price,image,page_created_at;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //casting the fragment_content into the viewgroup
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_content, container, false);
        make_model = (TextView) root.findViewById(R.id.textView1);
        location = (TextView) root.findViewById(R.id.textView2);
        veh_price = (TextView) root.findViewById(R.id.textView3);
        created_at = (TextView) root.findViewById(R.id.textView4);
        image_url = (ImageView) root.findViewById(R.id.imageView);
        make_model.setText(make + " - " + model);
        location.setText(veh_desc);
        veh_price.setText("Price: $" + make_price);
        Picasso.with(getContext()).load(image).into(image_url);
        created_at.setText("Created At: " + page_created_at);
        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
