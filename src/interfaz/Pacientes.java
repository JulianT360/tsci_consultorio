package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import conexion.ConexionSql;
import util.Constantes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Pacientes extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pacientes frame = new Pacientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static int id_Exp = 0;
	private JTextField txt_Exp;
	private JTextField txt_Nom;
	private JTextField txt_AP;
	private JTextField txt_AM;
	private JTextField txt_Edad;
	private JTextField txt_Cel;
	private JTextField txt_Estado;
	private JTextField txt_Municipio;
	private JTextField txt_Calle;
	JComboBox comboBox = new JComboBox();
	
	/**
	 * Create the frame.
	 */
	public Pacientes() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Pacientes.class.getResource("/img/estetoscopio.png")));
		setTitle("PACIENTE                            "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                               ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 440);
		JPanel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		
		
		JLabel lbl_Exp = new JLabel("No. Expediente");
		lbl_Exp.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Exp.setBounds(298, 56, 200, 14);
		panel.add(lbl_Exp);
		
		txt_Exp = new JTextField();
		txt_Exp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_Exp.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Exp.setBounds(298, 81, 149, 20);
		panel.add(txt_Exp);
		txt_Exp.setColumns(10);
		/********************************************************/
		String query = "SELECT `AUTO_INCREMENT`\r\n" + 
				"FROM  INFORMATION_SCHEMA.TABLES\r\n" + 
				"WHERE TABLE_SCHEMA = 'medic'\r\n" + 
				"AND   TABLE_NAME   = 'paciente';";
		Statement st;
		try {
			st = reg.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				txt_Exp.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
		}
		/********************************************************/
		id_Exp=Integer.parseInt(txt_Exp.getText());
		
		
		JLabel lbl_Nom = new JLabel("Nombre");
		lbl_Nom.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Nom.setBounds(298, 112, 200, 14);
		panel.add(lbl_Nom);
		
		txt_Nom = new JTextField();
		txt_Nom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_Nom.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Nom.setColumns(10);
		txt_Nom.setBounds(298, 137, 200, 20);
		panel.add(txt_Nom);
		
		JLabel lbl_AP = new JLabel("Apellido Paterno");
		lbl_AP.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_AP.setBounds(298, 168, 200, 14);
		panel.add(lbl_AP);
		
		txt_AP = new JTextField();
		txt_AP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_AP.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_AP.setColumns(10);
		txt_AP.setBounds(298, 193, 200, 20);
		panel.add(txt_AP);
		
		JLabel lbl_AM = new JLabel("Apellido Materno");
		lbl_AM.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_AM.setBounds(298, 224, 200, 14);
		panel.add(lbl_AM);
		
		txt_AM = new JTextField();
		txt_AM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_AM.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_AM.setColumns(10);
		txt_AM.setBounds(298, 249, 200, 20);
		panel.add(txt_AM);
		
		JLabel label = new JLabel("Sexo");
		label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		label.setBounds(298, 280, 200, 14);
		panel.add(label);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECCION", "MASCULINO", "FEMENINO"}));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox.setBounds(298, 303, 200, 20);
		panel.add(comboBox);
		
		JLabel lbl_Edad = new JLabel("Edad");
		lbl_Edad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Edad.setBounds(600, 56, 200, 14);
		panel.add(lbl_Edad);
		
		txt_Edad = new JTextField();
		txt_Edad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_Edad.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Edad.setColumns(10);
		txt_Edad.setBounds(600, 81, 200, 20);
		panel.add(txt_Edad);
		
		JLabel lbl_Celular = new JLabel("Celular");
		lbl_Celular.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Celular.setBounds(600, 112, 200, 14);
		panel.add(lbl_Celular);
		
		txt_Cel = new JTextField();
		txt_Cel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_Cel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Cel.setColumns(10);
		txt_Cel.setBounds(600, 137, 200, 20);
		panel.add(txt_Cel);
		
		JLabel lbl_Estado = new JLabel("Estado");
		lbl_Estado.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Estado.setBounds(600, 168, 200, 14);
		panel.add(lbl_Estado);
		
		txt_Estado = new JTextField();
		txt_Estado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_Estado.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Estado.setColumns(10);
		txt_Estado.setBounds(600, 193, 200, 20);
		panel.add(txt_Estado);
		
		JLabel lbl_Municipio = new JLabel("Municipio");
		lbl_Municipio.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Municipio.setBounds(600, 224, 200, 14);
		panel.add(lbl_Municipio);
		
		txt_Municipio = new JTextField();
		txt_Municipio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_Municipio.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Municipio.setColumns(10);
		txt_Municipio.setBounds(600, 249, 200, 20);
		panel.add(txt_Municipio);
		
		JLabel lbl_Calle_y_No = new JLabel("Calle y No.");
		lbl_Calle_y_No.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Calle_y_No.setBounds(600, 280, 200, 14);
		panel.add(lbl_Calle_y_No);
		
		txt_Calle = new JTextField();
		txt_Calle.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Calle.setColumns(10);
		txt_Calle.setBounds(600, 305, 200, 20);
		panel.add(txt_Calle);
		
		JButton btn_busqueda = new JButton(">");
		btn_busqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busqueda();
			}
		});
		btn_busqueda.setBounds(457, 81, 41, 21);
		panel.add(btn_busqueda);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_1.setBounds(255, 40, 596, 348);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Pacientes.class.getResource("/img/paciente/user_paciente.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(53, 44, 160, 128);
		panel.add(label_2);
		
		JButton button = new JButton("Guardar");
		button.setMnemonic('s');
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				
				int resp = JOptionPane.showConfirmDialog(null, "Entrar en Consulta ", "Alerta!", JOptionPane.YES_NO_OPTION);
			    if(resp == JOptionPane.YES_OPTION) {
								
				   Consulta l = new Consulta ();
				   Menu.esc_dp.add(l);
				   Dimension desktopSize = Menu.esc_dp.getSize();
		     	   l.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				   l.show();
									
					String envia=txt_Exp.getText();
					Consulta.txt_exp.setText(envia);
				   } else {
					Rconsulta rc = new Rconsulta ();
					Menu.esc_dp.add(rc);
					Dimension desktopSize = Menu.esc_dp.getSize();
			     	rc.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
					rc.show();
					   
					String envia_p=txt_Exp.getText();
				    Rconsulta.txt_Exp_P.setText(envia_p);
						  }
							
				    dispose();		
			   	   }
			});
		button.setBounds(53, 192, 160, 40);
		panel.add(button);
		
		JButton button_1 = new JButton("Editar");
		button_1.setMnemonic('e');
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		button_1.setBounds(53, 236, 160, 40);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Eliminar");
		button_2.setMnemonic('w');
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar();
			}
		});
		button_2.setBounds(53, 281, 160, 40);
		panel.add(button_2);
		
		JButton button_3 = new JButton(Constantes.STR_EXIT);
		button_3.setMnemonic('x');
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setBounds(53, 327, 160, 40);
		panel.add(button_3);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setMnemonic('q');
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		btnLimpiar.setBounds(298, 11, 89, 23);
		panel.add(btnLimpiar);
		
		btn_Paciente = new JButton("Registros");
		btn_Paciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistrosPacientes rp = new RegistrosPacientes();
				Menu.esc_dp.add(rp);
				Dimension desktopSize = Menu.esc_dp.getSize();
		     	rp.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				rp.show();
			}
		});
		btn_Paciente.setBounds(397, 11, 89, 23);
		panel.add(btn_Paciente);

	}

	private void setIcon(Image image) {
		// TODO Auto-generated method stub
	}
