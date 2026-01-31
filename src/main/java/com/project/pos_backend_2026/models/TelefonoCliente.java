package com.project.pos_backend_2026.models; 

public class TelefonoCliente {

    private int idTelefonoCliente;
    private int idCliente;
    private String telefono;

    public TelefonoCliente() {
    }

    public int getIdTelefonoCliente() {
        return idTelefonoCliente;
    }

    public void setIdTelefonoCliente(int idTelefonoCliente) {
        this.idTelefonoCliente = idTelefonoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
