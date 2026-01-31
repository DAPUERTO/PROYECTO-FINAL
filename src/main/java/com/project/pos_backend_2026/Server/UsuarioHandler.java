//=====================GET=========================

// package Server;

// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.nio.charset.StandardCharsets;

// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;

// import models.Usuario;

// public class UsuarioHandler implements HttpHandler {

//     @Override
//     public void handle(HttpExchange exchange) throws IOException {

//         // Obtener el método HTTP (GET, POST, etc.)
//         String metodo = exchange.getRequestMethod();

//         if (metodo.equalsIgnoreCase("POST")) {
//             manejarPost(exchange);
//         } else if (metodo.equalsIgnoreCase("GET")) {
//             manejarGet(exchange);
//         } else {
//             exchange.sendResponseHeaders(405, -1);
//         }

//     }

//     private void manejarPost(HttpExchange exchange) throws IOException {

//         // 1️ Leer el JSON que envía Insomnia
//         InputStream is = exchange.getRequestBody();
//         String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

//         System.out.println("JSON recibido: " + json);

//         // 2️ Convertir JSON → Usuario
//         Usuario usuario = convertirJsonAUsuario(json);

//         // (Aquí luego irá guardar en BD)

//         // 3️ Respuesta
//         String respuesta = "{\"mensaje\":\"Usuario creado correctamente\"}";

//         exchange.getResponseHeaders().add("Content-Type", "application/json");
//         exchange.sendResponseHeaders(201, respuesta.getBytes().length);

//         OutputStream os = exchange.getResponseBody();
//         os.write(respuesta.getBytes());
//         os.close();
//     }

//     private void manejarGet(HttpExchange exchange) throws IOException {

//     // Simulación de usuarios (luego vendrán de la BD)
//     String respuesta = """
//         [
//           {
//             "idUsuario": 1,
//             "nombreUsuario": "admin",
//             "nombreCompleto": "Administrador",
//             "estado": true
//           },
//           {
//             "idUsuario": 2,
//             "nombreUsuario": "diego01",
//             "nombreCompleto": "Diego Puerto",
//             "estado": true
//           }
//         ]
//         """;

//     exchange.getResponseHeaders().add("Content-Type", "application/json");
//     exchange.sendResponseHeaders(200, respuesta.getBytes().length);

//     OutputStream os = exchange.getResponseBody();
//     os.write(respuesta.getBytes());
//     os.close();
// }

//     private Usuario convertirJsonAUsuario(String json) {

//         Usuario u = new Usuario();

//         // Conversión MANUAL (simple)
//         json = json.replace("{", "")
//                 .replace("}", "")
//                 .replace("\"", "");

//         String[] campos = json.split(",");

//         for (String campo : campos) {
//             String[] par = campo.split(":");

//             String clave = par[0].trim();
//             String valor = par[1].trim();

//             switch (clave) {
//                 case "nombreUsuario":
//                     u.setNombreUsuario(valor);
//                     break;
//                 case "passwordHash":
//                     u.setPasswordHash(valor);
//                     break;
//                 case "nombreCompleto":
//                     u.setNombreCompleto(valor);
//                     break;
//                 case "estado":
//                     u.setEstado(Boolean.parseBoolean(valor));
//                     break;
//             }
//         }

//         return u;
//     }
// }

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

// =====================PUT=========================

package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import models.Usuario;

public class UsuarioHandler implements HttpHandler {

