package com.example.dcorona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dcorona.R;

import java.security.PrivateKey;

public class Splash_Activity extends AppCompatActivity {
    int SCREENTIME_OUT=2500;
   // private Animation splash_anim;
   // private ImageView splash_image;
   private static final String TAG = "Splash_Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);

        //connection
       // splash_image=(ImageView) findViewById(R.id.splash_image);



        //Animation code
      //  splash_anim= AnimationUtils.loadAnimation(this,R.anim.splash_anim);
       // splash_image.setAnimation(splash_anim);

        Log.d(TAG, "Thread id:"+Thread.currentThread().getId());



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash_Activity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },SCREENTIME_OUT);



    }
}