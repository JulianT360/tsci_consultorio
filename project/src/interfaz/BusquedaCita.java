package interfaz;

import com.toedter.calendar.JDateChooser;
import conexion.ConexionSql;
import util.Constantes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusquedaCita extends JFrame {

	private JPanel contentPane;
	JPanel panel = new Panel("/img/fondo.jpg");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaCita frame = new BusquedaCita();
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
	public BusquedaCita() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Pacientes.class.getResource("/img/estetoscopio.png")));
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
	        
	} catch (SQLException ex){
	Logger.getLogger(Expedientes.class.getName()).log(Level.SEVERE,null,ex);   
	}   
	     
	     addPopup(table, popupMenu);
	     JMenuItem mntmEnvia = new JMenuItem("Envia");
			mntmEnvia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
		     		int select =table.getSelectedRow();
		     		
		     		SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
		     		Date fechaT;
		     		
		     		try {
						fechaT = formatoFecha.parse(String.valueOf(table.getValueAt(select, 7)));
						Rconsulta.C_fecha.setDate(fechaT);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		     		
		     				     		
		     		Rconsulta.txt_Exp_P.setText(String.valueOf(table.getValueAt(select, 1)));
		     		Rconsulta.txt_Nom.setText(String.valueOf(table.getValueAt(select, 2)));
		     		Rconsulta.txt_App.setText(String.valueOf(table.getValueAt(select, 3)));
		     		Rconsulta.txt_Apm.setText(String.valueOf(table.getValueAt(select, 4)));
		     		Rconsulta.txt_correo.setText(String.valueOf(table.getValueAt(select, 6)));
		     		Rconsulta.txt_telefono.setText(String.valueOf(table.getValueAt(select, 5)));
		     		//Rconsulta.C_fecha.setDateFormatString (String.valueOf(table.getValueAt(select, 7)));
		     		Rconsulta.CBox_Hora.setSelectedItem(String.valueOf(table.getValueAt(select, 8)));
		     		
		     		
		     		
					
				}
			});
			popupMenu.add(mntmEnvia);
	     
	     
	     
	     btnExit.setMnemonic('x');
	     btnExit.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		dispose();
	     	}
	     });
	     btnExit.setBounds(31, 11, 105, 37);
	     
	     panel.add(btnExit);
	     lblCitasRealizadas.setFont(new Font("Tahoma", Font.BOLD, 18));
	     lblCitasRealizadas.setHorizontalAlignment(SwingConstants.CENTER);
	     lblCitasRealizadas.setBounds(31, 8, 1074, 37);
	     
	     panel.add(lblCitasRealizadas);
	     lbl_fecha.setHorizontalAlignment(SwingConstants.CENTER);
	     lbl_fecha.setFont(new Font("Times New Roman", Font.BOLD, 16));
	     lbl_fecha.setBounds(155, 75, 81, 18);
	     
	     panel.add(lbl_fecha);
	     btn_buscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
	     btn_buscar.setBounds(464, 73, 107, 23);
	     
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
	     Fecha1.setBounds(246, 76, 208, 20);
	     panel.add(Fecha1);
	     
	     
	     
	     
	     
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
	
	

	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();
    private final JButton btnExit = new JButton(Constantes.STR_EXIT);
  	private final JLabel lblCitasRealizadas = new JLabel("CITAS REALIZADAS");
  	private final JLabel lbl_fecha = new JLabel("Fecha");
  	private final JButton btn_buscar = new JButton("Buscar");
  	private JDateChooser Fecha1;
  	private final JPopupMenu popupMenu = new JPopupMenu();
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public JTable getTable() {
		return table;
	}
}
