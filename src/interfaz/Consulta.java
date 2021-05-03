package interfaz;

import conexion.ConexionSql;
import util.Constantes;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;



public class Consulta extends JInternalFrame {
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String f;
	public static String exp;
	static int id_Folio = 0;
	static JTextField txt_folio;
	private JTextField txt_fecha;
	//private JTextField txt_exp;
	//private JTextField txt_nom;
	private JTextField txt_ap;
	private JTextField txt_am;
	private JTextField txt_sexo;
	private JTextField txt_edad;
	private JTextField txt_nom = new JTextField();
	static JTextField txt_exp;
	

	/**
	 * Create the frame.
	 */
	public Consulta() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Consulta.class.getResource("/img/estetoscopio.png")));
		setTitle("CONSULTAS                           "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                                   "
				+ "                            ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1333, 620);
		Panel panel = new Panel("/img/fondo 6.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		
		JLabel lblicon = new JLabel("");
		lblicon.setIcon(new ImageIcon(Consulta.class.getResource("/img/Consulta/consulta 2.png")));
		lblicon.setBounds(10, 11, 64, 64);
		panel.add(lblicon);
		
		JLabel lblDG = new JLabel("DATOS GENERALES");
		lblDG.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDG.setHorizontalAlignment(SwingConstants.CENTER);
		lblDG.setBounds(87, 11, 180, 64);
		panel.add(lblDG);
		
		JLabel lblN = new JLabel("Nombre (s)");
		lblN.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblN.setBounds(201, 99, 64, 14);
		panel.add(lblN);
		
		JLabel lblAP = new JLabel("Apellido Paterno");
		lblAP.setHorizontalAlignment(SwingConstants.CENTER);
		lblAP.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblAP.setBounds(559, 72, 200, 14);
		panel.add(lblAP);
		
		JLabel lblAM = new JLabel("Apellido Materno");
		lblAM.setHorizontalAlignment(SwingConstants.CENTER);
		lblAM.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblAM.setBounds(780, 72, 200, 14);
		panel.add(lblAM);
		
		JTextField txt_nom = new JTextField();
		txt_nom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_nom.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nom.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_nom.setBounds(278, 96, 200, 20);
		panel.add(txt_nom);
		txt_nom.setColumns(10);
		
		JTextField txt_ap = new JTextField();
		txt_ap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_ap.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ap.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_ap.setColumns(10);
		txt_ap.setBounds(559, 94, 200, 20);
		panel.add(txt_ap);
		
		JTextField txt_am = new JTextField();
		txt_am.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_am.setHorizontalAlignment(SwingConstants.CENTER);
		txt_am.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_am.setColumns(10);
		txt_am.setBounds(780, 94, 200, 20);
		panel.add(txt_am);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblSexo.setBounds(1016, 71, 103, 14);
		panel.add(lblSexo);
		
		JTextField txt_sexo = new JTextField();
		txt_sexo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_sexo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_sexo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_sexo.setColumns(10);
		txt_sexo.setBounds(1016, 93, 103, 20);
		panel.add(txt_sexo);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblEdad.setBounds(1156, 71, 103, 14);
		panel.add(lblEdad);
		
		JTextField txt_edad = new JTextField();
		txt_edad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_edad.setHorizontalAlignment(SwingConstants.CENTER);
		txt_edad.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_edad.setBounds(1156, 93, 103, 20);
		panel.add(txt_edad);
		txt_edad.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("FOLIO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(418, 37, 46, 14);
		panel.add(lblNewLabel);
		
		JTextField txt_folio = new JTextField();
		txt_folio.setEnabled(false);
		txt_folio.setHorizontalAlignment(SwingConstants.CENTER);
		txt_folio.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_folio.setBounds(471, 34, 129, 20);
		panel.add(txt_folio);
		txt_folio.setColumns(10);
		/********************************************************/
		String query = "SELECT `AUTO_INCREMENT`\r\n" + 
				"FROM  INFORMATION_SCHEMA.TABLES\r\n" + 
				"WHERE TABLE_SCHEMA = 'medic'\r\n" + 
				"AND   TABLE_NAME   = 'consulta_datos';";
		Statement st;
		try {
			st = reg.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				txt_folio.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
		}
		/********************************************************/
		id_Folio=Integer.parseInt(txt_folio.getText());
		String folio=String.valueOf(id_Folio);
		
		
		
		JLabel lblfecha = new JLabel("FECHA");
		lblfecha.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblfecha.setBounds(1016, 34, 46, 14);
		panel.add(lblfecha);
		
		JTextField txt_fecha = new JTextField();
		txt_fecha.setEnabled(false);
		txt_fecha.setHorizontalAlignment(SwingConstants.CENTER);
		txt_fecha.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_fecha.setBounds(1079, 31, 180, 20);
		panel.add(txt_fecha);
		txt_fecha.setColumns(10);
		
		Calendar c = new GregorianCalendar();
		txt_fecha.setText(""+c.get(c.DAY_OF_MONTH)+"/"+c.get(c.MONTH)+"/"+c.get(c.YEAR));

		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Busqueda_exp BE=new Busqueda_exp();
				BE.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Consulta.class.getResource("/img/Consulta/search2.png")));
		btnNewButton.setBounds(490, 93, 46, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Expediente");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 74, 103, 14);
		panel.add(lblNewLabel_1);
		
		txt_exp = new JTextField();
		txt_exp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_exp.setBounds(10, 96, 103, 20);
		panel.add(txt_exp);
		txt_exp.setColumns(10);
		
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String exp = txt_exp.getText();
								     
		        String sql="SELECT * FROM  paciente WHERE Expediente='"+exp+"'";
		        String datos[] = new String[10];
		         Statement st;
		        try{
		         st = reg.createStatement();
		         ResultSet rs = st.executeQuery(sql);
		         while(rs.next()){
		        datos[0]=rs.getString(1);//exp
		        datos[1]=rs.getString(2);//nom
		        datos[2]=rs.getString(3);//ap
		        datos[3]=rs.getString(4);//am
		        datos[4]=rs.getString(5);//sexo
		        datos[5]=rs.getString(6);//ed
		        datos[6]=rs.getString(7);  //cel
		        datos[7]=rs.getString(8);//estado
		        datos[8]=rs.getString(9);//municipio
		        datos[9]=rs.getString(10);//calle

		    }
		       // txt_expediente.setText(datos[0]);   
		        txt_nom.setText(datos[1]);
		        txt_ap.setText(datos[2]);
		        txt_am.setText(datos[3]);
		        txt_sexo.setText(datos[4]);
		        txt_edad.setText(datos[5]);
		       
		        // JOptionPane.showMessageDialog(null,"Datos del Expediente");
		         
		    } catch (SQLException ex){
		         Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE,null,ex);   
		        } 
			}
		});
		button.setIcon(new ImageIcon(Consulta.class.getResource("/img/Consulta/arrow_r.png")));
		button.setBounds(123, 95, 46, 23);
		panel.add(button);
		
		JButton btnExit = new JButton(Constantes.STR_EXIT);
		btnExit.setMnemonic('x');
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(277, 11, 89, 23);
		panel.add(btnExit);
		
		JLabel lblDatos = new JLabel("");
		lblDatos.setOpaque(true);
		lblDatos.setBackground(new Color(135, 206, 235));
		lblDatos.setBounds(0, 0, 1317, 170);
		
		panel.add(lblDatos);
		
		JDesktopPane esc = new JDesktopPane();
		esc.setOpaque(false);
		esc.setBounds(10, 205, 1300, 365);
		panel.add(esc);
		
				
		//mnNewMenu.add(mntmConfiguracion);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Consultas");
		mntmNewMenuItem.setForeground(new Color(0, 0, 0));
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setBounds(140, 172, 129, 22);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esc.removeAll();
				ConsultaSignos l = new ConsultaSignos();
				esc.add(l);
				Dimension desktopSize = esc.getSize();
			    //Dimension FrameSize = l.getSize();
				l.setLocation(esc.WIDTH , esc.HEIGHT);
				l.show();
				String envia=txt_exp.getText();
				ConsultaSignos.txt_expediente.setText(envia);
				ConsultaSignos.txt_f.setText(folio);
			
			}
		});
		panel.add(mntmNewMenuItem);
		
		JMenuItem mntmRecetaMedica = new JMenuItem("Receta Medica");
		mntmRecetaMedica.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		mntmRecetaMedica.setHorizontalAlignment(SwingConstants.CENTER);
		mntmRecetaMedica.setBounds(270, 172, 129, 22);
		mntmRecetaMedica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esc.removeAll();
				RecetaMedica l = new RecetaMedica();
				esc.add(l);
				Dimension desktopSize = esc.getSize();
				l.setLocation(esc.WIDTH , esc.HEIGHT);
				l.show();
				String envia=txt_exp.getText();
				RecetaMedica.txt_expediente.setText(envia);
				RecetaMedica.txt_folio.setText(folio);
				RecetaMedica.txt_n.setText(txt_nom.getText());
				RecetaMedica.txt_ap.setText(txt_ap.getText());
				RecetaMedica.txt_am.setText(txt_am.getText());
				RecetaMedica.txt_s.setText(txt_sexo.getText());
				RecetaMedica.txt_e.setText(txt_edad.getText());


				
				
				
				
			}
		});
		panel.add(mntmRecetaMedica);
		
		JMenuItem mntmHistoriaClinica = new JMenuItem("Historia Clinica");
		mntmHistoriaClinica.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		mntmHistoriaClinica.setHorizontalAlignment(SwingConstants.CENTER);
		mntmHistoriaClinica.setBounds(10, 172, 129, 22);
		mntmHistoriaClinica.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			esc.removeAll();
			HistoriaClinica l = new HistoriaClinica();
			esc.add(l);
			Dimension desktopSize = esc.getSize();
			l.setLocation(esc.WIDTH , esc.HEIGHT);
			l.show();
			String envia=txt_exp.getText();
			HistoriaClinica.txt_expediente.setText(envia);
		
		}
	});
		panel.add(mntmHistoriaClinica);
		
		
		/*System.out.println(rm);
		if (rm==1) {
			dispose();
		}*/
	}

	private void setIcon(Image image) {		
	}
//------------------------------------------------------------------------------
		
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    private JTextField txtrecive;
    private JRadioButton rdbtn__Salir;
   // static int rm=0;

	
		
	
}
