package com.example.workout;

/**
 * Created by neal on 11/10/17.
 */

public class Workout {

    private String workoutName;
    private String workoutDetails;

    public static final Workout[] workouts = {
            new Workout("The Limb Loosener","5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            new Workout("Core Agony","100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Workout("The Wimp Special","5 Pull-ups\n10 Push-ups\15 Squats"),
            new Workout("Strength and Length","500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
    };

    public Workout(String name, String details){
        this.workoutName = name;
        this.workoutDetails = details;
    }

    public String getWorkoutDetails() {
        return workoutDetails;
    }


    public String getWorkoutName() {

        return workoutName;
    }

    public String toString(){
        return this.workoutName;
    }
}
