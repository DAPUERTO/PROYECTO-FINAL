package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conexiondb {

    private static final String URL = "jdbc:mysql://localhost:3306/software_punto_venta";
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Conexiondb.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("No se encontró db.properties");
            } else {
                props.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConexion() throws SQLException {
        if (!props.containsKey("db.password")) {
            System.err.println("ERROR: La configuración de 'db.password' no se cargó correctamente.");
        }
        System.out.println("Intentando conexión con DB...");
        return DriverManager.getConnection(URL, props.getProperty("db.user"), props.getProperty("db.password"));
    }
}
