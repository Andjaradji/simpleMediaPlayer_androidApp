package com.rds.mediaplayerapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private Button mPlayButton;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = (Button)findViewById(R.id.playButtonID);

        mMediaPlayer = new MediaPlayer();

        mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.is_she_with_you);

        mSeekBar = (SeekBar)findViewById(R.id.soundSeekBarID);
        mSeekBar.setMax(mMediaPlayer.getDuration());

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser){
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int mediaDuration = (mp.getDuration()/1000);
                String mTextDuration = String.valueOf(mediaDuration/60);

                Toast.makeText(getApplicationContext(),"The song playing duration is  " + mTextDuration + "minutes",Toast.LENGTH_LONG).show();
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()){
                    pauseSound();

                } else {
                    startSound();

                }

            }
        });
    }

    public void startSound () {
        if (mMediaPlayer != null){
            mMediaPlayer.start();
            mPlayButton.setText("Pause");
        }

    }

    public void pauseSound (){
        if (mMediaPlayer != null){
            mMediaPlayer.pause();
            mPlayButton.setText("Play");
        }
    }

    @Override
    protected void onDestroy() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;


        }
        super.onDestroy();
    }
}
