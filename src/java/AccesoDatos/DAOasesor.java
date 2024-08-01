package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import java.sql.*;
import java.util.*;
public class DAOasesor {
    private Conexion conexion =  new Conexion();
    private DTOusuario usu =  new DTOusuario();
    public DAOasesor(){}   
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
    
    public void editarUsuario(DTOusuario usuarios){
        usu = usuarios;
    }//fin editar
    public boolean eliminarUsuario(String id){
        String sql="update usuarios set ind='N' where idcargo=?";
        try{
            PreparedStatement ps =  conexion.getCon().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se pude eliminar");
        }
        return false;
    }//fin eliminar
    //getter y setter
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
    public Conexion getConexion() { return conexion;}
    public void setConexion(Conexion conexion) { this.conexion = conexion;}

    public DTOusuario getUsu() {return usu;}
    public void setUsu(DTOusuario usu) {this.usu = usu; }
     
}//fin de la clase
