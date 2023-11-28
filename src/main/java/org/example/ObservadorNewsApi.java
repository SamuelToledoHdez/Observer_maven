package org.example;

import GUI.NewsGUI;
import com.kwabenaberko.newsapilib.models.Article;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ObservadorNewsApi implements Observador{
    Sujeto sujeto;
    String name;
    NewsGUI newsGUI;
    ArrayList<Article> noticias;
    ObservadorNewsApi(Sujeto sujeto, String name) {
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
