// package Server;

// import com.sun.net.httpserver.HttpServer;
// import java.net.InetSocketAddress;

// public class Servidor {

//     public static void main(String[] args) throws Exception {
//         HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

//         System.out.println("Servidor iniciado en http://localhost:8080");

//         server.start();
//     }
// }


package Server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Servidor {

    public static void main(String[] args) throws Exception {

        // Crear servidor HTTP en el puerto 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Registrar el endpoint /usuarios
        server.createContext("/usuarios", new UsuarioHandler());

        // Iniciar servidor
        server.start();

        System.out.println("Servidor iniciado en http://localhost:8080");
    }
}

