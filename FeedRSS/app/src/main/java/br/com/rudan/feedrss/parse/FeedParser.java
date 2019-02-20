package br.com.rudan.feedrss.parse;

import java.util.List;

import br.com.rudan.feedrss.model.Noticia;

public interface FeedParser {
    List<Noticia> parse();
}
