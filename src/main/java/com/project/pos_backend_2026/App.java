// import config.Conexiondb;
// import java.sql.Connection;
// import java.sql.SQLException;

// public class App {

//     public static void main(String[] args) {
//         try {
//             Connection con = Conexiondb.getConexion();

//             if (con != null) {
//                 System.out.println(" Conexión exitosa a la base de datos");
//             } else {
//                 System.out.println(" Error al conectar con la base de datos");
//             }
//         } catch (SQLException e) {
//             System.out.println(" Error al conectar con la base de datos: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
// }

//========================PRUEBAS ProductoDAO==============================

// import dao.ProductoDAO;
// import models.Producto;

// public class App {

//     public static void main(String[] args) {

//         ProductoDAO dao = new ProductoDAO();

//         // Crear producto de prueba
//         Producto p = new Producto();
//         p.setNombreProducto("Pizza Margherita");
//         p.setPrecioVenta(8000);
//         p.setCostoCompra(6000);
//         p.setStock(20);
//         p.setIdCategoria(1); //  Asegúrate que exista una categoría con id=1

//         if (dao.insertar(p)) {
//             System.out.println(" Producto insertado correctamente");
//         } else {
//             System.out.println(" Error al insertar producto");
//             System.out.println(" Solución: Ejecuta este SQL primero:");
//             System.out.println(
//                     "   INSERT INTO categorias (nombre_categoria, descripcion) VALUES ('Alimentos', 'Productos alimenticios');");
//         }

//         // Listar productos
//         System.out.println("\n Lista de productos:");
//         dao.listar().forEach(
//                 prod -> System.out.println("   • " + prod.getNombreProducto() + " - $" + prod.getPrecioVenta()));
//     }
// }

//========================PRUEBA CATEGORIA DAO ===============================

// import dao.CategoriaDAO;
// import models.Categoria;

// public class App {
//     public static void main(String[] args) {

//         CategoriaDAO dao = new CategoriaDAO();

//         Categoria c = new Categoria();
//         c.setNombreCategoria("cocteles");

//         dao.insertar(c);

//         for (Categoria cat : dao.listar()) {
//             System.out.println(cat.getIdCategoria() + " - " + cat.getNombreCategoria());
//         }
//     }
// }

//====================================================================================

//==========PRUEBA VENTA DAO ===============================

// import dao.VentaDAO;
// import dao.ClienteDAO;
// import dao.UsuarioDAO;
// import models.Venta;
// import models.Cliente;
// import models.Usuario;

// public class App {
//     public static void main(String[] args) {

//         // 1. Asegurar dependencias (Cliente y Usuario)
//         ClienteDAO clienteDAO = new ClienteDAO();
//         UsuarioDAO usuarioDAO = new UsuarioDAO();

//         // Crear Cliente de prueba si no existe (id arbitrario)
//         Cliente c = new Cliente();
//         c.setNombre("Cliente Test");
//         c.setNumeroIdentificacion("123456789");
//         c.setCelular("3001234567");
//         c.setDireccion("Calle 13 e 8b60");
//         c.setCorreo("test@cliente.com");
//         clienteDAO.insertar(c); // Intentamos insertar, si ya existe probablemente falle por unique key o se
//                                 // duplique, para prueba está bien.

//         // Crear Usuario de prueba
//         Usuario u = new Usuario();
//         u.setNombreUsuario("admin_test");
//         u.setPasswordHash("1234");
//         u.setNombreCompleto("Administrador Test");
//         u.setEstado(true);
//         usuarioDAO.insertar(u);

//         // NOTA: Para este ejemplo asumiremos que los IDs 1 existen o fueron creados.
//         // En un caso real recuperaríamos el ID del objeto insertado.

//         // 2. Probar VentaDAO
//         System.out.println("\n=== PRUEBA VENTA DAO ===");
//         VentaDAO ventaDAO = new VentaDAO();

//         Venta v = new Venta();
//         v.setTotalPagar(50000.0);
//         v.setEstado("Completada");
//         v.setIdCliente(1); // Asumiendo que existe cliente con ID 1
//         v.setIdUsuario(1); // Asumiendo que existe usuario con ID 1

//         // Registrar Venta
//         int idVenta = ventaDAO.registrarVenta(v);
//         if (idVenta > 0) {
//             System.out.println("Venta registrada con éxito. ID: " + idVenta);
//         } else {
//             System.out.println("Error al registrar venta. Verifica que existan Cliente ID=1 y Usuario ID=1");
//         }

//         // Listar Ventas
//         System.out.println("\n Lista de Ventas:");
//         for (Venta venta : ventaDAO.listar()) {
//             System.out.println("   • ID: " + venta.getIdVenta() +
//                     " | Fecha: " + venta.getFechaVenta() +
//                     " | Hora: " + venta.getHoraVenta() +
//                     " | Total: $" + venta.getTotalPagar() +
//                     " | Estado: " + venta.getEstado());
//         }
//     }

// }

//====================================================================================

//PRUEBAS USUARIO SERVICE

// import services.UsuarioService;
// import models.Usuario;

// public class App {

//     public static void main(String[] args) {

//         UsuarioService usuarioService = new UsuarioService();

