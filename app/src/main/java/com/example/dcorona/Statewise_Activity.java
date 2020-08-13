package com.example.dcorona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.dcorona.Adpter.StateAdpter;
import com.example.dcorona.Model.State_model;
import com.example.dcorona.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Statewise_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    StateAdpter stateAdpter;
    List<State_model>stateList;
    RequestQueue queue;
    private SwipeRefreshLayout refresing_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statewise_activity);

        queue= Volley.newRequestQueue(this);
        recyclerView=findViewById(R.id.recycler_state);
        refresing_layout=findViewById(R.id.refresing_layout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stateList=new ArrayList<>();


        /////////////////
        refresing_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stateList=getStateData();
                        recyclerView.setAdapter(stateAdpter);
                        stateAdpter.notifyDataSetChanged();


                        refresing_layout.setRefreshing(false);


                    }
                }).start();


            }
        });

        //////////

        stateAdpter=new StateAdpter(this,stateList);






    }

    public List<State_model> getStateData(){

        new Thread(new Runnable() {
            @Override
            public void run() {



                String url="https://api.covid19india.org/data.json";

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = null;



                        try {
                            array = response.getJSONArray("statewise");

                            for (int i=1;i<array.length();i++){
                                JSONObject stateobject=array.getJSONObject(i);
                                State_model state_model=new State_model();
                                state_model.setState_name(stateobject.getString("state"));
                                state_model.setState_active(stateobject.getString("confirmed"));
                                state_model.setState_death(stateobject.getString("deaths"));
                                state_model.setState_recover(stateobject.getString("recovered"));

                                // Log.d("onResponse: ",state_model.getState_name());
                                stateList.add(state_model);

                            }

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
        return stateList;


    }






}