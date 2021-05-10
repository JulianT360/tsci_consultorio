package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import conexion.ConexionSql;
import util.Constantes;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JRadioButton;

public class ConsultaSignos extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaSignos frame = new ConsultaSignos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txt_altura;
	private JTextField txt_peso;
	private JTextField txt_temperatura;
	private JTextField txt_presion;
	private JTextField txt_RC;
	JTextArea txa_motivo = new JTextArea();
	JTextArea txa_sintomas = new JTextArea();
	private String f;

	/**
	 * Create the frame.
	 */
	public ConsultaSignos() {
		//setFrameIcon(new ImageIcon(Uno.class.getResource("/img/password2.png")));
				setTitle("CONSULTAS"
						+ "                                         "
						+ "                                         "
						+ "                                          "
						+ "                                          "
						+ "                                           "
						+ "                                           "
						+ "                                           "
						+ "                                           "
						+ "                                           "
						+ "             ");
				setBounds(100, 100, 1300, 365);
				Panel panel = new Panel("/img/fondo.jpg");
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(panel);
				panel.setLayout(null);
				
				
				
				JButton btnSalir = new JButton("salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				btnSalir.setBounds(723, 336, 89, 23);
				panel.add(btnSalir);
				
				JLabel lblAltura = new JLabel("Altura");
				lblAltura.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 12));
				lblAltura.setBounds(91, 71, 89, 14);
				panel.add(lblAltura);
				
				JLabel lblPeso = new JLabel("Peso");
				lblPeso.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 12));
				lblPeso.setBounds(91, 102, 89, 14);
				panel.add(lblPeso);
				
				JLabel lblTemperatura = new JLabel("Temperatura");
				lblTemperatura.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 12));
				lblTemperatura.setBounds(91, 132, 89, 14);
				panel.add(lblTemperatura);
				
				JLabel lblNewLabel = new JLabel("Presion");
				lblNewLabel.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 12));
				lblNewLabel.setBounds(91, 167, 89, 14);
				panel.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Ritmo cardiaco");
				lblNewLabel_1.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 12));
				lblNewLabel_1.setBounds(91, 201, 89, 14);
				panel.add(lblNewLabel_1);
				
				JLabel lblicon_altura = new JLabel("");
				lblicon_altura.setHorizontalAlignment(SwingConstants.CENTER);
				lblicon_altura.setIcon(new ImageIcon(ConsultaSignos.class.getResource("/img/Consulta/altura.png")));
				lblicon_altura.setBounds(38, 59, 43, 32);
				panel.add(lblicon_altura);
				
				JLabel lblicon_peso = new JLabel("");
				lblicon_peso.setHorizontalAlignment(SwingConstants.CENTER);
				lblicon_peso.setIcon(new ImageIcon(ConsultaSignos.class.getResource("/img/Consulta/peso.png")));
				lblicon_peso.setBounds(38, 102, 43, 23);
				panel.add(lblicon_peso);
				
				JLabel lblicon_temperatura = new JLabel("");
				lblicon_temperatura.setIcon(new ImageIcon(ConsultaSignos.class.getResource("/img/Consulta/termometro.png")));
				lblicon_temperatura.setHorizontalAlignment(SwingConstants.CENTER);
				lblicon_temperatura.setBounds(38, 132, 43, 23);
				panel.add(lblicon_temperatura);
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(ConsultaSignos.class.getResource("/img/Consulta/pressure.png")));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setBounds(38, 167, 43, 23);
				panel.add(label);
				
				JLabel lblicon_RC = new JLabel("");
				lblicon_RC.setIcon(new ImageIcon(ConsultaSignos.class.getResource("/img/Consulta/latidos.png")));
				lblicon_RC.setHorizontalAlignment(SwingConstants.CENTER);
				lblicon_RC.setBounds(38, 201, 43, 23);
				panel.add(lblicon_RC);
				
				txt_altura = new JTextField();
				txt_altura.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c= e.getKeyChar();
						if((c<'0' ||  c>'9') && ( c>'.')) e.consume();
					}
				});
				txt_altura.setHorizontalAlignment(SwingConstants.CENTER);
				txt_altura.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				txt_altura.setBounds(190, 68, 111, 20);
				panel.add(txt_altura);
				txt_altura.setColumns(10);
				
				txt_peso = new JTextField();
				txt_peso.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c= e.getKeyChar();
						if((c<'0' ||  c>'9') && ( c>'.')) e.consume();
					}
				});
				txt_peso.setHorizontalAlignment(SwingConstants.CENTER);
				txt_peso.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				txt_peso.setText("");
				txt_peso.setBounds(190, 99, 111, 20);
				panel.add(txt_peso);
				txt_peso.setColumns(10);
				
				txt_temperatura = new JTextField();
				txt_temperatura.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c= e.getKeyChar();
						if((c<'0' ||  c>'9') && ( c>'.')) e.consume();
					}
				});
				txt_temperatura.setHorizontalAlignment(SwingConstants.CENTER);
				txt_temperatura.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				txt_temperatura.setText("");
				txt_temperatura.setBounds(190, 129, 111, 20);
				panel.add(txt_temperatura);
				txt_temperatura.setColumns(10);
				
				txt_presion = new JTextField();
				txt_presion.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c= e.getKeyChar();
						if((c<'0' ||  c>'9') && ( c>'.')) e.consume();
					}
				});
				txt_presion.setHorizontalAlignment(SwingConstants.CENTER);
				txt_presion.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				txt_presion.setBounds(190, 164, 43, 20);
				panel.add(txt_presion);
				txt_presion.setColumns(10);
				
				txt_RC = new JTextField();
				txt_RC.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c= e.getKeyChar();
						if((c<'0' ||  c>'9') && ( c>'.')) e.consume();
					}
				});
				txt_RC.setHorizontalAlignment(SwingConstants.CENTER);
				txt_RC.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				txt_RC.setBounds(190, 198, 111, 20);
				panel.add(txt_RC);
				txt_RC.setColumns(10);
				
				JLabel lbl_cm = new JLabel("Cm");
				lbl_cm.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lbl_cm.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_cm.setBounds(311, 71, 46, 14);
				panel.add(lbl_cm);
				
				JLabel lbl_kg = new JLabel("Kg");
				lbl_kg.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_kg.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lbl_kg.setBounds(311, 102, 46, 14);
				panel.add(lbl_kg);
				
				JLabel lbl_centigrados = new JLabel("\u00BAC");
				lbl_centigrados.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_centigrados.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lbl_centigrados.setBounds(311, 132, 46, 14);
				panel.add(lbl_centigrados);
				
				JLabel lbl_Mmhg = new JLabel("mmHg");
				lbl_Mmhg.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_Mmhg.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lbl_Mmhg.setBounds(311, 167, 46, 14);
				panel.add(lbl_Mmhg);
				
				JLabel lbl_RC = new JLabel("bpm");
				lbl_RC.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_RC.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lbl_RC.setBounds(311, 201, 46, 14);
				panel.add(lbl_RC);
				
				JLabel lblNewLabel_2 = new JLabel("SIGNOS");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lblNewLabel_2.setBounds(38, 22, 319, 14);
				panel.add(lblNewLabel_2);
				
				JLabel label_4 = new JLabel("/");
				label_4.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.BOLD, 20));
				label_4.setHorizontalAlignment(SwingConstants.CENTER);
				label_4.setBounds(232, 167, 24, 14);
				panel.add(label_4);
				
				txt_presion2 = new JTextField();
				txt_presion2.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c= e.getKeyChar();
						if((c<'0' ||  c>'9') && ( c>'.')) e.consume();
					}
				});
				txt_presion2.setHorizontalAlignment(SwingConstants.CENTER);
				txt_presion2.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				txt_presion2.setColumns(10);
				txt_presion2.setBounds(258, 164, 43, 20);
				panel.add(txt_presion2);
				
				JLabel label_1 = new JLabel("");
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				label_1.setBounds(21, 11, 351, 265);
				panel.add(label_1);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(413, 66, 285, 197);
				panel.add(scrollPane);
				
				txt_motivo = new JTextField();
				txt_motivo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						int c=(int) e.getKeyChar();
						if(c>47 && c<58) {
							e.setKeyChar((char) KeyEvent.VK_CLEAR);
						}
					}
				});
				scrollPane.setViewportView(txt_motivo);
				txt_motivo.setColumns(10);
				
				JLabel lblNewLabel_3 = new JLabel("Motivo de la consulta");
				lblNewLabel_3.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lblNewLabel_3.setBounds(413, 22, 161, 14);
				panel.add(lblNewLabel_3);			
				
				JLabel label_2 = new JLabel("");
				label_2.setHorizontalAlignment(SwingConstants.CENTER);
				label_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				label_2.setBounds(382, 11, 351, 265);
				panel.add(label_2);
				
				JLabel lblSintomas = new JLabel("Sintomas");
				lblSintomas.setFont(new Font(Constantes.TIMES_NEW_ROMAN, Font.PLAIN, 13));
				lblSintomas.setBounds(774, 22, 161, 14);
				panel.add(lblSintomas);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(774, 59, 285, 197);
				panel.add(scrollPane_1);
				
				txt_sintomas = new JTextField();
				txt_sintomas.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						int c=(int) e.getKeyChar();
						if(c>47 && c<58) {
							e.setKeyChar((char) KeyEvent.VK_CLEAR);
						}
					}
				});
				scrollPane_1.setViewportView(txt_sintomas);
				txt_sintomas.setColumns(10);
				
				JLabel label_3 = new JLabel("");
				label_3.setHorizontalAlignment(SwingConstants.CENTER);
				label_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				label_3.setBounds(743, 11, 351, 265);
				panel.add(label_3);
				
				txt_expediente = new JTextField();
				txt_expediente.setBounds(21, 287, 86, 20);
				panel.add(txt_expediente);
				txt_expediente.setVisible(false);
				txt_expediente.setColumns(10);
				
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardar();
					} 
				});
				btnGuardar.setBounds(1154, 67, 89, 23);
				panel.add(btnGuardar);
				
				txt_fecha = new JTextField();
				txt_fecha.setHorizontalAlignment(SwingConstants.CENTER);
				txt_fecha.setBounds(152, 287, 124, 20);
				panel.add(txt_fecha);
				txt_fecha.setColumns(10);
				txt_fecha.setVisible(false);
				Calendar c = new GregorianCalendar();
				txt_fecha.setText(""+c.get(c.DAY_OF_MONTH)+"/"+c.get(c.MONTH)+"/"+c.get(c.YEAR));
				
				rdbtn_Mostrar_Datos = new JRadioButton("Ver datos en la tabla");
				rdbtn_Mostrar_Datos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int eleccion = 1;
						if (eleccion==1) {
							
							    String f=txt_f.getText();
							    String dato = txt_expediente.getText();
						        String sql="SELECT * FROM signos_consulta WHERE Expediente='"+dato+"'";
						        String datos[] = new String[11];
						        Statement st;
						        try {
						        	st = reg.createStatement();
						            ResultSet rs = st.executeQuery(sql);
						            while(rs.next()){
						            	datos[0]=rs.getString(1);//num
								        datos[1]=rs.getString(2);//exp
								        datos[2]=rs.getString(3);//fecha
								        datos[3]=rs.getString(4);//altura
								        datos[4]=rs.getString(5);//peso
								        datos[5]=rs.getString(6);//temperatura
								        datos[6]=rs.getString(7);//precion_i
								        datos[7]=rs.getString(8);//precion_f
								        datos[8]=rs.getString(9);//RC
								        datos[9]=rs.getString(10);//motivo
								        datos[10]=rs.getString(11);//sintomas
								           
								           
						            }
								} catch (Exception ex) {
									Logger.getLogger(ConsultaSignos.class.getName()).log(Level.SEVERE,null,ex);

								}
						        String folio = datos[0];
					            if (f.equals(folio)) {
					            	txt_altura.setText(datos[3]);
					            	txt_peso.setText(datos[4]);
					            	txt_temperatura.setText(datos[5]);
					            	txt_presion.setText(datos[6]);
					            	txt_presion2.setText(datos[7]);
					            	txt_RC.setText(datos[8]);
					            	txt_motivo.setText(datos[9]);
					            	txt_sintomas.setText(datos[10]);
					            }
								
						        rdbtn_Mostrar_Datos.setVisible(false);
							}//fin del if 
						//elimine codigo que esta pegado en la interfaz ejem al final comentado	
					}
				});
				rdbtn_Mostrar_Datos.setOpaque(false);
				rdbtn_Mostrar_Datos.setBounds(556, 286, 179, 23);
				panel.add(rdbtn_Mostrar_Datos);
				
				txt_f = new JTextField();
				txt_f.setBounds(304, 287, 100, 20);
				panel.add(txt_f);
				txt_f.setVisible(false);
				txt_f.setColumns(10);
	}
	
