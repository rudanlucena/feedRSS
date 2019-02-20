package br.com.rudan.feedrss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.rudan.feedrss.R;
import br.com.rudan.feedrss.model.Noticia;

public class NoticiaAdapter extends BaseAdapter {
    private final Context context;
    private final List<Noticia> noticias;

    public  NoticiaAdapter(Context context, List<Noticia> noticias){
        this.context = context;
        this.noticias = noticias;
    }

    @Override
    public int getCount() {
        return noticias != null ? noticias.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return noticias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_noticia, parent, false);
        TextView t = view.findViewById(R.id.textTituloNoticia);
        TextView data = view.findViewById(R.id.textDataNoticia);
        TextView link = view.findViewById(R.id.textLink);

        Noticia noticia = noticias.get(position);
        t.setText(noticia.getTitle());
        data.setText(noticia.getPublished().toString());
        link.setText(noticia.getLink().toString());
        return view;
    }
}
