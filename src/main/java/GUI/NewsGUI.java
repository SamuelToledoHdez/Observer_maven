package GUI;

import com.kwabenaberko.newsapilib.models.Article;
import org.example.SujetoConcreto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsGUI extends JFrame {
    private JPanel panelPrincipal;
    JPanel panelCabecera;
    JLabel labelCabecera;
    private JPanel panelNoticias;
    public NewsGUI(ArrayList<Article> noticias, String tituloSeccion) {
        // Configurar la ventana
        setTitle("Noticias - " + tituloSeccion);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setIconImage(new ImageIcon("src/main/img/nalogo.png").getImage());
        setResizable(false);

        panelPrincipal = crearPanelPrincipal(tituloSeccion);
        panelNoticias = crearPanelNoticias(noticias);
        panelPrincipal.add(new JScrollPane(panelNoticias), BorderLayout.CENTER);
        panelNoticias.setBackground(new Color(222, 222, 216));
        panelNoticias.setOpaque(true);


        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearPanelPrincipal(String tituloPagina) {
        panelCabecera = crearPanelCabecera(tituloPagina);
        panelPrincipal = new JPanel(new BorderLayout());

        panelCabecera.setBackground(new Color(222, 222, 216));
        panelCabecera.setOpaque(true);

        panelPrincipal.add(panelCabecera, BorderLayout.NORTH);

        return panelPrincipal;
    }

    private JPanel crearPanelCabecera(String tituloPagina) {
         panelCabecera = new JPanel(new FlowLayout(FlowLayout.CENTER));
         labelCabecera = new JLabel(tituloPagina);
         labelCabecera.setFont(new Font("Roboto", Font.BOLD, 18));
        panelCabecera.add(labelCabecera);

        ImageIcon iconoLogo = getLogo();
        if (iconoLogo != null) {
            JLabel labelLogo = new JLabel(iconoLogo);
            panelCabecera.add(labelLogo);
        }

        return panelCabecera;
    }

    private JPanel crearPanelNoticias(ArrayList<Article> noticias) {
        panelNoticias = new JPanel();
        panelNoticias.setLayout(new BoxLayout(panelNoticias, BoxLayout.Y_AXIS));
        for (Article noticia : noticias) {
            JPanel panelTituloNoticia = crearPanelTituloNoticia(noticia);
            panelNoticias.add(panelTituloNoticia);
            panelNoticias.add(Box.createRigidArea(new Dimension(5, 10)));
        }
        return panelNoticias;
    }

    private JPanel crearPanelTituloNoticia(Article noticia) {
        String titulo = noticia.getTitle();

        JPanel panelTituloNoticia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTituloNoticia.setBackground(new Color(222, 222, 216));
        panelTituloNoticia.setBorder(BorderFactory.createLineBorder(new Color(200, 195, 179), 1));

        JLabel labelTituloNoticia = new JLabel(titulo);
        configurarEstiloLabelTitulo(labelTituloNoticia, noticia);
        labelTituloNoticia.addMouseListener(new NoticiaMouseListener(noticia, titulo)); // Lanza noticia
        panelTituloNoticia.addMouseListener(new NoticiaMouseListener(noticia, titulo));

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

        panelTituloNoticia.add(labelTituloNoticia);
        return panelTituloNoticia;
    }

    private void configurarEstiloLabelTitulo(JLabel labelTituloNoticia, Article noticia) {
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

    public void update(ArrayList<Article> noticias, String tituloSeccion) {
        for (int i = 0; i < 10; i++) {
            // Repintamos la aplicación con un retraso de 10 milisegundos
            revalidate();
            repaint(10);
        }
        panelPrincipal.setVisible(false);

        setTitle("Noticias - " + tituloSeccion);
        panelPrincipal = crearPanelPrincipal(tituloSeccion);
        panelNoticias = crearPanelNoticias(noticias);
        panelPrincipal.add(new JScrollPane(panelNoticias), BorderLayout.CENTER);
        panelNoticias.setBackground(new Color(222, 222, 216));
        panelNoticias.setOpaque(true);
        add(panelPrincipal);
        setVisible(true);


    }

    private static class NoticiaMouseListener extends MouseAdapter {
        final private Article noticia;
        final private String tituloNoticia;
        final private URL urlNoticia;

        public NoticiaMouseListener(Article noticia, String tituloNoticia) {
            this.noticia = noticia;
            this.tituloNoticia = tituloNoticia;
            try {
                this.urlNoticia = new URL(noticia.getUrl());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Crear una nueva ventana para mostrar la noticia completa
            JFrame ventanaNoticia = new JFrame(tituloNoticia);
            ventanaNoticia.setIconImage(new ImageIcon("nalogo.png").getImage());
            ventanaNoticia.setSize(600, 400);
            ventanaNoticia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo la ventana actual al cerrar

            // Crear un panel principal con BorderLayout
            JPanel panelPrincipal = new JPanel(new BorderLayout());

            // Crear un área de texto estilizada para la noticia
            JLabel labelTituloNoticia = new JLabel("<html><div style='width: 400px; text-align: center;'>" + tituloNoticia + "</div></html>");
            labelTituloNoticia.setFont(new Font("Roboto", Font.BOLD, 18)); // Ajustar la fuente y el tamaño
            labelTituloNoticia.setBackground(new Color(222, 222, 216)); // Color de fondo suave
            labelTituloNoticia.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajustar el margen interno
            labelTituloNoticia.setOpaque(true); // Hacer visible el fondo
            labelTituloNoticia.setPreferredSize(new Dimension(400, labelTituloNoticia.getPreferredSize().height)); // Limitar el ancho
            panelPrincipal.add(labelTituloNoticia, BorderLayout.NORTH);

            JTextArea textoNoticia = new JTextArea(noticia.getDescription());
            textoNoticia.setLineWrap(true);
            textoNoticia.setWrapStyleWord(true);
            textoNoticia.setEditable(false);
            textoNoticia.setBackground(new Color(220, 220, 216));
            textoNoticia.setFont(new Font("Roboto", Font.PLAIN, 16)); // Ajustar la fuente y el tamaño

            // Agregar el área de texto a un JScrollPane para permitir el desplazamiento
            JScrollPane scrollPane = new JScrollPane(textoNoticia);
            scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Eliminar el borde del JScrollPane
            panelPrincipal.add(scrollPane, BorderLayout.CENTER);

            // Configurar el fondo y el título resaltado
            panelPrincipal.setBackground(new Color(222, 222, 216)); // Color de fondo suave
            textoNoticia.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(149, 148, 138), 2), // Borde resaltado
                    BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margen interno
            ));
            JLabel urlLabel = new JLabel("<html><div style='width: 400px; text-align: center;'><a href='" + urlNoticia + "'>Ver noticia completa</a></div></html>");
            urlLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
            urlLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(urlNoticia.toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            panelPrincipal.add(urlLabel, BorderLayout.SOUTH);

            // Agregar el panel principal a la ventana de la noticia
            ventanaNoticia.add(panelPrincipal);
            ventanaNoticia.setVisible(true);
        }

    }


}



