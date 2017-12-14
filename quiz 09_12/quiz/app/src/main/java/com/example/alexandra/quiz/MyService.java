package com.example.alexandra.quiz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;

public class MyService extends Service {
    NotificationManager nm;
    private MyTimer timer;
    private static final int NOTIFY_ID = 101;

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        timer = new MyTimer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel(false);

    }

    private class MyTimer extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while (!this.isCancelled()) {
                Context context = getApplicationContext();
                Intent notificationIntent = new Intent(context, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                Resources res = context.getResources();
                Notification.Builder builder = new Notification.Builder(context);
                builder.setContentIntent(contentIntent)
                        .setSmallIcon(R.drawable.cloud)
                        .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.cloud))
                        .setTicker("Ещё какой-то текст")
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentTitle("ПОИГРАЙ СО МНОЙ")
                        .setContentText("ТЫ ЗАЧЕМ ВЫШЕЛ? ПОИГРАЙ СО МНОЙ");
                Notification notification = builder.build();
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFY_ID, notification);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException i) {
                }
            }
            return null;
        }
    }
}