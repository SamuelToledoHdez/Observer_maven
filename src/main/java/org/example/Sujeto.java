package org.example;

public interface Sujeto {
    void agregarObservador(Observer observador);
    void quitarObservador(Observer observador);
    void notificarObservadores();
}

