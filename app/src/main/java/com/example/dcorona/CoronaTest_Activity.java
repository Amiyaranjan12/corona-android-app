package com.example.dcorona;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dcorona.R;

public class CoronaTest_Activity extends AppCompatActivity {

    private Button corona_test_button;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9;
    private int total_checkbox_select=0;
    private final int total_checkbox=9;
    private static final String TAG = "CoronaTest_Activity";

    //pop activity

    TextView txtclose,corona_percentage_text;
    Dialog resultDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_test_);

        ///////////////
        resultDialog = new Dialog(this);


        resultDialog.setContentView(R.layout.testpop);
        txtclose = (TextView) resultDialog.findViewById(R.id.txtclose);
        corona_percentage_text=(TextView)resultDialog.findViewById(R.id.corona_percentage_text);
       /////////





        corona_test_button=findViewById(R.id.corona_test_button);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);
        checkBox7=findViewById(R.id.checkBox7);
        checkBox8=findViewById(R.id.checkBox8);
        checkBox9=findViewById(R.id.checkBox9);


        corona_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /////////////
                        Log.d(TAG, "Thread id:"+Thread.currentThread().getId());

                        if(!(total_checkbox_select==0)){
                            total_checkbox_select=0;



                        }

                        if(checkBox1.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox2.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox3.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox4.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox5.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox6.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox7.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox8.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }
                        if(checkBox9.isChecked()){
                            total_checkbox_select=total_checkbox_select+1;
                        }



                        float percentage_corona=(float)total_checkbox_select/total_checkbox*100;
                        int percentage_answer=(int)percentage_corona;
                        //  trrr.setText(Integer.toString(percentage_answer));

                        corona_percentage_text.setText(Integer.toString(percentage_answer));
                        corona_percentage_text.setTextColor(Color.RED);





                        //////////

                    }
                }).start();


                //////////////////////////////////////////////////////////////
                //pop activity
                txtclose.setText("x");
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                resultDialog.show();











            }
        });



    }

}