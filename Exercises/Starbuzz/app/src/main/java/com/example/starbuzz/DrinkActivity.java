package com.example.starbuzz;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    private int id;
    private ImageView imageView;
    private TextView nameView, descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("itemPosition");
        Drink drink = Drink.drinks[id];
        imageView = (ImageView) findViewById(R.id.image_view);
        nameView = (TextView)findViewById(R.id.name_view);
        descriptionView = (TextView)findViewById(R.id.description_view);
        imageView.setImageResource(drink.getImageResourceId());
        nameView.setText(drink.getName());
        descriptionView.setText(drink.getDescription());
    }
}
