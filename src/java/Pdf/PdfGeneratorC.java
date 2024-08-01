/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pdf;

import Pdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfGeneratorC {

    public void generatePdfC(String data) throws IOException, DocumentException {
        Document document = new Document();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=\"Citas.pdf\"");
            
            ServletOutputStream outputStream = response.getOutputStream();
            PdfWriter.getInstance(document, outputStream);

            document.open();
            
            // Add title
            document.add(new Paragraph("Lista de Citas"));
            
            // Create table
            PdfPTable table = new PdfPTable(10); 
            table.addCell("Id");
            table.addCell("clienteID");
            table.addCell("doctorID");
            table.addCell("mascotaID");
            table.addCell("salaID");
            table.addCell("fechaHoraInicio");
            table.addCell("fechaHoraFin");
            table.addCell("motivo");
            table.addCell("notas");
            table.addCell("estado");
            // Add rows
            String[] rows = data.split("\n");
            for (String row : rows) {
                String[] cells = row.split("\t");
                for (String cell : cells) {
                    table.addCell(cell);
                }
            }
            
            document.add(table);
            document.close();
            
            facesContext.responseComplete();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
