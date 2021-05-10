package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conexion.ConexionSql;
import util.Constantes;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class Apoyo extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apoyo frame = new Apoyo();
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
	public Apoyo() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Pacientes.class.getResource("/img/estetoscopio.png")));
		setTitle("APOYO                               "
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
		setBounds(100, 100, 848, 507);
		JPanel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		lblAlt_8 = new JLabel("Alt + B");
		lblAlt_8.setBounds(162, 407, 76, 20);
		panel.add(lblAlt_8);
		
		lblReporte_1 = new JLabel("Reporte");
		lblReporte_1.setBounds(60, 407, 76, 21);
		panel.add(lblReporte_1);
		
		lblCitas = new JLabel("Citas");
		lblCitas.setBounds(60, 386, 76, 21);
		panel.add(lblCitas);
		
		lblAlt_9 = new JLabel("Alt + A");
		lblAlt_9.setBounds(162, 386, 76, 20);
		panel.add(lblAlt_9);
		
		JLabel lblElSistemaCuenta = new JLabel("El sistema cuenta con atajos de teclado");
		lblElSistemaCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblElSistemaCuenta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblElSistemaCuenta.setBounds(60, 74, 655, 35);
		panel.add(lblElSistemaCuenta);
		
		JLabel lblPantallaMenu = new JLabel("Pantalla Menu");
		lblPantallaMenu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPantallaMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPantallaMenu.setBounds(60, 131, 165, 21);
		panel.add(lblPantallaMenu);
		
		JLabel lblConfiguracion = new JLabel("Configuracion");
		lblConfiguracion.setBounds(60, 163, 76, 21);
		panel.add(lblConfiguracion);
		
		JLabel lblAltS = new JLabel("Alt + 1");
		lblAltS.setBounds(162, 163, 76, 20);
		panel.add(lblAltS);
		
		JLabel lblAyuda = new JLabel("Ayuda");
		lblAyuda.setBounds(60, 184, 76, 21);
		panel.add(lblAyuda);
		
		JLabel lblAlt = new JLabel("Alt + 2");
		lblAlt.setBounds(162, 184, 76, 20);
		panel.add(lblAlt);
		
		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setBounds(60, 206, 76, 21);
		panel.add(lblSalir);
		
		JLabel lblAlt_1 = new JLabel("Alt + 3");
		lblAlt_1.setBounds(162, 206, 76, 20);
		panel.add(lblAlt_1);
		
		JLabel lblRegisro = new JLabel("Paciente");
		lblRegisro.setBounds(60, 238, 76, 21);
		panel.add(lblRegisro);
		
		JLabel lblAlt_2 = new JLabel("Alt + 4");
		lblAlt_2.setBounds(162, 238, 76, 20);
		panel.add(lblAlt_2);
		
		JLabel lblConsulta = new JLabel("Cita Medica");
		lblConsulta.setBounds(60, 259, 76, 21);
		panel.add(lblConsulta);
		
		JLabel lblAlt_3 = new JLabel("Alt + 5");
		lblAlt_3.setBounds(162, 259, 76, 20);
		panel.add(lblAlt_3);
		
		JLabel lblAlt_4 = new JLabel("Alt + 6");
		lblAlt_4.setBounds(162, 281, 76, 20);
		panel.add(lblAlt_4);
		
		JLabel lblExpedientes = new JLabel("Consulta");
		lblExpedientes.setBounds(60, 281, 76, 21);
		panel.add(lblExpedientes);
		
		JLabel lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setBounds(60, 312, 76, 21);
		panel.add(lblMedicamento);
		
		JLabel lblAlt_5 = new JLabel("Alt + 7");
		lblAlt_5.setBounds(162, 312, 76, 20);
		panel.add(lblAlt_5);
		
		JLabel lblAlt_6 = new JLabel("Alt + 8");
		lblAlt_6.setBounds(162, 333, 76, 20);
		panel.add(lblAlt_6);
		
		JLabel lblReporte = new JLabel("Expediente");
		lblReporte.setBounds(60, 333, 76, 21);
		panel.add(lblReporte);
		
		JLabel lblPantallaConfiguracion = new JLabel("Pantalla Configuracion");
		lblPantallaConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPantallaConfiguracion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPantallaConfiguracion.setBounds(313, 131, 165, 21);
		panel.add(lblPantallaConfiguracion);
		
		JLabel lblGuardar = new JLabel("Guardar");
		lblGuardar.setBounds(313, 163, 76, 21);
		panel.add(lblGuardar);
		
		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setBounds(313, 184, 76, 21);
		panel.add(lblEditar);
		
		JLabel lblEliminar = new JLabel("Eliminar");
		lblEliminar.setBounds(313, 206, 76, 21);
		panel.add(lblEliminar);
		
		JLabel lblSalir_1 = new JLabel("Salir");
		lblSalir_1.setBounds(313, 251, 76, 21);
		panel.add(lblSalir_1);
		
		JLabel lblLimpiar = new JLabel("Limpiar");
		lblLimpiar.setBounds(313, 272, 76, 21);
		panel.add(lblLimpiar);
		
		JLabel lblAltQ = new JLabel("Alt + q");
		lblAltQ.setBounds(415, 272, 76, 20);
		panel.add(lblAltQ);
		
		JLabel lblAltX = new JLabel("Alt + x");
		lblAltX.setBounds(415, 251, 76, 20);
		panel.add(lblAltX);
		
		JLabel lblAltW = new JLabel("Alt + w");
		lblAltW.setBounds(415, 206, 76, 20);
		panel.add(lblAltW);
		
		JLabel lblAltE = new JLabel("Alt + e");
		lblAltE.setBounds(415, 184, 76, 20);
		panel.add(lblAltE);
		
		JLabel lblAltS_1 = new JLabel("Alt + s");
		lblAltS_1.setBounds(415, 163, 76, 20);
		panel.add(lblAltS_1);
		
		JLabel lblPantallaRegistro = new JLabel("Pantalla Registro");
		lblPantallaRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPantallaRegistro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPantallaRegistro.setBounds(550, 131, 165, 21);
		panel.add(lblPantallaRegistro);
		
		JLabel label_2 = new JLabel("Guardar");
		label_2.setBounds(550, 163, 76, 21);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Alt + s");
		label_3.setBounds(652, 163, 76, 20);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Editar");
		label_4.setBounds(550, 184, 76, 21);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Eliminar");
		label_5.setBounds(550, 206, 76, 21);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Salir");
		label_6.setBounds(550, 251, 76, 21);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Limpiar");
		label_7.setBounds(550, 272, 76, 21);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Alt + q");
		label_8.setBounds(652, 272, 76, 20);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Alt + x");
		label_9.setBounds(652, 251, 76, 20);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("Alt + w");
		label_10.setBounds(652, 206, 76, 20);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("Alt + e");
		label_11.setBounds(652, 184, 76, 20);
		panel.add(label_11);
		
		JButton btnExit = new JButton(Constantes.STR_EXIT);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setMnemonic('x');
		btnExit.setBounds(38, 11, 89, 23);
		panel.add(btnExit);
		
		lblPacientes = new JLabel("Pacientes");
		lblPacientes.setBounds(60, 354, 76, 21);
		panel.add(lblPacientes);
		
		lblAlt_7 = new JLabel("Alt + 9");
		lblAlt_7.setBounds(162, 354, 76, 20);
		panel.add(lblAlt_7);
		
		label = new JLabel("");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBackground(SystemColor.controlHighlight);
		label.setBounds(32, 58, 714, 397);
		panel.add(label);
		
		
		
		
		

	}

	private void setIcon(Image image) {
		// TODO Auto-generated method stub
	}
//----------------------------------------------------------------	
	
	
	
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    private JLabel lblPacientes;
    private JLabel lblAlt_7;
    private JLabel label;
    private JLabel lblReporte_1;
    private JLabel lblCitas;
    private JLabel lblAlt_8;
    private JLabel lblAlt_9;
}
