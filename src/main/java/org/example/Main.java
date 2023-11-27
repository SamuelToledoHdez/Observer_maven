package org.example;

import com.kwabenaberko.newsapilib.NewsApiClient;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String apiKey = "895c1b9e570349cc830c4571482d4758";

        NewsApiParser newsApiParser = new NewsApiParser(apiKey);

        // Obtener noticias de todo ("everything") con la palabra clave "trump"
        newsApiParser.parseEverything("trump");

    }
}