package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import Pdf.PdfGeneratorD;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
public class DAOdoctor {
    private Conexion conexion =  new Conexion();
    private DTOdoctor doc =  new DTOdoctor();
    public DAOdoctor(){}   
    public ArrayList<DTOdoctor>ListaDoctor(){
        ArrayList<DTOdoctor> Lista = new ArrayList<>();
    String consulta = "select * from doctor";
    try {
        Statement st = conexion.getCon().createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            DTOdoctor doc = new DTOdoctor(); // Crear nueva instancia en cada iteración
            doc.setID(rs.getInt(1));
            doc.setNombre(rs.getString(2));
            doc.setApellido(rs.getString(3));
            doc.setEspecializacion(rs.getString(4));
            doc.setCorreoElectronico(rs.getString(5));
            doc.setTelefono(rs.getString(6));
            Lista.add(doc);
        }
    } catch (Exception ex) {
        System.out.println("ERROR al recuperar personal..." + ex);
    }
    return Lista;
    }//fin de listausuarios
    
    public boolean agregarPersonal(){
        String sql="insert into doctor values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setByte(1, (byte) doc.getID());
            ps.setString(2, doc.getNombre());
            ps.setString(3, doc.getApellido());
            ps.setString(4, doc.getEspecializacion());
            ps.setString(5, doc.getCorreoElectronico());
            ps.setString(6, doc.getTelefono());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede agregar.."+ex);
       }
        return false;
    }//fin agregar   
    public boolean actualizarPersonal(){
        String sql="update doctor set Nombre=?,Apellido=?,Especializacion=?,CorreoElectronico=?, Telefono=? where ID=?";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);           
            ps.setString(1,doc.getNombre() );
            ps.setString(2, doc.getApellido());
            ps.setString(3, doc.getEspecializacion());
            ps.setString(4, doc.getCorreoElectronico());
            ps.setString(5, doc.getTelefono());
            ps.setByte(6,(byte) doc.getID());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede actualizar.."+ex);
       }
        return false;
    }//fin actualizar
    
    public void editarPersonal(DTOdoctor doctor){
        doc = doctor;
    }//fin editar
    
    public boolean eliminarPersonal(String id) {
    String sql = "DELETE FROM doctor WHERE ID = ?";
    try {
        PreparedStatement ps = conexion.getCon().prepareStatement(sql);
        ps.setString(1, id);
        int rowsAffected = ps.executeUpdate();
        
        // Verificar si se eliminó algún registro
        if (rowsAffected > 0) {
            System.out.println("Doctor eliminado correctamente.");
            return true;
        } else {
            System.out.println("No se encontró un Doctor con el ID especificado.");
            return false;
        }
    } catch (Exception ex) {
        System.out.println("ERROR: No se pudo eliminar el usuario.");
        ex.printStackTrace();
        return false;
    }
}
     //********************************************************************************
     //genertador de PDF 
    public String obtenerDatosParaPDF() {
    StringBuilder sb = new StringBuilder();
    //sb.append("ID\tNombre\tApellido\tEspecializacion\tCorreoElectronico\tTelefono\n");
    
    ArrayList<DTOdoctor> doctor = ListaDoctor(); 
    
    for (DTOdoctor doc : doctor) {
        sb.append(doc.getID()).append("\t")

          .append(doc.getNombre()).append("\t")
          .append(doc.getApellido()).append("\n")
          .append(doc.getEspecializacion()).append("\n")
          .append(doc.getCorreoElectronico()).append("\n")      
          .append(doc.getTelefono()).append("\n");
                
    }
    
    return sb.toString();
}
     
     public void descargarPdfD() {
    PdfGeneratorD pdfGeneratorD = new PdfGeneratorD();
    try {
        String data = obtenerDatosParaPDF();
        pdfGeneratorD.generatePdfD(data);
    } catch (IOException | DocumentException e) {
        e.printStackTrace();
    }
}
   
//fin eliminar
    //getter y setter
   // public boolean verificarCredenciales(String username, String password) {
          // String consulta = "SELECT * FROM doctor WHERE nombreusuario = ? AND contraseña = ?";
          // try {
              // PreparedStatement ps = conexion.getCon().prepareStatement(consulta);
              // ps.setString(1, username);
              // ps.setString(2, password);
               
               
               
               
           //    ResultSet rs = ps.executeQuery();
           //    return rs.next(); // Retorna true si encuentra resultados, es decir, credenciales válidas
         //  } catch (Exception ex) {
          //     System.out.println("ERROR al verificar credenciales..." + ex);
          //     return false;
          // }
 //   }
   // public Conexion getConexion() { return conexion;}
    // public void setConexion(Conexion conexion) { this.conexion = conexion;}

    public Conexion getConexion() {return conexion; }
    public void setConexion(Conexion conexion) { this.conexion = conexion; }
    
    public DTOdoctor getDoc() {        return doc;   }
    public void setDoc(DTOdoctor doc) { this.doc = doc;}

     
}//fin de la clase
