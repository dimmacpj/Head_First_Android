package com.example.beeradviser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView brands;
    private Button findBeer;
    private Spinner beerColors;
    private String beerType;
    private BeerExpert be = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brands = (TextView)findViewById(R.id.brands);
        findBeer = (Button)findViewById(R.id.find_beer);
        beerColors = (Spinner)findViewById(R.id.color);
    }

    public void onClickFindBeer(View view){
        beerType = String.valueOf(beerColors.getSelectedItem());
        //brands.setText(beerType);
        List<String> brandsList = be.getBrands(beerType);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < brandsList.size(); i++){
            builder.append(brandsList.get(i)).append("\n");
        }
        brands.setText(builder);

    }

}
