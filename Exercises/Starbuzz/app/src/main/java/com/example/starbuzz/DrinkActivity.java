package com.example.starbuzz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.AsyncTask;
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
    private StarbuzzDatabaseHelper helper;
    private SQLiteDatabase db;

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
            helper = new StarbuzzDatabaseHelper(this);
            db = helper.getWritableDatabase();
            Cursor cursor = db.query("drink", new String[] {"name", "description", "image_resource_id", "favorite"}, "_id = ?", new String[] {Integer.toString(id)}, null, null, null);
            if (cursor.moveToFirst()){
                nameView.setText(cursor.getString(cursor.getColumnIndex("name")));
                descriptionView.setText(cursor.getString(cursor.getColumnIndex("description")));
                imageView.setImageResource(cursor.getInt(cursor.getColumnIndex("image_resource_id")));
                favorCheckbox = (CheckBox)findViewById(R.id.favorite_checkbox);
                favorCheckbox.setChecked(cursor.getInt(cursor.getColumnIndex("favorite")) == 1);
            }
            cursor.close();
            //db.close();
        }catch (SQLiteException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
        favorCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*ContentValues checkboxValue = new ContentValues();
                checkboxValue.put("favorite", favorCheckbox.isChecked());
                try {
                    db.update("drink", checkboxValue, "_id=?", new String[]{Integer.toString(id)});
                } catch (SQLiteException e) {
                    Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT).show();
                }*/
                new UpdateFavoriteTask().execute(id);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private class UpdateFavoriteTask extends AsyncTask<Integer, Void, Boolean>{
        ContentValues checkboxValue;

        @Override
        protected void onPreExecute() {
            checkboxValue = new ContentValues();
            checkboxValue.put("favorite", favorCheckbox.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int drinkId = integers[0];
            try{
                db.update("drink", checkboxValue, "_id = ?", new String[] {Integer.toString(drinkId)});
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(!aBoolean){
                Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

