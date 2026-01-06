package model;

public class CorreoCliente {

    private int idCorreoCliente;
    private int idCliente;
    private String correo;

    public CorreoCliente() {
    }

    public int getIdCorreoCliente() {
        return idCorreoCliente;
    }

    public void setIdCorreoCliente(int idCorreoCliente) {
        this.idCorreoCliente = idCorreoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
