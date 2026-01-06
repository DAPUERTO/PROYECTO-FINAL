package models;

public class CorreoProveedor {
    private int idCorreo;
    private int idProveedor;
    private String correo;

    public CorreoProveedor(){
    }

    public int getIdCorreo() {
        return idCorreo;
    }
    
    public void setIdCorreo(int idCorreo) {
        this.idCorreo = idCorreo;
    }
    
    public int getIdProveedor() {
        return idProveedor;
    }
    
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}


