/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Axl
 */
public class DTOasesor {
    private int Id, DNI, IdUsuario;
    private Double Salario;
    private String Nombre,Apellido,Cargo,NombreUsuario,ContraUsuario;
    private Date fechaContratacion;

    public DTOasesor() {
    }

    public DTOasesor(int Id, int DNI, int IdUsuario, Double Salario, String Nombre, String Apellido, String Cargo, String NombreUsuario, String ContraUsuario, Date fechaContratacion) {
        this.Id = Id;
        this.DNI = DNI;
        this.IdUsuario = IdUsuario;
        this.Salario = Salario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Cargo = Cargo;
        this.NombreUsuario = NombreUsuario;
        this.ContraUsuario = ContraUsuario;
        this.fechaContratacion = fechaContratacion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public Double getSalario() {
        return Salario;
    }

    public void setSalario(Double Salario) {
        this.Salario = Salario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getContraUsuario() {
        return ContraUsuario;
    }

    public void setContraUsuario(String ContraUsuario) {
        this.ContraUsuario = ContraUsuario;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
    
    
    
}
