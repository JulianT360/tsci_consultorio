package interfaz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import static interfaz.InicioSesion.apm;
import static interfaz.InicioSesion.app;
import static interfaz.InicioSesion.catego;
import static interfaz.InicioSesion.nom;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conexion.ConexionSql;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Menu extends JFrame {

	private JPanel panel;
	public static javax.swing.JDesktopPane esc_dp;
	String ADMINISTRADOR;
	String SECRETARIA;


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioSesion.class.getResource("/img/estetoscopio.png")));
		setTitle("CONSULTORIO");
		this.setExtendedState(Menu.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1353, 718);
		Panel panelm = new Panel("/img/fondo5.jpg");
		panelm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelm);
		this.setResizable(true);
		panelm.setLayout(null);
		
		esc_dp = new javax.swing.JDesktopPane();
		esc_dp.setOpaque(false);
		esc_dp.setBounds(10, 34, 1342, 635);
		panelm.add(esc_dp);
		
				
		
		JLabel nom_doc = new JLabel();
		nom_doc.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		nom_doc.setHorizontalAlignment(SwingConstants.LEFT);
		nom_doc.setBounds(268, -2, 558, 23);
		nom_doc.setText(nom+" "+app+" "+apm);
		panelm.add(nom_doc);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		comboBox_catego = new JComboBox();
		comboBox_catego.setEnabled(false);
		comboBox_catego.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox_catego.setBounds(983, 2, 220, 20);
		comboBox_catego.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONA", "ADMINISTRADOR", "SECRETARIA", "DOCTOR"}));
		comboBox_catego.setSelectedItem(catego);
		panelm.add(comboBox_catego);
		
		 if (comboBox_catego.getSelectedItem() == "SECRETARIA") {
	        	//JOptionPane.showMessageDialog(null, catego);
				mntmConsulta.setVisible(false);
				mntmExpedientes.setVisible(false);
				mntmMedicamento.setVisible(false);
				
				
	        }
		 
		 if (comboBox_catego.getSelectedItem() == "DOCTOR") {		
				JLabel lblDr = new JLabel("DR.");
				//lblDr.setVisible(false);
				lblDr.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
				lblDr.setHorizontalAlignment(SwingConstants.CENTER);
				lblDr.setBounds(218, -2, 50, 25);
				panelm.add(lblDr);
				}
		 
		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		mntmConfiguracion.setMnemonic(KeyEvent.VK_1);
		mntmConfiguracion.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Config l = new Config ();
			esc_dp.add(l);
			Dimension desktopSize = esc_dp.getSize();
			l.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
			l.show();
			
		
		}
	});
		mnNewMenu.add(mntmConfiguracion);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Apoyo l = new Apoyo ();
				esc_dp.add(l);
				Dimension desktopSize = esc_dp.getSize();
				l.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				l.show();
				
			}
		});
		mntmAyuda.setMnemonic(KeyEvent.VK_2);
		mnNewMenu.add(mntmAyuda);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setMnemonic(KeyEvent.VK_3);
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int resp = JOptionPane.showConfirmDialog(null, "Salir del sistema ", "Alerta!", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			});
		mnNewMenu.add(mntmSalir);
		
		JMenu mnUsuario = new JMenu("Generar");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmRegistro = new JMenuItem("Paciente");
		mnUsuario.add(mntmRegistro);
		mntmRegistro.setMnemonic(KeyEvent.VK_4);
		mntmRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pacientes l = new Pacientes ();
				esc_dp.add(l);
				Dimension desktopSize = esc_dp.getSize();
				l.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				l.show();
			}
		});
		
		JMenuItem mntmRconsulta = new JMenuItem("Cita Medica");
		mnUsuario.add(mntmRconsulta);
		mntmRconsulta.setMnemonic(KeyEvent.VK_5);//7
		mntmRconsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rconsulta rc = new Rconsulta ();
				esc_dp.add(rc);
				Dimension desktopSize = esc_dp.getSize();
				rc.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				rc.show();
			}
		});
		
	
		mntmConsulta.setMnemonic(KeyEvent.VK_6);//5
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, catego);
								
				Consulta l = new Consulta ();
				esc_dp.add(l);
				Dimension desktopSize = esc_dp.getSize();
				l.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				l.show();
				
				
				
			}
		});
		
		mnUsuario.add(mntmConsulta);
		
		
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		
		mntmMedicamento.setMnemonic(KeyEvent.VK_7);//8
		mnRegistro.add(mntmMedicamento);
		mntmMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Medicamentos l = new Medicamentos ();
				esc_dp.add(l);
				Dimension desktopSize = esc_dp.getSize();
				l.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				l.show();
			}
		});
		 	
		
		mnRegistro.add(mntmExpedientes);
		mntmExpedientes.setMnemonic(KeyEvent.VK_8);//6
		mntmExpedientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Expedientes l = new Expedientes ();
				esc_dp.add(l);
				Dimension desktopSize = esc_dp.getSize();
				l.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				l.show();
			}
		});
		
		
		mn_Paciente = new JMenuItem("Pacientes");
		mntmExpedientes.setMnemonic(KeyEvent.VK_9);
		mn_Paciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrosPacientes rp = new RegistrosPacientes();
				Menu.esc_dp.add(rp);
				Dimension desktopSize = Menu.esc_dp.getSize();
		     	rp.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				rp.show();
			}
		});
		mnRegistro.add(mn_Paciente);
		
		mn_Citas = new JMenuItem("Citas");
		mntmExpedientes.setMnemonic(KeyEvent.VK_A);
		mn_Citas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroCitas rc = new RegistroCitas();
				Menu.esc_dp.add(rc);
				Dimension desktopSize = Menu.esc_dp.getSize();
		     	rc.setLocation(Menu.esc_dp.WIDTH , Menu.esc_dp.HEIGHT);
				rc.show();
			}
		});
		mnRegistro.add(mn_Citas);
		
		
		mn_Procesar = new JMenu("Procesar");
		menuBar.add(mn_Procesar);
						
		JMenuItem mntmReporte = new JMenuItem("Reporte");
		mn_Procesar.add(mntmReporte);
		mntmReporte.setMnemonic(KeyEvent.VK_B);//9
		mntmReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reporte m = new Reporte ();
				esc_dp.add(m);
				Dimension desktopSize = esc_dp.getSize();
				m.setLocation(esc_dp.WIDTH , esc_dp.HEIGHT);
				m.show();
			}
		});
		
		
	}
	
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    JMenuItem mntmConsulta = new JMenuItem("Consulta");
    JMenuItem mntmExpedientes = new JMenuItem("Expedientes");
    JMenuItem mntmMedicamento = new JMenuItem("Medicamento");
    private JMenu mn_Procesar;
    private JMenuItem mn_Paciente;
    private JMenuItem mn_Citas;
    private JComboBox comboBox_catego;
}
