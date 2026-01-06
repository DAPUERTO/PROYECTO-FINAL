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

//PRUEBAS ProductoDAO

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
