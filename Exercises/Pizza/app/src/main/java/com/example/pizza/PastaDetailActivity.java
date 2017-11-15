package com.example.pizza;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class PastaDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PASTANO = "pasta no";
    private ShareActionProvider shareActionProvider;
    private TextView pastaText;
    private ImageView pastaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int pastaNo = (Integer) getIntent().getExtras().get(EXTRA_PASTANO);
        pastaText = (TextView)findViewById(R.id.pasta_text);
        pastaText.setText(Pasta.pastas[pastaNo].getName());
        pastaImage = (ImageView) findViewById(R.id.pasta_image);
        pastaImage.setImageResource(Pasta.pastas[pastaNo].getImageId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_order:
                Intent intent = new Intent(PastaDetailActivity.this, OrderActivity.class);
                intent.putExtra(OrderActivity.ORDER_NAME, pastaText.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
