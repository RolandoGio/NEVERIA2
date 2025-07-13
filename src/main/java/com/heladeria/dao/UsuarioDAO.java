package com.heladeria.dao;

import com.heladeria.config.DBConnection;
import com.heladeria.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para la entidad Usuario.
 * Encapsula TODA la interacción con la base de datos.
 *
 * Métodos incluidos:
 *   - agregar(Usuario u)            → INSERT
 *   - buscarPorCodigo(String c)     → SELECT WHERE codigo = ?
 *   - desactivarPorId(int id)       → UPDATE activo = 0
 *   - listarActivos()               → SELECT WHERE activo = 1
 *
 * Nota: NO usamos DELETE; el borrado es lógico (activo = 0).
 */
public class UsuarioDAO {

    // ────────────────────────────────────────────────────────────────
    // 1. Agregar un usuario nuevo
    // ────────────────────────────────────────────────────────────────
    public boolean agregar(Usuario usuario) {
        final String sql = """
                INSERT INTO usuarios (nombre_completo, rol, clave_acceso)
                VALUES (?, ?, ?);
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreCompleto());
            ps.setString(2, usuario.getRol());
            ps.setString(3, usuario.getClaveAcceso()); // En producción: ¡hash aquí!
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ────────────────────────────────────────────────────────────────
    // 2. Buscar usuario por código (SU0001, AD0001, CJ0001…)
    // ────────────────────────────────────────────────────────────────
    public Usuario buscarPorCodigo(String codigo) {
        final String sql = "SELECT * FROM usuarios WHERE codigo = ? AND activo = 1;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToUsuario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // no encontrado o inactivo
    }

    // ────────────────────────────────────────────────────────────────
    // 3. Desactivar usuario (borrado lógico)
    // ────────────────────────────────────────────────────────────────
    public boolean desactivarPorId(int id) {
        final String sql = "UPDATE usuarios SET activo = 0 WHERE id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ────────────────────────────────────────────────────────────────
    // 4. Listar todos los usuarios activos
    // ────────────────────────────────────────────────────────────────
    public List<Usuario> listarActivos() {
        final String sql = "SELECT * FROM usuarios WHERE activo = 1;";
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSetToUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ────────────────────────────────────────────────────────────────
    // 5. Método auxiliar para mapear ResultSet → Usuario
    // ────────────────────────────────────────────────────────────────
    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("codigo"),
                rs.getString("nombre_completo"),
                rs.getString("rol"),
                rs.getString("clave_acceso"),
                rs.getInt("activo") == 1
        );
    }
}
