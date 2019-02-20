package br.com.rudan.feedrss.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.rudan.feedrss.activity.MainActivity;
import br.com.rudan.feedrss.model.Noticia;
import br.com.rudan.feedrss.util.NotificationUtil;

public class NotificarAtualizacaoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        List<Noticia> noticias;
        noticias  = (ArrayList<Noticia>) intent.getSerializableExtra("noticias");

        if(!MainActivity.noticias.equals(noticias)){

            Intent notifIntent = new Intent(context, MainActivity.class);

            Intent intentAtualizarFeed = new Intent("LISTAR");
            intentAtualizarFeed.putExtra("noticias", (Serializable) noticias);
            MainActivity.noticias = noticias;
            context.sendBroadcast(intentAtualizarFeed);
            NotificationUtil.create(context, notifIntent, "Novas Noticias", "você tem novas noticias não lidas", 1 );
        }
    }

}
