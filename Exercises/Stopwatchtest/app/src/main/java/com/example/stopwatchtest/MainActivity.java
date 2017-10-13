package com.example.stopwatchtest;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView timeView;

    private Button startButton, stopButton, resetButton;

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeView = (TextView)findViewById(R.id.time_text);
        startButton = (Button)findViewById(R.id.start_button);
        stopButton = (Button)findViewById(R.id.stop_button);
        resetButton = (Button)findViewById(R.id.reset_button);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        runTimer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_button:
                running = true;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.reset_button:
                seconds = 0;
                running = false;
                break;
            default:
                break;
        }
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
