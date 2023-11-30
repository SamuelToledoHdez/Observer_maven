package GUI;

import org.app.ObservadorNewsApi;
import org.app.Sujeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioGUI extends JFrame {

    private Sujeto sujeto;

    public UsuarioGUI(Sujeto sujeto) {
        this.sujeto = sujeto;

        // Configuración de la interfaz gráfica
        setTitle("Selección de Categoría");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Crear botones para cada categoría
        for (Categoria categoria : Categoria.values()) {
            JButton button = new JButton(categoria.getCat());
            button.addActionListener(new BotonCategoriaListener(categoria));
            add(button);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class BotonCategoriaListener implements ActionListener {
        private Categoria categoria;

        public BotonCategoriaListener(Categoria categoria) {
            this.categoria = categoria;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Agregar el observador correspondiente al sujeto
            sujeto.agregarObservador(new ObservadorNewsApi(sujeto, "NombreDeUsuario", categoria));
            JOptionPane.showMessageDialog(UsuarioGUI.this, "Observador añadido para la categoría: " + categoria.getCat());
        }
    }


}