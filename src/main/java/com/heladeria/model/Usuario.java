package com.heladeria.model;

/**
 * Representa a un usuario del sistema de heladería.
 * Este POJO se usa para transferir datos entre la capa DAO y el resto de la aplicación.
 */
public class Usuario {

    // ──────────────────────────────────
    // Atributos
    // ──────────────────────────────────
    private int id;                 // ID autoincremental en la BD
    private String codigo;          // SU0001, AD0001, CJ0001, etc.
    private String nombreCompleto;  // Nombre y apellidos
    private String rol;             // 'SU', 'AD' o 'CJ'
    private String claveAcceso;     // Contraseña (idealmente hash en la BD)
    private boolean activo;         // true = activo, false = desactivado

    // ──────────────────────────────────
    // Constructores
    // ──────────────────────────────────

    /** Constructor vacío (requerido por muchos frameworks y para instancias flexibles). */
    public Usuario() { }

    /** Constructor completo. Útil al mapear registros desde la BD. */
    public Usuario(int id,
                   String codigo,
                   String nombreCompleto,
                   String rol,
                   String claveAcceso,
                   boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.claveAcceso = claveAcceso;
        this.activo = activo;
    }

    // ──────────────────────────────────
    // Getters y Setters
    // ──────────────────────────────────

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // ──────────────────────────────────
    // toString() para depuración rápida
    // ──────────────────────────────────
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", rol='" + rol + '\'' +
                ", activo=" + activo +
                '}';
    }
}
