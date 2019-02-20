package br.com.rudan.feedrss.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.rudan.feedrss.service.AtualizacaoService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver","Alarm Receiver Chamado");
        context.startService(new Intent(context, AtualizacaoService.class));

    }
}
