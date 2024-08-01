package Configuracion;
import java.sql.*;
public class Conexion implements Parametros {
    private Connection con; 
    public Conexion() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, CLAVE);
        } catch (Exception ex) {
            System.out.println("ERROR no se puede conectar.." + ex);
        }
    }//Fin constructor
    //getter y setter
    public Connection getCon() {     return con;    }
    public void setCon(Connection con) { this.con = con;   }    
}//fin de la clase
