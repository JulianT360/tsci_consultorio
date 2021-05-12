package interfaz;

import conexion.ConexionSql;
import util.Constantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Busqueda_exp extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Medicamento;
	ConexionSql cos=new ConexionSql();
	Connection reg = cos.conectar();

	JScrollPane scrollPane = new JScrollPane();
	JTable table_bm = new JTable();
	DefaultTableModel modelo = new DefaultTableModel();
	private JTextField txtn;
	private JTextField txtap;
	private JTextField txtam;
	private JTextField txte;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda_exp frame = new Busqueda_exp();
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
	public Busqueda_exp() {
		setIcon(Toolkit.getDefaultToolkit().getImage(BusquedaMedic.class.getResource("/img/estetoscopio.png")));
		setTitle("EXPEDIENTE        ");
		setBounds(100, 100, 843, 432);
		//setBounds(100, 100, 620, 432);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lbl_Medicamento = new JLabel("Apellido Paterno");
		lbl_Medicamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Medicamento.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Medicamento.setBounds(45, 76, 116, 14);
		panel.add(lbl_Medicamento);
		
		txt_Medicamento = new JTextField();
		txt_Medicamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					
					limpiar();
					String dato = txt_Medicamento.getText(); 
			        String sql="SELECT * FROM  paciente WHERE A_Paterno='"+dato+"'";
				    String datos[] = new String[10];
				    Statement st;
				     try{
				    	 st = reg.createStatement();
				         ResultSet rs = st.executeQuery(sql);
				         while(rs.next()){
				        datos[0]=rs.getString(1);//exp
				        datos[1]=rs.getString(2);//nom
				        datos[2]=rs.getString(3);//ap
				        datos[3]=rs.getString(4);//am
				       

				         modelo.addRow(datos);
				     }    
				    scrollPane.setBounds(39, 133, 510, 205);
				     panel.add(scrollPane);
				     scrollPane.setViewportView(table_bm);
				     table_bm.setModel(new DefaultTableModel(
				     	new Object[][] {			     		
				     	},
				     	new String[] {
				     		//"Numero",
				     		"Expediente","Nombre","Apellido Paterno","Apellido Materno"
				     	}
				     ));
				     table_bm.setModel(modelo);
				     
				          
				} catch (SQLException ex){
				Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
				}   
									
					
				}
			}
		});
		txt_Medicamento.setBounds(171, 73, 230, 20);
		panel.add(txt_Medicamento);
		txt_Medicamento.setColumns(10);
		
		
        modelo.addColumn("Expediente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");

       	
        String sql="SELECT * FROM paciente ";
	    String datos[] = new String[10];
	    Statement st;
	     try{
	          st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	        	  datos[0]=rs.getString(1);//exp
			        datos[1]=rs.getString(2);//nom
			        datos[2]=rs.getString(3);//ap
			        datos[3]=rs.getString(4);//am
	       //  datos[2]=rs.getString(1);

	         modelo.addRow(datos);
	     }    
	    scrollPane.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    	}
	    });
	   
	    scrollPane.setBounds(39, 133, 510, 205);
	     panel.add(scrollPane);
	     table_bm.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     		int select =table_bm.getSelectedRow();
		     		
	     		txte.setText(String.valueOf(table_bm.getValueAt(select, 0)));
	     		txtn.setText(String.valueOf(table_bm.getValueAt(select, 1)));
	     		txtap.setText(String.valueOf(table_bm.getValueAt(select, 2)));
	     		txtam.setText(String.valueOf(table_bm.getValueAt(select, 3)));
	     		
	     	}
	     });
	     scrollPane.setViewportView(table_bm);
	     table_bm.setModel(new DefaultTableModel(
	     	new Object[][] {			     		
	     	},
	     	new String[] {
	     		//"Numero",
	     			"Expediente","Nombre","Apellido Paterno","Apellido Materno"
	     	}
	     ));
	     table_bm.setModel(modelo);
	     
	          
	} catch (SQLException ex){
	Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
	}   
	     
				
					
		     		
				
	     
		JButton btn_exit = new JButton(Constantes.STR_EXIT);
		btn_exit.setMnemonic('x');
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setBounds(23, 11, 89, 23);
		panel.add(btn_exit);
		
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				String dato = txt_Medicamento.getText(); 
		        String sql="SELECT * FROM  paciente WHERE A_Paterno='"+dato+"'";
			    String datos[] = new String[10];
			    Statement st;
			     try{
			    	 st = reg.createStatement();
			         ResultSet rs = st.executeQuery(sql);
			         while(rs.next()){
			        datos[0]=rs.getString(1);//exp
			        datos[1]=rs.getString(2);//nom
			        datos[2]=rs.getString(3);//ap
			        datos[3]=rs.getString(4);//am
			       

			         modelo.addRow(datos);
			     }    
			    scrollPane.setBounds(39, 133, 510, 205);
			     panel.add(scrollPane);
			     scrollPane.setViewportView(table_bm);
			     table_bm.setModel(new DefaultTableModel(
			     	new Object[][] {			     		
			     	},
			     	new String[] {
			     		//"Numero",
			     		"Expediente","Nombre","Apellido Paterno","Apellido Materno"
			     	}
			     ));
			     table_bm.setModel(modelo);
			     
			          
			} catch (SQLException ex){
			Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
			}   
								
			}
		});
		btn_buscar.setBounds(411, 72, 107, 23);
		panel.add(btn_buscar);
		
		JRadioButton M_Tabla = new JRadioButton("Mostrar tabla");
		M_Tabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = 1;
				if (seleccion==1) {
					limpiar();
					String sql="SELECT * FROM paciente ";
				    String datos[] = new String[10];
				    Statement st;
				     try{
				          st = reg.createStatement();
				          ResultSet rs = st.executeQuery(sql);
				          while(rs.next()){
				        	  datos[0]=rs.getString(1);//exp
						        datos[1]=rs.getString(2);//nom
						        datos[2]=rs.getString(3);//ap
						        datos[3]=rs.getString(4);//am

				         modelo.addRow(datos);
				     }    
				   
				    scrollPane.setBounds(39, 133, 510, 205);
				     panel.add(scrollPane);
				     scrollPane.setViewportView(table_bm);
				     table_bm.setModel(new DefaultTableModel(
				     	new Object[][] {			     		
				     	},
				     	new String[] {
				     			"Expediente","Nombre","Apellido Paterno","Apellido Materno"
				     	}
				     ));
				     table_bm.setModel(modelo);
				     
				} catch (SQLException ex){
				Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
				} 
					M_Tabla.setSelected(false);
				}
			}
		});
		M_Tabla.setOpaque(false);
		M_Tabla.setHorizontalAlignment(SwingConstants.CENTER);
		M_Tabla.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		M_Tabla.setBounds(411, 102, 107, 23);
		panel.add(M_Tabla);
		
		txtn = new JTextField();
		txtn.setBounds(578, 187, 182, 20);
		panel.add(txtn);
		txtn.setColumns(10);
		
		txtap = new JTextField();
		txtap.setColumns(10);
		txtap.setBounds(578, 219, 182, 20);
		panel.add(txtap);
		
		txtam = new JTextField();
		txtam.setColumns(10);
		txtam.setBounds(578, 250, 182, 20);
		panel.add(txtam);
		
		JButton btn_enviar = new JButton("ENVIAR");
		btn_enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				String envia=txte.getText();
				Consulta.txt_exp.setText(envia);
				dispose();
				
			}
		});
		btn_enviar.setBounds(578, 92, 95, 33);
		panel.add(btn_enviar);
		
		txte = new JTextField();
		txte.setBounds(578, 156, 86, 20);
		panel.add(txte);
		txte.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(23, 45, 763, 319);
		panel.add(label);
		
		
		
	}
	
	public void limpiar() {
		int fila=table_bm.getRowCount();
		for (int i=fila-1; i >= 0; i--) {
			modelo.removeRow(i);
			
		}
	}
	
	
	private void setIcon(Image image) {
	}
}

