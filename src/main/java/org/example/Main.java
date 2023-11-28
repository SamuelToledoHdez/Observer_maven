package org.example;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String apiKey = "895c1b9e570349cc830c4571482d4758";

        NewsApiParser newsApiParser = new NewsApiParser(apiKey);

        // Obtener noticias de todo ("everything") con la palabra clave "trump"
        //newsApiParser.parseEverything("trump");

        //System.out.println(newsApiParser.searchNew("tesla", "Amazon Puts Alexa in the Driver’s Seat, Walmart Revs Up Package Delivery").getDescription());




        CompletableFuture<Map<String, String>> future = newsApiParser.parseEverythingToMap("trump");

        try {
            Map<String, String> result = future.get(); // Obtener el resultado del CompletableFuture
            System.out.println("Mapa de artículos completo:");
            result.forEach((title, url) -> System.out.println("Título: " + title + ", URL: " + url));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
