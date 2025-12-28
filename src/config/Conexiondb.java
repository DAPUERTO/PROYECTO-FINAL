package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiondb {

    private static final String URL ="jdbc:mysql://localhost:3306/software_punto_venta";

    

    private static final String USER = "root";

    private static final String PASSWD_DB = "Dapa1994+";

    public static Connection getConexion() throws SQLException{
        if (PASSWD_DB == null) {
            System.err.println("ERROR: La variable de entorno DB_PASSWD no está configurada.");
        }
        System.out.println("Intentando conexión con DB...");
        return DriverManager.getConnection(URL, USER, PASSWD_DB);
    }
}
