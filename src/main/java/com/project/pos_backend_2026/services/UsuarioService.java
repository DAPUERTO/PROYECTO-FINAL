package com.project.pos_backend_2026.services;

import com.project.pos_backend_2026.dao.UsuarioDAO;
import com.project.pos_backend_2026.models.Usuario;
import java.util.List;
import java.security.MessageDigest;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // 1️ Crear usuario
    public boolean crearUsuario(Usuario usuario) {
        // Validar que no exista el usuario
        if (usuarioDAO.existeUsuario(usuario.getNombreUsuario())) {
            System.out.println("El usuario ya existe");
            return false;
        }

        // Cifrar contraseña
        String passwordCifrado = cifrarPassword(usuario.getPasswordHash());
        usuario.setPasswordHash(passwordCifrado);

        return usuarioDAO.insertar(usuario);
    }

    // 2️ Login
    public Usuario login(String nombreUsuario, String passwordPlano) {
        Usuario usuario = usuarioDAO.buscarPorNombre(nombreUsuario);

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return null;
        }

        if (!usuario.isEstado()) {
            System.out.println("Usuario inactivo");
            return null;
        }

        String passwordCifrado = cifrarPassword(passwordPlano);

        if (!passwordCifrado.equals(usuario.getPasswordHash())) {
            System.out.println("Contraseña incorrecta");
            return null;
        }

        return usuario;
    }

    // 3 Cambiar estado
    public boolean cambiarEstado(int idUsuario, boolean estado) {
        return usuarioDAO.actualizarEstado(idUsuario, estado);
    }

    // 4️ Listar usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    // Método privado de cifrado
    private String cifrarPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar contraseña");
        }
    }
}
