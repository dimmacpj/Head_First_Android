package com.example.starbuzz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    private int id;
    private ImageView imageView;
    private TextView nameView, descriptionView;
    private CheckBox favorCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("itemPosition");
        //Drink drink = Drink.drinks[id];
        imageView = (ImageView) findViewById(R.id.image_view);
        nameView = (TextView)findViewById(R.id.name_view);
        descriptionView = (TextView)findViewById(R.id.description_view);
        /*imageView.setImageResource(drink.getImageResourceId());
        nameView.setText(drink.getName());
        descriptionView.setText(drink.getDescription());*/
        try{
            StarbuzzDatabaseHelper helper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query("drink", new String[] {"name", "description", "image_resource_id"}, "_id = ?", new String[] {Integer.toString(id)}, null, null, null);
            if (cursor.moveToFirst()){
                nameView.setText(cursor.getString(cursor.getColumnIndex("name")));
                descriptionView.setText(cursor.getString(cursor.getColumnIndex("description")));
                imageView.setImageResource(cursor.getInt(cursor.getColumnIndex("image_resource_id")));
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
        favorCheckbox = (CheckBox) findViewById(R.id.favorite_checkbox);
        favorCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checked = ((CheckBox)view).isChecked();
                if(checked){

                }else {
                    
                }
            }
        });
    }
}
