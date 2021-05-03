package interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.ConexionSql;
import util.Constantes;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class RegistrosPacientes extends JInternalFrame {
	private JButton btnExit;
	private JLabel lblNewLabel;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrosPacientes frame = new RegistrosPacientes();
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
	public RegistrosPacientes() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Pacientes.class.getResource("/img/estetoscopio.png")));
		setTitle("Registros Pacientes                 "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                      ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1119, 476);
		JPanel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		btnExit = new JButton(Constantes.STR_EXIT);
		btnExit.setMnemonic('x');
	    btnExit.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		dispose();
	     	}
	    });
		btnExit.setBounds(30, 28, 89, 23);
		panel.add(btnExit);
		
		lblNewLabel = new JLabel("Registro de Pacientes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(60, 61, 978, 52);
		panel.add(lblNewLabel);
		
/************************************************************************************/
		
		modelo.addColumn("Expediente");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido Paterno");
		modelo.addColumn("Apellido Materno");
		modelo.addColumn("Sexo");
		modelo.addColumn("Edad");
		modelo.addColumn("Celular");
		modelo.addColumn("Estado");
		modelo.addColumn("Municipio");
		modelo.addColumn("Calle");
		
		String sql ="SELECT * FROM  paciente ";
		String datos[] = new String[10];
	    Statement st;
	    
	     try {
	    	 st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	         datos[0]=rs.getString(1);//exp
	         datos[1]=rs.getString(2);//nom
	         datos[2]=rs.getString(3);//ap
	         datos[3]=rs.getString(4);//am
	         datos[4]=rs.getString(5);//sexo
	         datos[5]=rs.getString(6);//edad        
	         datos[6]=rs.getString(7);//cel
	         datos[7]=rs.getString(8);//estado
	         datos[8]=rs.getString(9);//municipio
	         datos[9]=rs.getString(10);//calle
	         
	         modelo.addRow(datos);
	     } 
	          scrollPane.setBounds(60, 114, 978, 261);
			  panel.add(scrollPane);
			  scrollPane.setViewportView(table);
			  table.setModel(new DefaultTableModel(
			    new Object[][] {
			     		
			    },
			    new String[] {
			        "Expediente","Nombre", "Apellido Paterno", "Apellido Materno", "Sexo ", "Edad", "Celular", "Estado", "Municipio", "Calle"
			    }
			    ));
			    table.setModel(modelo);
			    
			    label = new JLabel("");
			    label.setHorizontalAlignment(SwingConstants.CENTER);
			    label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			    label.setBounds(30, 52, 1049, 365);
			    panel.add(label);


			 
		} catch (Exception e) {
			Logger.getLogger(RegistrosPacientes.class.getName()).log(Level.SEVERE,null,e);
		}
		
		

	}

	private void setIcon(Image image) {
		// TODO Auto-generated method stub
	}

	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();	
    private JLabel label;
}

