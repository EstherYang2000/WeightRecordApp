package com.example.weightrecord;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class AlarmBrodcast extends BroadcastReceiver {

        private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;
    private static final int WATER_REMINDER_PENDING_INTENT_ID = 3417;
    private static final String WATER_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String date = bundle.getString("time");

        //Click on Notification

        Intent intent1 = new Intent(context, NotificationMessage.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("message", date);
        //Notification Builder
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,WATER_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.browser_actions_bg_grey))
                .setSmallIcon(R.drawable.ic_baseline_history_edu_24)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
                notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());


//        Notification notification = mBuilder.build();
//        notificationManager.notify(1, notification);


    }
        private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_baseline_history_edu_24);
        return largeIcon;
    }
    private static PendingIntent contentIntent(Context context) {

        Intent startActivityIntent = new Intent(context, LoginActivity.class);

        return PendingIntent.getActivity(
                context,
                WATER_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
