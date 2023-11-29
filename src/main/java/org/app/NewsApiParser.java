package org.app;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CompletableFuture;


public class NewsApiParser {

    private final NewsApiClient newsApiClient;

    public NewsApiParser(String apiKey) {
        this.newsApiClient = new NewsApiClient(apiKey);
    }

    public void parseEverything(String query) {
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println("Everything Articles:");
                        response.getArticles().forEach(article -> System.out.println(article.getTitle()));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Error fetching everything articles: " + throwable.getMessage());
                    }
                }
        );
    }

    public void parseTopHeadlines(String query, String language) {
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q(query)
                        .language(language)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println("Top Headlines Articles:");
                        response.getArticles().forEach(article -> System.out.println(article.getTitle()));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Error fetching top headlines articles: " + throwable.getMessage());
                    }
                }
        );
    }

    public void parseSources(String language, String country) {
        newsApiClient.getSources(
                new SourcesRequest.Builder()
                        .language(language)
                        .country(country)
                        .build(),
                new NewsApiClient.SourcesCallback() {
                    @Override
                    public void onSuccess(SourcesResponse response) {
                        System.out.println("Sources:");
                        response.getSources().forEach(source -> System.out.println(source.getName()));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Error fetching sources: " + throwable.getMessage());
                    }
                }
        );
    }

    public Article searchNew(String query, String newTitle) {
        final AtomicReference<Article> foundArticle = new AtomicReference<>();

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println("Everything Articles:");
                        response.getArticles().forEach(article -> {
                            System.out.println(article.getTitle());
                            if (article.getTitle() != null && article.getTitle().equals(newTitle)) {
                                foundArticle.set(article); // Establece el artículo encontrado
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Error fetching everything articles: " + throwable.getMessage());
                    }
                }
        );

        return foundArticle.get(); // Devuelve el artículo encontrado (puede ser null si no se encuentra)
    }

    public CompletableFuture<Map<String, String>> parseEverythingToMap(String query) {
        CompletableFuture<Map<String, String>> future = new CompletableFuture<>();

        Map<String, String> articlesMap = new HashMap<>();

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println("Everything Articles:");

                        // Iterar sobre los artículos y guardar en el mapa por título
                        response.getArticles().forEach(article -> {
                            articlesMap.put(article.getTitle(), article.getUrl());
                        });

                        // Completar el futuro con el mapa de artículos
                        future.complete(articlesMap);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Error fetching everything articles: " + throwable.getMessage());
                        // Completar el futuro con un error
                        future.completeExceptionally(throwable);
                    }
                }
        );

        // Devolver el CompletableFuture que se completará en el futuro
        return future;
    }

    public CompletableFuture<List<Article>> parseEverythingToList(String query) {
        CompletableFuture<List<Article>> future = new CompletableFuture<>();

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .sortBy("publishedAt")
                        .pageSize(100)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println("Buscando nuevos articulos ...");

                        // Obtener la lista de artículos y completar el futuro
                        future.complete(response.getArticles());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Error fetching everything articles: " + throwable.getMessage());
                        // Completar el futuro con un error
                        future.completeExceptionally(throwable);
                    }
                }
        );

        // Devolver el CompletableFuture que se completará en el futuro
        return future;
    }
}


class ArticleUtils {

    public static Article findByTitle(List<Article> articles, String title) {
        for (Article article : articles) {
            if (article.getTitle().equals(title)) {
                return article;
            }
        }
        return null; // Retorna null si no se encuentra ninguna coincidencia
    }
}
