package com.example.arpit.eckovation_gcm;

/**
 * Created by arpit on 24-10-2015.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;



public class NotificationUtils {

    private String TAG = NotificationUtils.class.getSimpleName();
    private Context mContext;
    static String[] events = new String[20];
    int i=0;
    SharedPreferences sharedpreferences ;

    public NotificationUtils() {
    }

    public NotificationUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void showNotificationMessage(String title, String message, Intent intent) {

        sharedpreferences = mContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
         i=sharedpreferences.getInt("number", 0);


        if (TextUtils.isEmpty(message))
            return;

        if(i>20)
            events[i]="You have more notifications";
        else
            events[i]=title;

        i++;

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("number",i);
        editor.commit();



            int icon = R.mipmap.ic_launcher;

            int mNotificationId = 100;

            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            mContext,
                            0,
                            intent,
                            PendingIntent.FLAG_CANCEL_CURRENT
                    );


       NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
       inboxStyle.setBigContentTitle("New messages");

         for (int j = 0; j < i; j++) {
                inboxStyle.addLine(events[j]);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
        Notification notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setStyle(inboxStyle)
                .setContentIntent(resultPendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentText(message)
                .build();

            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(mNotificationId, notification);


    }



}
