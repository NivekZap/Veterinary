/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Axl
 */
public class DTOusuario {
    private int id;
    private String nomUsuario,contra,rol;

    public DTOusuario() {
    }

    public DTOusuario(int id, String nomUsuario, String contra, String rol) {
        this.id = id;
        this.nomUsuario = nomUsuario;
        this.contra = contra;
        this.rol = rol;
    }

    public String getRol() { return rol;}
    public void setRol(String rol) {this.rol = rol;}

    public int getId() {return id;}
    public void setId(int id) { this.id = id; }

    public String getNomUsuario() { return nomUsuario; }
    public void setNomUsuario(String nomUsuario) { this.nomUsuario = nomUsuario; }

    public String getContra() { return contra;}
    public void setContra(String contra) { this.contra = contra;}   
}
