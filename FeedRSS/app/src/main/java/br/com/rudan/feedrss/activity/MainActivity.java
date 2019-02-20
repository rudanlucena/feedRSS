package br.com.rudan.feedrss.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.rudan.feedrss.util.AlarmUtil;
import br.com.rudan.feedrss.model.Noticia;
import br.com.rudan.feedrss.adapter.NoticiaAdapter;
import br.com.rudan.feedrss.R;
import br.com.rudan.feedrss.parse.SaxFeedParser;
import br.com.rudan.feedrss.service.NoticiaService;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SaxFeedParser sfp;
    private ListView listNoticias;
    public static List<Noticia> noticias;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            noticias  = (ArrayList<Noticia>) intent.getSerializableExtra("noticias");
            carregarFeed(noticias);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(receiver, new IntentFilter("LISTAR"));

        listNoticias = findViewById(R.id.listNoticias);

        startService(new Intent(this, NoticiaService.class));
    }

    public void carregarFeed(final List<Noticia> noticias){
        listNoticias.setAdapter( new NoticiaAdapter(this, noticias));
        listNoticias.setOnItemClickListener(this);
        agendarAlarm();
    }

    public void onItemClick(AdapterView<?> parent, View view, int idx, long id){
        Noticia n = this.noticias.get(idx);
        Toast.makeText(this, "Noticia: "+n.toString(), Toast.LENGTH_LONG).show();
    }

    public long getTime(){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis((System.currentTimeMillis()));
        c.add(Calendar.SECOND, 60 * 5);
        long time = c.getTimeInMillis();
        return  time;
    }

    public  void agendarAlarm(){
        Intent intent = new Intent("BUSCARATUALIZACAO");
        AlarmUtil.scheduleRepeat(this, intent, getTime(), 5*60*1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
