package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        textView = (TextView)findViewById(R.id.show_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message_data");
        textView.setText(message);
    }
}
