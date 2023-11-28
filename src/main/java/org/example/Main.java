package org.example;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        SujetoConcreto sujetoTesla = new SujetoConcreto("tesla");
        ObservadorNewsApi obs1 = new ObservadorNewsApi(sujetoTesla, "obs1");
        sujetoTesla.agregarObservador(new ObservadorNewsApi(sujetoTesla, "ob2"));
        sujetoTesla.agregarObservador(new ObservadorNewsApi(sujetoTesla,"ob3"));
        sujetoTesla.agregarObservador(obs1);
        sujetoTesla.quitarObservador(obs1);
        sujetoTesla.refrescarEstado();



    }
}
