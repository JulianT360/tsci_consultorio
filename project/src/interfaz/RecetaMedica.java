package interfaz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import conexion.ConexionSql;
import generapdf.Genpdf_v;

import static interfaz.InicioSesion.usuarioMedico;
import static interfaz.InicioSesion.usuarioPersona;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class RecetaMedica extends JInternalFrame {

	Genpdf_v pdf =new Genpdf_v();
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
					RecetaMedica frame = new RecetaMedica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static int id_Receta = 0;
	private JTable table;
	

	/**
	 * Create the frame.
	 */
	public RecetaMedica() {
		setTitle("RECETA MEDICA"
				+ "                                         "
				+ "                                         "
				+ "                                          "
				+ "                                          "
				+ "                                           "
				+ "                                           "
				+ "                                           "
				+ "                                           "
				+ "                                           "
				+ "       ");//100, 100, 1300, 365
		setBounds(100, 100, 1300, 381);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
			
		JButton btnMas = new JButton("MAS");
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusquedaMedic BM=new BusquedaMedic();
				BM.setVisible(true);
			}
		});
		btnMas.setBounds(461, 32, 89, 23);
		panel.add(btnMas);
		
		JButton btnMenos = new JButton("MENOS");
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selec= table_rm.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel) RecetaMedica.table_rm.getModel();
				if (selec >= 0) {
					model.removeRow(selec);					
					JOptionPane.showMessageDialog(null, "no se que pasa ");

				}else {
					JOptionPane.showMessageDialog(null, "Selecciona el dato a eliminar");

				}
			}
		});
		btnMenos.setBounds(560, 32, 89, 23);
		panel.add(btnMenos);
		
		JLabel lblRecetaMedica = new JLabel("RECETA MEDICA");
		lblRecetaMedica.setBounds(24, 36, 148, 14);
		panel.add(lblRecetaMedica);
		
		//textArea.setBounds(720, 61, 417, 240);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(720, 61, 428, 240);
		scrollPane.setPreferredSize(new Dimension(txt_instruccion.WIDTH,txt_instruccion.HEIGHT));
		panel.add(scrollPane);
		
		txt_instruccion = new JTextField();
		txt_instruccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if((c>47 && c<58)){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		scrollPane.setViewportView(txt_instruccion);
		txt_instruccion.setColumns(10);
		
		
		JLabel lblInstuccionesMedicas = new JLabel("INSTUCCIONES MEDICAS");
		lblInstuccionesMedicas.setBounds(720, 36, 250, 14);
		panel.add(lblInstuccionesMedicas);
		
		JButton btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				consulta_d();
				d_rm();
				exp();
				pdf_receta();
				
				//dispose();
			}
		});
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnImprimir.setBounds(1170, 67, 89, 23);
		panel.add(btnImprimir);
		
		scrollPane_1.setBounds(24, 66, 625, 240);
		panel.add(scrollPane_1);
		
		table_rm = new JTable();
		table_rm.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicamento", "Cantidad", "Tomar", "Frecuencia", "Durante"
			}
		));
		table_rm.getColumnModel().getColumn(0).setPreferredWidth(185);
		table_rm.getColumnModel().getColumn(1).setPreferredWidth(91);
		table_rm.getColumnModel().getColumn(2).setPreferredWidth(71);
		table_rm.getColumnModel().getColumn(3).setPreferredWidth(92);
		table_rm.getColumnModel().getColumn(4).setPreferredWidth(83);
		scrollPane_1.setViewportView(table_rm);
		
		txt_expediente = new JTextField();
		txt_expediente.setBounds(268, 33, 48, 20);
		panel.add(txt_expediente);
		txt_expediente.setVisible(false);
		txt_expediente.setColumns(10);
				
		txt_folio = new JTextField();
		txt_folio.setBounds(326, 33, 48, 20);
		txt_folio.setVisible(false);
		panel.add(txt_folio);
		txt_folio.setColumns(10);
        
		
		
		txt_n = new JTextField();
		txt_n.setBounds(720, 5, 86, 20);
		txt_n.setVisible(false);
		panel.add(txt_n);
		txt_n.setColumns(10);
		
		txt_ap = new JTextField();
		txt_ap.setBounds(823, 5, 86, 20);
		txt_ap.setVisible(false);
		panel.add(txt_ap);
		txt_ap.setColumns(10);
		
		txt_am = new JTextField();
		txt_am.setBounds(919, 5, 86, 20);
		txt_am.setVisible(false);
		panel.add(txt_am);
		txt_am.setColumns(10);
		
		txt_s = new JTextField();
		txt_s.setVisible(false);		
		txt_s.setBounds(1015, 5, 86, 20);
		panel.add(txt_s);
		txt_s.setColumns(10);
		
		txt_e = new JTextField();
		txt_e.setVisible(false);
		txt_e.setBounds(1111, 5, 86, 20);
		panel.add(txt_e);
		txt_e.setColumns(10);
		
		txt_receta = new JTextField();
		txt_receta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_receta.setHorizontalAlignment(SwingConstants.CENTER);
		txt_receta.setEditable(false);
		txt_receta.setBounds(162, 33, 56, 20);
		panel.add(txt_receta);
		txt_receta.setVisible(true);
		txt_receta.setColumns(10);
		/********************************************************/
		String query = "SELECT `AUTO_INCREMENT`\r\n" + 
				"FROM  INFORMATION_SCHEMA.TABLES\r\n" + 
				"WHERE TABLE_SCHEMA = 'medic'\r\n" + 
				"AND   TABLE_NAME   = 'detalle_rm';";
		Statement st;
		try {
			st = reg.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				txt_receta.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
		}
		/********************************************************/
		id_Receta=Integer.parseInt(txt_receta.getText()); 
		
		comboBox = new JComboBox();
		comboBox.setVisible(true);
		//comboBox1.setVisible(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Carta", "Legal"}));
		//comboBox1.setBounds(453, 291, 112, 20);
		//Selecciona el Tama�o de hoja del PDF
        comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                        
                            if (comboBox.getSelectedIndex() != -1) {
                           SizePage = ArraySizePage[comboBox.getSelectedIndex()];
                            System.err.println(comboBox.getSelectedIndex());
    }
                
	  }	
	});
		comboBox.setBounds(1162, 116, 112, 20);
		panel.add(comboBox);
		
		 area = new JTextArea();
		 area = new JTextArea();
		 area.setVisible(false);
		 area.setColumns(20);
         area.setAutoscrolls(rootPaneCheckingEnabled);
		 area.setBounds(1150, 172, 124, 230);
		 panel.add(area);
		 /* 
		 rdbtnNewRadioButton = new JRadioButton("New radio button");
		 rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 System.out.println("HOLA");
				   
					//if (RM==1) {
					
						//	Consulta l = new Consulta ();
					//	Menu.esc_dp.add(l);
						//Consulta.main(nameSizePage );
						//dispose();
						/*Dimension desktopSize = Menu.esc_dp.getSize();
						l.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				        l.dispose();/
						
						Consulta.rm=1;
						System.out.println("1_RM");
						String[] args = null ;
						Consulta.main(null);
						dispose();
					}
					
				
		        //}
					
			});
		 rdbtnNewRadioButton.setBounds(326, 3, 109, 23);
		 panel.add(rdbtnNewRadioButton);
		*/
		
	}
	public void consulta_d() {
		java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(" yyyy/MM/dd   HH:mm:ss ");
        String  Fecha = sdf.format(date);
        
        String sql,exp,n,ap,am,s,e;
        
        exp=txt_expediente.getText();
        n=txt_n.getText();
        ap=txt_ap.getText();
        am=txt_am.getText();
        s=txt_s.getText();
        e=txt_e.getText();
        sql="INSERT INTO  consulta_datos (Fecha,Expediente_cd, Nombre,AP,AM,Sexo,Edad )VALUES(?,?,?,?,?,?,?)";
   	   try{
   	        PreparedStatement pst=reg.prepareStatement(sql);
   	        pst.setString(1,Fecha);
   	       pst.setString(2,exp);
   	        pst.setString(3,n);
   	        pst.setString(4,ap);
   	        pst.setString(5,am);
   	        pst.setString(6,s);
   	        pst.setString(7,e);
   	          	       
   	        pst.executeUpdate();    	             
   	       }catch(SQLException ex){
   	         Logger.getLogger(RecetaMedica.class.getName()).log(Level.SEVERE, null, ex);
   	       } 
        
        
		
	}
	
	public void exp() {
		comboBox.setSelectedIndex(0);
		java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(" yyyy/MM/dd   HH:mm:ss ");
        String  Fecha = sdf.format(date);  
        
        String sql,nom,ap,am,e,f;
        e=txt_expediente.getText();
        f=txt_folio.getText();
        nom=txt_n.getText();
        ap=txt_ap.getText();
        am=txt_am.getText();
        
        sql="INSERT INTO  expediente (Exp_exp,Nombre, AP, AM,Folio_exp, FECHA)VALUES(?,?,?,?,?,?)";
   	   try{
   	        PreparedStatement pst=reg.prepareStatement(sql);
   	        pst.setString(1,e);
   	        pst.setString(2,nom);
   	        pst.setString(3,ap);
   	        pst.setString(4,am);
   	        pst.setString(5,f);
   	        pst.setString(6,Fecha);
   	          	       
   	        pst.executeUpdate();    	             
   	       }catch(SQLException ex){
   	         Logger.getLogger(RecetaMedica.class.getName()).log(Level.SEVERE, null, ex);
   	       } 
		
	}
	public void d_rm() {
		java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(" yyyy/MM/dd   HH:mm:ss ");
        String  Fecha = sdf.format(date);  
        
        String sql,exp,f,instruccion;
        
        exp=txt_expediente.getText();
        f=txt_folio.getText();
        instruccion=txt_instruccion.getText();
       
		 sql="INSERT INTO  detalle_rm (Fecha, Folio, Expediente, Instrucciones_drm)VALUES(?,?,?,?)";
  	   try{
  	        PreparedStatement pst=reg.prepareStatement(sql);
  	        pst.setString(1,Fecha);
  	        pst.setString(2,f);
  	        pst.setString(3,exp);
  	        pst.setString(4,instruccion);
  	          	       
  	        pst.executeUpdate();    	             
  	       }catch(SQLException ex){
  	         Logger.getLogger(RecetaMedica.class.getName()).log(Level.SEVERE, null, ex);
  	       } 	  
		
	}
	
	public void guardar() {
		
		    java.util.Date date = new java.util.Date();
	        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(" yyyy/MM/dd   HH:mm:ss ");
	        String  Fecha = sdf.format(date);        
	       // String e;
			 		      
	        for (int i = 0; i < table_rm.getRowCount(); i++) {
	     	 
	     	   int t,cant,f,d;
	           String sql,m,exp,folio;
	           
	           folio=txt_folio.getText();
	           exp=txt_expediente.getText();
	     	   m=table_rm.getValueAt(i, 0).toString();
	     	   cant = Integer.parseInt(table_rm.getValueAt(i, 1).toString());
	     	   t=Integer.parseInt(table_rm.getValueAt(i, 2).toString());
	     	   f=Integer.parseInt(table_rm.getValueAt(i, 3).toString());
	     	   d=Integer.parseInt(table_rm.getValueAt(i, 4).toString());
	     	   
	     	  sql="INSERT INTO  receta_medica (Expediente,Folio, Fecha, Medicamento, Cantidad, Tomar,"
		        		+ " Frecuencia, Durante )VALUES(?,?,?,?,?,?,?,?)";
	     	   try{
	     	        PreparedStatement pst=reg.prepareStatement(sql);
	     	        pst.setString(1,exp);
	     	        pst.setString(2,folio);
	     	        pst.setString(3,Fecha);
	     	        pst.setString(4,m);
	     	        pst.setInt(5,cant);
	     	        pst.setInt(6,t);
	     	        pst.setInt(7,f);
	     	        pst.setInt(8,d); 
	     	        
	     	        pst.executeUpdate();  
	     	        
	     	       }catch(SQLException ex){
	     	         Logger.getLogger(RecetaMedica.class.getName()).log(Level.SEVERE, null, ex);
	     	       } 	   
	 	}
	        
	        
	        
	        
	}//guardar
	
	public void pdf_receta() {
		
		
		String n,ap,am,s,e,instrucciones;
        n=txt_n.getText();
        ap=txt_ap.getText();
        am=txt_am.getText();
        s=txt_s.getText();
        e=txt_e.getText();
        instrucciones=txt_instruccion.getText();
		Calendar c = new GregorianCalendar();
		String id = txt_folio.getText();     
        String sql="SELECT * FROM receta_medica WHERE Folio='"+id+"'";
        String datos[] = new String[10];
         Statement st;
         
         try{
             st = reg.createStatement();
             ResultSet rs = st.executeQuery(sql);
           //.app rellena el papel 
                        
				area.append("                                                               CONSULTORIO MEDICO GENERAL MIGUEL                                                  \n");
				area.append("\n");
				area.append("                                                              "+"DR."+ usuarioPersona.toString() + "  ");area.append("                          "+"Fecha: "+c.get(c.DAY_OF_MONTH)+"/"+c.get(c.MONTH)+"/"+c.get(c.YEAR)+"\n");
				area.append("                                                  "+"Direccion: Edison Nte. Numero 218 Colonia Tracalosa de Monterrey ");
				area.append("\n");
				area.append("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				area.append("Nombre: "+n+" "+ap+" "+am+" \n");	
				area.append("Sexo: "+s+"    ");area.append("                Edad: "+e+"    \n");
				area.append("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				
				area.append("Medicamento                         ");area.append("Cantidad                     ");area.append("Tomar                       ");area.append("Frecuencia        ");area.append("Duracion   \n");                 
			
				area.append("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
               	
             while(rs.next()) {
            	 for (int i = 0; i < table_rm.getRowCount(); i++) { 
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            datos[4]=rs.getString(5);
            datos[5]=rs.getString(6);
            datos[6]=rs.getString(7);
            datos[7]=rs.getString(8);
            datos[8]=rs.getString(9);
            
            	 
            	 }					
									
			area.append(""+datos[4]+"                                "+datos[5]+"                          "+datos[6]+"                       "+datos[7]+"                        "+datos[8]+"    \n");
			
            
             }           
            } catch (SQLException ex){
              Logger.getLogger(RecetaMedica.class.getName()).log(Level.SEVERE,null,ex);
               } 
			area.append("\n");	
			area.append("\n");
			area.append("\n");
			area.append("Instrucciones \n");
			area.append(" "+instrucciones);
			area.append("\n");							
			area.append("\n");
			area.append("\n");
			area.append("-----------------------------------------");
			area.append("\n");
			area.append("DR."+nom+" "+app+" "+apm+"  \n");
			area.append("\n");
			 //Imprime la Factura en PDF
            ruta_destino = pdf.Colocar_Destino(ruta_destino);
          //si destino es diferente de null
             // if (this.ruta_destino != null) {
            
            try {      
            	 pdf.PDF(area.getText(), ruta_destino, SizePage);
                 System.out.println(SizePage);
                 JOptionPane.showMessageDialog(null, "Documento PDF creado");
            } catch (Exception ex) {
            	Logger.getLogger(RecetaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
            area.setText("");
            
            
	}
	
	

	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    
    
    static JTextField txt_folio;
    static JTextField txt_expediente;
    private JTable table2;
    JScrollPane scrollPane_1 = new JScrollPane();//table_rm
	public static JTable table_rm;
	private JTextField txt_receta;
	private JTextField txt_instruccion;
	 static JTextField txt_n;
	 static JTextField txt_ap;
	 static JTextField txt_am;
	 static JTextField txt_s;
	 static JTextField txt_e;
	 private JComboBox comboBox;
	 private JTextArea area;
	 private JRadioButton rdbtnNewRadioButton;
}
