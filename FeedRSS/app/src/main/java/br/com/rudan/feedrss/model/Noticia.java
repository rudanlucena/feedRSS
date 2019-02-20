package br.com.rudan.feedrss.model;

import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Noticia implements Comparable<Noticia>, Serializable {

    public static DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private String title;
    private URL link;
    private Date published;
    private String id;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link =  link;
//        try {
//            this.link = new URL(link);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    public Date getPublished() {
        return this.published;
        //return FORMATTER.format(this.published);
    }

    public void setPublished(String published) {
        try {
            this.published = FORMATTER.parse(published);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return Objects.equals(title, noticia.title) &&
                Objects.equals(link, noticia.link) &&
                Objects.equals(published, noticia.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, published);
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "title='" + title + '\'' +
                ", link=" + link +
                ", published=" + published +
                '}';
    }

    @Override
    public int compareTo(Noticia o) {
        if (o == null) return 1;
        // sort descending, most recent first
        return o.published.compareTo(published);
    }
}
