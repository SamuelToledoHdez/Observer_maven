package org.example;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;


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

    // Otros métodos según sea necesario
}