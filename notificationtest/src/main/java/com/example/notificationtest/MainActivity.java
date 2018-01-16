package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_notice :
//                Intent intent = new Intent(MainActivity.this, com.example.notificationtest.Notification.class);
                PendingIntent pi = PendingIntent.getActivity(this, 1, new Intent(), 0);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是标题")
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("这是内容!这是内容!这是内容!这是内容!这是内容!" +
//                                "这是内容!这是内容!这是内容!这是内容!这是内容!" +
//                                "这是内容!这是内容!这是内容!这是内容!这是内容!" +
//                                "这是内容!这是内容!这是内容!这是内容!这是内容!" +
//                                "这是内容!这是内容!这是内容!这是内容!这是内容!"))
                        .setContentText("这是内容!这是内容!这是内容!这是内容!这是内容!")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)
//                        .setVibrate(new long[]{0, 1000, 3000, 500})
//                        .setLights(Color.GREEN , 1000, 1000)
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Lyra.ogg")))
//                        .setSound(Uri.parse("/system/media/audio/ringtones/Lyra.ogg"))//自己写的，第一行代码上不是这么写的
//                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.aaa)))
//                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setFullScreenIntent(pi,true)
                        .build();
                manager.notify(10,notification);
                break;
            default:
                break;
        }
    }
}