//----------------------------------------------------------------	
	public void guardar() {
		
		String nom,ap,am,sexo,edad,cel,estado,municipio,calle;
		String sql;
		
		//exp=txt_Exp.getText();
		nom=txt_Nom.getText();
		ap=txt_AP.getText();
		am=txt_AM.getText();
		edad=txt_Edad.getText();
		cel=txt_Cel.getText();
		estado=txt_Estado.getText();
		municipio=txt_Municipio.getText();
		calle=txt_Calle.getText();
		sexo=(String) comboBox.getSelectedItem();
		
		sql="INSERT INTO paciente (Nombre ,A_Paterno ,"
			+ "A_Materno ,Sexo_paciente ,Edad ,Celular_paciente ,Estado , Municipio ,"
			+ " Calle)VALUES(?,?,?,?,?,?,?,?,?)";
		 try {
		    	PreparedStatement pst=reg.prepareStatement(sql);
		    	
		   // 	pst.setString(0,exp);
		    	pst.setString(1,nom);
		    	pst.setString(2,ap);
		    	pst.setString (3,am);
		    	pst.setString(4,sexo);
		    	pst.setString(5,edad);
		    	pst.setString(6,cel);
		    	pst.setString(7,estado);
		    	pst.setString(8,municipio);
		    	pst.setString(9,calle);
	            pst.executeUpdate();	    	
						
				} catch (SQLException ex) {
				     Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE, null, ex);
				}//fin del catch
		
			
		   
		  
		 
	}
	
	public void Editar() {
		String exp,nom,ap,am,sexo,edad,cel,estado,municipio,calle;
		String sql;
		
		exp=txt_Exp.getText();
		nom=txt_Nom.getText();
		ap=txt_AP.getText();
		am=txt_AM.getText();
		edad=txt_Edad.getText();
		cel=txt_Cel.getText();
		estado=txt_Estado.getText();
		municipio=txt_Municipio.getText();
		calle=txt_Calle.getText();
		sexo=(String) comboBox.getSelectedItem();
		
		
		try {
			PreparedStatement pps= reg.prepareStatement("UPDATE paciente SET Nombre='"+nom+
                    "',A_Paterno='"+ap+"',A_Materno='"+am+"',Sexo_paciente='"+sexo+"',Edad='"
					+edad+"',Celular_paciente='" +cel+"',Estado='"+estado+"',Municipio='"
                    +municipio+"',Calle='"+calle+"'  WHERE Expediente='"+exp+ "'");
			
			pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
			
		} catch (Exception e) {
			Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void Eliminar() {
		String exp=txt_Exp.getText();
    	try {
    		PreparedStatement pps =reg.prepareStatement("DELETE FROM paciente WHERE Expediente='"+exp+"'");   
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dato Eliminado");
			Limpiar();
		} catch (Exception e) {
			Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void Limpiar() {
		txt_Exp.setText("");
		txt_Nom.setText("");
		txt_AP.setText("");
		txt_AM.setText("");
		comboBox.setSelectedIndex(0);
		txt_Edad.setText("");
		txt_Cel.setText("");
		txt_Estado.setText("");
		txt_Municipio.setText("");
		txt_Calle.setText("");
		
	}
	
	public void busqueda() {
		String expediente = txt_Exp.getText();     
        String sql="SELECT * FROM paciente WHERE Expediente='"+expediente+"'";
        String datos[] = new String[10];
         Statement st;
        try{
         st = reg.createStatement();
         ResultSet rs = st.executeQuery(sql);
         while(rs.next()){
        datos[0]=rs.getString(1);
        datos[1]=rs.getString(2);//nom
        datos[2]=rs.getString(3);
        datos[3]=rs.getString(4);
        datos[4]=rs.getString(5);//sexo
        datos[5]=rs.getString(6);
        datos[6]=rs.getString(7);  //cel
        datos[7]=rs.getString(8);
        datos[8]=rs.getString(9);
        datos[9]=rs.getString(10);

    }
           
        txt_Nom.setText(datos[1]);
        txt_AP.setText(datos[2]);
        txt_AM.setText(datos[3]);
        comboBox.setSelectedItem(datos[4]);
        txt_Edad.setText(datos[5]);
        txt_Cel.setText(datos[6]);
        txt_Estado.setText(datos[7]);                                
        txt_Municipio.setText(datos[8]);
        txt_Calle.setText(datos[9]);                                

         JOptionPane.showMessageDialog(null,"Datos del Expediente");
         
} catch (SQLException ex){
Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE,null,ex);   
} 
	}
	
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    private JButton btn_Paciente;
}





