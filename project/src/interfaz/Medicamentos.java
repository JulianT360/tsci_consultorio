package interfaz;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.mysql.cj.util.StringUtils;
import conexion.ConexionSql;
import domain.entity.Medicamento;
import domain.service.MedicamentoService;
import generapdf.GeneraPDF;
import util.Constantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Medicamentos extends JInternalFrame {

    private MedicamentoService medicamentoService;

    GeneraPDF PDF = new GeneraPDF();
    private Rectangle ArraySizePage[] = {PageSize.LEGAL, PageSize.LETTER};
    public Rectangle SizePage = null;
    String nameSizePage[] = {"Legal", "Carta"};
    private File ruta_destino = null;

    ConexionSql cos = new ConexionSql();
    Connection reg = cos.conectar();
    private JTextField numero;
    private JTextField num_eliminar;
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();

    Panel panel = new Panel("/img/fondo.jpg");
    private JTextField txt_Medicamento;
    private JTextField txt_medic;
    private JTextField txt_cant;
    public static int id_numero = 0;
    int eleccion = 1;
    int seleccion = 0;

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

    /**
     * Constructor.
     */
    public Medicamentos() {

        this.medicamentoService = new MedicamentoService();

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

        JLabel lbl_Medicamento = new JLabel(Constantes.LBL_MEDICAMENTO);
        lbl_Medicamento.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_Medicamento.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbl_Medicamento.setBounds(666, 95, 96, 14);
        panel.add(lbl_Medicamento);

        txt_Medicamento = new JTextField();
        txt_Medicamento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int c = (int) e.getKeyChar();
                if (c > 47 && c < 58) {
                    e.setKeyChar((char) KeyEvent.VK_CLEAR);
                }
            }
        });
        txt_Medicamento.setBounds(772, 92, 230, 20);
        panel.add(txt_Medicamento);
        txt_Medicamento.setColumns(10);

        // Se crea la tabla de medicamento
        modelo.addColumn(Constantes.LBL_NUMERO);
        modelo.addColumn(Constantes.LBL_MEDICAMENTO);
        modelo.addColumn(Constantes.LBL_CANTIDAD);

        // Se consultan los medicamentos

        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos = medicamentoService.getAll();
        if (!medicamentos.isEmpty()) {
            for(Medicamento medicamento : medicamentos) {
                modelo.addRow(new String[] {
                        String.valueOf(medicamento.getId()),
                        medicamento.getNombre(),
                        String.valueOf(medicamento.getCantidad())
                });
            }
        }

        scrollPane.setBounds(676, 151, 534, 300);
        panel.add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleccion = table.rowAtPoint(e.getPoint());
                num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
                txt_medic.setText(String.valueOf(table.getValueAt(seleccion, 1)));
                txt_cant.setText(String.valueOf(table.getValueAt(seleccion, 2)));
            }
        });

        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        Constantes.LBL_NUMERO,
                        Constantes.LBL_MEDICAMENTO,
                        Constantes.LBL_CANTIDAD
                }
        ));
        table.setModel(modelo);
        table.setModel(modelo);

        JRadioButton M_Tabla = new JRadioButton(Constantes.LBL_MOSTRAR_TABLA);
        M_Tabla.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                seleccion = 1;
                if (seleccion == 1) {
                    limpiarTabla();
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

        JLabel lbl_medic = new JLabel(Constantes.LBL_MEDICAMENTO);
        lbl_medic.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_medic.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbl_medic.setBounds(150, 94, 96, 14);
        panel.add(lbl_medic);

        txt_medic = new JTextField();
        txt_medic.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int c = (int) e.getKeyChar();
                if (c > 47 && c < 58) {
                    e.setKeyChar((char) KeyEvent.VK_CLEAR);
                }
            }
        });
        txt_medic.setColumns(10);
        txt_medic.setBounds(256, 91, 230, 20);
        panel.add(txt_medic);

        JLabel lblCantidad = new JLabel(Constantes.LBL_CANTIDAD);
        lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCantidad.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblCantidad.setBounds(150, 151, 96, 14);
        panel.add(lblCantidad);

        txt_cant = new JTextField();
        txt_cant.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c < '0' || c > '9') e.consume();
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

        JButton btn_buscar = new JButton(Constantes.LBL_BUSCAR);
        btn_buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarTabla();
                busqueda();
            }
        });

        btn_buscar.setBounds(1012, 91, 107, 23);
        panel.add(btn_buscar);

        JButton btn_agregar = new JButton(Constantes.LBL_AGREGAR);
        btn_agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                guardar();
            }
        });
        btn_agregar.setBounds(397, 201, 89, 23);
        panel.add(btn_agregar);

        JButton btneliminar = new JButton(Constantes.LBL_ELIMINAR);
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
        try {
            List<Medicamento> medicamentos = medicamentoService.getAll();

            if (Objects.nonNull(medicamentos) && !medicamentos.isEmpty()) {
                for(Medicamento medicamento : medicamentos) {
                    modelo.addRow(new String[]{
                            String.valueOf(medicamento.getId()),
                            medicamento.getNombre(),
                            String.valueOf(medicamento.getCantidad())
                    });
                }

                scrollPane.setBounds(676, 151, 534, 300);
                panel.add(scrollPane);
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int seleccion = table.rowAtPoint(e.getPoint());
                        num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
                        txt_medic.setText(String.valueOf(table.getValueAt(seleccion, 1)));
                        txt_cant.setText(String.valueOf(table.getValueAt(seleccion, 2)));
                    }
                });


                scrollPane.setViewportView(table);
                table.setModel(new DefaultTableModel(
                        new Object[][]{

                        },
                        new String[]{
                                "Numero", "Medicamento", "Cantidad"
                        }
                ));
                table.setModel(modelo);
                table.setModel(modelo);
            } else {
                throw new Exception("No se encontraron medicamentos registrados");
            }

        } catch (Exception ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void busqueda() {

        String nombreMedicamento = txt_Medicamento.getText().trim();
        Medicamento medicamento = medicamentoService.getByNombre(nombreMedicamento);

        if (Objects.nonNull(medicamento)) {
            modelo.addRow(new String[]{
                    String.valueOf(medicamento.getId()),
                    medicamento.getNombre(),
                    String.valueOf(medicamento.getCantidad())
            });

            scrollPane.setBounds(676, 151, 534, 300);
            panel.add(scrollPane);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int seleccion = table.rowAtPoint(e.getPoint());
                    num_eliminar.setText(String.valueOf(table.getValueAt(seleccion, 0)));
                    txt_medic.setText(String.valueOf(table.getValueAt(seleccion, 1)));
                    txt_cant.setText(String.valueOf(table.getValueAt(seleccion, 2)));
                }
            });

            scrollPane.setViewportView(table);
            table.setModel(new DefaultTableModel(
                    new Object[][]{

                    },
                    new String[]{
                            Constantes.LBL_NUMERO,
                            Constantes.LBL_MEDICAMENTO,
                            Constantes.LBL_CANTIDAD
                    }
            ));
            table.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el medicamento");
        }
    }

    public void eliminar() {

        int seleccion = table.getSelectedRow();

        if(seleccion >= 0) {
            Long idMedicamento = Long.valueOf(num_eliminar.getText().trim());
            medicamentoService.delete(idMedicamento);

            JOptionPane.showMessageDialog(null, "Dato Eliminado");

            modelo.removeRow(seleccion);
            limpiarAddMedicamento();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona el dato a eliminar");
        }
    }

    public void guardar() {
        try {
            Medicamento medicamento = new Medicamento();
            medicamento.setNombre(StringUtils.isEmptyOrWhitespaceOnly(txt_medic.getText()) ? null : txt_medic.getText().trim());
            medicamento.setCantidad(StringUtils.isEmptyOrWhitespaceOnly(txt_cant.getText()) ? null : Integer.parseInt(txt_cant.getText().trim()));

            if (Objects.isNull(medicamento.getNombre()) || Objects.isNull(medicamento.getCantidad())) {
                JOptionPane.showMessageDialog(null, "Error datos no validos ");
                throw new Exception("Error datos no validos");
            }

            Long idMedicamento = medicamentoService.save(medicamento);
            medicamento.setId(idMedicamento);

            JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");

            modelo.addRow(new String[]{
                    String.valueOf(medicamento.getId()),
                    medicamento.getNombre(),
                    String.valueOf(medicamento.getCantidad())
            });

            txt_medic.setText("");
            txt_cant.setText("");
        } catch (Exception e) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, e.getLocalizedMessage());
        }
    }

    private void limpiarTabla() {
        int fila = table.getRowCount();
        for (int i = fila - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void limpiarAddMedicamento() {
        txt_medic.setText("");
        txt_cant.setText("");
        num_eliminar.setText("");
    }

    private void setIcon(Image image) {

    }
}
