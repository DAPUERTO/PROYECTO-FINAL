package config;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Servidor Web Básico
 * Implementación cruda de un servidor HTTP utilizando Sockets TCP.
 * 
 * Responsabilidades:
 * 1. Aceptar conexiones entrantes (TCP)
 * 2. Leer y parsear requests HTTP
 * 3. Enrutar peticiones (TODO)
 * 4. Enviar respuestas HTTP
 */

/*
 * Estructura del servidor
 * 1- SocketServer para aceptar conexiones,
 * 2- bucle de manejo de clientes en hilos separados,
 * 3- protocolo de comunicación simple (lectura/escritura de bytes)
 * 4- mecanismos de cierre graceful para evitar fugas de recursos.
 */

public class serverMain {

    // *Puerto del servidor
    final static int port = 8080;

    // ? Conexion Server-Socket, inicializacion del socket para establecer la
    // conexion con el cliente
    public static void getConexion() {
        // *Inicializacion del socket en el puerto 8080
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Servidor funcionando con el socket: " + server);

            // *Bucle de manejo de clientes en un solo hilo
            while (!server.isClosed()) {
                try {
                    // *Espera a que un cliente se conecte, mientras esta en espera (congelado)
                    Socket socket = server.accept();
                    System.out.println("Conexion en el Socket establecido con el cliente: " + socket);

                    /*
                     * Permite crear multiples hilos (threads) dentro del servidor permitiendo
                     * atender multiples clientes al mismo tiempo cada uno con su propio socket
                     */
                    new Thread(() -> {
                        handleInOut(socket);
                    }).start();

                } catch (SocketException e) {
                    // TODO: handle exception
                    System.err.println("Error: Se cerro la conexion inesperadamente. E: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.err.println("Error: El puerto ya esta ocupado. E: " + e.getMessage());
            /*
             * ! Mostrara un mensaje de error avisando que el puerto desigando ya esta
             * ! ocupado por otro programa.
             */
        }
    }

    // ? Metodo encargado de establecer la comunicacion entre el cliente y el
    // servidor, ofreciendo respuestas.
    public static void handleInOut(Socket socket) {
        // * Inicializa los streams: 'in' para recibir peticiones, 'out' para enviar
        // respuestas
        try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {

            // ? Recibimiento y lectura de la peticion del cliente
            // * Array de bytes (Buffer) para almacenar temporalmente los datos crudos
            // recibidos
            byte[] buffer = new byte[4000];

            // * Lee los datos del stream hacia el buffer y retorna la CANTIDAD de bytes
            // leídos
            int datosleidos = in.read(buffer);

            if (datosleidos > 0) {
                // * Construye un String (Texto) a partir de los bytes válidos leídos
                String peticion = new String(buffer, 0, datosleidos);
                // * Imprime en consola la petición HTTP del cliente

                System.out.println("\nPeticion del cliente: \n" + peticion);
                System.out.println("\n\n");

                // ? Respuesta de la peticion del cliente

                // * Parseo de la peticion para obtener el tipo de metodo y la ruta que se
                // solicito.

                // * Con split se verifica hasta donde hay un salto de linea y se corta.
                String[] headerPeti = peticion.split("\n");
                // * Al retornar un array, la peticion queda separada quedando el header en la
                // primera posicion "[0]"
                System.out.println("Header de la peticion: " + headerPeti[0]);

                // * Luego se hace lo mismo con el array, cortando donde haya salto de linea en
                // el dato completo de la primera posicion del array, diviendo el header ahora
                // entre parte: (metodo http/ruta/tipo y version de protocolo) -> GET - "/" -
                // http 1.1
                String[] datosHeader = headerPeti[0].split(" ");

                String metodo = datosHeader[0];
                String ruta = datosHeader[1];
                System.out.println(
                        "Metodo: " + datosHeader[0] + ", Ruta: " + datosHeader[1] + ", protocolo: " + datosHeader[2]);

                // * Enrutamiento de la respuesta segun la ruta y el metodo

                // * Variable para almacenar la respuesta HTTP que se enviará al cliente
                String response = "";

                // ? Parseo de Query Parameters (parámetros en la URL)
                // * Divide la ruta usando "?" como separador
                // * Ejemplo: "/hola?nombre=maria" -> ["hola", "nombre=maria"]
                // * Se usa "\\?" porque "?" es un caracter especial en regex
                String[] parts = ruta.split("\\?");

                // * La primera parte siempre es la ruta sin parámetros
                String rutaLimpia = parts[0];

                // * Variables para almacenar el parámetro y su valor
                String parametro = "";
                String nombre = "";

                // * Verifica si hay parámetros (si el array tiene más de 1 elemento)
                if (parts.length > 1) {
                    // * Obtiene la parte después del "?" -> "nombre=maria"
                    parametro = parts[1];

                    // * Divide el parámetro usando "=" como separador
                    // * Ejemplo: "nombre=maria" -> ["nombre", "maria"]
                    String[] valorParametro = parametro.split("=");

                    if (valorParametro.length > 1) {
                        nombre = valorParametro[1];
                    } else {
                        nombre = "invitado";
                    }

                    // * Obtiene el valor del parámetro (posición 1 del array)
                    nombre = valorParametro[1];
                }

                // ? Definición de respuestas según la ruta solicitada

                // * Ruta raíz "/" - Página principal
                if (rutaLimpia.equals("/") && metodo.equals("GET")) {
                    response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type:  Application/json\r\n"
                            + "\r\n"
                            + "{ \"Mensaje\": \"Zona principal\" }";

                    // * Ruta "/hola" - Saludo personalizado con el nombre del parámetro
                } else if (rutaLimpia.equals("/hola")) {
                    response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type:  Application/json\r\n"
                            + "\r\n"
                            + "{ \"Mensaje\": \"Bienvenido " + nombre + "\" }";

                    // * Cualquier otra ruta - Error 404 (No encontrado)
                } else if (metodo.equals("POST") && rutaLimpia.equals("/")) {
                    String[] partPeti = peticion.split("\r\n\r\n");
                    String body = "";
                    if (partPeti.length > 1) {
                        body = partPeti[1];
                    }

                    response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type:  Application/json\r\n"
                            + "\r\n"
                            + "{ \"Mensaje\": \"Datos recibidos\", \"Body\": " + body + "}";
                } else {
                    response = "HTTP/1.1 404 Not Found\r\n"
                            + "Content-Type:  Application/json\r\n"
                            + "\r\n"
                            + "{ \"Mensaje\": \"Ruta no encontrada\" }";
                }

                out.write(response.getBytes());
                out.flush();

            }

            System.out.println("\n//Conexion cerrada con el cliente//\n");
        } catch (IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
}
