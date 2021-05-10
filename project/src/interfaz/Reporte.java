package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import conexion.ConexionSql;
import generapdf.GeneraPDF;
import generapdf.Genpdf_v;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;
import util.Constantes;

public class Reporte extends JInternalFrame {

	Genpdf_v pdf =new Genpdf_v();
	 GeneraPDF PDF = new GeneraPDF();
	    private Rectangle ArraySizePage[] = {PageSize.LEGAL, PageSize.LETTER};
	    public Rectangle SizePage = null;
	   String nameSizePage[] = {"Legal", "Carta"};
	    private File ruta_destino = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reporte() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Reporte.class.getResource("/img/estetoscopio.png")));
		setTitle("REPORTES                            "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 420);
		//setBounds(100, 100, 1333, 620);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REPORTES");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(171, 56, 220, 50);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TABALS  ACTUALIZADAS ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(171, 117, 220, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reporte.class.getResource("/img/reporte/reporte.png")));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_2.setBounds(489, 61, 158, 77);
		panel.add(lblNewLabel_2);
		
		JButton btn_exit = new JButton(Constantes.STR_EXIT);
		btn_exit.setMnemonic('x');
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setBounds(716, 37, 89, 23);
		panel.add(btn_exit);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(51, 25, 773, 161);
		panel.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TEBLA DE EXPEDIENTE","TABAL DE CONSULTAS "}));
		comboBox.setBounds(106, 329, 256, 20);
		panel.add(comboBox);
		
		JButton btnGenerarReporte = new JButton("GENERAR REPORTE");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 				
				if (comboBox.getSelectedIndex() != 0) {
					consultas();
					comboBox.setSelectedIndex(0);
				}else
				if (comboBox.getSelectedIndex() != -1) {
					expediente();
			        comboBox.setSelectedIndex(0);
				}
							
			}
		});
		btnGenerarReporte.setBounds(433, 282, 158, 38);
		panel.add(btnGenerarReporte);
		
		JComboBox comboBox1 = new JComboBox();
		 //Selecciona el Tama�o de hoja del PDF
        comboBox1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                        
                            if (comboBox1.getSelectedIndex() != -1) {
                           SizePage = ArraySizePage[comboBox1.getSelectedIndex()];
                            System.err.println(comboBox1.getSelectedIndex());
    }
                
	  }	
	});
		comboBox1.setVisible(true);
		//comboBox1.setVisible(false);
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Carta", "Legal"}));
		comboBox1.setBounds(451, 236, 112, 20);
		panel.add(comboBox1);
		
		//		area.setBounds(586, 197, 150, 300);
		 area = new JTextArea();
		 area.setVisible(false);
		 area.setColumns(20);
         area.setAutoscrolls(rootPaneCheckingEnabled);    
		area.setBounds(624, 405, 150, 300);
		panel.add(area);
		
		JLabel lblTamao = new JLabel("Tama\u00F1o");
		lblTamao.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamao.setBounds(451, 211, 112, 14);
		panel.add(lblTamao);
		
		F_inicio = new JDateChooser();
		F_inicio.setBounds(106, 246, 256, 20);
		panel.add(F_inicio);
		
		lbl_Fecha1 = new JLabel("Fecha Inicial");
		lbl_Fecha1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Fecha1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Fecha1.setBounds(106, 211, 256, 14);
		panel.add(lbl_Fecha1);
	}
	private void expediente() {
		 //comboBox1.setSelectedItem("Carta");
		String sql="SELECT * FROM   expediente";
		String datos[] = new String[10];
        Statement st;
        
	
			area.append("");
			area.append("                                                                                             EXPEDIENTES                                                    \n");
			area.append("\n");
			area.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			area.append("");
            area.append("   REGISTRO         NOMBRE                   APELLIDO PATERNO             APELLIDO MATERNO                    FECHA        \n");
            area.append("\n ");
            try{
                st = reg.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
               datos[0]=rs.getString(1);//Registro
               datos[1]=rs.getString(2);//Nombre
               datos[2]=rs.getString(3);//AP
               datos[3]=rs.getString(4);//AM
               datos[4]=rs.getString(5);//Fecha
            
             area.append("       "+datos[0]+"                           "+datos[1]+"                                   "+datos[2]+"                                      "+datos[3]+"                             "+datos[4]+"\n");

           }            
           } catch (SQLException ex){
             Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,null,ex);                   
                }  
			area.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			area.append("");
			//Imprime la Factura en PDF
            ruta_destino = pdf.Colocar_Destino(ruta_destino);
            //si destino es diferente de null  
			 try {      
	                pdf.PDF(area.getText(), ruta_destino, SizePage);
	                System.out.println(SizePage);
	                JOptionPane.showMessageDialog(null, "Documento PDF creado");
	            } catch (Exception ex) {
	                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            area.setText("");
	            dispose();		
	}
	
	private void consultas() {
		//comboBox1.setSelectedItem("Carta");
		String sql="SELECT * FROM  detalle_rm";
		String datos[] = new String[10];
        Statement st;
        
	
			area.append("");
			area.append("                                                   CONSULTAS                                                    \n");
			area.append("\n");
			area.append("-----------------------------------------------------------------------------------------------------------\n");
			area.append("");
            area.append("             RECETA              FECHA             FOLIO              EXPEDIENTE     \n");
            area.append("\n ");
            try{
                st = reg.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
               datos[0]=rs.getString(1);//Receta
               datos[1]=rs.getString(2);//Fecha
               datos[2]=rs.getString(3);//Folio
               datos[3]=rs.getString(3);//Expediente
               datos[4]=rs.getString(3);//Indicaicones
            
             area.append("             "+datos[0]+"            "+datos[1]+"         "+datos[2]+"             "+datos[3]+"  \n");
            // area.append("               "+datos[0]);
           //  area.append("                                                     "+datos[1]);
            // area.append("                                                                                           "+datos[2]+"             \n");
           }            
           } catch (SQLException ex){
             Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,null,ex);                   
                }  
			area.append("-------------------------------------------------------------------------------------------------------------\n");
			area.append("");
			//Imprime la Factura en PDF
            ruta_destino = PDF.Colocar_Destino(ruta_destino);
            //si destino es diferente de null  
			 try {      
	                PDF.PDF(area.getText(), ruta_destino, SizePage);
	                System.out.println(SizePage);
	                JOptionPane.showMessageDialog(null, "Documento PDF creado");
	            } catch (Exception ex) {
	                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            area.setText("");
	            dispose();
			
		
	}
	
	
	
	

	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    private JTextArea area;
    JComboBox comboBox1 = new JComboBox();
    JComboBox comboBox = new JComboBox();
    private JDateChooser F_inicio;
    private JLabel lbl_Fecha1;
//------------------------------------------	
	private void setIcon(Image image) {	}
}
