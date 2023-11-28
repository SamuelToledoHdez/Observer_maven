package org.example;

import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



public class SujetoConcreto implements Sujeto {
    private List<Observador> observadores = new ArrayList<>();
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
    public void agregarObservador(Observador observador) {
        System.out.println("Agregando " + observador + " a la lista de observadores");
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        System.out.println("Quitando observador" + observador + " de la lista de observadores");
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.update();
        }
    }

    public void refrescarEstado() {
        future = newsApiParser.parseEverythingToList(consulta);
        notificarObservadores();
    }

    public CompletableFuture<List<Article>> getFuture(){
        return this.future;
    }
    public String getConsulta(){
        return this.consulta;
    }


}



