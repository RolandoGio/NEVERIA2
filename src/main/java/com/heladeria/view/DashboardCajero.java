package com.heladeria.view;

import com.heladeria.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class DashboardCajero extends JFrame {

    public DashboardCajero(Usuario u) {
        setTitle("Cajero · " + u.getNombreCompleto());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("Bienvenido, Cajero", SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(lbl, BorderLayout.CENTER);
    }
}