//         // ===============================
//         // 1️ CREAR USUARIO
//         // ===============================
//         Usuario usuario = new Usuario();
//         usuario.setNombreUsuario("admin");
//         usuario.setPasswordHash("1234"); // en texto plano (service la cifra)
//         usuario.setNombreCompleto("Administrador General");
//         usuario.setEstado(true);

//         boolean creado = usuarioService.crearUsuario(usuario);
//         System.out.println("Usuario creado: " + creado);

//         // ===============================
//         // 2️ INTENTAR LOGIN CORRECTO
//         // ===============================
//         Usuario loginOk = usuarioService.login("admin", "1234");

//         if (loginOk != null) {
//             System.out.println("Login exitoso");
//             System.out.println("Bienvenido: " + loginOk.getNombreCompleto());
//         } else {
//             System.out.println("Login fallido");
//         }

//         // ===============================
//         // 3️ LOGIN CON CONTRASEÑA INCORRECTA
//         // ===============================
//         Usuario loginError = usuarioService.login("admin", "0000");
//         System.out.println(loginError == null
//                 ? "Contraseña incorrecta detectada correctamente"
//                 : "Error de validación");

//         // ===============================
//         // 4️ DESACTIVAR USUARIO
//         // ===============================
//         boolean desactivado = usuarioService.cambiarEstado(1, false);
//         System.out.println("Usuario desactivado: " + desactivado);

//         // ===============================
//         // 5️ INTENTAR LOGIN CON USUARIO INACTIVO
//         // ===============================
//         Usuario loginInactivo = usuarioService.login("admin", "1234");
//         System.out.println(loginInactivo == null
//                 ? "Acceso bloqueado por usuario inactivo"
//                 : "Error: no debería permitir acceso");

//         // ===============================
//         // 6️ LISTAR USUARIOS
//         // ===============================
//         System.out.println("Listado de usuarios:");
//         usuarioService.listarUsuarios().forEach(u ->
//                 System.out.println(
//                         u.getIdUsuario() + " - " +
//                         u.getNombreUsuario() + " - " +
//                         (u.isEstado() ? "Activo" : "Inactivo")
//                 )
//         );
//     }
// }

//===========PRUEBAS PRODUCTO SERVICE================

//==========================SIGUIENTE PRUEBA==========================================================================0

//===========PRUEBAS PRODUCTO SERVICE================

// import models.Producto;
// import services.ProductoService;

// public class App {

//     public static void main(String[] args) {

//         ProductoService productoService = new ProductoService();

//         // 1️ Crear producto
//         Producto p = new Producto();
//         p.setNombreProducto("Coca Cola 350ml");
//         p.setPrecioVenta(2500);
//         p.setCostoCompra(1800);
//         p.setStock(10);
//         p.setIdCategoria(1); // Asegúrate que exista

//         boolean creado = productoService.crearProducto(p);
//         System.out.println("Producto creado: " + creado);

//         // 2️ Aumentar stock (compra)
//         boolean aumento = productoService.aumentarStock(1, 5);
//         System.out.println("Stock aumentado: " + aumento);

//         // 3️ Disminuir stock (venta)
//         boolean disminuyo = productoService.disminuirStock(1, 3);
//         System.out.println("Stock disminuido: " + disminuyo);
//     }
// }

//===========PRUEBAS VENTA SERVICE================

// Importamos el modelo Venta para crear objetos de tipo Venta
import models.Venta;
// Importamos el modelo DetalleVenta para manejar los detalles de la venta
import models.DetalleVenta;
// Importamos el servicio VentaService que contiene la lógica de negocio
import services.VentaService;

// Importamos ArrayList para crear listas dinámicas
import java.util.ArrayList;
// Importamos List para manejar colecciones de objetos
import java.util.List;

// Clase principal de la aplicación para pruebas
public class App {

    // Método principal que ejecuta la prueba
    public static void main(String[] args) {

        // Instanciamos el servicio de ventas
        VentaService ventaService = new VentaService();

        // 1️ Crear el objeto venta con sus datos básicos
        Venta venta = new Venta();
        // Establecemos el total a pagar de la venta
        venta.setTotalPagar(7500);
        // Establecemos el estado de la venta
        venta.setEstado("Pagada");
        // Asignamos el ID del cliente (debe existir en la base de datos)
        venta.setIdCliente(1);
        // Asignamos el ID del usuario que realiza la venta (debe existir)
        venta.setIdUsuario(1);

        // 2️ Crear los detalles de la venta (productos vendidos)
        DetalleVenta d1 = new DetalleVenta();
        // Asignamos el ID del producto vendido
        d1.setIdProducto(1);
        // Establecemos la cantidad vendida
        d1.setCantidad(3);
        // Establecemos el precio unitario del producto
        d1.setPrecioUnitario(2500);
        // Calculamos y asignamos el subtotal (cantidad * precio)
        d1.setSubtotal(7500);

        // Creamos una lista para almacenar los detalles de la venta
        List<DetalleVenta> detalles = new ArrayList<>();
        // Agregamos el detalle creado a la lista
        detalles.add(d1);

        // 3️ Llamamos al servicio para registrar la venta completa (cabecera +
        // detalles)
        // El método devuelve true si se registró correctamente
        boolean ok = ventaService.registrarVentaCompleta(venta, detalles);

        // Imprimimos el resultado de la operación en la consola
        System.out.println("Venta registrada correctamente: " + ok);
    }
}
