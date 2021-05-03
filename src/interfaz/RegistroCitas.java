package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import conexion.ConexionSql;
import util.Constantes;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class RegistroCitas extends JInternalFrame {
	JPanel panel = new Panel("/img/fondo.jpg");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroCitas frame = new RegistroCitas();
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
	public RegistroCitas() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Pacientes.class.getResource("/img/estetoscopio.png")));
		setTitle("Citas Medicas                       "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+"                                    "
				+ "    ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 476);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		modelo.addColumn("ID");
        modelo.addColumn("Expediente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        
	
		String sql="SELECT * FROM  citas ";
	    String datos[] = new String[10];
	    Statement st;
	     try{
	          st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	         datos[0]=rs.getString(1);//ID
	         datos[1]=rs.getString(2);//exp
	         datos[2]=rs.getString(3);//nom
	         datos[3]=rs.getString(4);//ap
	         datos[4]=rs.getString(5);//am
	         datos[5]=rs.getString(6);//Telef
	         datos[6]=rs.getString(7);//correo
	         datos[7]=rs.getString(8);//fecha
	         datos[8]=rs.getString(9);//hora
	         

	         modelo.addRow(datos);
	     }    
	         
	    
	     scrollPane.setBounds(31, 150, 1074, 251);
	     panel.add(scrollPane);
	     scrollPane.setViewportView(table);
	     table.setModel(new DefaultTableModel(
	     	new Object[][] {
	     		
	     	},
	     	new String[] {
	     		"ID","Expediente","Nombre","Apellido Paterno","Apellido Materno","Telefono","Correo","Fecha","Hora"
	     	}
	     ));
	     table.setModel(modelo);
	     
	     btnExit.setMnemonic('x');
	     btnExit.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		dispose();
	     	}
	     });
	     btnExit.setBounds(21, 11, 105, 37);
	     
	     panel.add(btnExit);
	     lblCitasRealizadas.setFont(new Font("Tahoma", Font.BOLD, 18));
	     lblCitasRealizadas.setHorizontalAlignment(SwingConstants.CENTER);
	     lblCitasRealizadas.setBounds(136, 8, 884, 37);
	     
	     panel.add(lblCitasRealizadas);
	     
	          
	} catch (SQLException ex){
	Logger.getLogger(Expedientes.class.getName()).log(Level.SEVERE,null,ex);   
	}   
	
	     lbl_fecha.setHorizontalAlignment(SwingConstants.CENTER);
	     lbl_fecha.setFont(new Font("Times New Roman", Font.BOLD, 16));
	     lbl_fecha.setBounds(160, 91, 81, 18);
	     
	     panel.add(lbl_fecha);
	     btn_buscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
	     btn_buscar.setBounds(491, 82, 105, 37);
	     
	     btn_buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Fecha1.getDate()==null) {
						JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha");
						Datos();
					}
					else {
						BusquedaXfecha();
					}
					
				}
			});
	     panel.add(btn_buscar);
	     
	     Fecha1 = new JDateChooser();
	     Fecha1.setBounds(251, 92, 230, 20);
	     panel.add(Fecha1);
	     
	     label = new JLabel("");
	     label.setHorizontalAlignment(SwingConstants.CENTER);
	     label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	     label.setBounds(21, 59, 1097, 365);
	     panel.add(label);
	         
	     
		  

	}
	
	private void setIcon(Image image) {
		// TODO Auto-generated method stub
		
	}
	
public void Datos() {
		
		modelo.setRowCount(0);
		String sql="SELECT * FROM  citas ";
	    String datos[] = new String[10];
	    Statement st;
	     try{
	    	 
	    
	          st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	         datos[0]=rs.getString(1);//ID
	         datos[1]=rs.getString(2);//exp
	         datos[2]=rs.getString(3);//nom
	         datos[3]=rs.getString(4);//ap
	         datos[4]=rs.getString(5);//am
	         datos[5]=rs.getString(6);//Telef
	         datos[6]=rs.getString(7);//correo
	         datos[7]=rs.getString(8);//fecha
	         datos[8]=rs.getString(9);//hora
	         

	         modelo.addRow(datos);
	        }
	          panel.add(scrollPane);
		   	  scrollPane.setViewportView(table);
		   	  table.setModel(new DefaultTableModel(
		   	     	new Object[][] {
		   	     		
		   	     	},
		   	     	new String[] {
		   	     		"ID","Expediente","Nombre","Apellido Paterno","Apellido Materno","Telefono","Correo","Fecha","Hora"
		   	     	}
		   	     ));
		   	     table.setModel(modelo);    
	          
	          
	          
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public void BusquedaXfecha() {
		
		 Date dato = Fecha1.getDate();

			long d =dato.getTime();
			java.sql.Date  fecha_sql = new java.sql.Date(d);
		
		 modelo.setRowCount(0);
	     String sql="SELECT * FROM citas WHERE Fecha='"+fecha_sql+"'";
	     String datos[] = new String[10];
	     Statement st;
		 try {
			    st = reg.createStatement();
	            ResultSet rs = st.executeQuery(sql);
	            while(rs.next()){
	           datos[0]=rs.getString(1);
	           datos[1]=rs.getString(2);
	           datos[2]=rs.getString(3);
	           datos[3]=rs.getString(4);
	           datos[4]=rs.getString(5);
	           datos[5]=rs.getString(6);
	           datos[6]=rs.getString(7);
	           datos[7]=rs.getString(8);
	           datos[8]=rs.getString(9);
	           
	           modelo.addRow(datos);
	         }	
	           
	         panel.add(scrollPane);
	   	     scrollPane.setViewportView(table);
	   	     table.setModel(new DefaultTableModel(
	   	     	new Object[][] {
	   	     		
	   	     	},
	   	     	new String[] {
	   	     		"ID","Expediente","Nombre","Apellido Paterno","Apellido Materno","Telefono","Correo","Fecha","Hora"
	   	     	}
	   	     ));
	   	     table.setModel(modelo);    
	            
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		
    }
	
	
	
	
	
//----------------------------------------------------------------	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();
    private final JLabel lbl_fecha = new JLabel("Fecha");
  	private final JButton btn_buscar = new JButton("Buscar");
  	private JDateChooser Fecha1;
    private final JButton btnExit = new JButton(Constantes.STR_EXIT);
  	private final JLabel lblCitasRealizadas = new JLabel("CITAS REALIZADAS");
  	private JLabel label;
}
