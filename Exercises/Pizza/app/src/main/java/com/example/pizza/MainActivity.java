package com.example.pizza;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView drawerList;

    private DrawerLayout mDrawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch ((int)l){
                    case 1:
                        replaceFrag(new PizzaMaterialFragment());
                        getSupportActionBar().setTitle("Pizzas");
                        break;
                    case 2:
                        replaceFrag(new PastaMaterialFragment());
                        getSupportActionBar().setTitle("Pasta");

                        break;
                    case 3:
                        replaceFrag(new StoreFragment());
                        getSupportActionBar().setTitle("Stores");
                        break;
                    default:
                        replaceFrag(new TopFragment());
                        getSupportActionBar().setTitle("Bits and Pizzas");
                }
            }
        });
        replaceFrag(new TopFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerlayout.openDrawer(Gravity.START);
                break;
            case R.id.action_create_order:
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                break;
            default:
                break;
        }
        return true;
    }

    private void replaceFrag(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_layout, fragment);
        ft.addToBackStack(null);
        ft.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        mDrawerlayout.closeDrawers();
    }
}
