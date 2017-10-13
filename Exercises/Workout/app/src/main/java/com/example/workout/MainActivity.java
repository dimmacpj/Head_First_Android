package com.example.workout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.detail_fragment_container);
        if(fragmentContainer != null){
            WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            workoutDetailFragment.setWorkoutId(id);
            ft.replace(R.id.detail_fragment_container, workoutDetailFragment);
            ft.addToBackStack(null);
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }
    }
}
