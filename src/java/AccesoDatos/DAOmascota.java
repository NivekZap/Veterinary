package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import java.sql.*;
import java.util.*;
import java.sql.Date;

import static openejb.shade.org.apache.xalan.lib.ExsltDatetime.date;
public class DAOmascota {
    private Conexion conexion =  new Conexion();
    
    private DTOmascota masc =  new DTOmascota();
    
    public DAOmascota(){}   
    
    public ArrayList<DTOmascota>ListaMascota(){
        ArrayList<DTOmascota> Lista = new ArrayList<>();
    String consulta = "select * from mascota";
    try {
        Statement st = conexion.getCon().createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            DTOmascota masc = new DTOmascota(); // Crear nueva instancia en cada iteración
            masc.setId(rs.getInt(1));
            masc.setNombre(rs.getString(2));
            masc.setEspecie(rs.getString(3));
            masc.setRaza(rs.getString(4));
            masc.setEdad(rs.getString(5));
            masc.setPeso(rs.getString(6));
            masc.setDueño_id(rs.getInt(7));
            masc.setFechaNacimiento(rs.getDate(8));
            masc.setVacunaciones(rs.getString(9));
            masc.setNotas(rs.getString(10));
            Lista.add(masc);
        }
    } catch (Exception ex) {
        System.out.println("ERROR al recuperar mascotas..." + ex);
    }
    return Lista;
    }//fin de listausuarios
    //*************************************************************************************
    public boolean agregarMascota() {
    String sql = "INSERT INTO mascota VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = conexion.getCon().prepareStatement(sql);
        ps.setByte(1, (byte) masc.getId());
        ps.setString(2, masc.getNombre());
        ps.setString(3, masc.getEspecie());
        ps.setString(4, masc.getRaza());
        ps.setString(5, masc.getEdad());
        ps.setString(6, masc.getPeso());
        ps.setByte(7, (byte) masc.getDueño_id());
        
        // Convert to java.sql.Date
        java.util.Date utilDate = masc.getFechaNacimiento();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        ps.setDate(8, sqlDate);
        
        ps.setString(9, masc.getVacunaciones());
        ps.setString(10, masc.getNotas());
        
        ps.executeUpdate();
        return true;
    } catch (Exception ex) {
        System.out.println("ERROR no se puede agregar.." + ex);
        return false;
    }
}

    
        //*************************************************************************************
    public boolean actualizarMascota(){
        String sql="update mascota set  nombre=?, especie=?, raza=?, edad=?, peso=?, dueño_id=? fechaNacimiento=?, vacunaciones=?, notas=? where id=?";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);           
            ps.setString(1, masc.getNombre());
            ps.setString(2, masc.getEspecie());
            ps.setString(3, masc.getRaza());
            ps.setString(4, masc.getEdad());
            ps.setString(5, masc.getPeso());
            ps.setByte(6, (byte) masc.getDueño_id());
            java.util.Date utilDate = masc.getFechaNacimiento();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(7, sqlDate);
            ps.setString(8, masc.getVacunaciones());
            ps.setString(9, masc.getNotas());
            ps.setByte(10,(byte) masc.getId());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede actualizar.."+ex);
       }
        return false;
    }//fin actualizar
        //*************************************************************************************
    public void editarMascota(DTOmascota mascota){
        masc = mascota;
    }//fin editar
    //*************************************************************************************
    public boolean eliminarMascota(String id) {
    String sql = "DELETE FROM mascota WHERE id = ?";
    try {
        PreparedStatement ps = conexion.getCon().prepareStatement(sql);
        ps.setString(1, id);
        int rowsAffected = ps.executeUpdate();
        
        // Verificar si se eliminó algún registro
        if (rowsAffected > 0) {
            System.out.println("Usuario eliminado correctamente.");
            return true;
        } else {
            System.out.println("No se encontró un usuario con el ID especificado.");
            return false;
        }
    } catch (Exception ex) {
        System.out.println("ERROR: No se pudo eliminar el usuario.");
        ex.printStackTrace();
        return false;
    }
}//fin de eliminar 
        //*************************************************************************************
    //constructores y getter and setter
        //*************************************************************************************

    public Conexion getConexion() {return conexion;}
    public void setConexion(Conexion conexion) {this.conexion = conexion;}

    public DTOmascota getMasc() {return masc;}
    public void setMasc(DTOmascota masc) { this.masc = masc; }
    
}//fin de la clase
