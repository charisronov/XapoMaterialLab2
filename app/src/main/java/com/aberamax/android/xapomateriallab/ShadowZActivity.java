package com.aberamax.android.xapomateriallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ShadowZActivity extends AppCompatActivity {


    SeekBar mSeekBarElevation;
    SeekBar mSeekBarTranslationZ;
    View yellowSquare;  
    View blueSquare;
    TextView textElevation;
    TextView textTranslationZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_z);

        this.mSeekBarElevation = (SeekBar)findViewById(R.id.seekBarElevation);
        this.mSeekBarTranslationZ = (SeekBar)findViewById(R.id.seekBarTranslationZ);
        this.yellowSquare = findViewById(R.id.pinkSquare);
        this.blueSquare = findViewById(R.id.greenSquare);
        this.textElevation = (TextView)findViewById(R.id.txtElevation2);
        this.textTranslationZ = (TextView)findViewById(R.id.txtTranslationZ);

        this.mSeekBarElevation.setMax(30);
        this.mSeekBarElevation.setProgress(0);
        this.mSeekBarTranslationZ.setMax(30);
        this.mSeekBarTranslationZ.setProgress(0);

        textElevation.setText("elevation = " + Integer.toString(0));
        textTranslationZ.setText("translation Z = " + Integer.toString(0));

        this.yellowSquare.setElevation(0);
        this.blueSquare.setElevation(0);

        SeekBar.OnSeekBarChangeListener myListenerElevation = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int i = seekBar.getProgress();
                textElevation.setText("elevation = " + Integer.toString(i));

                yellowSquare.setElevation(i);
                blueSquare.setElevation(i);

            }
        };

        mSeekBarElevation.setOnSeekBarChangeListener(myListenerElevation);


        SeekBar.OnSeekBarChangeListener myListenerTranslation = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int i = mSeekBarTranslationZ.getProgress();
                textTranslationZ.setText("translation = " + Integer.toString(i));

                yellowSquare.setTranslationZ(i);
                blueSquare.setTranslationZ(i);

            }
        };

        mSeekBarTranslationZ.setOnSeekBarChangeListener(myListenerTranslation);
    }
}
