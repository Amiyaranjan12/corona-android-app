package com.example.dcorona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dcorona.Statewise_Activity;
import com.example.dcorona.R;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private NavigationView  navigationView;
    private ImageView navigation_buttom;
    private DrawerLayout drwa_nav_layout;

    private static final String TAG = "HomeActivity";

    RequestQueue queue;

    private TextView textworld_cases,textworld_deaths,textworld_recover;
    String url="https://disease.sh/v2/all ";
    private SwipeRefreshLayout refresh_layout;

    private TextView text_indiacases,text_indiadeath,text_indiarecover;
    String india_url="https://api.covid19india.org/data.json";

    private Button corona_test_button,statewise_button,corona_tip_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

         queue= Volley.newRequestQueue(this);


        //navigation acitivity
        navigationView = (NavigationView)findViewById(R.id.navig_view);
        drwa_nav_layout= (DrawerLayout) findViewById(R.id.drwa_nav_layout);
        navigation_buttom = findViewById(R.id.navigation_buttom);

        Log.d(TAG, "Thread id:"+Thread.currentThread().getId());

       //main screen activity

        textworld_cases=findViewById(R.id.textworld_cases);
        textworld_deaths=findViewById(R.id.textworld_deaths);
        textworld_recover=findViewById(R.id.textworld_recover);
        refresh_layout=findViewById(R.id.refresh_layout);
        corona_test_button=findViewById(R.id.corona_test_button);
        statewise_button=findViewById(R.id.statewise_button);
        corona_tip_button=findViewById(R.id.corona_tip_button);

        text_indiacases=findViewById(R.id.text_indiacases);
        text_indiadeath=findViewById(R.id.text_indiadeath);
        text_indiarecover=findViewById(R.id.text_indiarecover);

       /// //navigation acitivity//////////
        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.HomeNId);

        navigation_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigthread nthread=new navigthread();
                nthread.start();



            }
        });
       //navigation view
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ShareNId:
                        {
                        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/Amiyaranjan12/corona-android-app"));
                        startActivity(intent);

                        }



                }

                return true;
            }
        });


      ////////// //main screen activity///////////////////

        //text color
        textworld_cases.setTextColor(Color.BLUE);
        textworld_deaths.setTextColor(Color.RED);
        textworld_recover.setTextColor(Color.GREEN);
        text_indiacases.setTextColor(Color.RED);
        text_indiarecover.setTextColor(Color.RED);
        text_indiadeath.setTextColor(Color.RED);



        corona_tip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Tips_Activity.class);
                startActivity(intent);

            }
        });


       //refersh functional
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Thread id:"+Thread.currentThread().getId());

                        findworldData();
                        findindiaData();
                        refresh_layout.setRefreshing(false);


                    }
                }).start();


            }
        });



        coronabutton();
        statebutton();


    }
    //world corona code

    ///////////

    //////////
    public void findworldData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Thread world:"+Thread.currentThread().getId());
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            textworld_cases.setText(jsonObject.getString("cases"));
                            textworld_deaths.setText(jsonObject.getString("deaths"));
                            textworld_recover.setText(jsonObject.getString("recovered"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }); queue.add(stringRequest);

            }
        }).start();

    }

   //India code
    private void findindiaData(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread india:"+Thread.currentThread().getId());

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, india_url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = null;

                        try {
                            array = response.getJSONArray("statewise");
                            JSONObject object = array.getJSONObject(0);
                            text_indiacases.setText(object.getString("confirmed"));
                            text_indiadeath.setText(object.getString("deaths"));
                            text_indiarecover.setText(object.getString("recovered"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(request);


            }
        }).start();


   }

   //corona test button method
    private void coronabutton(){
        corona_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Thread id:"+Thread.currentThread().getId());


                        Intent intent=new Intent(HomeActivity.this,CoronaTest_Activity.class);
                        startActivity(intent);

                    }
                }).start();

            }
        });



   }

   //statewise button method
    private void statebutton(){

        statewise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Thread id:"+Thread.currentThread().getId());
                        Intent intent=new Intent(HomeActivity.this, Statewise_Activity.class);
                        startActivity(intent);

                    }
                }).start();


            }
        });


    }

    class navigthread extends Thread{
        @Override
        public void run() {
            Log.d(TAG, "Thread id:"+Thread.currentThread().getId());

            if(drwa_nav_layout.isDrawerVisible(GravityCompat.START)){

                drwa_nav_layout.closeDrawer(GravityCompat.START);
            }
            else {
                drwa_nav_layout.openDrawer(GravityCompat.START);

            }


        }
    }



}