package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DAOusuario {
    private Conexion conexion =  new Conexion();
    private DTOusuario usu =  new DTOusuario();
    public DAOusuario(){}   
    public ArrayList<DTOusuario>ListaUsuario(){
        ArrayList<DTOusuario> Lista = new ArrayList<>();
    String consulta = "select * from usuarios";
    try {
        Statement st = conexion.getCon().createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            DTOusuario usu = new DTOusuario(); // Crear nueva instancia en cada iteración
            usu.setId(rs.getInt(1));
            usu.setNomUsuario(rs.getString(2));
            usu.setContra(rs.getString(3));
            usu.setRol(rs.getString(4)); // Asumiendo que el rol está en la cuarta columna
            Lista.add(usu);
        }
    } catch (Exception ex) {
        System.out.println("ERROR al recuperar usuarios..." + ex);
    }
    return Lista;
    }//fin de listausuarios
    
    public boolean agregarUsuario(){
        String sql="insert into usuarios values(?,?,?,?)";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setByte(1, (byte) usu.getId());
            ps.setString(2, usu.getNomUsuario());
            ps.setString(3, usu.getContra());
            ps.setString(4, usu.getRol());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede agregar.."+ex);
       }
        return false;
    }//fin agregar   
    
    public boolean actualizarUsuarios(){
        String sql="update usuarios set nombreusuario=?,contraseña=?,rol=? where Id=?";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);           
            ps.setString(1, usu.getNomUsuario());
            ps.setString(2, usu.getContra());
            ps.setString(3, usu.getRol());
            ps.setByte(4,(byte) usu.getId());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede actualizar.."+ex);
       }
        return false;
    }//fin actualizar
    ///////////////////////////////////////////////////
    public void editarUsuario(DTOusuario usuarios){
        usu = usuarios;
    }//fin editar
    
    
    
public boolean eliminarUsuario(String id) {
    String sql = "DELETE FROM usuarios WHERE Id = ?";
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
}
    public boolean verificarCredenciales(String username, String password) {
           String consulta = "SELECT * FROM usuarios WHERE nombreusuario = ? AND contraseña = ?";
           try {
               PreparedStatement ps = conexion.getCon().prepareStatement(consulta);
               ps.setString(1, username);
               ps.setString(2, password);
               ResultSet rs = ps.executeQuery();
               return rs.next(); // Retorna true si encuentra resultados, es decir, credenciales válidas
           } catch (Exception ex) {
               System.out.println("ERROR al verificar credenciales..." + ex);
               return false;
           }
    }
    
    //********************************************************************************
     //genertador de PDF 
    public String obtenerDatosParaPDF() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID\tNombre\tContraseña\tRol\n");
    
    ArrayList<DTOusuario> usuarios = ListaUsuario(); // Obtener los usuarios desde la base de datos
    
    for (DTOusuario usuario : usuarios) {
        sb.append(usuario.getId()).append("\t")
          .append(usuario.getNomUsuario()).append("\t")
          .append(usuario.getContra()).append("\t")
          .append(usuario.getRol()).append("\n");
    }
    
    return sb.toString();
}
     
     public void descargarPdf() {
    PdfGenerator pdfGenerator = new PdfGenerator();
    try {
        String data = obtenerDatosParaPDF();
        pdfGenerator.generatePdf(data);
    } catch (IOException | DocumentException e) {
        e.printStackTrace();
    }
}
//********************************************************************************
     //genertador de csv para excel
     public void descargarCsv() {
    CsvGenerator csvGenerator = new CsvGenerator();
    try {
        ArrayList<DTOusuario> usuarios = ListaUsuario();
        csvGenerator.generateCsv(usuarios);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
//********************************************************************************
        //getter y setter
    public Conexion getConexion() { return conexion;}
    public void setConexion(Conexion conexion) { this.conexion = conexion;}

    public DTOusuario getUsu() {return usu;}
    public void setUsu(DTOusuario usu) {this.usu = usu; }
     
}//fin de la clase
