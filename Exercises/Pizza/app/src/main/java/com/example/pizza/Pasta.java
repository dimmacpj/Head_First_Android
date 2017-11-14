package com.example.pizza;

/**
 * Created by neal on 14/11/17.
 */

public class Pasta {
    private String name;
    private int imageId;

    public static final Pasta[] pastas = {
            new Pasta("Spaghetti Bolognese", R.drawable.spag_bol),
            new Pasta("Lasagne", R.drawable.lasagne)
    };

    private Pasta(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
