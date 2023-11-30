package org.app;

import com.kwabenaberko.newsapilib.models.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SujetoConcreto implements Sujeto {
    public List<Observador> getObservadores() {
        return observadores;
    }

    private List<Observador> observadores = new ArrayList<>();
    private String consulta;
    private NewsApiParser newsApiParser;
    private CompletableFuture<List<Article>> future;
    private Timer timer;


    public SujetoConcreto(String consulta) {
        this.consulta = consulta;
        String apiKey = "895c1b9e570349cc830c4571482d4758";
        newsApiParser = new NewsApiParser(apiKey);
        future = newsApiParser.parseEverythingToList(consulta);
    }

    public void run() {
        timer = new Timer(REFRESH_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backGroundRefresh();
            }
        });
        timer.start();
    }

    @Override
    public void agregarObservador(Observador observador) {
        System.out.println("Agregando " + observador + " a la lista de observadores");
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        System.out.println("Quitando observador " + observador + " de la lista de observadores");
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

    public CompletableFuture<List<Article>> getFuture() {
        return this.future;
    }

    public String getConsulta() {
        return this.consulta;
    }

    protected void backGroundRefresh() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                refrescarEstado();
                return null;
            }

            @Override
            protected void done() {
                // Puedes realizar acciones después de que la tarea en segundo plano haya finalizado, si es necesario
            }
        };
        worker.execute();
    }

    final public static int REFRESH_TIME = 10000;
}
