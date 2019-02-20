package br.com.rudan.feedrss.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import br.com.rudan.feedrss.R;

public class NotificationUtil {
    private  static PendingIntent getpendingIntent(Context context, Intent intent, int id){
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    public static  void create(Context context, Intent intent, String contentTitle, String contentText, int id){
        PendingIntent p = getpendingIntent(context, intent, id);
        Notification.Builder b = new Notification.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL);
        b.setSmallIcon(R.drawable.ic_launcher_background);
        b.setContentTitle(contentTitle);
        b.setContentText(contentText);
        b.setContentIntent(p);
        b.setAutoCancel(true);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public void cancell(Context context, int id){
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public void cancellAll(Context context){
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
