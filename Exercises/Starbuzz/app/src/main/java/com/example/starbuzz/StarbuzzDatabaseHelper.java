package com.example.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by neal on 27/10/17.
 */

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_DRINK = "create table drink ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "description text, "
            + "image_resource_id integer) ";

    private static final String DB_NAME = "startbuzz";
    private static final int DB_VERSION = 2;

    StarbuzzDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);
    }

    private static void insertDrink(SQLiteDatabase db, String name, String discription, int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("name", name);
        drinkValues.put("description", discription);
        drinkValues.put("image_resource_id", resourceId);
        db.insert("drink", null, drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){
            db.execSQL(CREATE_DRINK);
            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }
        if(oldVersion < 2){
            db.execSQL("alter table drink add column favorite numeric;");
        }
    }
}
