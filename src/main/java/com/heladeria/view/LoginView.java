package com.heladeria.view;

import com.heladeria.dao.UsuarioDAO;
import com.heladeria.model.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de inicio de sesión del sistema.
 * Responsable de autenticar al usuario y redirigirlo al dashboard
 * correspondiente según su rol.
 */
public class LoginView extends JFrame {

    // ────────────────────────────────────────────────────────────────
    // Componentes de la interfaz
    // ────────────────────────────────────────────────────────────────
    private final JTextField       txtCodigo = new JTextField();
    private final JPasswordField   txtClave  = new JPasswordField();
    private final JButton          btnEntrar = new JButton("Entrar");
    private final JLabel           lblMensaje = new JLabel(" ");

    // ────────────────────────────────────────────────────────────────
    // Constructor
    // ────────────────────────────────────────────────────────────────
    public LoginView() {
        setTitle("Sistema Heladería · Login");
        setSize(360, 220);
        setLocationRelativeTo(null);           // Centrar pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        initEvents();
    }

    // ────────────────────────────────────────────────────────────────
    // Construir interfaz
    // ────────────────────────────────────────────────────────────────
    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new JLabel("Código:"));
        form.add(txtCodigo);
        form.add(new JLabel("Clave:"));
        form.add(txtClave);

        add(lblMensaje, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(btnEntrar, BorderLayout.SOUTH);
    }

    // ────────────────────────────────────────────────────────────────
    // Eventos
    // ────────────────────────────────────────────────────────────────
    private void initEvents() {
        btnEntrar.addActionListener(e -> autenticar());
        txtClave.addActionListener(e -> autenticar());   // Enter en clave
    }

    // ────────────────────────────────────────────────────────────────
    // Autenticación
    // ────────────────────────────────────────────────────────────────
    private void autenticar() {
        String codigo = txtCodigo.getText().trim();
        String clave  = new String(txtClave.getPassword()).trim();

        // Validaciones básicas
        if (codigo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Ingresa código y clave.");
            return;
        }

        // Buscar usuario activo
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.buscarPorCodigo(codigo);

        if (u == null) {
            lblMensaje.setText("Código no encontrado o usuario inactivo.");
            return;
        }

        // Comparar clave (TODO: reemplazar por hash en producción)
        if (!u.getClaveAcceso().equals(clave)) {
            lblMensaje.setText("Clave incorrecta.");
            return;
        }

        // Éxito
        abrirDashboard(u);
    }

    // ────────────────────────────────────────────────────────────────
    // Navegación
    // ────────────────────────────────────────────────────────────────
    private void abrirDashboard(Usuario u) {
        dispose(); // Cerrar login
        switch (u.getRol()) {
            case "SU" -> new DashboardSuperusuario(u).setVisible(true);
            case "AD" -> new DashboardAdministrador(u).setVisible(true);
            case "CJ" -> new DashboardCajero(u).setVisible(true);
            default -> JOptionPane.showMessageDialog(this, "Rol desconocido: " + u.getRol());
        }
    }
}
