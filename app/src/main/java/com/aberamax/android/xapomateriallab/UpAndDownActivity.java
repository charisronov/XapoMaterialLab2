package com.aberamax.android.xapomateriallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class UpAndDownActivity extends AppCompatActivity {

    View circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_and_down);

        circleView = findViewById(R.id.circle_view);

        circleView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    Log.d("TouchTest", "Touch down");
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    Log.d("TouchTest", "Touch up");
                }
                return true;
            }
        });

    }


}
