package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import conexion.ConexionSql;
import generapdf.GeneraPDF;
import util.Constantes;


import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Medicamentos extends JInternalFrame {
	
	GeneraPDF PDF = new GeneraPDF();
    private Rectangle ArraySizePage[] = {PageSize.LEGAL, PageSize.LETTER};
    public Rectangle SizePage = null;
   String nameSizePage[] = {"Legal", "Carta"};
    private File ruta_destino = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Medicamentos frame = new Medicamentos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	Panel panel = new Panel("/img/fondo.jpg");
	private JTextField txt_Medicamento;
	private JTextField txt_medic;
	private JTextField txt_cant;
    public static int id_numero = 0;
    int eleccion=1;
    int seleccion=0;
    

	/**
	 * Create the frame.
	 */
	public Medicamentos() {
		setIcon(Toolkit.getDefaultToolkit().getImage(Medicamentos.class.getResource("/img/estetoscopio.png")));
		setTitle("MEDICAMENTOS                        "
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
				+ "                        ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1333, 620);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lbl_Medicamento = new JLabel("Medicamento");
		lbl_Medicamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Medicamento.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Medicamento.setBounds(666, 95, 96, 14);
		panel.add(lbl_Medicamento);
		
		txt_Medicamento = new JTextField();
		txt_Medicamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_Medicamento.setBounds(772, 92, 230, 20);
		panel.add(txt_Medicamento);
		txt_Medicamento.setColumns(10);
		
		
		
		modelo.addColumn("Numero");
        modelo.addColumn("Medicamento");
        modelo.addColumn("Cantidad");
	
		String sql="SELECT * FROM  medicamento ";
	    String datos[] = new String[10];
	    Statement st;
	     try{
	          st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	         datos[0]=rs.getString(1);
	         datos[1]=rs.getString(2);
	         datos[2]=rs.getString(3);

	         modelo.addRow(datos);
	     }    
	         
	    
	     scrollPane.setBounds(676, 151, 534, 300);
	     panel.add(scrollPane);
	     table.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     		int seleccion=table.rowAtPoint(e.getPoint());
	     		num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
	     		txt_medic.setText(String.valueOf(table.getValueAt(seleccion, 1)));
	     		txt_cant.setText(String.valueOf(table.getValueAt(seleccion, 2)));
	     	}
	     });
	     
	     
	     scrollPane.setViewportView(table);
	     table.setModel(new DefaultTableModel(
	     	new Object[][] {
	     		
	     	},
	     	new String[] {
	     		"Numero","Medicamento","Cantidad"
	     	}
	     ));
	     table.setModel(modelo);
	     table.setModel(modelo);
	          
	} catch (SQLException ex){
	Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE,null,ex);   
	}   
		
		JRadioButton M_Tabla = new JRadioButton("Mostrar tabla");
		M_Tabla.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				seleccion =1;
				if (seleccion==1) {
					limpiar();
					tabla();
					M_Tabla.setSelected(false);
				}
			}
		});
		M_Tabla.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		M_Tabla.setHorizontalAlignment(SwingConstants.CENTER);
		M_Tabla.setBounds(1012, 121, 107, 23);
		M_Tabla.setOpaque(false);
		panel.add(M_Tabla);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(631, 55, 623, 450);
		panel.add(label);
		
		JLabel lbl_medic = new JLabel("Medicamento");
		lbl_medic.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_medic.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_medic.setBounds(150, 94, 96, 14);
		panel.add(lbl_medic);
		
		txt_medic = new JTextField();
		txt_medic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c=(int) e.getKeyChar();
				if(c>47 && c<58) {
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
				}
			}
		});
		txt_medic.setColumns(10);
		txt_medic.setBounds(256, 91, 230, 20);
		panel.add(txt_medic);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCantidad.setBounds(150, 151, 96, 14);
		panel.add(lblCantidad);
		
		txt_cant = new JTextField();
		txt_cant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(c<'0' ||  c>'9') e.consume();
			}
		});
		txt_cant.setColumns(10);
		txt_cant.setBounds(256, 148, 230, 20);
		panel.add(txt_cant);
		
		JButton btnExit = new JButton(Constantes.STR_EXIT);
		btnExit.setMnemonic('x');
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(132, 201, 89, 23);
		panel.add(btnExit);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_1.setBounds(114, 55, 485, 200);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Medicamentos.class.getResource("/img/medicamentos/medicamento.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(214, 272, 407, 233);
		panel.add(lblNewLabel);
				
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				busqueda();
			}
		});
		
		btn_buscar.setBounds(1012, 91, 107, 23);
		panel.add(btn_buscar);
		
		JButton btn_agregar = new JButton("Agregar");
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		btn_agregar.setBounds(397, 201, 89, 23);
		panel.add(btn_agregar);
		
		JButton btneliminar = new JButton("Eliminar");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btneliminar.setBounds(496, 201, 89, 23);
		panel.add(btneliminar);
		
		num_eliminar = new JTextField();
		num_eliminar.setBounds(499, 168, 86, 20);
		panel.add(num_eliminar);
		num_eliminar.setColumns(10);
		num_eliminar.setVisible(false);
		
		numero = new JTextField();
		numero.setBounds(114, 11, 86, 20);
		panel.add(numero);
		numero.setColumns(10);
		numero.setVisible(false);
	}
	
	
	public void tabla() {
		String sql="SELECT * FROM  medicamento ";
	    String datos[] = new String[10];
	    Statement st;
	     try{
	          st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	         datos[0]=rs.getString(1);
	         datos[1]=rs.getString(2);
	         datos[2]=rs.getString(3);

	         modelo.addRow(datos);
	     }    
	         
	    
	     scrollPane.setBounds(676, 151, 534, 300);
	     panel.add(scrollPane);
	     table.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     		int seleccion=table.rowAtPoint(e.getPoint());
	     		num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
	     		txt_medic.setText(String.valueOf(table.getValueAt(seleccion, 1)));
	     		txt_cant.setText(String.valueOf(table.getValueAt(seleccion, 2)));
	     	}
	     });
	     
	     
	     scrollPane.setViewportView(table);
	     table.setModel(new DefaultTableModel(
	     	new Object[][] {
	     		
	     	},
	     	new String[] {
	     		"Numero","Medicamento","Cantidad"
	     	}
	     ));
	     table.setModel(modelo);
	     table.setModel(modelo);
	          
	} catch (SQLException ex){
	Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE,null,ex);   
	}      
	}
	
		
	public void limpiar() {
		int fila=table.getRowCount();
		for (int i=fila-1; i >= 0; i--) {
			modelo.removeRow(i);
			
		}
	}
	
	public void busqueda() {
	
        if(eleccion==1) {
        	
            
		String dato = txt_Medicamento.getText(); 
        String sql="SELECT * FROM medicamento WHERE Medicamento='"+dato+"'";
        String datos[] = new String[10];
        Statement stt;
        try {
        	stt = reg.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while(rs.next()){
           datos[0]=rs.getString(1);
           datos[1]=rs.getString(2);
           datos[2]=rs.getString(3);
           modelo.addRow(datos);
   	     }    
   	         
   	    
   	     scrollPane.setBounds(676, 151, 534, 300);
   	     panel.add(scrollPane);
   	     table.addMouseListener(new MouseAdapter() {
   	     	@Override
   	     	public void mouseClicked(MouseEvent e) {
   	     		int seleccion=table.rowAtPoint(e.getPoint());
   	     		num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
   	     		txt_medic.setText(String.valueOf(table.getValueAt(seleccion, 1)));
   	     		txt_cant.setText(String.valueOf(table.getValueAt(seleccion, 2)));
   	     	}
   	     });
   	     
   	     
   	     scrollPane.setViewportView(table);
   	     table.setModel(new DefaultTableModel(
   	     	new Object[][] {
   	     		
   	     	},
   	     	new String[] {
   	     		"Numero","Medicamento","Cantidad"
   	     	}
   	     ));
   	     table.setModel(modelo);
   	          
   	} catch (SQLException ex){
   	Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE,null,ex);   
   	}   
           }
	}
	
	public void eliminar() {
		int seleccion= table.getSelectedRow();
		if (seleccion>=0) {
			String num=num_eliminar.getText();
			try {
	    		PreparedStatement pps =reg.prepareStatement("DELETE FROM  medicamento WHERE Registro='"+num+"'");   
	            pps.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Dato Eliminado");
	            
			} catch (Exception e) {
				Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, e);
			}
			
            modelo.removeRow(seleccion);
			
			txt_medic.setText("");
			txt_cant.setText("");
			num_eliminar.setText("");

		}else {
			JOptionPane.showMessageDialog(null, "Selecciona el dato a eliminar");
			
		}
		
	}
	
	public void guardar() {
		
		/********************************************************/
		String query = "SELECT `AUTO_INCREMENT`\r\n" + 
				"FROM  INFORMATION_SCHEMA.TABLES\r\n" + 
				"WHERE TABLE_SCHEMA = 'medic'\r\n" + 
				"AND   TABLE_NAME   = 'medicamento';";
		Statement stt;
		try {
			stt = reg.createStatement();
			ResultSet rs = stt.executeQuery(query);
			while (rs.next()) {
				numero.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
		}
		/********************************************************/
		id_numero=Integer.parseInt(numero.getText());
		
		String medic, cant;
		String sql;
		
		medic=txt_medic.getText();
		cant=txt_cant.getText();
		
		sql="INSERT INTO medicamento (Medicamento,Cantidad)"
				+ "VALUES(?,?)";
		
		 try {
		    	PreparedStatement pst=reg.prepareStatement(sql);
		    	
		   // 	pst.setString(0,exp);
		    	pst.setString(1,medic);
		    	pst.setString(2,cant);
		    	
		    	pst.executeUpdate();
		    	
				JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
				String info[]= new String[3];
				info[0]=numero.getText();
				info[1]=medic;
				info[2]=cant;
				
				modelo.addRow(info);
				txt_medic.setText("");
				txt_cant.setText("");
				    			    	
						
				} catch (SQLException ex) {
				     Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
				}//fin del catch	
		 
	}
	
	
	
	
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    private JTextField numero;
    private JTextField num_eliminar;
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
  	DefaultTableModel modelo = new DefaultTableModel();
	
    private void setIcon(Image image) {
	}
}
