package GUI;

import org.app.Observador;
import org.app.ObservadorNewsApi;
import org.app.Sujeto;
import org.app.SujetoConcreto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class AgregarUsuarioGUI extends JFrame {

    private Sujeto sujeto;

    public AgregarUsuarioGUI(Sujeto sujeto) {
        this.sujeto = sujeto;

        setTitle("Agregar Observador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nombreLabel = new JLabel("Nombre del usuario:");
        JTextField nombreField = new JTextField(20);
        JButton agregarButton = new JButton("Agregar Observador");

        JLabel quitarLabel = new JLabel("Quitar Observador:");
        JTextField quitarField = new JTextField(20);
        JButton quitarButton = new JButton("Quitar Observador");

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = nombreField.getText().trim();
                if (!nombreUsuario.isEmpty()) {
                    sujeto.agregarObservador(new ObservadorNewsApi(sujeto, nombreUsuario));
                    JOptionPane.showMessageDialog(AgregarUsuarioGUI.this, "Observador añadido para el usuario: " + nombreUsuario);
                } else {
                    JOptionPane.showMessageDialog(AgregarUsuarioGUI.this, "Por favor, ingresa un nombre de usuario.");
                }
            }
        });

        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = quitarField.getText().trim();
                if (!nombreUsuario.isEmpty()) {
                    quitarObservadorPorNombre(nombreUsuario);
                    JOptionPane.showMessageDialog(AgregarUsuarioGUI.this, "Observador quitado: " + nombreUsuario);
                } else {
                    JOptionPane.showMessageDialog(AgregarUsuarioGUI.this, "Por favor, ingresa un nombre de observador a quitar.");
                }
            }
        });



        add(nombreLabel);
        add(nombreField);
        add(agregarButton);

        add(quitarLabel);
        add(quitarField);
        add(quitarButton);




        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void quitarObservadorPorNombre(String nombre) {


        java.util.List<Observador> observadores = new ArrayList<>();
        observadores = sujeto.getObservadores();
        Iterator<Observador> iterator = observadores.iterator();
        while (iterator.hasNext()) {
            Observador observador = iterator.next();
            if (observador.getName().equals(nombre)) {
                iterator.remove();
                sujeto.quitarObservador(observador);
                break; // Rompe el bucle después de encontrar y quitar el observador
            }
        }
    }


}
