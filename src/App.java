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

