package com.aberamax.android.xapomateriallab;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class VibrationTouchActivity extends AppCompatActivity {

    View btnNaboo;
    View btnDeboo;
    Vibrator mVibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration_touch);

        btnDeboo = findViewById(R.id.btnDeboo);
        btnNaboo = findViewById(R.id.btnNaboo);



                     /*
           in manifest
           <uses-permission android:name="android.permission.VIBRATE" />
         */
       mVibr = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);


        View.OnTouchListener mTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                ObjectAnimator animation1 = ObjectAnimator.ofFloat(btnNaboo,"elevation", 2f, 30f);
                animation1.setDuration(100);
                animation1.start();

                mVibr.vibrate(30);
                return false;
            }
        };

        btnNaboo.setOnTouchListener(mTouchListener);

    }




}
