package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.ConexionSql;
import util.Constantes;

import javax.swing.JRadioButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BusquedaMedic extends JFrame {

	Panel panel = new Panel("/img/fondo.jpg");
	private JTextField txt_Medicamento;
	int eleccion=1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaMedic frame = new BusquedaMedic();
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
	public BusquedaMedic() {
		setIcon(Toolkit.getDefaultToolkit().getImage(BusquedaMedic.class.getResource("/img/estetoscopio.png")));
		setTitle("MEDICAMENTOS        ");
		setBounds(100, 100, 620, 432);
		//setBounds(100, 100, 620, 432);
		Panel panel = new Panel("/img/fondo.jpg");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lbl_Medicamento = new JLabel("Medicamento");
		lbl_Medicamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Medicamento.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_Medicamento.setBounds(45, 76, 96, 14);
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
		txt_Medicamento.setBounds(151, 73, 230, 20);
		panel.add(txt_Medicamento);
		txt_Medicamento.setColumns(10);
		
		//modelo.addColumn("Numero");
        modelo.addColumn("Medicamento");
        modelo.addColumn("Cantidad");
       	
        String sql="SELECT * FROM medicamento ";
	    String datos[] = new String[10];
	    Statement st;
	     try{
	          st = reg.createStatement();
	          ResultSet rs = st.executeQuery(sql);
	          while(rs.next()){
	         datos[0]=rs.getString(2);
	         datos[1]=rs.getString(3);
	       //  datos[2]=rs.getString(1);

	         modelo.addRow(datos);
	     }    
	   
	    scrollPane.setBounds(39, 133, 510, 205);
	     panel.add(scrollPane);
	     scrollPane.setViewportView(table_bm);
	     table_bm.setModel(new DefaultTableModel(
	     	new Object[][] {			     		
	     	},
	     	new String[] {
	     		//"Numero",
	     		"Medicamento","Cantidad"
	     	}
	     ));
	     table_bm.setModel(modelo);
	     table_bm.setModel(modelo);
	          
	} catch (SQLException ex){
	Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
	}   
	     popupMenu = new JPopupMenu();
			addPopup(table_bm, popupMenu);
			
			JMenuItem mntmEnvia = new JMenuItem("Envia");
			mntmEnvia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					DefaultTableModel model=(DefaultTableModel) RecetaMedica.table_rm.getModel();
		     		int select =table_bm.getSelectedRow();
		     		String []Datos = new String[2];
		     		Datos[0]=table_bm.getValueAt(select, 0).toString();
		     		Datos[1]=table_bm.getValueAt(select, 1).toString();
		     		model.addRow(Datos);
		     		RecetaMedica.table_rm.setModel(model);
					
				}
			});
			popupMenu.add(mntmEnvia);
        
	     
		JButton btn_exit = new JButton(Constantes.STR_EXIT);
		btn_exit.setMnemonic('x');
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setBounds(495, 11, 89, 23);
		panel.add(btn_exit);
		
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				String dato = txt_Medicamento.getText(); 
		        String sql="SELECT * FROM medicamento WHERE Medicamento='"+dato+"'";
			    String datos[] = new String[10];
			    Statement st;
			     try{
			          st = reg.createStatement();
			          ResultSet rs = st.executeQuery(sql);
			          while(rs.next()){
			         datos[0]=rs.getString(2);
			         datos[1]=rs.getString(3);
			       //  datos[2]=rs.getString(1);

			         modelo.addRow(datos);
			     }    
			    scrollPane.setBounds(39, 133, 510, 205);
			     panel.add(scrollPane);
			     scrollPane.setViewportView(table_bm);
			     table_bm.setModel(new DefaultTableModel(
			     	new Object[][] {			     		
			     	},
			     	new String[] {
			     		//"Numero",
			     		"Medicamento","Cantidad"
			     	}
			     ));
			     table_bm.setModel(modelo);
			     table_bm.setModel(modelo);
			          
			} catch (SQLException ex){
			Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
			}   
								
			}
		});
		btn_buscar.setBounds(391, 72, 107, 23);
		panel.add(btn_buscar);
		
		JRadioButton M_Tabla = new JRadioButton("Mostrar tabla");
		M_Tabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = 1;
				if (seleccion==1) {
					limpiar();
					String sql="SELECT * FROM medicamento ";
				    String datos[] = new String[10];
				    Statement st;
				     try{
				          st = reg.createStatement();
				          ResultSet rs = st.executeQuery(sql);
				          while(rs.next()){
				         datos[0]=rs.getString(2);
				         datos[1]=rs.getString(3);
				         //datos[2]=rs.getString(1);

				         modelo.addRow(datos);
				     }    
				   
				    scrollPane.setBounds(39, 133, 510, 205);
				     panel.add(scrollPane);
				     scrollPane.setViewportView(table_bm);
				     table_bm.setModel(new DefaultTableModel(
				     	new Object[][] {			     		
				     	},
				     	new String[] {
				     		//"Numero",
				     		"Medicamento","Cantidad"
				     	}
				     ));
				     table_bm.setModel(modelo);
				     table_bm.setModel(modelo);
				          
				} catch (SQLException ex){
				Logger.getLogger(BusquedaMedic.class.getName()).log(Level.SEVERE,null,ex);
				} 
					M_Tabla.setSelected(false);
				}
			}
		});
		M_Tabla.setOpaque(false);
		M_Tabla.setHorizontalAlignment(SwingConstants.CENTER);
		M_Tabla.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		M_Tabla.setBounds(391, 103, 107, 23);
		panel.add(M_Tabla);
		
		
		
	}
	
	public void limpiar() {
		int fila=table_bm.getRowCount();
		for (int i=fila-1; i >= 0; i--) {
			modelo.removeRow(i);
			
		}
	}
	
	
	private void setIcon(Image image) {
	}
	ConexionSql cos=new ConexionSql();
    Connection reg = cos.conectar();
    
	JScrollPane scrollPane = new JScrollPane();
	JTable table_bm = new JTable();
	DefaultTableModel modelo = new DefaultTableModel();
	private JPopupMenu popupMenu;
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
 