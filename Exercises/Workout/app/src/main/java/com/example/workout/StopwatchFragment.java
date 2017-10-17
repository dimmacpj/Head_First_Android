package com.example.workout;


import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {


    private View view;
    private int second = 0;
    private boolean running, wasRunning;
    private Button startButton, stopButton, resetButton;

    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("second",second);
        outState.putBoolean("running",running);
        outState.putBoolean("wasrunning",wasRunning);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        startButton = (Button)view.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        stopButton = (Button)view.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        resetButton = (Button)view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        if(savedInstanceState!=null){
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasrunning");
        }
        runTimer(view);
        return view;
    }

    private void runTimer(View view){
        final TextView timeText = (TextView)view.findViewById(R.id.time_text);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minutes = (second%3600)/60;
                int secs = second%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeText.setText(time);
                if(running){
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.start_button:
                running = true;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.reset_button:
                running = false;
                second = 0;
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }
}
