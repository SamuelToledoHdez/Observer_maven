package org.example;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        SujetoConcreto sujetoFerrari = new SujetoConcreto("world");
        sujetoFerrari.agregarObservador(new ObservadorNewsApi(sujetoFerrari, "ob2"));
        sujetoFerrari.run();




    }
}
