package org.example;

import GUI.NewsGUI;

import java.util.ArrayList;

public class MainPruebaGUI {
      public static void main(String[] args) {
          ArrayList<String> noticias = new ArrayList<>();
          noticias.add("Noticias deportivas. Tratan sobre sucesos vinculados con los distintos deportes que se practican en una comunidad, por lo general dando prioridad a los más populares. Por ejemplo:");
          String titulo = new String("Actualidad");
          NewsGUI gui = new NewsGUI(noticias, titulo);
    }


}
