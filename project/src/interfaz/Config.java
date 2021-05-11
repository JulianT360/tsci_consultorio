package interfaz;

import conexion.*;
import domain.entity.*;
import domain.service.*;
import util.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import static interfaz.InicioSesion.*;

public class Config extends JInternalFrame {

	private PersonaService personaService;
	private MedicoService medicoService;

	private JTextField txtnum;
	private JTextField txt_Nombre;
	private JTextField txt_AP;
	private JTextField txt_AM;
	private JTextField txt_esp;
	private JTextField txt_usuario;
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_catego = new JComboBox();
	JTextField txt_cedula = new JTextField();
	JTextField contraseña = new JTextField();
	JButton btnEliminar = new JButton("Eliminar");
	JButton btnLimpiar = new JButton("limpiar");
	JButton btnGuardar = new JButton("Guardar");


	//public static int num=0;
	
	//String DOCTOR;
	String ADMINISTRADOR;
	
	
	/**
	 Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config frame = new Config();
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
	public Config() {

		this.personaService = new PersonaService();
		this.medicoService = new MedicoService();

		setIcon(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/img/estetoscopio.png")));
		setTitle("CONFIGURACION                       "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                  ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 465);
		//setBounds(100, 100, 1333, 620);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lbl_img = new JLabel("");
		lbl_img.setIcon(new ImageIcon(Config.class.getResource("/img/config/doc_user.png")));
		lbl_img.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_img.setBounds(50, 52, 160, 128);
		panel.add(lbl_img);
		
		JLabel lbl_Nombre = new JLabel("Nombre");
		lbl_Nombre.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nombre.setBounds(290, 178, 220, 14);
		panel.add(lbl_Nombre);
		
		txt_Nombre = new JTextField();
		txt_Nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_Nombre.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Nombre.setBounds(290, 201, 220, 20);
		txt_Nombre.setColumns(10);
		txt_Nombre.setText(Objects.isNull(usuarioPersona) ? null : usuarioPersona.getNombre());
		panel.add(txt_Nombre);
		
		JLabel lbl_AP = new JLabel("Apellido Paterno");
		lbl_AP.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_AP.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AP.setBounds(290, 242, 220, 14);
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
		txt_AP.setHorizontalAlignment(SwingConstants.CENTER);
		txt_AP.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_AP.setColumns(10);
		txt_AP.setBounds(290, 267, 220, 20);
		txt_AP.setText(Objects.isNull(usuarioPersona) ? null : usuarioPersona.getApPaterno());
		panel.add(txt_AP);
		
		JLabel lbl_AM = new JLabel("Apellido Materno");
		lbl_AM.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AM.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_AM.setBounds(290, 298, 220, 14);
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
		txt_AM.setHorizontalAlignment(SwingConstants.CENTER);
		txt_AM.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_AM.setColumns(10);
		txt_AM.setBounds(290, 323, 220, 20);
		txt_AM.setText(Objects.isNull(usuarioPersona) ? null : usuarioPersona.getApMaterno());
		panel.add(txt_AM);
		
		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspecialidad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblEspecialidad.setBounds(602, 112, 220, 14);
		panel.add(lblEspecialidad);
		
		txt_esp = new JTextField();
		txt_esp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_esp.setHorizontalAlignment(SwingConstants.CENTER);
		txt_esp.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_esp.setColumns(10);
		txt_esp.setBounds(602, 135, 220, 20);
		txt_esp.setText(Objects.isNull(usuarioMedico) ? null : usuarioMedico.getEspecialidad());
		panel.add(txt_esp);
		
		JLabel lbl_Cedula = new JLabel("Cedula");
		lbl_Cedula.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Cedula.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Cedula.setBounds(290, 112, 220, 14);
		panel.add(lbl_Cedula);
		txt_cedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		
		
		txt_cedula.setHorizontalAlignment(SwingConstants.CENTER);
		txt_cedula.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_cedula.setColumns(10);
		txt_cedula.setBounds(290, 135, 220, 20);
		txt_cedula.setText(Objects.isNull(usuarioMedico) ? null : usuarioMedico.getCedula());
		panel.add(txt_cedula);
		
		JLabel lbl_Sexo = new JLabel("Sexo");
		lbl_Sexo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Sexo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Sexo.setBounds(602, 165, 220, 14);
		panel.add(lbl_Sexo);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECCIONA","MASCULINO ", "FEMENINO"}));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox.setBounds(602, 188, 220, 20);
		comboBox.setSelectedItem(Objects.isNull(usuarioPersona) ? null : usuarioPersona.getSexo());
		panel.add(comboBox);
		
		JLabel lbl_Usuario = new JLabel("Usuario");
		lbl_Usuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Usuario.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Usuario.setBounds(610, 242, 212, 14);
		panel.add(lbl_Usuario);
		
		txt_usuario = new JTextField();
		txt_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		txt_usuario.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_usuario.setColumns(10);
		txt_usuario.setBounds(602, 265, 220, 20);
		txt_usuario.setText(Objects.isNull(usuarioMedico) ? null : usuarioMedico.getUsuario());
		panel.add(txt_usuario);
		
		JLabel lbl_Contraseña = new JLabel("Contrase\u00F1a");
		lbl_Contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Contraseña.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Contraseña.setBounds(602, 296, 220, 14);
		panel.add(lbl_Contraseña);
		
		contraseña = new JTextField();
		contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		contraseña.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		contraseña.setColumns(10);
		contraseña.setBounds(602, 320, 220, 20);
		contraseña.setText(Objects.isNull(usuarioMedico) ? null : usuarioMedico.getPassword());
		panel.add(contraseña);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCategoria.setBounds(290, 63, 220, 14);
		panel.add(lblCategoria);
		
		
		comboBox_catego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_catego.getSelectedItem() != "ADMINISTRADOR") {
					lbl_img.setIcon(new ImageIcon(Config.class.getResource("/img/config/doc_user.png")));

				}
				 if (comboBox_catego.getSelectedItem() != "DOCTOR") {
					lbl_img.setIcon(new ImageIcon(Config.class.getResource("/img/config/icon_administrator.png")));					

				 }
			}
		});
		comboBox_catego.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONA", "ADMINISTRADOR", "SECRETARIA", "DOCTOR"}));
		comboBox_catego.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox_catego.setBounds(591, 60, 220, 20);
		
		comboBox_catego.setSelectedItem(Objects.isNull(usuarioMedico) ? null : usuarioMedico.getCategoria());
		panel.add(comboBox_catego);

		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(260, 52, 602, 339);
		panel.add(label);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setMnemonic('s');
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					guardar();														
				}
			});
		btnGuardar.setBounds(50, 191, 160, 40);
		panel.add(btnGuardar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setMnemonic('e');
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(50, 235, 160, 40);
		panel.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setMnemonic('w');
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						eliminar();					
				}
			});			
		btnEliminar.setBounds(50, 280, 160, 40);
		panel.add(btnEliminar);
		
		JButton btnSalir = new JButton(Constantes.STR_EXIT);
		btnSalir.setMnemonic('x');
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(50, 326, 160, 40);
		panel.add(btnSalir);
		
	   if (comboBox_catego.getSelectedItem() == "DOCTOR") {
			comboBox_catego.setEnabled(false);
			btnLimpiar.setVisible(false);
			btnGuardar.setVisible(false);
			btnEliminar.setVisible(false);
			txt_cedula.setEditable(false);
     		
	   }
	   btnLimpiar.setMnemonic('q');
	   btnLimpiar.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
				limpiar();					 
			}
		}); 		
   		btnLimpiar.setBounds(262, 23, 89, 23);
		panel.add(btnLimpiar);
		
		btnRegistros = new JButton("Registros");
		if (comboBox_catego.getSelectedItem() == "DOCTOR") {
			btnRegistros.setVisible(false);
		}
		
		btnRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistrosConfig rc = new RegistrosConfig ();
				Menu.esc_dp.add(rc);
				Dimension desktopSize = Menu.esc_dp.getSize();
		     	rc.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				rc.show();
			}
		});
		btnRegistros.setBounds(361, 23, 89, 23);
		panel.add(btnRegistros);
		
		
	}//fin del public Config
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    private JButton btnRegistros;

//------------------------------------------
  
    public void limpiar() {
		 txt_Nombre.setText("");
		 txt_AP.setText("");
		 txt_AM.setText("");
		 txt_esp.setText("");
		 txt_cedula.setText("");
		 txt_usuario.setText("");
		 contraseña.setText("");
		 comboBox.setSelectedIndex(0);
   } 
   
    public void guardar() {

		// Se obtienen los datos de la persona
		Persona persona = new Persona();
		persona.setNombre(txt_Nombre.getText());
		persona.setApPaterno(txt_AP.getText());
		persona.setApMaterno(txt_AM.getText());
		persona.setSexo((String) comboBox.getSelectedItem());
		Long idPersona = personaService.savePersona(persona);

		// Se obtienen los datos del medico
		Medico medico = new Medico();
		medico.setCedula(txt_cedula.getText());
		medico.setEspecialidad(txt_esp.getText());
		medico.setCategoria((String) comboBox_catego.getSelectedItem());
		medico.setUsuario(txt_usuario.getText());
		medico.setPassword(contraseña.getText());
		medico.setIdPersona(idPersona);

		Long idMedico = medicoService.saveMedico(medico);
		medico.setId(idMedico);

		JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");

		this.limpiar();
    }
    
    public void editar() {
    	/*
    	String nom,ap,am,esp,cedula,sexo,categoria,user,pass;
		String sql;
				
		nom=txt_Nombre.getText();
		ap=txt_AP.getText();
		am=txt_AM.getText();
		cedula=txt_cedula.getText();
		esp=txt_esp.getText();
		user=txt_usuario.getText();
		pass=contraseña.getText();
		sexo=(String) comboBox.getSelectedItem();
		categoria=(String) comboBox_catego.getSelectedItem();
		
		try {
			PreparedStatement pps= reg.prepareStatement("UPDATE config SET Nombre_config='"+nom+
                    "',Ap_config='"+ap+"',A_Materno_config='"+am+"',Sexo_config='"
                    +sexo+"',Esp_config='"+esp+"',Usuario_config='" +user+"',Contraseña_config='"+pass+"' WHERE Cedula_config='"+cedula+ "'");
			
			pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
			
		} catch (Exception e) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, e);
		}
   		*/


    }
    
    public void eliminar() {
    	String cedula=txt_cedula.getText();
    	try {
    		PreparedStatement pps =reg.prepareStatement("DELETE FROM config WHERE Cedula_config='"+cedula+"'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dato Eliminado");
			
		} catch (Exception e) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, e);
		}
    	
    }

	
	
//------------------------------------------	
	private void setIcon(Image image) {
		// TODO Auto-generated method stub	
	}
}
