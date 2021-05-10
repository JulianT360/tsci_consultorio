package interfaz;

import com.toedter.calendar.JDateChooser;
import conexion.ConexionSql;
import util.Constantes;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rconsulta extends JInternalFrame  {
	
	private JButton btn_registro;
	private JLabel lbl_Nom;
	private JLabel lbl_AP;
	private JLabel lbl_AM;
	static JTextField txt_Nom;
	static JTextField txt_App;
	static JTextField txt_Apm;
	private JLabel lbl_Exp;
	static JTextField txt_Exp_P;
	private JButton btn_busqueda;
	private JLabel lbl_Correo;
	static JTextField txt_correo;
	private JLabel lbl_Fecha;
	private JLabel lbl_Hora;
	static JComboBox CBox_Hora;
	private JLabel lbl_Telefono;
	static JTextField txt_telefono;
	private JButton btn_Generar_cita;
	private JButton btn_Eliminar_cita;
	private JButton btn_Modificar_cita;
	private JButton btn_Exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rconsulta frame = new Rconsulta();
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
	public Rconsulta() {
		setTitle("Registro de Consulta                "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                       ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 457);
		JPanel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		btn_registro = new JButton("Registrar Paciente");
		btn_registro.setMnemonic('z');
		btn_registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pacientes l = new Pacientes ();
				Menu.esc_dp.add(l);
				Dimension desktopSize = Menu.esc_dp.getSize();
				l.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				l.show();
				
				  dispose();
			}
		});
		
		btn_citas = new JButton("Citas");
		btn_citas.setBounds(213, 11, 89, 23);
		btn_citas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                BusquedaCita BC =new BusquedaCita();
				BC.setVisible(true);
			}
		});
		panel.add(btn_citas);
		btn_registro.setBounds(553, 65, 144, 35);
		panel.add(btn_registro);
		
		lbl_Nom = new JLabel("Nombre");
		lbl_Nom.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nom.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Nom.setBounds(77, 138, 200, 14);
		panel.add(lbl_Nom);
		
		lbl_AP = new JLabel("Apellido Paterno");
		lbl_AP.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AP.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_AP.setBounds(287, 138, 200, 14);
		panel.add(lbl_AP);
		
		lbl_AM = new JLabel("Apellido Materno");
		lbl_AM.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AM.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_AM.setBounds(497, 138, 200, 14);
		panel.add(lbl_AM);
		
		txt_Nom = new JTextField();
		txt_Nom.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Nom.setColumns(10);
		txt_Nom.setBounds(77, 163, 200, 20);
		panel.add(txt_Nom);
		
		txt_App = new JTextField();
		txt_App.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_App.setColumns(10);
		txt_App.setBounds(287, 163, 200, 20);
		panel.add(txt_App);
		
		txt_Apm = new JTextField();
		txt_Apm.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Apm.setColumns(10);
		txt_Apm.setBounds(497, 163, 200, 20);
		panel.add(txt_Apm);
		
		lbl_Exp = new JLabel("No. Expediente Paciente");
		lbl_Exp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Exp.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Exp.setBounds(77, 65, 149, 14);
		panel.add(lbl_Exp);
		
		txt_Exp_P = new JTextField();
		txt_Exp_P.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Exp_P.setColumns(10);
		txt_Exp_P.setBounds(77, 90, 149, 20);
		panel.add(txt_Exp_P);
		
		btn_busqueda = new JButton(">");
		btn_busqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busqueda();
				
			}
		});
		btn_busqueda.setBounds(236, 90, 41, 21);
		panel.add(btn_busqueda);
		
		lbl_Correo = new JLabel("Correo");
		lbl_Correo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Correo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Correo.setBounds(77, 197, 58, 14);
		panel.add(lbl_Correo);
		
		txt_correo = new JTextField();
		txt_correo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_correo.setColumns(10);
		txt_correo.setBounds(145, 194, 342, 20);
		panel.add(txt_correo);
		
		lbl_Fecha = new JLabel("Fecha");
		lbl_Fecha.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Fecha.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Fecha.setBounds(77, 245, 58, 14);
		panel.add(lbl_Fecha);
		
		lbl_Hora = new JLabel("Hora");
		lbl_Hora.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Hora.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Hora.setBounds(379, 245, 58, 14);
		panel.add(lbl_Hora);
		
		CBox_Hora = new JComboBox();
		
		CBox_Hora.setModel(new DefaultComboBoxModel(new String[] 
				{"SELECCIONE", "8:10 AM - 8:40 AM", "8:45 AM - 9:15 AM",
				"9:20 AM - 9:50 AM", "9:55 AM - 10:25 AM", "10:30 AM - 11:00 AM", 
				"11:05 AM - 11:35 AM", "11:40 AM - 12:10 PM", "2:00 PM - 2:30 PM", 
				"2:35 PM - 3:05 PM", "3:10 PM - 3:40 PM", "3:45 PM - 4:15 PM",
				"4:20 PM - 4:50 PM", "4:55 PM - 5:25 PM ", "5:30 PM - 6:00 PM"}));
		
		CBox_Hora.setBounds(435, 241, 172, 22);
		panel.add(CBox_Hora);
		
		lbl_Telefono = new JLabel("Tel.");
		lbl_Telefono.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Telefono.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Telefono.setBounds(507, 194, 48, 14);
		panel.add(lbl_Telefono);
		
		txt_telefono = new JTextField();
		txt_telefono.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_telefono.setColumns(10);
		txt_telefono.setBounds(553, 191, 144, 20);
		panel.add(txt_telefono);
		
		btn_Generar_cita = new JButton("Generar Cita");
		btn_Generar_cita.setMnemonic('s');
		btn_Generar_cita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardar();
			}
		});
		btn_Generar_cita.setBounds(77, 340, 172, 35);
		panel.add(btn_Generar_cita);
		
		btn_Eliminar_cita = new JButton("Eliminar Cita");
		btn_Eliminar_cita.setMnemonic('w');
		btn_Eliminar_cita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				
			}
		});
		btn_Eliminar_cita.setBounds(525, 340, 172, 35);
		panel.add(btn_Eliminar_cita);
		
		btn_Modificar_cita = new JButton("Modificar Cita");
		btn_Modificar_cita.setMnemonic('e');
		btn_Modificar_cita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				
			}
		});
		btn_Modificar_cita.setBounds(302, 340, 172, 35);
		panel.add(btn_Modificar_cita);
		
		btn_Exit = new JButton(Constantes.STR_EXIT);
		btn_Exit.setMnemonic('x');
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btn_Exit.setBounds(46, 11, 89, 23);
		panel.add(btn_Exit);
		
		C_fecha = new JDateChooser();
		C_fecha.setBounds(145, 245, 200, 20);
		panel.add(C_fecha);
		
		label = new JLabel("");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBackground(new Color(32, 178, 170));
		label.setBounds(46, 43, 670, 340);
		panel.add(label);
		
		btn_Correo = new JButton("Envia Correo");
		btn_Correo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				correo_perso();
				
			}
		});
		btn_Correo.setBounds(348, 11, 144, 23);
		panel.add(btn_Correo);

	}
	
    public void eliminar() {
    	String exp=txt_Exp_P.getText();
    	try {
    		PreparedStatement pps =reg.prepareStatement("DELETE FROM citas WHERE Expediente='"+exp+"'");   
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dato Eliminado");
			
		} catch (Exception e) {
			Logger.getLogger(Rconsulta.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
   //--------------------------------------------------------------------------------------------------------CORREO 
    public void correo_perso() {
    	
    	Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
       // props.put("mail.smtp.user", "medicconsultorio98@gmail.com");
        //props.put("mail.smtp.clave", "Medic0_98");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        
        Session session =Session.getDefaultInstance(props);
        String correo_remitente = "medicconsultorio98@gmail.com";
        String password_remi = "Medic0-98";
        String correo_receptor = "luisa07_serna@hotmail.com";
        String asunto = "Confirmacion de cita medica";
        String mss = "Recordando que tiene una cita medica llegar 15 minutos antes de la cita.<br> "
        		+ "Se le esperara 15 minutos despues de la hora cita para concelar la cia.<br> "
        		+ "<b>Confirme de enterado de lo conrario se cancelara la cita automaticamente.</b> ";
        
        MimeMessage message = new MimeMessage(session);
        		
		try {
			message.setFrom(new InternetAddress(correo_remitente));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo_receptor));
	   		message.setSubject(asunto);
	   		message.setText(mss,"ISO-8859-1","html");
	   		
	   		Transport t = session.getTransport("smtp");
	   		t.connect(correo_remitente,password_remi);
	   		t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	   		t.close(); 	
	   		
	   		   		
	   		
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
   			
			
			
			
		
    	
    }
    
    public void correo() {
    	    
        String sql="SELECT Nombre,Correo, Fecha FROM paciente" ;
      //String datos[] = new String[10];
        String [] correo = null;

         Statement st;
         
         
     /*    Properties props = new Properties();
         props.setProperty("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.user", "medicconsultorio98@gmail.com");
         props.put("mail.smtp.clave", "Medic0_98");
         props.setProperty("mail.smtp.port", "587");
         props.setProperty("mail.smtp.starttls.enable", "true");
         props.setProperty("mail.smtp.auth", "true");
         
         Session session =Session.getDefaultInstance(props);*/
         
         
        try{
         st = reg.createStatement();
         ResultSet rs = st.executeQuery(sql);
         java.sql.ResultSetMetaData rd = rs.getMetaData();
         
         int fila = rd.getColumnCount();
         correo = new String [fila +1];
         int indice=0;        
         
         
         while(rs.next()){
        	 correo[indice] = rs.getString("Correo");
        	         	
           /*  
             String correo_remitente = "medicconsultorio98@gmail.com";
             String password_remi = "Medic0_98";
             String correo_receptor = "Correo";
             String asunto = "Confirmacion de cita medica";
             String mss = "Recordando que tiene una cita medica llegar 15 minutos antes de la cita.<br> "
             		+ "Se le esperara 15 minutos despues de la hora cita para concelar la cia.<br> "
             		+ "<b>Confirme de enterado de lo conrario se cancelara la cita automaticamente.</b> ";
             
             MimeMessage message = new MimeMessage(session);
     		try {
				message.setFrom(new InternetAddress(correo_remitente));
				
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo_receptor));
	    		message.setSubject(asunto);
	    		message.setText(mss,"ISO-8859-1","html");
	    		
	    		Transport t = session.getTransport("smtp");
	    		t.connect(correo_remitente,password_remi);
	    		t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	    		t.close(); 		
				
				
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		
     		*/
        	 
        	 
        	 indice++;
//------------------------------------------------------------------------------------------------------------------------        	 
    }
                           
       JOptionPane.showMessageDialog(null,"Correos enviados exitosamente");
         
     
        } catch (SQLException  ex){
       Logger.getLogger(Rconsulta.class.getName()).log(Level.SEVERE,null,ex);   
     } 
        
   
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        
        Session session =Session.getDefaultInstance(props);
        
        String correo_remitente = "medicconsultorio98@gmail.com";
        String password_remi = "";
     // String correo_receptor = "";
        String asunto = "Confirmacion de cita medica";
        String mss = "Recordando que tiene una cita medica llegar 15 minutos antes de la cita."
        		+ "Se le esperara 15 minutos despues de la hora cita para concelar la cia. "
        		+ "Confirme de enterado de lo conrario se cancelara la cita automaticamente. ";
   try {
	
        MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(correo_remitente));
		
		Address [] receptor = new Address[correo.length];
		int j=0;
		while ( j < correo.length ) {
			receptor[j] = new InternetAddress(correo[j]);
			j++;
			
		}	
		
		message.addRecipients(Message.RecipientType.TO, receptor);
		message.setSubject(asunto);
		message.setText(mss);
		
		Transport t = session.getTransport("smtp");
		t.connect(correo_remitente,password_remi);
		t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		t.close();
			
			
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    	
    }
    
    
	
	public void modificar() {
	
		String exp,nom,ap,am,correo,tel,fecha,hora;
		//String sql;
		
		exp=txt_Exp_P.getText();
		nom=txt_Nom.getText();
		ap=txt_App.getText();
		am=txt_Apm.getText();
		correo=txt_correo.getText();
		tel=txt_telefono.getText();
		//f=txt_fecha.getText();
		hora=(String)CBox_Hora.getSelectedItem();
		
		Date dato = C_fecha.getDate();

		long d =dato.getTime();
		java.sql.Date  fecha_sql = new java.sql.Date(d);
		
		
		try {
			PreparedStatement pps= reg.prepareStatement("UPDATE citas SET Nombre='"+nom+
                    "',App='"+ap+"',Apm='"+am+"',Telefono='"+tel+"',Correo='"
					+correo+"',Fecha='" +fecha_sql+"',Hora='"+hora+"' WHERE Expediente='"+exp+ "'");
			
			pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
			
		} catch (Exception e) {
			Logger.getLogger(Rconsulta.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
	
	public void guardar() {
		String exp,nom,ap,am,correo,tel,fecha,hora;
		String sql;
		
		exp=txt_Exp_P.getText();
		nom=txt_Nom.getText();
		ap=txt_App.getText();
		am=txt_Apm.getText();
		correo=txt_correo.getText();
		tel=txt_telefono.getText();
		//f=txt_fecha.getText();
		hora=(String)CBox_Hora.getSelectedItem();
		
		Date dato = C_fecha.getDate();

		long d =dato.getTime();
		java.sql.Date  fecha_sql = new java.sql.Date(d);
		
		
     sql="INSERT INTO citas (Expediente, Nombre, App ,Apm, Telefono, "
     		+ "Correo, Fecha ,Hora) VALUES(?,?,?,?,?, ?,?,?)";      
	    
	    try {
	    	PreparedStatement pst=reg.prepareStatement(sql);
	    	
	    	pst.setString(1,exp);
	    	pst.setString(2,nom);
	    	pst.setString (3,ap);
	    	pst.setString(4,am);
	    	pst.setString(5,tel);
	    	pst.setString(6,correo);
	    	pst.setDate(7,fecha_sql);
	    	pst.setString(8,hora);
            pst.executeUpdate();

			JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");	
			    	
		    txt_Exp_P.setText("");
		    txt_Nom.setText("");
			txt_App.setText("");
			txt_Apm.setText("");
			txt_correo.setText("");
			txt_telefono.setText("");
			
			CBox_Hora.setSelectedIndex(0);
			    	
					
			} catch (SQLException ex) {
			     Logger.getLogger(Rconsulta.class.getName()).log(Level.SEVERE, null, ex);
			}//fin del catch
		
	}
	
	
	public void busqueda() {
		String expediente = txt_Exp_P.getText();     
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
        txt_App.setText(datos[2]);
        txt_Apm.setText(datos[3]);
        txt_telefono.setText(datos[6]);
                                     

         JOptionPane.showMessageDialog(null,"Datos del Expediente");
         
} catch (SQLException ex){
Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE,null,ex);   
} 
	}
	
	public void fecha() {
		
		String anio,dia,mes;

		anio = String.valueOf(C_fecha.getCalendar().get(Calendar.YEAR));
		 mes = String.valueOf(C_fecha.getCalendar().get(Calendar.MONTH) + 1);
		 dia = String.valueOf(C_fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
		
		
		
		f= dia + "/" + mes + "/" + anio;
		
	   
	}
	
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    static JDateChooser C_fecha;
    private JLabel label;
    String f;
    private JButton btn_citas;
    private JButton btn_Correo;
}
