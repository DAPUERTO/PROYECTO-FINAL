//=====================GET=========================

package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import models.Usuario;

public class UsuarioHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Obtener el método HTTP (GET, POST, etc.)
        String metodo = exchange.getRequestMethod();

        if (metodo.equalsIgnoreCase("POST")) {
            manejarPost(exchange);
        } else if (metodo.equalsIgnoreCase("GET")) {
            manejarGet(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1);
        }

    }

    private void manejarPost(HttpExchange exchange) throws IOException {

        // 1️ Leer el JSON que envía Insomnia
        InputStream is = exchange.getRequestBody();
        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        System.out.println("JSON recibido: " + json);

        // 2️ Convertir JSON → Usuario
        Usuario usuario = convertirJsonAUsuario(json);

        // (Aquí luego irá guardar en BD)

        // 3️ Respuesta
        String respuesta = "{\"mensaje\":\"Usuario creado correctamente\"}";

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(201, respuesta.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(respuesta.getBytes());
        os.close();
    }

    private void manejarGet(HttpExchange exchange) throws IOException {

    // Simulación de usuarios (luego vendrán de la BD)
    String respuesta = """
        [
          {
            "idUsuario": 1,
            "nombreUsuario": "admin",
            "nombreCompleto": "Administrador",
            "estado": true
          },
          {
            "idUsuario": 2,
            "nombreUsuario": "diego01",
            "nombreCompleto": "Diego Puerto",
            "estado": true
          }
        ]
        """;

    exchange.getResponseHeaders().add("Content-Type", "application/json");
    exchange.sendResponseHeaders(200, respuesta.getBytes().length);

    OutputStream os = exchange.getResponseBody();
    os.write(respuesta.getBytes());
    os.close();
}


    private Usuario convertirJsonAUsuario(String json) {

        Usuario u = new Usuario();

        // Conversión MANUAL (simple)
        json = json.replace("{", "")
                .replace("}", "")
                .replace("\"", "");

        String[] campos = json.split(",");

        for (String campo : campos) {
            String[] par = campo.split(":");

            String clave = par[0].trim();
            String valor = par[1].trim();

            switch (clave) {
                case "nombreUsuario":
                    u.setNombreUsuario(valor);
                    break;
                case "passwordHash":
                    u.setPasswordHash(valor);
                    break;
                case "nombreCompleto":
                    u.setNombreCompleto(valor);
                    break;
                case "estado":
                    u.setEstado(Boolean.parseBoolean(valor));
                    break;
            }
        }

        return u;
    }
}

// ==================================================

// =====================POST=========================

// package Server;

// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;
// import models.Usuario;

// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.nio.charset.StandardCharsets;

// public class UsuarioHandler implements HttpHandler {

// @Override
// public void handle(HttpExchange exchange) throws IOException {

// // Obtener el método HTTP (GET, POST, etc.)
// String metodo = exchange.getRequestMethod();

// if (metodo.equalsIgnoreCase("POST")) {
// manejarPost(exchange);
// } else {
// // Método no permitido
// exchange.sendResponseHeaders(405, -1);
// }
// }

// private void manejarPost(HttpExchange exchange) throws IOException {

// // 1️ Leer el JSON que envía Insomnia
// InputStream is = exchange.getRequestBody();
// String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

// System.out.println("JSON recibido: " + json);

// // 2️ Convertir JSON → Usuario
// Usuario usuario = convertirJsonAUsuario(json);

// // (Aquí luego irá guardar en BD)

// // 3️ Respuesta
// String respuesta = "{\"mensaje\":\"Usuario creado correctamente\"}";

// exchange.getResponseHeaders().add("Content-Type", "application/json");
// exchange.sendResponseHeaders(201, respuesta.getBytes().length);

// OutputStream os = exchange.getResponseBody();
// os.write(respuesta.getBytes());
// os.close();
// }

// private Usuario convertirJsonAUsuario(String json) {

// Usuario u = new Usuario();

// // Conversión MANUAL (simple)
// json = json.replace("{", "")
// .replace("}", "")
// .replace("\"", "");

// String[] campos = json.split(",");

// for (String campo : campos) {
// String[] par = campo.split(":");

// String clave = par[0].trim();
// String valor = par[1].trim();

// switch (clave) {
// case "nombreUsuario":
// u.setNombreUsuario(valor);
// break;
// case "passwordHash":
// u.setPasswordHash(valor);
// break;
// case "nombreCompleto":
// u.setNombreCompleto(valor);
// break;
// case "estado":
// u.setEstado(Boolean.parseBoolean(valor));
// break;
// }
// }

// return u;
// }
// }

// ==================================================
