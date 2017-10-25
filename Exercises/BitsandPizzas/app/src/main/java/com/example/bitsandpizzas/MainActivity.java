package com.example.bitsandpizzas;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;

    private ListView drawerList;

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch ((int) l){
                    case 1:
                        replaceFrag(new PizzaFragment());
                        getActionBar().setTitle("Pizzas");
                        break;
                    case 2:
                        replaceFrag(new PastaFragment());
                        getActionBar().setTitle("Pasta");
                        break;
                    case 3:
                        replaceFrag(new StoreFragment());
                        getActionBar().setTitle("Stores");
                        break;
                    default:
                        replaceFrag(new TopFragment());
                        getActionBar().setTitle("Bits and Pizzas");
                        break;
                }
            }
        });
        if(savedInstanceState == null){
            replaceFrag(new TopFragment());
            getActionBar().setTitle("Bits and Pizzas");
        }
    }

    private void replaceFrag(android.app.Fragment fragment){
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_layout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        mDrawerLayout.closeDrawer(drawerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.actionBar_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setIntent("This is example text");
        return super.onCreateOptionsMenu(menu);
    }

    private void setIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.actionBar_createOrder:
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.actionBar_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
