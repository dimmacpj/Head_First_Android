package com.example.joke;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;

/**
 * Created by neal on 3/11/17.
 */

public class NotificationUtils extends ContextWrapper {

    private NotificationManager nManager;
    public static final String CHANNEL_NAME = "Test";
    public static final String CHANNEL_ID = "Channel ID";

    public NotificationUtils(Context mContext){
        super(mContext);
        createChannel();
    }

    public void createChannel(){
        NotificationChannel nc = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        //nc.enableLights(true);
        //nc.setLightColor(Color.GREEN);
        getManager().createNotificationChannel(nc);
    }

    public NotificationManager getManager(){
        if(nManager == null){
            nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return nManager;
    }

    public Notification.Builder getNotification(String title, String body){
        return new Notification.Builder(getApplicationContext(), CHANNEL_ID).setContentTitle(title).setContentText(body).setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true);
    }
}
