package com.example.joke;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;




public class DelayedMessageService extends IntentService {

    //private NotificationUtils notificationUtils;

    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 1;
    //public static final int EXTRA_TEXT = 1;

    //private String mText;

    //private Handler handler;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    /*@Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case EXTRA_TEXT:
                        Toast.makeText(getApplicationContext(), mText, Toast.LENGTH_SHORT).show();
                }
            }
        };
        return super.onStartCommand(intent, flags, startId);
    }*/

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
                wait(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        //mText = "The message is " + text;
        //Message message = new Message();
        //message.what = EXTRA_TEXT;
        //handler.sendMessage(message);
        showText(text);
    }

    private void showText(final String text){
        //Log.v("DelayedMessageService", "The message is " + text);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String channel_id = "test_channel_01";
        String channelName = "test channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel nc = new NotificationChannel(channel_id, channelName, importance);
        nc.enableLights(true);
        nc.enableVibration(true);
        nc.setLightColor(Color.GREEN);
        notificationManager.createNotificationChannel(nc);

        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pi = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, channel_id).setSmallIcon(android.R.drawable.stat_notify_chat).setContentTitle(getString(R.string.app_name)).setAutoCancel(true).setContentIntent(pi).setContentText(text).build();
        notificationManager.notify(NOTIFICATION_ID, notification);
        /*notificationUtils = new NotificationUtils(this);
        String title = getString(R.string.app_name);
        String body = text;
        Notification.Builder nb = notificationUtils.getNotification(title, body);
        notificationUtils.getManager().notify(NOTIFICATION_ID, nb.build());*/
    }
}
