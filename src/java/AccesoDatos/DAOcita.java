package AccesoDatos;
import Configuracion.*;
import Modelo.*;
import Pdf.PdfGeneratorC;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOcita {
    private Conexion conexion = new Conexion();
    private DTOcita cita = new DTOcita();
    
   public ArrayList<DTOcita> listaCitas() {
        ArrayList<DTOcita> lista = new ArrayList<>();
        String consulta = "SELECT * FROM cita";
        try (Statement st = conexion.getCon().createStatement();
             ResultSet rs = st.executeQuery(consulta)) {
            while (rs.next()) {
                DTOcita cita = new DTOcita();
                cita.setId(rs.getInt(1));
                cita.setClienteID(rs.getInt(2));
                cita.setDoctorID(rs.getInt(3));
                cita.setMascotaID(rs.getInt(4));
                cita.setSalaID(rs.getInt(5));
                cita.setFechaHoraInicio(rs.getTimestamp(6).toLocalDateTime());
                cita.setFechaHoraFin(rs.getTimestamp(7).toLocalDateTime());
                cita.setMotivo(rs.getString(8));
                cita.setNotas(rs.getString(9));
                cita.setEstado(rs.getString(10));
                lista.add(cita);
            }
        } catch (SQLException ex) {
            // Manejo de excepciones con logging
            ex.printStackTrace();
        }
        return lista;
    }

    // Método para obtener la lista de clientes


    public boolean agregarCita() {
        String sql = "INSERT INTO cita (ClienteID, DoctorID, MascotaID, SalaID, Fecha, Hora, Motivo, Notas, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setInt(1, cita.getClienteID());
            ps.setInt(2, cita.getDoctorID());
            ps.setInt(3, cita.getMascotaID());
            ps.setInt(4, cita.getSalaID());
            ps.setTimestamp(5, Timestamp.valueOf(cita.getFechaHoraInicio()));
            ps.setTimestamp(6, Timestamp.valueOf(cita.getFechaHoraFin()));
            ps.setString(7, cita.getMotivo());
            ps.setString(8, cita.getNotas());
            ps.setString(9, cita.getEstado());

            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("ERROR no se puede agregar.." + ex);
            return false;
        }
    }

    public boolean actualizarCita() {
        String sql = "UPDATE cita SET ClienteID=?, DoctorID=?, MascotaID=?, SalaID=?, Fecha=?, Hora=?, Motivo=?, Notas=?, Estado=? WHERE ID=?";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setInt(1, cita.getClienteID());
            ps.setInt(2, cita.getDoctorID());
            ps.setInt(3, cita.getMascotaID());
            ps.setInt(4, cita.getSalaID());
            ps.setTimestamp(5, Timestamp.valueOf(cita.getFechaHoraInicio()));
            ps.setTimestamp(6,Timestamp.valueOf(cita.getFechaHoraFin()));
            ps.setString(7, cita.getMotivo());
             ps.setString(8, cita.getNotas());
            ps.setString(9, cita.getEstado());
            ps.setInt(10, cita.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            // Manejo de excepciones con logging
            ex.printStackTrace();
            return false;
        
        }
    }

    public void editarCita(DTOcita cita) {
        this.cita = cita;
    }

    public boolean eliminarCita(String id) {
        String sql = "DELETE FROM cita WHERE id = ?";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();

            // Verificar si se eliminó algún registro
            if (rowsAffected > 0) {
                System.out.println("Cita eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un Cita con el ID especificado.");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("ERROR: No se pudo eliminar el Cita.");
            ex.printStackTrace();
            return false;
        }
    }
 //genertador de PDF 
    public String obtenerDatosParaPDF() {
    StringBuilder sb = new StringBuilder();
    //sb.append("ID\tClienteID\tDoctorID\tMascotaID\tSalaID\tFecha\tHora\tMotivo\tNotas\tEstado\n");
    
    ArrayList<DTOcita> cita = listaCitas(); 
    
    for (DTOcita citas : cita) {
        sb.append(citas.getId()).append("\t")
          .append(citas.getClienteID()).append("\n")
          .append(citas.getDoctorID()).append("\n")
          .append(citas.getMascotaID()).append("\n")
          .append(citas.getSalaID()).append("\n")
          .append(citas.getFechaHoraFin()).append("\n")
          .append(citas.getFechaHoraInicio()).append("\n")
          .append(citas.getMotivo()).append("\n")
          .append(citas.getNotas()).append("\n")
          .append(citas.getEstado()).append("\n");
        
    }
    return sb.toString();
}
     public void descargarPdfC() {
    PdfGeneratorC pdfGeneratorC = new PdfGeneratorC();
    try {
        String data = obtenerDatosParaPDF();
        pdfGeneratorC.generatePdfC(data);
    } catch (IOException | DocumentException e) {
        e.printStackTrace();
    }
}
    
         //genertador de csv para excel
     public void descargarCsv() {
    CsvGeneratorC csvGeneratorC = new CsvGeneratorC();
    try {
        ArrayList<DTOcita> citas = listaCitas();
        csvGeneratorC.generateCsvC(citas);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    
    
    
    
    
    
    // Getters and setters
    public Conexion getConexion() { return conexion;}
    public void setConexion(Conexion conexion) {this.conexion = conexion; }
    public DTOcita getCita() { return cita; }
    public void setCita(DTOcita cita) { this.cita = cita;}
}
