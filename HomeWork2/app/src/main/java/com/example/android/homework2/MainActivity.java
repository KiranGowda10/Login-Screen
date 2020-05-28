package com.example.android.homework2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;




public class MainActivity extends AppCompatActivity {

    public static Spinner sp;
    public Spinner sp2;
    public static ArrayList<String> id = new ArrayList<>();
    public static ArrayList<String> name = new ArrayList<>();
    public static ArrayList<String> model_id = new ArrayList<>();
    public static ArrayList<String> model = new ArrayList<>();
    public static ArrayList<String> v_id = new ArrayList<>();
    public static ArrayList<String> listview_id = new ArrayList<>();
    public static ArrayList<String> color = new ArrayList<>();
    public static ArrayList<String> price = new ArrayList<>();
    public static ArrayList<String> vehicle_desc = new ArrayList<>();
    public static ArrayList<String> image_url = new ArrayList<>();
    public static ArrayList<String> created_on = new ArrayList<>();

    public static String make_id;
    public static String selected_make;
    public static String selected_model_id;
    public static String selected_veh_desc;
    public static String selected_price;
    public static String selected_id;
    public static String selected_model;
    public static String selected_image_url;
    public static String selected_created_on;
    boolean tablet;
    //FrameLayout frame = (FrameLayout)findViewById(R.id.frame1);


    public static JSONObject c;
    public ProgressDialog pDialog;
    private String TAG = MainActivity.class.getSimpleName();

    public static String url = "https://thawing-beach-68207.herokuapp.com/carmakes";
    public static String model_url;
    public static String listview_url;
    public ListView lv;


    public static ArrayList<String> carList = new ArrayList<String>();
    public static ArrayList<String> carList1 = new ArrayList<String>();
    public static ArrayList<String> carlist2 = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.frame1)!=null)        //if the fragment is not null, then the application should run on tablet mode
        {                                          //so, the tablet boolean variable is set to true. Else it is set to false, to run on mobile mode.
            tablet = true;
        }
        else{
            tablet = false;
        }
        sp = (Spinner) findViewById(R.id.spinner);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        lv = (ListView) findViewById(R.id.list);

        new GetCarName().execute();

    }


    private class GetCarName extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonObj = new JSONArray(jsonStr);
                    id.clear();
                    name.clear();
                    carList.clear();
                    for (int i = 0; i < jsonObj.length(); i++) {     //creating a JsonObject to iterate through the loop, and
                        JSONObject c = (JSONObject) jsonObj.get(i);  // add the respective fields into the arraylist

                        id.add(c.getString("id"));
                        name.add(c.getString("vehicle_make"));

                        // adding vehicle make to the arraylist carList
                        carList.add(c.getString("vehicle_make"));
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            //creating an array adapter for the spinner
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, carList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(arrayAdapter);
            //arrayAdapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {
                    selected_make = name.get(position);  //once the spinner field is selected, the respective make and make_id
                    make_id = id.get(position);          //is fetched by the position argument.

                    new GetCarName1().execute();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }
    }


    class GetCarName1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            model_url = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/" + make_id;
            System.out.println(make_id);
            HttpHandler sh1 = new HttpHandler();
            System.out.println(model_url);
            // Making a request to url and getting response

            String jsonStr1 = sh1.makeServiceCall(model_url);
            System.out.println(jsonStr1);
            Log.e(TAG, "Response from url: " + jsonStr1);

            if (jsonStr1 != null) {
                try {
                    JSONArray jsonObj = new JSONArray(jsonStr1);

                    carList1.clear();
                    model_id.clear();
                    model.clear();
                    v_id.clear();
                    // looping through All model
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = (JSONObject) jsonObj.get(i);

                        model_id.add(c.getString("id"));
                        model.add(c.getString("model"));
                        v_id.add(c.getString("vehicle_make_id"));

                        // adding model to the carList1 arraylist
                        carList1.add(c.getString("model"));
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, carList1);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp2.setAdapter(arrayAdapter);
            //arrayAdapter.notifyDataSetChanged();
            sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                    selected_model_id = model_id.get(position);
                    selected_model = model.get(position);
                    System.out.println(selected_model_id);
                    new GetCarName2().execute();


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }
    }

    class GetCarName2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            listview_url = "https://thawing-beach-68207.herokuapp.com/cars/" + make_id + "/" + selected_model_id + "/92603";

            HttpHandler sh2 = new HttpHandler();

            // Making a request to url and getting response

            String jsonStr2 = sh2.makeServiceCall(listview_url);
            System.out.println(listview_url);
            Log.e(TAG, "Response from url: " + jsonStr2);


            if(jsonStr2 != null) {
                try {
                    JSONObject jsonObj_temp = new JSONObject(jsonStr2);
                    JSONArray jsonObj = (JSONArray) jsonObj_temp.get("lists");

                    carlist2.clear();
                    color.clear();
                    price.clear();
                    vehicle_desc.clear();
                    image_url.clear();
                    created_on.clear();



                    // looping through All data fields
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = (JSONObject) jsonObj.get(i);


                        listview_id.add(c.getString("id"));
                        color.add(c.getString("color"));
                        price.add(c.getString("price"));
                        vehicle_desc.add(c.getString("veh_description"));
                        image_url.add(c.getString("image_url"));
                        created_on.add(c.getString("created_at"));
                        model.add(c.getString("model"));
                        name.add(c.getString("vehicle_make"));

                        carlist2.add(selected_make + " " + Integer.toString(i + 1) + " - " + selected_model + "   Price: $" + price.get(i) + "  Color: " + color.get(i));

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, carlist2);
            lv.setAdapter(arrayAdapter);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    selected_veh_desc= vehicle_desc.get(position);
                    selected_price = price.get(position);
                    selected_id = listview_id.get(position);
                    selected_image_url = image_url.get(position);
                    selected_created_on = created_on.get(position);

                    if(tablet)       //if tablet is  true, it runs on tbelt mode by creating a contentfragment object
                    {
                        ContentFragment frag = new ContentFragment(selected_make,selected_model,selected_price,selected_veh_desc,selected_created_on,selected_image_url);
                        FragmentTransaction trasaction = getSupportFragmentManager().beginTransaction();
                        trasaction.add(R.id.frame1,frag);
                        trasaction.replace(R.id.frame1,frag);
                        trasaction.commit();
                    }
                    else           //else it should run on mobile mode, showing a new activity
                    {
                        //creating an intent to navigate to the other activity when the listview  data field is  clicked.
                        Intent intent = new Intent(MainActivity.this, Vehicle_Details.class);

                        //putting the data into the intent putExtra, and fetch the data by using getStringExtra
                        intent.putExtra("name",selected_make);
                        intent.putExtra("model",selected_model);
                        intent.putExtra("vehicle_description",selected_veh_desc);
                        intent.putExtra("price",selected_price);
                        intent.putExtra("image_url",selected_image_url);
                        intent.putExtra("created_at",selected_created_on);
                        startActivity(intent);
                    }

                }
            });
        }



    }
}



