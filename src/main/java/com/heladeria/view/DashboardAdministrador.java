package com.heladeria.view;

import com.heladeria.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class DashboardAdministrador extends JFrame {

    public DashboardAdministrador(Usuario u) {
        setTitle("Administrador · " + u.getNombreCompleto());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("Bienvenido, Administrador", SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(lbl, BorderLayout.CENTER);
    }
}
