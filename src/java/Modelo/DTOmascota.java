package Modelo;

import java.sql.Date;

public class DTOmascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private String edad;
    private String peso;
    private int dueño_id; // Campo para almacenar el ID del dueño de la mascota
    private Date fechaNacimiento;
    private String vacunaciones;
    private String notas;

    // Constructor con todos los atributos
    public DTOmascota(int id, String nombre, String especie, String raza, String edad, String peso, int dueño_id, Date fechaNacimiento, String vacunaciones, String notas) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.dueño_id = dueño_id;
        this.fechaNacimiento = fechaNacimiento;
        this.vacunaciones = vacunaciones;
        this.notas = notas;
    }

    // Constructor vacío
    public DTOmascota() {
        // Aquí puedes inicializar los atributos con valores por defecto, si es necesario
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getDueño_id() {
        return dueño_id;
    }

    public void setDueño_id(int dueño_id) {
        this.dueño_id = dueño_id;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getVacunaciones() {
        return vacunaciones;
    }

    public void setVacunaciones(String vacunaciones) {
        this.vacunaciones = vacunaciones;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
