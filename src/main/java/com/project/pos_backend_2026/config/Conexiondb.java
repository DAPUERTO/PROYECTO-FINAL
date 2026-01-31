package com.project.pos_backend_2026.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Conexiondb {

    private static final String URLDB = Dotenv.load().get("DB_URL");
    private static final String USERDB = Dotenv.load().get("DB_USER");
    private static final String PASSWORDDB = Dotenv.load().get("DB_PASSWORD");

    public static Connection getConexion() throws SQLException {
        try {
            if (URLDB == null || USERDB == null || PASSWORDDB == null) {
                System.err.println("ERROR: La configuración de 'DB_PASSWORD' no se cargó correctamente.");
            }
            System.out.println("Intentando conexión con DB...");
            return DriverManager.getConnection(URLDB, USERDB, PASSWORDDB);
        } catch (SQLException e) {
            System.err.println("ERROR: No se pudo establecer la conexión con la base de datos.");
            e.printStackTrace();
            throw e;
        }
    }
}
