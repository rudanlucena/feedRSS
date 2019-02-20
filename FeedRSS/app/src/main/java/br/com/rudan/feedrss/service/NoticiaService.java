package br.com.rudan.feedrss.service;

import android.app.IntentService;
import android.content.Intent;

import java.io.Serializable;
import java.util.List;

import br.com.rudan.feedrss.model.Noticia;
import br.com.rudan.feedrss.parse.SaxFeedParser;

public class NoticiaService extends IntentService {
    List<Noticia> noticias;
    SaxFeedParser sfp;

    public NoticiaService() {
        super("IntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sfp = new SaxFeedParser("https://www.diariodosertao.com.br/feed/atom");
        noticias = sfp.parse();

        Intent intentEnviarLista = new Intent("LISTARNOTICIAS");
        intentEnviarLista.putExtra("noticias", (Serializable) noticias);

        sendBroadcast(intentEnviarLista);
    }
}
