package org.app;


import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Sujeto {
    public void agregarObservador(Observador observador);
    public void quitarObservador(Observador observador);
    public void notificarObservadores();
    public CompletableFuture<List<Article>> getFuture();
    public String getConsulta();
    public void refrescarEstado();
    public void run();
}

