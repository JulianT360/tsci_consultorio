package interfaz;

import util.CategoriasEnum;
import util.Constantes;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static interfaz.InicioSesion.usuarioMedico;
import static interfaz.InicioSesion.usuarioPersona;

public class Menu extends JFrame {

    private JPanel panel;
    public static javax.swing.JDesktopPane esc_dp;

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
     * Constructor de la clase
     */
    public Menu() {
        initMenu(); //Inicializa las opciones del menu
    }

    /**
     * Create the frame.
     */
    private void initMenu() {
        // Opciones del menu
        JMenuBar menuBar = new JMenuBar();
        // Se agregan las opciones del sistema
        JMenu menuSistema = new JMenu(Constantes.MENU_SISTEMA);

        // Menu sistema, opcion Configuracion
        JMenuItem menuItemConfiguracion = new JMenuItem(Constantes.MENU_ITEM_CONFIG);
        menuItemConfiguracion.setMnemonic(KeyEvent.VK_1);
        menuItemConfiguracion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Config l = new Config();
                esc_dp.add(l);
                Dimension desktopSize = esc_dp.getSize();
                l.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                l.show();
            }
        });
        menuSistema.add(menuItemConfiguracion);

        // Menu sistema, opcion Ayuda
        JMenuItem menuItemAyuda = new JMenuItem(Constantes.MENU_ITEM_AYUDA);
        menuItemAyuda.setMnemonic(KeyEvent.VK_2);
        menuItemAyuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Apoyo l = new Apoyo();
                esc_dp.add(l);
                Dimension desktopSize = esc_dp.getSize();
                l.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                l.show();

            }
        });
        menuSistema.add(menuItemAyuda);

        // Menu Sistema, opcion Salir
        JMenuItem menuItemSalir = new JMenuItem(Constantes.STR_EXIT);
        menuItemSalir.setMnemonic(KeyEvent.VK_3);
        menuItemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null,
                        "Salir del sistema ",
                        "Alerta!",
                        JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        menuSistema.add(menuItemSalir);

        menuBar.add(menuSistema);

        // Menu generar
        JMenu menuGenerar = new JMenu(Constantes.MENU_GENERAR);

        // Menu generar, opcion paciente
        JMenuItem menuItemGenerarPaciente = new JMenuItem(Constantes.MENU_ITEM_PACIENTE);
        menuItemGenerarPaciente.setMnemonic(KeyEvent.VK_4);
        menuItemGenerarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pacientes l = new Pacientes();
                esc_dp.add(l);
                Dimension desktopSize = esc_dp.getSize();
                l.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                l.show();
            }
        });
        menuGenerar.add(menuItemGenerarPaciente);

        // Menu generar, opcion cita medica
        JMenuItem menuItemCitaMedica = new JMenuItem(Constantes.MENU_ITEM_CITA_MEDICA);
        menuItemCitaMedica.setMnemonic(KeyEvent.VK_5);//7
        menuItemCitaMedica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rconsulta rc = new Rconsulta();
                esc_dp.add(rc);
                Dimension desktopSize = esc_dp.getSize();
                rc.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                rc.show();
            }
        });
        menuGenerar.add(menuItemCitaMedica);

        // Menu generar, opcion consulta
        JMenuItem menuItemConsulta = new JMenuItem(Constantes.MENU_CONSULTA);
        menuItemConsulta.setMnemonic(KeyEvent.VK_6);//5
        menuItemConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, catego);
                Consulta l = new Consulta();
                esc_dp.add(l);
                Dimension desktopSize = esc_dp.getSize();
                l.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                l.show();
            }
        });
        menuGenerar.add(menuItemConsulta);

        menuBar.add(menuGenerar);

        // Menu registro
        JMenu menuRegistro = new JMenu(Constantes.MENU_REGISTRO);

        // Menu registro, opcion medicamento
        JMenuItem menuItemMedicamento = new JMenuItem(Constantes.MENU_MEDICAMENTO);
        menuItemMedicamento.setMnemonic(KeyEvent.VK_7);//8
        menuRegistro.add(menuItemMedicamento);
        menuItemMedicamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Medicamentos l = new Medicamentos();
                esc_dp.add(l);
                Dimension desktopSize = esc_dp.getSize();
                l.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                l.show();
            }
        });

        // Menu registro, opcion expedientes
        JMenuItem menuItemExpedientes = new JMenuItem(Constantes.MENU_EXPEDIENTES);
        menuItemExpedientes.setMnemonic(KeyEvent.VK_8);//6
        menuItemExpedientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Expedientes l = new Expedientes();
                esc_dp.add(l);
                Dimension desktopSize = esc_dp.getSize();
                l.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                l.show();
            }
        });
        menuRegistro.add(menuItemExpedientes);

        // Menu registro, opcion paciente
        JMenuItem menuItemRegistroPaciente = new JMenuItem(Constantes.MENU_ITEM_PACIENTE);
        menuItemRegistroPaciente.setMnemonic(KeyEvent.VK_9);
        menuItemRegistroPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrosPacientes rp = new RegistrosPacientes();
                Menu.esc_dp.add(rp);
                Dimension desktopSize = Menu.esc_dp.getSize();
                rp.setLocation(Menu.esc_dp.WIDTH, Menu.esc_dp.HEIGHT);
                rp.show();
            }
        });
        menuRegistro.add(menuItemRegistroPaciente);

        // Menu registro, opcion citas
        JMenuItem menuItemRegistroCitas = new JMenuItem(Constantes.MENU_CITAS);
        menuItemRegistroCitas.setMnemonic(KeyEvent.VK_A);
        menuItemRegistroCitas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistroCitas rc = new RegistroCitas();
                Menu.esc_dp.add(rc);
                Dimension desktopSize = Menu.esc_dp.getSize();
                rc.setLocation(Menu.esc_dp.WIDTH, Menu.esc_dp.HEIGHT);
                rc.show();
            }
        });
        menuRegistro.add(menuItemRegistroCitas);

        menuBar.add(menuRegistro);

        // Menu procesar
        JMenu menuProcesar = new JMenu(Constantes.MENU_PROCESAR);

        // Menu procesar, opcion reporte
        JMenuItem menuItemReporte = new JMenuItem(Constantes.MENU_REPORTE);
        menuItemReporte.setMnemonic(KeyEvent.VK_B);//9
        menuItemReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reporte m = new Reporte();
                esc_dp.add(m);
                Dimension desktopSize = esc_dp.getSize();
                m.setLocation(esc_dp.WIDTH, esc_dp.HEIGHT);
                m.show();
            }
        });
        menuProcesar.add(menuItemReporte);

        menuBar.add(menuProcesar);

        setJMenuBar(menuBar);

        // Se agregan las demas reglas de la pantalla

        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(InicioSesion.class.getResource("/img/estetoscopio.png")));
        setTitle(Constantes.TITLE_CONSULTORIO);
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
        nom_doc.setText(usuarioPersona.getNombre()
                + " " + usuarioPersona.getApPaterno()
                + " " + usuarioPersona.getApMaterno());
        panelm.add(nom_doc);

        JComboBox comboBox_catego = new JComboBox();
        comboBox_catego.setEnabled(false);
        comboBox_catego.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        comboBox_catego.setBounds(983, 2, 220, 20);

        comboBox_catego.setModel(new DefaultComboBoxModel(new String[]{
                Constantes.STR_DEFAULT_SELECCIONA,
                CategoriasEnum.ADMINISTRADOR.toString(),
                CategoriasEnum.DOCTOR.toString(),
                CategoriasEnum.SECRETARIA.toString()}));

        comboBox_catego.setSelectedItem(usuarioMedico.getCategoria());
        panelm.add(comboBox_catego);

        // Menu para secretaria
        if (CategoriasEnum.SECRETARIA.toString().equals(comboBox_catego.getSelectedItem())) {
            menuItemConsulta.setVisible(false);
            menuItemExpedientes.setVisible(false);
            menuItemMedicamento.setVisible(false);
        }

        // Menu para doctor
        if (CategoriasEnum.DOCTOR.toString().equals(comboBox_catego.getSelectedItem())) {
            JLabel lblDr = new JLabel(Constantes.STR_PREFIJO_DR);
            lblDr.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
            lblDr.setHorizontalAlignment(SwingConstants.CENTER);
            lblDr.setBounds(218, -2, 50, 25);
            panelm.add(lblDr);
        }
    }
}