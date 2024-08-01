package Modelo;
import java.time.LocalDateTime;


public class DTOcita {
    private int id;
    private int clienteID;
    private int doctorID;
    private int mascotaID;
    private int salaID;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin; // Usar solo si optas por la segunda opción
    private String motivo;
    private String notas; // Usar solo si optas por la segunda opción
    private String estado;

    // Constructor para la segunda opción
    public DTOcita(int id, int clienteID, int doctorID, int mascotaID, int salaID, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String motivo, String notas, String estado) {
        this.id = id;
        this.clienteID = clienteID;
        this.doctorID = doctorID;
        this.mascotaID = mascotaID;
        this.salaID = salaID;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.motivo = motivo;
        this.notas = notas;
        this.estado = estado;
    }

    public DTOcita() {
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getMascotaID() {
        return mascotaID;
    }

    public void setMascotaID(int mascotaID) {
        this.mascotaID = mascotaID;
    }

    public int getSalaID() {
        return salaID;
    }

    public void setSalaID(int salaID) {
        this.salaID = salaID;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
