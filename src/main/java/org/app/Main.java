package org.app;

public class Main {
    public static void main(String[] args) {
        SujetoConcreto sujetoFerrari = new SujetoConcreto("world");
        sujetoFerrari.agregarObservador(new ObservadorNewsApi(sujetoFerrari, "ob2"));
        sujetoFerrari.run();




    }
}
