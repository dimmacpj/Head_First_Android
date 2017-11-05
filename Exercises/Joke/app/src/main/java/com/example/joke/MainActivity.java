package com.example.joke;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //private NotificationUtils nu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nu = new NotificationUtils(this);

        Button startButton = (Button)findViewById(R.id.start_service_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DelayedMessageService.class);
                intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.button_response));
                startService(intent);

                //String title = "this is title";
                //String body = "this is body";
                //Notification.Builder nb = nu.getNotification(title, body);
                //nu.getManager().notify(200, nb.build());
            }
        });
    }
}
