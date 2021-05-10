package interfaz;
import generapdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/***/
public class GeneraPDF {
    public void PDF(String jText, File Destino, Rectangle TamanioPagina) throws DocumentException {
        /*Declaramos documento como un objeto Document
         Asignamos el tamaño de hoja y los margenes */
        Document documento = new Document(TamanioPagina, 80, 80, 75, 75);
        //writer es declarado como el metodo utilizado para escribir en el archivo
        PdfWriter writer = null;
        try {
            //Obtenemos la instancia del archivo a utilizar
            writer = PdfWriter.getInstance(documento, new FileOutputStream(Destino + ".pdf"));
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        //Agregamos un titulo al archivo
        documento.addTitle("Archivo pdf generado desde Java");
        //Agregamos el autor del archivo
        documento.addAuthor("Programa Java");
        //Abrimos el documento para edicion
        documento.open();
        //Declaramos un texto como Paragraph
        //Le podemos dar formado como alineacion, tamaño y color a la fuente.
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        //parrafo.setFont(FontFactory.getFont("Sans", 20, Font.BOLD, BaseColor.BLUE));
        parrafo.add(jText);
        try {
            //Agregamos el texto al documento
            documento.add(parrafo);
            //Agregamos un salto de linea
          //  documento.add(new Paragraph("Registro de Usuarios"));
           //AQUI
            //Agregamos la tabla al documento haciendo 
            //la llamada al metodo tabla()
        // documento.add(DefaultTableModel modelo);
           
        } catch (DocumentException ex) {
            ex.getMessage();
        }
        documento.close(); //Cerramos el documento
        writer.close(); //Cerramos writer
        try {
            File path;
            path = new File(Destino + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public File Colocar_Destino(File ruta_destino) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "PDF");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
        return ruta_destino;
    }
}