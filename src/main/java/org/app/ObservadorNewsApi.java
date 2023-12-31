package org.app;

import GUI.NewsGUI;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;

public class ObservadorNewsApi implements Observador{
    Sujeto sujeto;

    public String getName() {
        return name;
    }

    String name;
    NewsGUI newsGUI;
    ArrayList<Article> noticias;
    public ObservadorNewsApi(Sujeto sujeto, String name) {
        this.sujeto = sujeto;
        this.name = name;
        try {
            noticias = (ArrayList<Article>) sujeto.getFuture().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newsGUI = new NewsGUI(noticias, sujeto.getConsulta());
    }
    public void update() {
        try {
           noticias = (ArrayList<Article>) sujeto.getFuture().get();
           newsGUI.update(noticias, sujeto.getConsulta());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String toString(){
        return "Observador " + this.name;
    }

}
