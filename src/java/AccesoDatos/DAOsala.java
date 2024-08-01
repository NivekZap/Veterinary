package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import java.sql.*;
import java.util.*;
public class DAOsala {
    private Conexion conexion =  new Conexion();
    private DTOcargo car =  new DTOcargo();
    public DAOsala(){}   
    public ArrayList<DTOcargo> ListaCargos(){
        ArrayList<DTOcargo> Lista = new ArrayList();
        String consulta = "select * from cargo where ind='S' order by 2";
        try{
            Statement st = conexion.getCon().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()){
                DTOcargo car=new DTOcargo();
                car.setIdcargo(rs.getString(1));
                car.setNombre(rs.getString(2));
                car.setSueldo_min(rs.getLong(3));
                car.setSueldo_max(rs.getLong(4));
                car.setInd(rs.getString(5));  
                Lista.add(car);
            }
        }catch(Exception ex){
            System.out.println("ERROR al recuperar cargos..."+ex);
        }
        return Lista;
    }//fin de listacargo
    
    public boolean agregarCargo(){
        String sql="insert into cargo values(?,?,?,?,'S')";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, car.getIdcargo());
            ps.setString(2, car.getNombre());
            ps.setLong(3, car.getSueldo_min());
            ps.setLong(4, car.getSueldo_max());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede agregar.."+ex);
       }
        return false;
    }//fin agregar   
    public boolean actualizarCargo(){
        String sql="update cargo set nombre=?,sueldo_min=?,sueldo_max=? where idcargo=?";
        try{
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);           
            ps.setString(1, car.getNombre());
            ps.setLong(2, car.getSueldo_min());
            ps.setLong(3, car.getSueldo_max());
            ps.setString(4, car.getIdcargo());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR no se puede actualizar.."+ex);
       }
        return false;
    }//fin actualizar
    
    public void editarCargo(DTOcargo cargo){
        car = cargo;
    }//fin editar
    public boolean eliminarCargo(String id){
        String sql="update cargo set ind='N' where idcargo=?";
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
    public Conexion getConexion() {      return conexion;    }
    public void setConexion(Conexion conexion) {     this.conexion = conexion;    }
    public DTOcargo getCar() {      return car;    }
    public void setCar(DTOcargo car) {       this.car = car;    }    
}//fin de la clase