    /**
     * Maneja las solicitudes HTTP entrantes y las redirige al método
     * correspondiente
     * según el verbo HTTP (GET, POST, PUT).
     *
     * @param exchange El contexto del intercambio HTTP, que contiene la solicitud y
     *                 la respuesta.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Obtener el método HTTP (recupera si es GET, POST, PUT, DELETE, etc.)
        String metodo = exchange.getRequestMethod();

        // Evaluar el método y delegar a la función auxiliar correspondiente
        if (metodo.equalsIgnoreCase("POST")) {
            manejarPost(exchange);
        } else if (metodo.equalsIgnoreCase("GET")) {
            manejarGet(exchange);
        } else if (metodo.equalsIgnoreCase("PUT")) {
            manejarPut(exchange);
        } else {
            // Si el método no es ninguno de los soportados, devolver 405 Method Not Allowed
            exchange.sendResponseHeaders(405, -1);
        }
    }

    /**
     * Maneja las solicitudes POST para crear un nuevo usuario.
     * Lee el cuerpo de la solicitud, lo convierte a un objeto Usuario y devuelve
     * una respuesta de éxito.
     *
     * @param exchange El contexto del intercambio HTTP.
     * @throws IOException Si ocurre un error de lectura o escritura.
     */
    private void manejarPost(HttpExchange exchange) throws IOException {

        // 1. Leer el cuerpo de la solicitud (JSON) enviado por el cliente (ej.
        // Insomnia, Postman)
        InputStream is = exchange.getRequestBody();
        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        // Imprimir el JSON recibido en consola para depuración
        System.out.println("JSON recibido: " + json);

        // 2. Convertir la cadena JSON recibida a un objeto Java de tipo Usuario
        Usuario usuario = convertirJsonAUsuario(json);

        // (Aquí iría la lógica para guardar el usuario en la base de datos)

        // 3. Preparar la respuesta JSON para el cliente
        String respuesta = "{\"mensaje\":\"Usuario creado correctamente\"}";

        // Configurar los encabezados de la respuesta (Content-Type: application/json)
        exchange.getResponseHeaders().add("Content-Type", "application/json");

        // Enviar encabezados de respuesta (código 201 Created y longitud de la
        // respuesta)
        exchange.sendResponseHeaders(201, respuesta.getBytes().length);

        // Escribir el cuerpo de la respuesta en el flujo de salida
        OutputStream os = exchange.getResponseBody();
        os.write(respuesta.getBytes());

        // Cerrar el flujo de salida para finalizar la respuesta
        os.close();
    }

    /**
     * Maneja las solicitudes GET para listar usuarios.
     * Devuelve una lista simulada de usuarios en formato JSON.
     *
     * @param exchange El contexto del intercambio HTTP.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void manejarGet(HttpExchange exchange) throws IOException {

        // Definir una cadena JSON con usuarios simulados (Hardcoded)
        // En una implementación real, estos datos se obtendrían de la Base de Datos
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

        // Configurar el tipo de contenido como JSON
        exchange.getResponseHeaders().add("Content-Type", "application/json");

        // Enviar código de respuesta 200 OK y la longitud del contenido
        exchange.sendResponseHeaders(200, respuesta.getBytes().length);

        // Escribir el JSON en el cuerpo de la respuesta
        OutputStream os = exchange.getResponseBody();
        os.write(respuesta.getBytes());

        // Cerrar el flujo
        os.close();
    }

    /**
     * Maneja las solicitudes PUT para actualizar un usuario.
     * Lee el JSON, convierte a objeto y simula una actualización exitosa.
     *
     * @param exchange El contexto del intercambio HTTP.
     * @throws IOException Si ocurre un error de lectura o escritura.
     */
    private void manejarPut(HttpExchange exchange) throws IOException {

        // 1. Obtener el cuerpo de la solicitud (RequestBody)
        InputStream is = exchange.getRequestBody();
        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        // Imprimir para verificar qué llega
        System.out.println("JSON recibido (PUT): " + json);

        // 2. Convertir el JSON recibido a un objeto Usuario
        Usuario usuario = convertirJsonAUsuario(json);

        // (Aquí iría la lógica para hacer el UPDATE en la Base de Datos usando JDBC)

        // 3. Preparar la respuesta de éxito
        String respuesta = "{\"mensaje\":\"Usuario actualizado correctamente\"}";

        // Configurar headers y enviar respuesta 200 OK
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, respuesta.getBytes().length);

        // Escribir en la salida
        OutputStream os = exchange.getResponseBody();
        os.write(respuesta.getBytes());
        os.close();
    }

    /**
     * Método auxiliar para convertir manualmente una cadena JSON en un objeto
     * Usuario.
     * Realiza un parseo básico string-manipulation sin usar librerías externas como
     * Jackson o GSON.
     *
     * @param json La cadena JSON recibida.
     * @return Un objeto Usuario populado con los datos del JSON.
     */
    private Usuario convertirJsonAUsuario(String json) {

        Usuario u = new Usuario();

        // Limpieza básica del JSON: quitar llaves y comillas para facilitar el split
        // NOTA: Esto es muy frágil y solo funciona con JSONs simples y planos.
        json = json.replace("{", "")
                .replace("}", "")
                .replace("\"", "");

        // Separar por comas para obtener los pares clave:valor
        String[] campos = json.split(",");

        for (String campo : campos) {
            // Separar cada par por los dos puntos
            String[] par = campo.split(":");

            // Validar que el split haya funcionado (evita IndexOutOfBounds si viene vacío)
            if (par.length < 2)
                continue;

            String clave = par[0].trim();
            String valor = par[1].trim();

            // Asignar al atributo correspondiente según la clave
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
                    // Convertir el string "true"/"false" a boolean
                    u.setEstado(Boolean.parseBoolean(valor));
                    break;
            }
        }

        return u;
    }
}