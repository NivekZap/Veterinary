package Modelo;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
public class CsvGeneratorC  {

    public void generateCsvC (ArrayList<DTOcita> cita) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=\"citas.csv\"");

        ServletOutputStream outputStream = response.getOutputStream();
        
        // Escribir encabezados
        outputStream.println("ID,ClienteID, DoctorID, MascotaID, SalaID, Fecha, Hora, Motivo, Notas, Estado");

        // Escribir datos
        for (DTOcita citas : cita) {
            StringBuilder line = new StringBuilder();
            line.append(citas.getId()).append(",");
            line.append(citas.getClienteID()).append(",");
            line.append(citas.getDoctorID()).append(",");
            line.append(citas.getMascotaID());
            line.append(citas.getSalaID());
            line.append(citas.getFechaHoraInicio());
            line.append(citas.getFechaHoraFin());
            line.append(citas.getMotivo());
            line.append(citas.getNotas());
            line.append(citas.getEstado());
            outputStream.println(line.toString());
        }

        outputStream.flush();
        outputStream.close();
        
        facesContext.responseComplete();
    }
   
}
    

