package br.com.rudan.feedrss.parse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.rudan.feedrss.model.Noticia;

public class RssHandler extends DefaultHandler {
    private List<Noticia> noticias;
    private Noticia currentNoticia;
    private StringBuilder builder;

    public List<Noticia> getNoticias(){
        return this.noticias;
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        super.endElement(uri, localName, name);
        if (this.currentNoticia != null){
            if (localName.equalsIgnoreCase("title")){
                currentNoticia.setTitle(builder.toString());
            } else if (localName.equalsIgnoreCase("id")){
                try {
                    currentNoticia.setLink(new URL(builder.toString().trim()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (localName.equalsIgnoreCase("published")){
                currentNoticia.setPublished(builder.toString().trim());
            }
            else if (localName.equalsIgnoreCase("entry")){
                noticias.add(currentNoticia);
            }
            builder.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        noticias = new ArrayList<>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equalsIgnoreCase("entry")){
            this.currentNoticia = new Noticia();
        }
    }
}
