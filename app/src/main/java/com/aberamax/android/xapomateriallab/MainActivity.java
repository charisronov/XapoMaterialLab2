package com.aberamax.android.xapomateriallab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
}


    public void onShadowClicked(View view){
        Intent intent = new Intent(MainActivity.this, ShadowActivity.class);
        startActivity(intent);
    }


    public void btnTranslationZClicked(View view){
        Intent intent = new Intent(MainActivity.this, ShadowZActivity.class);
        startActivity(intent);
    }


    public void onClickAnimation(View view){
        Intent intent = new Intent(MainActivity.this, ClickAnimationActivity.class);
        startActivity(intent);
    }

    public void onTouchVibrate(View view){
        Intent intent = new Intent(MainActivity.this, VibrationTouchActivity.class);
        startActivity(intent);
    }



    public void onTouchUpAndDownClicked(View view){

        Intent intent = new Intent(MainActivity.this, UpAndDownActivity.class);
        startActivity(intent);

    }

    public void onJogClicked(View view){

        Intent intent = new Intent(MainActivity.this, JogActivity.class);
        startActivity(intent);

    }

    public void onJogSongClicked(View view){

        Intent intent = new Intent(MainActivity.this, JogSongActivity.class);
        startActivity(intent);

    }
}
