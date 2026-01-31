package dao; // Paquete que contiene las clases de acceso a datos (DAO)

import config.Conexiondb; // Importa la clase de configuración para la conexión a la base de datos
import models.Categoria; // Importa la clase modelo Categoria que representa la entidad

import java.sql.Connection; // Importa la interfaz Connection para manejar la conexión a la BD
import java.sql.PreparedStatement; // Importa PreparedStatement para ejecutar consultas SQL parametrizadas
import java.sql.ResultSet; // Importa ResultSet para manejar los resultados obtenidos de la BD
import java.sql.Statement; // Importa Statement para ejecutar sentencias SQL simples
import java.util.ArrayList; // Importa ArrayList para manejar listas dinámicas
import java.util.List; // Importa la interfaz List para colecciones ordenadas

// Clase Data Access Object (DAO) para manejar las operaciones CRUD de la tabla CATEGORIAS
public class CategoriaDAO {

    // Método para insertar una nueva categoría en la base de datos
    public boolean insertar(Categoria categoria) {
        // Define la sentencia SQL para insertar un registro, usando ? como marcador de
        // posición
        String sql = "INSERT INTO CATEGORIAS (nombre_categoria) VALUES (?)";

        // Bloque try-with-resources que abre la conexión y prepara la sentencia
        // Se cerrarán automáticamente al finalizar el bloque
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            // Asigna el nombre de la categoría al primer parámetro (?) de la consulta
            ps.setString(1, categoria.getNombreCategoria());
            // Ejecuta la actualización en la BD y retorna true si se afectó al menos una
            // fila
            return ps.executeUpdate() > 0;

        } catch (Exception e) { // Captura cualquier excepción que ocurra durante el proceso
            e.printStackTrace(); // Imprime la traza del error en la consola
            return false; // Retorna false indicando que la inserción falló
        }
    }

    // Método para listar todas las categorías existentes en la base de datos
    public List<Categoria> listar() {
        // Crea una lista vacía para almacenar los objetos Categoria recuperados
        List<Categoria> lista = new ArrayList<>();
        // Define la sentencia SQL para seleccionar todos los registros de la tabla
        // CATEGORIAS
        String sql = "SELECT * FROM CATEGORIAS";

        // Bloque try-with-resources para manejar conexión, statement y resultset
        try (Connection con = Conexiondb.getConexion(); // Obtiene la conexión
                Statement st = con.createStatement(); // Crea un objeto Statement
                ResultSet rs = st.executeQuery(sql)) { // Ejecuta la consulta y obtiene los resultados

            // Itera sobre cada fila del resultado obtenido
            while (rs.next()) {
                // Crea una nueva instancia de Categoria
                Categoria c = new Categoria();
                // Obtiene el valor de 'id_categoria' y lo asigna al objeto
                c.setIdCategoria(rs.getInt("id_categoria"));
                // Obtiene el valor de 'nombre_categoria' y lo asigna al objeto
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                // Añade el objeto categoría con sus datos a la lista
                lista.add(c);
            }

        } catch (Exception e) { // Captura posibles errores durante la consulta
            e.printStackTrace(); // Muestra el error en consola para depuración
        }
        return lista; // Retorna la lista de categorías (puede estar vacía si no hay datos o hubo
                      // error)
    }

    // Método para buscar una categoría específica por su ID
    public Categoria buscarPorId(int id) {
        // Inicializa la variable categoria como null, por si no se encuentra
        Categoria categoria = null;
        // Define la consulta SQL filtrando por id_categoria
        String sql = "SELECT * FROM CATEGORIAS WHERE id_categoria = ?";

        // Abre la conexión y prepara la sentencia SQL
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            // Asigna el ID recibido al parámetro de la consulta
            ps.setInt(1, id);
            // Ejecuta la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            // Verifica si hay algún resultado en el ResultSet
            if (rs.next()) {
                // Si existe, crea una nueva instancia de Categoria
                categoria = new Categoria();
                // Asigna el ID recuperado de la BD
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                // Asigna el nombre recuperado de la BD
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
            }

        } catch (Exception e) { // Manejo de excepciones
            e.printStackTrace(); // Imprime errores si ocurren
        }
        return categoria; // Retorna la categoría encontrada o null si no existe
    }

    // Método para actualizar los datos de una categoría existente
    public boolean actualizar(Categoria categoria) {
        // Define la sentencia SQL de actualización filtrando por ID
        String sql = "UPDATE CATEGORIAS SET nombre_categoria = ? WHERE id_categoria = ?";

        // Abre conexión y prepara el statement
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            // Asigna el nuevo nombre al primer parámetro (?)
            ps.setString(1, categoria.getNombreCategoria());
            // Asigna el ID de la categoría (que no cambia) al segundo parámetro (?) para el
            // WHERE
            ps.setInt(2, categoria.getIdCategoria());
            // Ejecuta la actualización y devuelve true si tuvo éxito (filas > 0)
            return ps.executeUpdate() > 0;

        } catch (Exception e) { // Captura errores
            e.printStackTrace(); // Imprime el error
            return false; // Retorna false en caso de fallo
        }
    }

    // Método para eliminar una categoría de la base de datos por su ID
    public boolean eliminar(int id) {
        // Define la sentencia SQL de eliminación
        String sql = "DELETE FROM CATEGORIAS WHERE id_categoria = ?";

        // Abre conexión y prepara la sentencia
        try (Connection con = Conexiondb.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            // Asigna el ID a eliminar al parámetro de la consulta
            ps.setInt(1, id);
            // Ejecuta la eliminación y retorna true si se eliminó alguna fila
            return ps.executeUpdate() > 0;

        } catch (Exception e) { // Captura excepciones
            e.printStackTrace(); // Imprime el error
            return false; // Retorna false si hubo un problema
        }
    }
}
