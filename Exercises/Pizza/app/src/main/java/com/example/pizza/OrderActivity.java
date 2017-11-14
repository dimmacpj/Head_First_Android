package com.example.pizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private String orderDetail;
    public static final String ORDER_NAME = "order name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        TextView orderText = (TextView) findViewById(R.id.order_text);
        orderDetail = getIntent().getStringExtra(ORDER_NAME);
        if(orderDetail != null){
            orderText.setText("You want to order a " + orderDetail + ".");
        }else {
            orderText.setText("You need to chooes at least one item.");
        }
    }
}
