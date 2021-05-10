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
public class Genpdf_v {
    public void PDF(String jText, File Destino, Rectangle  TamanioPagina) throws DocumentException {
        /*Declaramos docu como un objeto Document
         Asignamos el tamaño de hoja y los margenes */
    	Rectangle pageSize = new Rectangle(800f, 600f); //ancho y alto
    	Document docu = new Document(pageSize, 20, 20, 10, 50);
    	
    	//Document docu = new Document(TamanioPagina, 20, 20, 10, 50);
        //writer es declarado como el metodo utilizado para escribir en el archivo
        PdfWriter writer = null;
        try {
            //Obtenemos la instancia del archivo a utilizar
            writer = PdfWriter.getInstance(docu, new FileOutputStream(Destino + ".pdf"));
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        //Agregamos un titulo al archivo
        docu.addTitle("Archivo pdf generado desde Java");
        //Agregamos el autor del archivo
        docu.addAuthor("Programa Java");
        //Abrimos el docu para edicion
        docu.open();
        //Declaramos un texto como Paragraph
        //Le podemos dar formado como alineacion, tamaño y color a la fuente.
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        //parrafo.setFont(FontFactory.getFont("Sans", 20, Font.BOLD, BaseColor.BLUE));
        parrafo.add(jText);
        try {
            //Agregamos el texto al docu
            docu.add(parrafo);
            //Agregamos un salto de linea
          //  docu.add(new Paragraph("Registro de Usuarios"));
           //AQUI
            //Agregamos la tabla al docu haciendo 
            //la llamada al metodo tabla()
        // docu.add(DefaultTableModel modelo);
           
        } catch (DocumentException ex) {
            ex.getMessage();
        }
        docu.close(); //Cerramos el docu
        writer.close(); //Cerramos writer
        try {
            File path;
            path = new File(Destino + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void TamanioPagina() {
		// TODO Auto-generated method stub
		
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