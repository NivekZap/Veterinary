package Modelo;

public class DTOsala {
    private int ID;
    private String NombreSala, Especializacion, Estado;

    public DTOsala() {
    }

    public DTOsala(int ID, String NombreSala,String Especializacion, String Estado) {
        this.ID = ID;
        this.NombreSala = NombreSala;
        this.Especializacion = Especializacion;
        this.Estado = Estado;
    }

    public int getID() { return ID; }
    public void setID(int ID) {   this.ID = ID;  }

    public String getNombreSala() {  return NombreSala;  }
    public void setNombreSala(String NombreSala) {this.NombreSala = NombreSala; }

    public String getEspecializacion() {      return Especializacion;   }
    public void setEspecializacion(String Especializacion) {  this.Especializacion = Especializacion;}

    public String getEstado() {   return Estado;  }
    public void setEstado(String Estado) { this.Estado = Estado;  }
}
