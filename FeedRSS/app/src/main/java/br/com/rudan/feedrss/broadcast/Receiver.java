package br.com.rudan.feedrss.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.rudan.feedrss.model.Noticia;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        List<Noticia> noticias  = (ArrayList<Noticia>) intent.getSerializableExtra("noticias");
        Intent intent1 = new Intent("LISTAR");
        intent1.putExtra("noticias", (Serializable) noticias);
        context.sendBroadcast(intent1);
    }

}
