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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.ConexionSql;
import util.Constantes;

public class Expedientes extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Expedientes frame = new Expedientes();
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
	public Expedientes() {
			setIcon(Toolkit.getDefaultToolkit().getImage(Pacientes.class.getResource("/img/estetoscopio.png")));
			setTitle("EXPEDIENTE                          "
					+ "                                   "
					+ "                                   "
					+ "                                   "
					+ "                                   "
					+ "                                   "
					+ "                                   "
					+ "                                   "
					+ "                                    "
					+"                           ");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1119, 476);
			JPanel panel = new Panel("/img/fondo.jpg");
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(panel);
			panel.setLayout(null);
			
			modelo.addColumn("Registro");
	        modelo.addColumn("Expediente");
	        modelo.addColumn("Nombre");
	        modelo.addColumn("Apellido Paterno");
	        modelo.addColumn("Apellido Materno");
	        modelo.addColumn("Folio");
	        modelo.addColumn("Fecha");
	        
		
			String sql="SELECT * FROM  expediente ";
		    String datos[] = new String[10];
		    Statement st;
		     try{
		          st = reg.createStatement();
		          ResultSet rs = st.executeQuery(sql);
		          while(rs.next()){
		         datos[0]=rs.getString(1);//reg
		         datos[1]=rs.getString(2);//exp
		         datos[2]=rs.getString(3);//nom
		         datos[3]=rs.getString(4);//ap
		         datos[4]=rs.getString(5);//am
		         datos[5]=rs.getString(6);//folio
		         datos[6]=rs.getString(7);//fecha
		         

		         modelo.addRow(datos);
		     }    
		         
		    
		     scrollPane.setBounds(60, 114, 978, 300);
		     panel.add(scrollPane);
		     scrollPane.setViewportView(table);
		     table.setModel(new DefaultTableModel(
		     	new Object[][] {
		     		
		     	},
		     	new String[] {
		     		"Registro","Expediente","Cantidad","Apellido Paterno","Apellido Materno","Folio","Fecha"
		     	}
		     ));
		     table.setModel(modelo);
		     
		     btnExit.setMnemonic('x');
		     btnExit.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		dispose();
		     	}
		     });
		     btnExit.setBounds(60, 11, 105, 37);
		     
		     panel.add(btnExit);
		     lblConsultasRealizadas.setFont(new Font("Tahoma", Font.BOLD, 18));
		     lblConsultasRealizadas.setHorizontalAlignment(SwingConstants.CENTER);
		     lblConsultasRealizadas.setBounds(60, 51, 978, 37);
		     
		     panel.add(lblConsultasRealizadas);
		     
		          
		} catch (SQLException ex){
		Logger.getLogger(Expedientes.class.getName()).log(Level.SEVERE,null,ex);   
		}   
			
			
			

		}

		private void setIcon(Image image) {
			// TODO Auto-generated method stub
		}
	//----------------------------------------------------------------
		
		ConexionSql cos = new ConexionSql();
	    Connection reg = cos.conectar();
	    JTable table = new JTable();
	    JScrollPane scrollPane = new JScrollPane();
	  	DefaultTableModel modelo = new DefaultTableModel();
	  	private final JButton btnExit = new JButton(Constantes.STR_EXIT);
	  	private final JLabel lblConsultasRealizadas = new JLabel("CONSULTAS REALIZADAS");
	}
