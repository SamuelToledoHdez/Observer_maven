package org.app;

public abstract class Categoria {

    Categoria(String categoria){
        cat = categoria;
    }
    public String getCat() {
        return cat;
    }

    String cat;

}
