package br.com.rudan.feedrss.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmUtil {
    public static void scheduleRepeat(Context context, Intent intent, long triggerAtMillis, long intervalMillis){
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarme.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, p);
        Log.d("alarm", "alarm criado");
    }
}
