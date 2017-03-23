package com.aberamax.android.xapomateriallab;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class ClickAnimationActivity extends AppCompatActivity {

    private static final String TAG = "ClickAnimationActivity";

    View relativeLayout;
    View viewBoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_animation);


        relativeLayout = findViewById(R.id.btnPressMe);
        viewBoo = findViewById(R.id.btnDeboo);


    }


    public void onPressMeClicked(View view){

        ValueAnimator animation = ValueAnimator.ofFloat(-28f, +28f);
        animation.setInterpolator( new DecelerateInterpolator());
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float v = ((Float)valueAnimator.getAnimatedValue());

                if (v < 0){
                    v = 2 - v;
                }else{
                    v = 16 - v;
                }

                relativeLayout.setElevation(v);
            }
        });

    }


    public void onPressBooClicked(View view){

        ObjectAnimator animation1 = ObjectAnimator.ofFloat(viewBoo,"elevation", 2f, 30f);
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(viewBoo,"elevation", 30f, 2f);
        animation1.setDuration(250);
        animation1.start();
        animation2.setDuration(250);
        animation2.start();

    }





}
