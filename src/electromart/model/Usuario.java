/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electromart.model;

/**
 *
 * @author VíctorAlfonsoGuzmánG
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String password;
    private Rol rol;
    
    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String password, Rol rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public boolean esAdministrador() {
        return rol == Rol.ADMINISTRADOR;
    }
    
    public boolean esGerenteInventario() {
        return rol == Rol.GERENTE_INVENTARIO;
    }

    public boolean esOperadorPedidos() {
        return rol == Rol.OPERADOR_PEDIDOS;
    }
    
    @Override
    public String toString() {
        return "Usuario\n" +
               "id=" + id + "\n" +
               "nombreUsuario='" + nombreUsuario + "'\n" +
               "rol=" + rol;
    }
}
