package com.example.pizza;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class PizzaDetailActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_PIZZANO = "pizzaNo";
    private TextView nameText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        int pizzaNo = (Integer) getIntent().getExtras().get(EXTRA_PIZZANO);
        String pizzaName = Pizza.pizzas[pizzaNo].getName();
        nameText = (TextView)findViewById(R.id.pizza_text);
        nameText.setText(pizzaName);
        imageView = (ImageView) findViewById(R.id.pizza_image);
        imageView.setImageResource(Pizza.pizzas[pizzaNo].getImageResourceId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        String pizzaName = nameText.getText().toString();
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, pizzaName);
        shareActionProvider.setShareIntent(intent);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_order:
                Intent intent = new Intent(PizzaDetailActivity.this, OrderActivity.class);
                intent.putExtra(OrderActivity.ORDER_NAME, nameText.getText().toString());
                startActivity(intent);
                break;
            case R.id.action_settings:
                break;
            default:
                break;
        }
        return true;
    }
}
