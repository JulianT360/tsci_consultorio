package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import conexion.ConexionSql;


import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistoriaClinica extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoriaClinica frame = new HistoriaClinica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTextField txt_antecedente;
    private JTextField txt_descripcion;
    
    public static int id_numero = 0;
    int eleccion=0;
    JTable table = new JTable();
	DefaultTableModel modelo = new DefaultTableModel();
   

	/**
	 * Create the frame.
	 */
	public HistoriaClinica() {
		setTitle("HISTORIA CLINICA"
				+ "                                         "
				+ "                                         "
				+ "                                          "
				+ "                                          "
				+ "                                           "
				+ "                                           "
				+ "                                           "
				+ "                                           "
				+ "                                           "
				+ "    ");
		setBounds(100, 100, 1300, 365);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblTipoDeAntecedente = new JLabel("TIPO DE ANTECEDENTE");
		lblTipoDeAntecedente.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeAntecedente.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblTipoDeAntecedente.setBounds(95, 101, 159, 14);
		panel.add(lblTipoDeAntecedente);
		
		txt_antecedente = new JTextField();
		txt_antecedente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_antecedente.setBounds(264, 98, 251, 20);
		panel.add(txt_antecedente);
		txt_antecedente.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblDescripcion.setBounds(95, 174, 159, 14);
		panel.add(lblDescripcion);
		
		txt_descripcion = new JTextField();
		txt_descripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_descripcion.setBounds(264, 171, 251, 20);
		panel.add(txt_descripcion);
		txt_descripcion.setColumns(10);
		
		JButton btn_guardar = new JButton("+");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		btn_guardar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_guardar.setBounds(426, 227, 89, 23);
		panel.add(btn_guardar);
		
		JButton btn_eliminar = new JButton("-");
		btn_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btn_eliminar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_eliminar.setBounds(543, 227, 89, 23);
		panel.add(btn_eliminar);
		
		modelo.addColumn("Numero");
        modelo.addColumn("Expediente");
        modelo.addColumn("Tipo");
        modelo.addColumn("Descripcion");
        
        txt_expediente = new JTextField();
		txt_expediente.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_expediente.setHorizontalAlignment(SwingConstants.CENTER);
		txt_expediente.setBounds(50, 40, 86, 20);
		//txt_expediente.setText(exp);
		txt_expediente.setVisible(false);
		panel.add(txt_expediente);
		txt_expediente.setColumns(10);
		
		
		
		JRadioButton rdbtn_Mostrar_Datos = new JRadioButton("Ver datos en la tabla");
		rdbtn_Mostrar_Datos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccion=1;
				if (eleccion==1) {
					limpiar();
					
					 String dato = txt_expediente.getText();
				        String sql="SELECT * FROM antecedente_clinico WHERE Expediente_ac='"+dato+"'";
				        String datos[] = new String[10];
				        Statement st;
				        try {
				        	st = reg.createStatement();
				            ResultSet rs = st.executeQuery(sql);
				            while(rs.next()){
				           datos[0]=rs.getString(1);
				           datos[1]=rs.getString(2);
				           datos[2]=rs.getString(3);
				           datos[3]=rs.getString(4);
				           
				           modelo.addRow(datos);
				         }
				            
				          JScrollPane tabla = new JScrollPane();
				     	  tabla.setOpaque(false);
				    	  tabla.setBounds(754, 48, 480, 225);
				    	  panel.add(tabla);
				    	  
				    	  table.addMouseListener(new MouseAdapter() {
				    	     	@Override
				    	     	public void mouseClicked(MouseEvent e) {
				    	     		int seleccion=table.rowAtPoint(e.getPoint());
				    	     		num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
				    	     		//txt_exp.setText(String.valueOf(table.getValueAt(seleccion, 1)));
				                    txt_antecedente.setText(String.valueOf(table.getValueAt(seleccion, 2)));
				    	     		txt_descripcion.setText(String.valueOf(table.getValueAt(seleccion, 3)));
				    	     	}
				    	     });
				    	  
				    	  tabla.setViewportView(table);
				    	     table.setModel(new DefaultTableModel(
				    	     	new Object[][] {
				    	     		
				    	     	},
				    	     	new String[] {
				    	     		"Numero","Expediente",
				    	     		"Tipo", "Descripcion"
				    	     	}
				    	     ));
				    	     table.setModel(modelo);
				    	     table.setModel(modelo);
				    		
						} catch (Exception ex) {
							Logger.getLogger(HistoriaClinica.class.getName()).log(Level.SEVERE,null,ex);

						}
						
				        rdbtn_Mostrar_Datos.setVisible(false);
					}//fin del if 
				//elimine codigo que esta pegado en la interfaz ejem al final comentado	
			}
		});
		rdbtn_Mostrar_Datos.setBounds(145, 43, 179, 23);
		rdbtn_Mostrar_Datos.setOpaque(false);
		panel.add(rdbtn_Mostrar_Datos);
		
		numero = new JTextField();
		numero.setBounds(50, 71, 86, 20);
		panel.add(numero);
		numero.setColumns(10);
		/********************************************************/
		String query = "SELECT `AUTO_INCREMENT`\r\n" + 
				"FROM  INFORMATION_SCHEMA.TABLES\r\n" + 
				"WHERE TABLE_SCHEMA = 'medic'\r\n" + 
				"AND   TABLE_NAME   = 'antecedente_clinico';";
		Statement st;
		try {
			st = reg.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				numero.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
		}
		/********************************************************/
		id_numero=Integer.parseInt(numero.getText());
		numero.setVisible(false);
		
		num_eliminar = new JTextField();
		num_eliminar.setBounds(543, 185, 86, 20);
		panel.add(num_eliminar);
		num_eliminar.setColumns(10);
		num_eliminar.setVisible(false);
		
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(36, 27, 620, 265);
		panel.add(label);
		
		
	}
	public void limpiar() {
		int fila=table.getRowCount();
		for (int i=fila-1; i >= 0; i--) {
			modelo.removeRow(i);
			
		}
	}
	public void guardar() {
		
		//int expediente;
		String tipo_a, desc,expediente;
		String sql;
		
		expediente=txt_expediente.getText();
		tipo_a=txt_antecedente.getText();
		desc=txt_descripcion.getText();
		
		sql="INSERT INTO antecedente_clinico (Expediente_ac,Tipo_ac, Descrip_ac)"
				+ "VALUES(?,?,?)";
		
		 try {
		    	PreparedStatement pst=reg.prepareStatement(sql);
		    	
		   // 	pst.setString(0,exp);
		    	
		    	pst.setString(1,expediente);
		    	pst.setString(2,tipo_a);
		    	pst.setString (3,desc);
		    	pst.executeUpdate();
		    	
				JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
				String info[]= new String[4];
				info[0]=numero.getText();
				info[1]=expediente;
				info[2]=tipo_a;
				info[3]=desc;
				modelo.addRow(info);
				txt_antecedente.setText("");
				txt_descripcion.setText("");
				    			    	
						
				} catch (SQLException ex) {
				     Logger.getLogger(HistoriaClinica.class.getName()).log(Level.SEVERE, null, ex);
				}//fin del catch	
		 
	}
	
	public void eliminar() {
		int seleccion= table.getSelectedRow();
		if (seleccion>=0) {
			String num=num_eliminar.getText();
			try {
	    		PreparedStatement pps =reg.prepareStatement("DELETE FROM  antecedente_clinico WHERE Numero='"+num+"'");   
	            pps.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Dato Eliminado");
	            
			} catch (Exception e) {
				Logger.getLogger(Pacientes.class.getName()).log(Level.SEVERE, null, e);
			}
			
            modelo.removeRow(seleccion);
			
			txt_antecedente.setText("");
			txt_descripcion.setText("");
			num_eliminar.setText("");
			
		}else {
			JOptionPane.showMessageDialog(null, "Selecciona el dato a eliminar");
			
		}
	}
	
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    static JTextField txt_expediente;
    private JTextField numero;
    private JTextField num_eliminar;
}

