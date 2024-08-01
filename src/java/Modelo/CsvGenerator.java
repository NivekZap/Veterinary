package Modelo;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
public class CsvGenerator  {

    public void generateCsv(ArrayList<DTOusuario> usuarios) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=\"usuarios.csv\"");

        ServletOutputStream outputStream = response.getOutputStream();
        
        // Escribir encabezados
        outputStream.println("ID,Nombre,Contraseña,Rol");

        // Escribir datos
        for (DTOusuario usuario : usuarios) {
            StringBuilder line = new StringBuilder();
            line.append(usuario.getId()).append(",");
            line.append(usuario.getNomUsuario()).append(",");
            line.append(usuario.getContra()).append(",");
            line.append(usuario.getRol());
            outputStream.println(line.toString());
        }

        outputStream.flush();
        outputStream.close();
        
        facesContext.responseComplete();
    }
   
}
    

