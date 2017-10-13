package com.example.workout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by neal on 11/10/17.
 */

public class WorkoutListFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<Workout> adapter;

    interface WorkoutListListener{
        void itemClicked(long id);
    }

    private WorkoutListListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workoutlistfragment,container,false);
        listView = (ListView)view.findViewById(R.id.workout_list_view);
        adapter = new ArrayAdapter<Workout>(getContext(),android.R.layout.simple_list_item_1,Workout.workouts);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (WorkoutListListener)context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.itemClicked(l);
                /*WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_fragment);
                workoutDetailFragment.setWorkoutId(l);
                workoutDetailFragment.showDetail();*/
            }
        });
    }
}
