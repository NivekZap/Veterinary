package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import java.sql.*;
import java.util.*;
public class DAOcliente {
    private Conexion conexion =  new Conexion();
    private DTOcliente cli =  new DTOcliente();
    public DAOcliente(){}   
    
    public ArrayList<DTOcliente>ListaCliente(){
        ArrayList<DTOcliente> Lista = new ArrayList<>();
    String consulta = "select * from cliente";
    try {
        Statement st = conexion.getCon().createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            DTOcliente cli = new DTOcliente(); 
            cli.setId(rs.getInt(1));
            cli.setDni(rs.getString(2)); 
            cli.setNombre(rs.getString(3));
            cli.setApellido(rs.getString(4));
            cli.setCorreo(rs.getString(5)); 
            cli.setTelefono(rs.getString(6)); 
            Lista.add(cli);
        }
    } catch (Exception ex) {
        System.out.println("ERROR al recuperar cliente..." + ex);
    }
    return Lista;
    }//fin de listausuarios
    
    public boolean agregarCliente(){
        String sql="insert into cliente values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setByte(1, (byte) cli.getId());
            ps.setString(2, cli.getDni());
            ps.setString(3, cli.getNombre());
            ps.setString(4, cli.getApellido());
            ps.setString(5, cli.getCorreo());
            ps.setString(6, cli.getTelefono());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede agregar.."+ex);
       }
        return false;
    }//fin agregar   
    
    public boolean actualizarCliente(){
        String sql="update cliente set dni=?, nombre=?, apellido=?, correo=?, telefono=? where id=?";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);           
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getApellido());
            ps.setString(4, cli.getCorreo());
            ps.setString(5, cli.getTelefono());
            ps.setByte(6,(byte) cli.getId());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede actualizar.."+ex);
       }
        return false;
    }//fin actualizar
    
    public void editarCliente(DTOcliente cliente){
        cli = cliente;
    }//fin editar
    
   public boolean eliminarCliente(String id) {
    String sql = "DELETE FROM cliente WHERE id = ?";
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
    //getter y setter
    
    public Conexion getConexion() { return conexion;}
    public void setConexion(Conexion conexion) { this.conexion = conexion;}

    public DTOcliente getCli() {return cli; }
    public void setCli(DTOcliente cli) {this.cli = cli;}

     
}//fin de la clase
