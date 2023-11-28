package org.example;

import GUI.NewsGUI;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ObservadorNewsApi implements Observador{
    Sujeto sujeto;
    String name;
    NewsGUI newsGUI;
    ObservadorNewsApi(Sujeto sujeto, String name){
        this.sujeto = sujeto;
        this.name = name;
    }
    public void update() {
        try {
            ArrayList<Article> noticias = (ArrayList<Article>) sujeto.getFuture().get();
            newsGUI = new NewsGUI(noticias, sujeto.getConsulta());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public String toString(){
        return "Observador " + this.name;
    }

}
