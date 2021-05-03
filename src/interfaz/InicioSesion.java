package interfaz;

import conexion.ConexionSql;
import util.Constantes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class InicioSesion extends JFrame {

	int intento = 0;
	private JPanel panel;
	private JTextField txtusuario;
	private JPasswordField contrasena;
	private JLabel lblLogIn;
	public static String nom,app,apm,cedula,esp;
	public static String sexo,catego,usr,password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				InicioSesion frame = new InicioSesion();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void login() {
				 
		String user = txtusuario.getText();
		String pass = String.valueOf(contrasena.getPassword());

		String sql="SELECT * FROM config WHERE Usuario_config='"+user+ "'";
	   
       String datos[] = new String [10];
	   Statement st;
	   try {
		   st = reg.createStatement();
		   ResultSet rs = st.executeQuery(sql);
		   while (rs.next()) {
			   datos[0]=rs.getString(1);
			   datos[1]=rs.getString(2);
			   datos[2]=rs.getString(3);
			   datos[3]=rs.getString(4);
			   datos[4]=rs.getString(5);
			   datos[5]=rs.getString(6);
			   datos[6]=rs.getString(7);
			   datos[7]=rs.getString(8);
			   datos[8]=rs.getString(9);
			   datos[9]=rs.getString(10);
		}		
	} catch (Exception e1) {
		Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE,null,e1);
	}
//----------------------------------------
	   String usuario =  datos[8];
	   String contra = datos[9];
	   
	   if (user.equals(usuario) && String.valueOf(contrasena.getPassword()).equals(contra) ) {
		   nom=datos[1];app=datos[2];apm=datos[3];cedula=datos[5];esp=datos[6];
		   catego=datos[7];sexo=datos[4];usr=datos[8];password=datos[9];
		   JOptionPane.showMessageDialog(null, "Bienvenido\n Has ingresado satisfactoriamente al sistema",
				"Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
		   dispose();
		   Menu l= new Menu();
	       l.setVisible(true);
	}
	   else if (intento ==2) {
		   JOptionPane.showMessageDialog(null,"ACCESO RESTRINGIDO:\n"
				 + "Ha exedido el numeor de intentos. Vuelva mas tarde","AVISO", JOptionPane.ERROR_MESSAGE);
		   System.exit(0); 	
	}
	   else {
		   JOptionPane.showMessageDialog(null,"ACCESO DENEGADO:\n"
					+ "Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
		   
		   txtusuario.setText("");
		   contrasena.setText("");
		   
		   txtusuario.grabFocus();
		   
		   intento = intento + 1;
		
	}
		 
		  
	}
	
	/**
	 * Create the frame.
	 */
	public InicioSesion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAutoRequestFocus(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioSesion.class.getResource("/img/estetoscopio.png")));
		setTitle("CONSULTORIO");
		setBounds(100, 100, 420, 420);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5,   5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		ImageIcon img1 = new ImageIcon(getClass().getResource("/img/login/simbolo.png"));
		
		txtusuario = new JTextField();
		//txtusuario.grabFocus();
		//txtusuario.grabFocus();
		txtusuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtusuario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtusuario.setBounds(117, 140, 171, 29);
		panel.add(txtusuario);
		txtusuario.setColumns(10);
		
		JButton btnacceso = new JButton(Constantes.STR_INGRESAR);
		btnacceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
				
			}
		});
		btnacceso.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnacceso.setBounds(117, 254, 171, 29);
		panel.add(btnacceso);
		
		JButton btnsalida = new JButton(Constantes.STR_EXIT);
		btnsalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnsalida.setForeground(new Color(0, 0, 0));
		btnsalida.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnsalida.setBounds(170, 327, 67, 30);
		//btnsalida.setBackground(Color.BLUE);
		panel.add(btnsalida);
		
		lblLogIn = new JLabel(Constantes.STR_LOGIN);
		lblLogIn.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblLogIn.setBounds(61, 78, 119, 51);
		panel.add(lblLogIn);
		
		JLabel lblUsuario = new JLabel("");
		lblUsuario.setIcon(new ImageIcon(InicioSesion.class.getResource("/img/login/user.png")));
		lblUsuario.setBounds(74, 140, 50, 29);
		panel.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("");
		lblContraseña.setIcon(new ImageIcon(InicioSesion.class.getResource("/img/login/password2.png")));
		lblContraseña.setBounds(74, 195, 50, 29);
		panel.add(lblContraseña);
		
		JLabel lblmedicina = new JLabel("");
		lblmedicina.setBounds(127, 11, 161, 109);
		getContentPane().add(lblmedicina);
		Icon fondo = new ImageIcon(img1.getImage().getScaledInstance(lblmedicina.getWidth(),lblmedicina.getHeight() ,Image.SCALE_DEFAULT ));
		lblmedicina.setIcon(fondo);
		
		contrasena = new JPasswordField();
		contrasena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					login();					
				}
			}
		});
		contrasena.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contrasena.setHorizontalAlignment(SwingConstants.CENTER);
		contrasena.setBounds(117, 195, 171, 29);
		panel.add(contrasena);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(45, 61, 311, 244);
		panel.add(label);

		
	}
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();

}
