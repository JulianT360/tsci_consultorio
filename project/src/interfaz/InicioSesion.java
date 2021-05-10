package interfaz;

import domain.entity.Medico;
import domain.entity.Persona;
import domain.service.MedicoService;
import domain.service.PersonaService;
import util.Constantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class InicioSesion extends JFrame {

	private PersonaService personaService;
	private MedicoService medicoService;

	int intento = 0;
	private JPanel panel;
	private JTextField txtusuario;
	private JPasswordField contrasena;
	private JLabel lblLogIn;
	public static Persona usuarioPersona;
	public static Medico usuarioMedico;

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

	/**
	 * Create the frame.
	 */
	public InicioSesion() {
		this.usuarioPersona = new Persona();
		this.usuarioMedico = new Medico();

		this.personaService = new PersonaService();
		this.medicoService = new MedicoService();

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

		JLabel lblContrasena = new JLabel("");
		lblContrasena.setIcon(new ImageIcon(InicioSesion.class.getResource("/img/login/password2.png")));
		lblContrasena.setBounds(74, 195, 50, 29);
		panel.add(lblContrasena);

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

	public void login() {
		String user = txtusuario.getText();
		String pass = String.valueOf(contrasena.getPassword());

		Medico medico = medicoService.getMedicoByUsuario(user);

		// Se valida usuario y password
	   	if (user.equals(medico.getUsuario()) && pass.equals(medico.getPassword())) {

	   		Persona persona = personaService.getPersonaById(medico.getIdPersona());
	   		if(Objects.nonNull(persona)) {
	   			usuarioMedico = medico;
	   			usuarioPersona = persona;
			} else {
				System.out.println("No se encontraron los datos de la persona");
			}

		   JOptionPane.showMessageDialog(null, "Bienvenido\n Has ingresado satisfactoriamente al sistema",
				"Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
		   dispose();
		   Menu l = new Menu();
		   l.setVisible(true);
	   	} else if (intento == 2) {
	   		JOptionPane.showMessageDialog(null,"ACCESO RESTRINGIDO:\n"
				 + "Ha exedido el numeor de intentos. Vuelva mas tarde","AVISO", JOptionPane.ERROR_MESSAGE);
	   		System.exit(0);
	   	} else {
		   JOptionPane.showMessageDialog(null,"ACCESO DENEGADO:\n"
					+ "Por favor ingrese un usuario y/o contrase�a correctos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
		   
		   txtusuario.setText("");
		   contrasena.setText("");
		   txtusuario.grabFocus();
		   intento = intento + 1;
		}
	}
}
