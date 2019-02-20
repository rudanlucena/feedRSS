package br.com.rudan.feedrss.parse;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.com.rudan.feedrss.model.Noticia;
import br.com.rudan.feedrss.parse.BaseFeedParser;
import br.com.rudan.feedrss.parse.RssHandler;

public class SaxFeedParser extends BaseFeedParser {
    public SaxFeedParser(String feedUrl){
        super(feedUrl);
    }

    public List<Noticia> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getNoticias();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
