package com.example.workout;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by neal on 11/10/17.
 */

public class WorkoutDetailFragment extends Fragment {

    private int workoutId;
    private View view;
    private TextView nameTitle, detailView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.workoutdetailfragment, container, false);
        nameTitle = (TextView)view.findViewById(R.id.title_text);
        detailView = (TextView)view.findViewById(R.id.details_text);
        if (savedInstanceState!=null){
            workoutId = savedInstanceState.getInt("workoutId");
        }else {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            ft.replace(R.id.stopwatch_container, stopwatchFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        return view;
    }

    public void setWorkoutId(long id){
        this.workoutId = (int) id;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("workoutId", workoutId);
    }

    @Override
    public void onStart() {
        super.onStart();
        Workout workout = Workout.workouts[this.workoutId];
        nameTitle.setText(workout.getWorkoutName());
        detailView.setText(workout.getWorkoutDetails());
    }
}