public void guardar() {
	     java.util.Date date = new java.util.Date();
         java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(" yyyy/MM/dd   HH:mm:ss ");
         fecha = sdf.format(date);
        
		String exp,f,altura,peso,temperatura,presion_i,presion_f,ritmoc,motivo,sintomas;
		String sql;
		
		exp=txt_expediente.getText();
		//f=txt_fecha.getText();
		altura=txt_altura.getText();
		peso=txt_peso.getText();
		temperatura=txt_temperatura.getText();
		presion_i=txt_presion.getText();
		presion_f=txt_presion2.getText();
		ritmoc=txt_RC.getText();
		motivo=txt_motivo.getText();
		sintomas=txt_sintomas.getText();
		
		
		sql="INSERT INTO  signos_consulta (Expediente ,Fecha_consulta ,"
			+ "Altura ,Peso ,Temperatura ,Presion_i,Presion_f ,Ritmo_c , Motivo_consulta ,"
			+ " Sintomas_paciente)VALUES(?,?,?,?,?,?,?,?,?,?)";
		 try {
		    	PreparedStatement pst=reg.prepareStatement(sql);
		    	
		   // 	pst.setString(0,exp);
		    	pst.setString(1,exp);
		    	pst.setString(2,fecha);
		    	pst.setString (3,altura);
		    	pst.setString(4,peso);
		    	pst.setString(5,temperatura);
		    	pst.setString(6,presion_i);
		    	pst.setString(7,presion_f);
		    	pst.setString(8,ritmoc);
		    	pst.setString(9,motivo);
		    	pst.setString(10,sintomas);
	            pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
				
				    			    	
						
				} catch (SQLException ex) {
				     Logger.getLogger(ConsultaSignos.class.getName()).log(Level.SEVERE, null, ex);
				}//fin del catch
		
	}
public void ver_datos() {
	
}
	
	

	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    static JTextField txt_expediente;
    private JTextField txt_fecha;
    String fecha;
    private JTextField txt_presion2;
    private JTextField txt_motivo;
    private JTextField txt_sintomas;
    private JRadioButton rdbtn_Mostrar_Datos;
    static JTextField txt_f;
}
