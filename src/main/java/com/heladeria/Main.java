package com.heladeria;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // 1) Activar tema FlatLaf
        FlatLightLaf.setup();

        // 2) Lanzar GUI en el hilo de Swing
        SwingUtilities.invokeLater(() -> {
            new com.heladeria.view.LoginView().setVisible(true);
        });

    }
}
