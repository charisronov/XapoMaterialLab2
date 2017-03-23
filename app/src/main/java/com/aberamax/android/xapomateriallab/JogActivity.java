package com.aberamax.android.xapomateriallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.aberamax.android.ui.JogView;

public class JogActivity extends AppCompatActivity {

    JogView jogView;
    TextView txtAngle, txtMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jog);

        jogView = (JogView)findViewById(R.id.jog_view);
        txtAngle = (TextView)findViewById(R.id.txtAngle);
        txtMillis = (TextView)findViewById(R.id.txtMillis);

        jogView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    Log.d("TouchTest", "Touch down");
                   // txtAngle.setText(Integer.toString(Math.round(jogView.touchAngle(event.getX(), event.getY()))));
                   // onTouchMove(view, event);

                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    Log.d("TouchTest", "Touch up");
                   // jogView.resetLastAnglePosition();

                } else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE){
                   onTouchMove(view, event);
                   txtAngle.setText(String.format("%.1f°",jogView.getJogAngle()));
                   txtMillis.setText(Long.toString(jogView.getMillisCounter()));
                }
                return true;
            }
        });


    }


    private boolean onTouchMove(View v, MotionEvent e){

        JogView jv = (JogView)v;

        float angle = jogView.touchAngle(e.getX(), e.getY());

        if ( jv.setJogAngle(angle) != 0){
            jv.invalidate();
        }

        return true;

    }



}

