package org.example;

public class ObservadorNewsApi implements Observador{
    Sujeto sujeto;
    String name;
    ObservadorNewsApi(Sujeto sujeto, String name){
        this.sujeto = sujeto;
        this.name = name;
    }
    public void update() {


    }
    public String toString(){
        return "Observador " + this.name;
    }

}
