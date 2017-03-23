package com.aberamax.android.xapomateriallab;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aberamax.android.ui.JogView;

public class JogSongActivity extends AppCompatActivity {

    private final static String TAG = "JogSongActivity";

    JogView jogView;
    TextView txtAngle;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    float jogAngle = 0;

    long jogPosition;
    Handler mHandler;

    enum PlayerStatus { STOP, PLAYING, PAUSE};
    PlayerStatus playerStatus = PlayerStatus.STOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jog_song);


        jogView = (JogView) findViewById(R.id.jog_song_view);

        txtAngle = (TextView) findViewById(R.id.txtAngle2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        jogPosition = jogView.getMillisCounter();

        mHandler = new Handler();

        jogView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    Log.d("TouchTest", "Touch down");
                    // txtAngle.setText(Integer.toString(Math.round(jogView.touchAngle(event.getX(), event.getY()))));
                    // onTouchMove(view, event);

                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    Log.d("TouchTest", "Touch up");
                    // jogView.resetLastAnglePosition();

                } else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE) {
                    onTouchMove(view, event);

                    txtAngle.setText(String.format("%.1f", jogView.getJogAngle()));


                    if ( mediaPlayer != null) {
                        long duration = mediaPlayer.getDuration();
                        long currentPos = mediaPlayer.getCurrentPosition();

                        long currentJogMillis = jogView.getMillisCounter();

                        long jogDegreeMillis = duration / (5 * 360);

                        long deltaPos = (currentJogMillis - jogPosition) * jogDegreeMillis ;

                        jogPosition = currentJogMillis;

                        Log.v(TAG, "DURATION: " + Integer.toString(mediaPlayer.getDuration()));
                        Log.v(TAG, "SEEK: " + Integer.toString((int)currentPos + (int)deltaPos));

                        mediaPlayer.seekTo((int)currentPos + (int)deltaPos);
                    }
                }

                return true;
            }
        });

    }


    private void playSong() {
        mediaPlayer = MediaPlayer.create(this.getApplicationContext(), R.raw.westworld);
        mediaPlayer.start();

        seekBar.setMax(mediaPlayer.getDuration());

        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                updateProgressBar();
            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                onStopPlayClicked(jogView);
                updateProgressBar();
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





    public void onPlayClicked(View view){

        if (playerStatus == PlayerStatus.STOP){
            jogAngle = 0;
            jogView.setJogAngle(0);
            playSong();
            playerStatus = PlayerStatus.PLAYING;
        }else if (playerStatus == PlayerStatus.PAUSE){
            mediaPlayer.start();
            playerStatus = PlayerStatus.PLAYING;
        }else {
            playerStatus =PlayerStatus.PAUSE;
            mediaPlayer.pause();
        }

        updateProgressBar();

    }

    public void onStopPlayClicked(View view){

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            playerStatus = PlayerStatus.STOP;
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    /**
     * Update timer on seekbar
     * */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     * */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

            if ( mediaPlayer == null ) {
                seekBar.setProgress(0);
                return;
            }

            int totalDuration = mediaPlayer.getDuration();
            int currentDuration = mediaPlayer.getCurrentPosition();

            // Displaying Total Duration time
          //  songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
           // songCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            //int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            // seekBar.setProgress(progress);
            seekBar.setProgress(currentDuration);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }

    };
}
