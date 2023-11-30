package GUI;

import org.app.ObservadorNewsApi;
import org.app.Sujeto;
import org.app.SujetoConcreto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioGUI extends JFrame {

    private Sujeto sujeto;
    private String categoriaSeleccionada;

    public UsuarioGUI() {


        setTitle("Selección de Categoría");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        ButtonGroup buttonGroup = new ButtonGroup();

        // Crear botones tipo JRadioButton para cada categoría
        JRadioButton cineButton = new JRadioButton("Cine");
        cineButton.addActionListener(new BotonCategoriaListener("Cine"));
        buttonGroup.add(cineButton);
        add(cineButton);

        JRadioButton deportesButton = new JRadioButton("Deportes");
        deportesButton.addActionListener(new BotonCategoriaListener("Deportes"));
        buttonGroup.add(deportesButton);
        add(deportesButton);

        JRadioButton disenoButton = new JRadioButton("Diseño");
        disenoButton.addActionListener(new BotonCategoriaListener("Diseño"));
        buttonGroup.add(disenoButton);
        add(disenoButton);

        JRadioButton motorButton = new JRadioButton("Motor");
        motorButton.addActionListener(new BotonCategoriaListener("Motor"));
        buttonGroup.add(motorButton);
        add(motorButton);

        JRadioButton saludButton = new JRadioButton("Salud");
        saludButton.addActionListener(new BotonCategoriaListener("Salud"));
        buttonGroup.add(saludButton);
        add(saludButton);


        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ConfirmarButtonListener());
        add(confirmarButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class BotonCategoriaListener implements ActionListener {
        private String categoriaNombre;

        public BotonCategoriaListener(String categoriaNombre) {
            this.categoriaNombre = categoriaNombre;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Actualizar la categoría seleccionada
            categoriaSeleccionada = categoriaNombre;
            JOptionPane.showMessageDialog(UsuarioGUI.this, "Categoría seleccionada: " + categoriaNombre);
        }
    }

    private class ConfirmarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (categoriaSeleccionada != null) {
                // Agregar el observador correspondiente al sujeto
                Sujeto sujetoConcreto = new SujetoConcreto(categoriaSeleccionada);
                sujetoConcreto.agregarObservador(new ObservadorNewsApi(sujetoConcreto, "NombreDeUsuario"));

                dispose();
            } else {
                JOptionPane.showMessageDialog(UsuarioGUI.this, "Por favor, selecciona una categoría antes de confirmar.");
            }
        }
    }


}
