// Declaración del paquete donde se encuentra esta clase
package models;

public class Usuario {

    // Atributo privado que almacena el identificador único del usuario
    private int idUsuario;
    // Atributo privado que almacena el nombre de usuario para el login
    private String nombreUsuario;
    // Atributo privado que almacena la contraseña encriptada (hash) del usuario
    private String passwordHash;
    // Atributo privado que almacena el nombre completo del usuario
    private String nombreCompleto;
    // Atributo privado que indica si el usuario está activo (true) o inactivo
    // (false)
    private boolean estado;

    // Constructor vacío sin parámetros que permite crear instancias de Usuario
    public Usuario() {
    }

    // Método getter que retorna el identificador del usuario
    public int getIdUsuario() {
        // Retorna el valor del atributo idUsuario
        return idUsuario;
    }

    // Método setter que establece el identificador del usuario
    public void setIdUsuario(int idUsuario) {
        // Asigna el parámetro recibido al atributo idUsuario de la instancia actual
        this.idUsuario = idUsuario;
    }

    // Método getter que retorna el nombre de usuario
    public String getNombreUsuario() {
        // Retorna el valor del atributo nombreUsuario
        return nombreUsuario;
    }

    // Método setter que establece el nombre de usuario
    public void setNombreUsuario(String nombreUsuario) {
        // Asigna el parámetro recibido al atributo nombreUsuario de la instancia actual
        this.nombreUsuario = nombreUsuario;
    }

    // Método getter que retorna el hash de la contraseña
    public String getPasswordHash() {
        // Retorna el valor del atributo passwordHash
        return passwordHash;
    }

    // Método setter que establece el hash de la contraseña
    public void setPasswordHash(String passwordHash) {
        // Asigna el parámetro recibido al atributo passwordHash de la instancia actual
        this.passwordHash = passwordHash;
    }

    // Método getter que retorna el nombre completo del usuario
    public String getNombreCompleto() {
        // Retorna el valor del atributo nombreCompleto
        return nombreCompleto;
    }

    // Método setter que establece el nombre completo del usuario
    public void setNombreCompleto(String nombreCompleto) {
        // Asigna el parámetro recibido al atributo nombreCompleto de la instancia
        // actual
        this.nombreCompleto = nombreCompleto;
    }

    // Método getter que retorna el estado del usuario (activo/inactivo)
    // Nota: Para atributos boolean, el getter se nombra con "is" en lugar de "get"
    public boolean isEstado() {
        // Retorna el valor del atributo estado
        return estado;
    }

    // Método setter que establece el estado del usuario
    public void setEstado(boolean estado) {
        // Asigna el parámetro recibido al atributo estado de la instancia actual
        this.estado = estado;
    }
}
