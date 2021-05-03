package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.util.Properties;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Envio extends JFrame  {
    
	private JPanel contentPane;
	private JTextField txtCorreo;
	private JButton btnenvio;
	//-----------------------------
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws  MessagingException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Envio frame = new Envio();
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
	public Envio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(36, 29, 219, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		btnenvio = new JButton("envio");
		btnenvio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Properties props = new Properties();
		        props.setProperty("mail.smtp.host", "smtp.gmail.com");
		       // props.put("mail.smtp.user", "medicconsultorio98@gmail.com");
		        //props.put("mail.smtp.clave", "Medic0-98");
		        props.setProperty("mail.smtp.starttls.enable", "true");
		        props.setProperty("mail.smtp.port", "587");
		        props.setProperty("mail.smtp.auth", "true");
		        
		        Session session =Session.getDefaultInstance(props);
		        String correo_remitente = "medicconsultorio98@gmail.com";
		        String password_remi = "Medic0-98";
		        String correo_receptor = "medicconsultorio98@gmail.com";
		        String asunto = "Confirmacion de cita medica";
		        String mss = "Recordando que tiene una cita medica llegar 15 minutos antes de la cita.";
		        		
		        try {
		        MimeMessage message = new MimeMessage(session);
		       	message.setFrom(new InternetAddress(correo_remitente));
					
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo_receptor));
				   	message.setSubject(asunto);
				   	message.setText(mss,"ISO-8859-1","html");
				   		
				   		Transport t = session.getTransport("smtp");
				   		t.connect(correo_remitente,password_remi);
				   		t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
				   		t.close(); 	
					
					
					
					
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
			}

			
		});
		btnenvio.setBounds(280, 28, 89, 23);
		contentPane.add(btnenvio);
	}
	
	
}
