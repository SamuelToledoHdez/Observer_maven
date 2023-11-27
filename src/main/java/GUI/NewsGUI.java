package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsGUI extends JFrame {
    public NewsGUI(ArrayList<String> noticias, String tituloPagina) {
        // Configurar la ventana
        setTitle("Noticias - " + tituloPagina);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setIconImage(new ImageIcon("src/main/img/nalogo.png").getImage());
        setResizable(false);

        JPanel panelPrincipal = crearPanelPrincipal(tituloPagina);
        JPanel panelNoticias = crearPanelNoticias(noticias);
        panelPrincipal.add(new JScrollPane(panelNoticias), BorderLayout.CENTER);
        panelNoticias.setBackground(new Color(222, 222, 216));
        panelNoticias.setOpaque(true);


        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearPanelPrincipal(String tituloPagina) {
        JPanel panelCabecera = crearPanelCabecera(tituloPagina);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelCabecera.setBackground(new Color(222, 222, 216));
        panelCabecera.setOpaque(true);

        panelPrincipal.add(panelCabecera, BorderLayout.NORTH);

        return panelPrincipal;
    }

    private JPanel crearPanelCabecera(String tituloPagina) {
        JPanel panelCabecera = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel labelCabecera = new JLabel(tituloPagina);
        labelCabecera.setFont(new Font("Roboto", Font.BOLD, 18));
        panelCabecera.add(labelCabecera);

        ImageIcon iconoLogo = getLogo();
        if (iconoLogo != null) {
            JLabel labelLogo = new JLabel(iconoLogo);
            panelCabecera.add(labelLogo);
        }

        return panelCabecera;
    }

    private JPanel crearPanelNoticias(ArrayList<String> noticias) {
        JPanel panelNoticias = new JPanel();
        panelNoticias.setLayout(new BoxLayout(panelNoticias, BoxLayout.Y_AXIS));
        for (String noticia : noticias) {
            JPanel panelTituloNoticia = crearPanelTituloNoticia(noticia);

            // Agregar MouseListener para resaltar al pasar el ratón
            panelTituloNoticia.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    panelTituloNoticia.setBackground(new Color(160, 158, 149)); // Cambiar color al pasar el ratón
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    panelTituloNoticia.setBackground(new Color(222, 222, 216)); // Restaurar color al salir del ratón
                }
            });
            panelNoticias.add(panelTituloNoticia);
            panelNoticias.add(Box.createRigidArea(new Dimension(5, 10)));
        }
        return panelNoticias;
    }

    // En el método crearPanelTituloNoticia, para establecer el color inicial
    private JPanel crearPanelTituloNoticia(String noticia) {
        String titulo = extraerTituloNoticia(noticia);

        JPanel panelTituloNoticia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTituloNoticia.setBackground(new Color(222, 222, 216));
        panelTituloNoticia.setBorder(BorderFactory.createLineBorder(new Color(200, 195, 179), 1));

        // Agregar MouseListener para resaltar al pasar el ratón
        panelTituloNoticia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelTituloNoticia.setBackground(new Color(200, 200, 196)); // Cambiar color al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelTituloNoticia.setBackground(new Color(222, 222, 216)); // Restaurar color al salir del ratón
            }
        });

        JLabel labelTituloNoticia = new JLabel(titulo);
        configurarEstiloLabelTitulo(labelTituloNoticia, noticia);

        panelTituloNoticia.add(labelTituloNoticia);
        return panelTituloNoticia;
    }

    private void configurarEstiloLabelTitulo(JLabel labelTituloNoticia, String noticia) {
        labelTituloNoticia.setForeground(Color.BLACK);
        labelTituloNoticia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelTituloNoticia.setFont(new Font("Roboto", Font.BOLD, 14));
    }

    ImageIcon getLogo() {
        try {
            ImageIcon originalIcon = new ImageIcon("src/main/img/logo.png");
            Image resizedImage = originalIcon.getImage().getScaledInstance(130, 35, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String extraerTituloNoticia(String noticia) {
        // Se considera título todo lo que está antes del primer punto de la noticia
        String regex = "^(.*?\\.)";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(noticia);

        // Extraer el resultado si hay una coincidencia
        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        // Si no hay coincidencia, devolver toda la primera línea (o toda la noticia si no hay línea)
        String[] lineas = noticia.split("\n");
        return lineas.length > 0 ? lineas[0].trim() : noticia.trim();
    }


}
