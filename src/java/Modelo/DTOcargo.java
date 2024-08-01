package Modelo;
public class DTOcargo {
    private String idcargo;
    private String nombre;
    private long sueldo_min;
    private long sueldo_max;
    private String ind; //activo o inactivo
    public DTOcargo(){}
    //getter y setter
    public String getIdcargo() {   return idcargo;  }
    public void setIdcargo(String idcargo) {    this.idcargo = idcargo;    }
    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }
    public long getSueldo_min() {        return sueldo_min;    }
    public void setSueldo_min(long sueldo_min) {        this.sueldo_min = sueldo_min;   }
    public long getSueldo_max() {
        return sueldo_max;
    }

    public void setSueldo_max(long sueldo_max) {
        this.sueldo_max = sueldo_max;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }
    
}
