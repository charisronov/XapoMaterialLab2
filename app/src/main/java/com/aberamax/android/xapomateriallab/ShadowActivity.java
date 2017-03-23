package com.aberamax.android.xapomateriallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ShadowActivity extends AppCompatActivity {

    SeekBar mSeekBar;
    View yellowSquare;
    View blueSquare;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);

        this.mSeekBar = (SeekBar)findViewById(R.id.seekBarElevation1);
        this.yellowSquare = findViewById(R.id.yellowSquare);
        this.blueSquare = findViewById(R.id.blueSquare);
        this.textView = (TextView)findViewById(R.id.txtElevation1);

        this.mSeekBar.setMax(30);
        this.mSeekBar.setProgress(0);

        textView.setText("elevation = " + Integer.toString(0));

        this.yellowSquare.setElevation(0);
        this.blueSquare.setElevation(0);

        SeekBar.OnSeekBarChangeListener myListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int i = seekBar.getProgress();
                textView.setText("elevation = " + Integer.toString(i));

                yellowSquare.setElevation(i);
                blueSquare.setElevation(i);

            }
        };

        mSeekBar.setOnSeekBarChangeListener(myListener);
    }
}
