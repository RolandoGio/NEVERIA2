package com.heladeria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL =
            "jdbc:sqlite:src/main/resources/sistema_heladeria.db";   // usa ruta relativa al proyecto

    /** Devuelve SIEMPRE una conexión nueva. El que la use debe cerrarla. */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
