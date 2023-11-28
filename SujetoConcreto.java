package org.example;

import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class SujetoConcreto implements Sujeto {
    private List<Observer> observadores = new ArrayList<>();
    private String consulta;
    NewsApiParser newsApiParser;
    private CompletableFuture<List<Article>> future;


    public SujetoConcreto(String consulta){
        this.consulta = consulta;
        String apiKey = "895c1b9e570349cc830c4571482d4758";
        newsApiParser = new NewsApiParser(apiKey);
        future = newsApiParser.parseEverythingToList(consulta);

    }


    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.actualizar(estado);
        }
    }

    public void refrescarEstado() {
        future = newsApiParser.parseEverythingToList(consulta);
        notificarObservadores();
    }


}



