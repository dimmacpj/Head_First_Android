package com.example.workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int workoutId = (int)intent.getExtras().get(EXTRA_WORKOUT_ID);
        new WorkoutDetailFragment().setWorkoutId(workoutId);
    }
}
