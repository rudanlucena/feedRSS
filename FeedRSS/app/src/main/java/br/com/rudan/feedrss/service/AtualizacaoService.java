package br.com.rudan.feedrss.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import br.com.rudan.feedrss.model.Noticia;
import br.com.rudan.feedrss.parse.SaxFeedParser;

public class AtualizacaoService extends IntentService {

    List<Noticia> noticias;
    SaxFeedParser sfp;

    public AtualizacaoService() {
        super("IntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("AtualizacaoSercove","Chamado");
        sfp = new SaxFeedParser("https://www.diariodosertao.com.br/feed/atom");
        noticias = sfp.parse();

        Intent intentEnviarLista = new Intent("NOTIFICARATUALIZACAO");
        intentEnviarLista.putExtra("noticias", (Serializable) noticias);

        sendBroadcast(intentEnviarLista);
    }
}
