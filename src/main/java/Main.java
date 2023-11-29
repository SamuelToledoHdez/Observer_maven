import org.app.*;

public class Main {
    public static void main(String[] args) {
        // Contexto 1
        Sujeto sujetoHacking = new SujetoConcreto("hacking");
        sujetoHacking.agregarObservador(new ObservadorNewsApi(sujetoHacking, "Sergio"));
        sujetoHacking.agregarObservador(new ObservadorNewsApi(sujetoHacking, "Jorge"));

        // Contexto 2
        /*Sujeto sujetoPatrones = new SujetoConcreto("patrones de diseño");
        sujetoPatrones.agregarObservador(new ObservadorNewsApi(sujetoPatrones, "Samuel"));
        sujetoPatrones.agregarObservador(new ObservadorNewsApi(sujetoPatrones, "Francesco"));*/

        sujetoHacking.run();
        //sujetoPatrones.run();


    }
}
