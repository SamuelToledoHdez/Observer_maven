package org.example;


import org.example.Observador;

public interface Sujeto {
    void agregarObservador(Observador observador);
    void quitarObservador(Observador observador);
    void notificarObservadores();
}

