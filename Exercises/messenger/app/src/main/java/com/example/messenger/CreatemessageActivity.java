package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreatemessageActivity extends AppCompatActivity {

    private Button sendMessage;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmessage);
        sendMessage = (Button)findViewById(R.id.send_message);
        editText = (EditText)findViewById(R.id.message);
    }

    public void onSendMessage(View view){
        String message = editText.getText().toString();
        //Intent intent = new Intent(CreatemessageActivity.this, ReceiveMessageActivity.class);
        //intent.putExtra("message_data",message);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        String chooserTitle = getString(R.string.chooser);
        Intent chooserIntent = Intent.createChooser(intent,chooserTitle);
        startActivity(chooserIntent);
    }
}
