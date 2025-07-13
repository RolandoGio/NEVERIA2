package com.heladeria.view;

import com.heladeria.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class DashboardSuperusuario extends JFrame {

    public DashboardSuperusuario(Usuario u) {
        setTitle("Superusuario · " + u.getNombreCompleto());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel principal provisional
        JLabel lbl = new JLabel("Bienvenido, Superusuario", SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(lbl, BorderLayout.CENTER);
    }
}
