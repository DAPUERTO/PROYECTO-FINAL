package com.project.pos_backend_2026.dao; // Paquete para objetos de acceso a datos

import java.sql.Connection; // Importa la interfaz Connection
import java.sql.PreparedStatement; // Importa la clase PreparedStatement para consultas seguras
import java.sql.ResultSet; // Importa la clase ResultSet para resultados de consultas
import java.sql.SQLException; // Importa la clase para manejo de excepciones SQL
import java.util.ArrayList; // Importa la implementación de lista ArrayList
import java.util.List; // Importa la interfaz List

import com.project.pos_backend_2026.config.Conexiondb;
import com.project.pos_backend_2026.models.Cliente;

public class ClienteDAO { // Clase DAO para la entidad Cliente

    /**
     * Inserta un nuevo cliente en la base de datos.
     * 
     * @param cliente Objeto Cliente con los datos a registrar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(Cliente cliente) { // Método para insertar un cliente
        // SQL para insertar: nombre, id, celular, direccion, correo
        String sql = "INSERT INTO CLIENTES(nombre, numero_identificacion, celular, direccion, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexiondb.getConexion(); // Obtiene la conexión a la base de datos
                PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia SQL

            ps.setString(1, cliente.getNombre()); // Asigna el nombre al primer parámetro
            ps.setString(2, cliente.getNumeroIdentificacion()); // Asigna la identificación al segundo parámetro
            ps.setString(3, cliente.getCelular()); // Asigna el celular al tercer parámetro
            ps.setString(4, cliente.getDireccion()); // Asigna la dirección al cuarto parámetro
            ps.setString(5, cliente.getCorreo()); // Asigna el correo al quinto parámetro

            return ps.executeUpdate() > 0; // Ejecuta y retorna true si se insertó al menos una fila

        } catch (SQLException e) { // Captura excepciones SQL
            System.out.println("Error al insertar cliente: " + e.getMessage()); // Imprime el error
            return false; // Retorna false si hubo error
        }
    }

    /**
     * Obtiene una lista de todos los clientes registrados.
     * 
     * @return Lista de objetos Cliente.
     */
    public List<Cliente> listar() { // Método para listar clientes
        List<Cliente> lista = new ArrayList<>(); // Crea la lista para almacenar clientes
        String sql = "SELECT * FROM CLIENTES"; // SQL para seleccionar todos los clientes

        try (Connection con = Conexiondb.getConexion(); // Obtiene la conexión
                PreparedStatement ps = con.prepareStatement(sql); // Prepara la sentencia
                ResultSet rs = ps.executeQuery()) { // Ejecuta la consulta y obtiene resultados

            while (rs.next()) { // Itera sobre cada fila del resultado
                Cliente c = new Cliente(); // Crea un nuevo objeto Cliente
                c.setIdCliente(rs.getInt("id_cliente")); // Asigna el ID del cliente
                c.setNombre(rs.getString("nombre")); // Asigna el nombre
                c.setNumeroIdentificacion(rs.getString("numero_identificacion")); // Asigna la identificación
                c.setCelular(rs.getString("celular")); // Asigna el celular
                c.setDireccion(rs.getString("direccion")); // Asigna la dirección
                c.setCorreo(rs.getString("correo")); // Asigna el correo
                lista.add(c); // Agrega el cliente a la lista
            }

        } catch (SQLException e) { // Captura excepciones SQL
            System.out.println("Error al listar clientes: " + e.getMessage()); // Imprime el error
        }
        return lista; // Retorna la lista de clientes
    }

    /**
     * Busca un cliente por su ID.
     * 
     * @param id Identificador único del cliente.
     * @return Objeto Cliente si se encuentra, null si no existe.
     */
    public Cliente buscarPorId(int id) { // Método para buscar cliente por ID
        String sql = "SELECT * FROM CLIENTES WHERE id_cliente = ?"; // SQL para buscar por ID
        Cliente c = null; // Inicializa el cliente como null

        try (Connection con = Conexiondb.getConexion(); // Obtiene la conexión
                PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia

            ps.setInt(1, id); // Asigna el ID al parámetro
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta

            if (rs.next()) { // Si hay un resultado
                c = new Cliente(); // Crea el objeto Cliente
                c.setIdCliente(rs.getInt("id_cliente")); // Asigna el ID
                c.setNombre(rs.getString("nombre")); // Asigna el nombre
                c.setNumeroIdentificacion(rs.getString("numero_identificacion")); // Asigna la identificación
                c.setCelular(rs.getString("celular")); // Asigna el celular
                c.setDireccion(rs.getString("direccion")); // Asigna la dirección
                c.setCorreo(rs.getString("correo")); // Asigna el correo
            }

        } catch (SQLException e) { // Captura excepciones SQL
            System.out.println("Error al buscar cliente: " + e.getMessage()); // Imprime el error
        }
        return c; // Retorna el cliente encontrado o null
    }

    /**
     * Actualiza la información de un cliente existente.
     * 
     * @param cliente Objeto Cliente con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar(Cliente cliente) { // Método para actualizar cliente
        // SQL para actualizar datos del cliente
        String sql = "UPDATE CLIENTES SET nombre=?, numero_identificacion=?, celular=?, direccion=?, correo=? WHERE id_cliente=?";

        try (Connection con = Conexiondb.getConexion(); // Obtiene la conexión
                PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia

            ps.setString(1, cliente.getNombre()); // Asigna el nombre
            ps.setString(2, cliente.getNumeroIdentificacion()); // Asigna la identificación
            ps.setString(3, cliente.getCelular()); // Asigna el celular
            ps.setString(4, cliente.getDireccion()); // Asigna la dirección
            ps.setString(5, cliente.getCorreo()); // Asigna el correo
            ps.setInt(6, cliente.getIdCliente()); // Asigna el ID para el WHERE

            return ps.executeUpdate() > 0; // Retorna true si se actualizó

        } catch (SQLException e) { // Captura excepciones SQL
            System.out.println("Error al actualizar cliente: " + e.getMessage()); // Imprime el error
            return false; // Retorna false si falló
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     * 
     * @param id Identificador del cliente a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar(int id) { // Método para eliminar cliente
        String sql = "DELETE FROM CLIENTES WHERE id_cliente = ?"; // SQL para eliminar por ID

        try (Connection con = Conexiondb.getConexion(); // Obtiene la conexión
                PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia

            ps.setInt(1, id); // Asigna el ID al parámetro
            return ps.executeUpdate() > 0; // Retorna true si se eliminó

        } catch (SQLException e) { // Captura excepciones SQL
            System.out.println("Error al eliminar cliente: " + e.getMessage()); // Imprime el error
            return false; // Retorna false si falló
        }
    }
}
