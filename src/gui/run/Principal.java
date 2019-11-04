/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.run;

import datos.jdbc.ColasDaoJDBC;
import datos.jdbc.Conexion;
import datos.jdbc.MetodosDao;
import datos.jdbc.MetodosDaoJDBC;
import datos.jdbc.VentaJDBC;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import objetos.dto.ClienteColaDto;
import objetos.dto.VentaDTO;
import procesos.thread.EncargadoBStockMinimoThread;
import procesos.thread.EncargadoEncolarMasClientes;
import procesos.thread.EncargadoSurtirThread;
import procesos.thread.EncargadoVenderThread;
import procesos.thread.EncargadocolaThread;

/**
 *
 * @author Duvan Botello
 */
public class Principal extends javax.swing.JFrame {

    public static boolean StockMinimo = false;
    public boolean Simulacion = true;

    //TABLAS
    DefaultTableModel tb1_cola = new DefaultTableModel();
    DefaultTableModel tb2_cola = new DefaultTableModel();
    DefaultTableModel tb3_cola = new DefaultTableModel();
    //TABLAS

    //COLAS
    public static LinkedList<ClienteColaDto> cola1 = new LinkedList<ClienteColaDto>();
    public static LinkedList<ClienteColaDto> cola2 = new LinkedList<ClienteColaDto>();
    public static LinkedList<ClienteColaDto> cola3 = new LinkedList<ClienteColaDto>();
    //COLAS

    //ESTADO CAJAS
    public static boolean EstadoCaja1 = true;
    public static boolean EstadoCaja2 = true;
    public static boolean EstadoCaja3 = true;
    //ESTADO CAJAS

    MetodosDao MetodosDao = new MetodosDaoJDBC();
    ColasDaoJDBC colasDao = new ColasDaoJDBC();
    EncargadocolaThread HiloCajas;
    EncargadoVenderThread HijoVenderCaja1;
    EncargadoBStockMinimoThread HijoStockMinimo;
    EncargadoSurtirThread HiloSutir;
    EncargadoEncolarMasClientes hiloEncolar;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();

        tablas(tb1_cola, tb1);
        tablas(tb2_cola, tb2);
        tablas(tb3_cola, tb3);
        inicializar2();
    }

    private void inicializar2() {
        HiloCajas = new EncargadocolaThread("HiliCajas1", estado1,
                estado2, estado3, cliendoc1, clientenomb1, cliendoc2, clientenomb2,
                cliendoc3, clientenomb3, tb1, tb2,
                tb3, tb1_cola, tb2_cola, tb3_cola, tregistro);
        HijoVenderCaja1 = new EncargadoVenderThread("Hilo2", estado1,
                estado2, estado3, cliendoc1, clientenomb1, cliendoc2, clientenomb2,
                cliendoc3, clientenomb3, edoc1, edoc2, edoc3, ic1, ic2, ic3, tregistro);
        HijoStockMinimo = new EncargadoBStockMinimoThread(labelMensaje);
        HiloSutir = new EncargadoSurtirThread(tregistro);
        hiloEncolar = new EncargadoEncolarMasClientes();
    }

    private void tablas(DefaultTableModel tb, JTable tb1) {
        this.setLocationRelativeTo(null);
        tb.addColumn("#Cola");
        tb.addColumn("#Doc");
        tb.addColumn("Nombre");
        tb1.setModel(tb);
    }

    public void ActualizarEstadosCajas() {
        if (EstadoCaja1 == true) {
            estado1.setBackground(Color.GREEN);
            estado1.setText("DISPONIBLE");
        } else {
            estado1.setBackground(Color.RED);
            estado1.setText("NO DISPONIBLE");
        }

        if (EstadoCaja2 == true) {
            estado2.setBackground(Color.GREEN);
            estado2.setText("DISPONIBLE");
        } else {
            estado2.setBackground(Color.RED);
            estado2.setText("NO DISPONIBLE");
        }

        if (EstadoCaja3 == true) {
            estado3.setBackground(Color.GREEN);
            estado3.setText("DISPONIBLE");
        } else {
            estado3.setBackground(Color.RED);
            estado3.setText("NO DISPONIBLE");
        }
    }

    public void MostrarTabla() {

        tb1_cola.setRowCount(0);
        tb2_cola.setRowCount(0);
        tb3_cola.setRowCount(0);

        Object file[] = new Object[tb1.getColumnCount()];
        Object file1[] = new Object[tb2.getColumnCount()];
        Object file2[] = new Object[tb2.getColumnCount()];
        int cont = 1;

        for (ClienteColaDto f : cola1) {
            file[0] = cont;
            cont++;
            file[1] = f.getDoc_cliente();
            file[2] = f.getNombre() + " " + f.getApellido();
            tb1_cola.addRow(file);
        }

        cont = 1;
        for (ClienteColaDto d : cola2) {
            file1[0] = cont;
            cont++;
            file1[1] = d.getDoc_cliente();
            file1[2] = d.getNombre() + " " + d.getApellido();
            tb2_cola.addRow(file1);
        }

        cont = 1;
        for (ClienteColaDto g : cola3) {
            file2[0] = cont;
            cont++;
            file2[1] = g.getDoc_cliente();
            file2[2] = g.getNombre() + " " + g.getApellido();
            tb3_cola.addRow(file2);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelMensaje = new javax.swing.JLabel();
        PanelCaja1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        en1 = new javax.swing.JTextField();
        clientenomb1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        estado1 = new javax.swing.JTextField();
        c1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ic1 = new javax.swing.JTextField();
        edoc1 = new javax.swing.JTextField();
        cliendoc1 = new javax.swing.JTextField();
        PanelCaja2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        estado2 = new javax.swing.JTextField();
        c2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        ic2 = new javax.swing.JTextField();
        edoc2 = new javax.swing.JTextField();
        en2 = new javax.swing.JTextField();
        cliendoc2 = new javax.swing.JTextField();
        clientenomb2 = new javax.swing.JTextField();
        PanelCaja3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        estado3 = new javax.swing.JTextField();
        c3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        ic3 = new javax.swing.JTextField();
        edoc3 = new javax.swing.JTextField();
        en3 = new javax.swing.JTextField();
        cliendoc3 = new javax.swing.JTextField();
        clientenomb3 = new javax.swing.JTextField();
        panelColas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb3 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tregistro = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuConsultas = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        MenuSimulacion = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRINCIPAL");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SIMULADOR"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMensaje.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(labelMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 400, 50));

        PanelCaja1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelCaja1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel1.setText("Encargado de Caja:");
        PanelCaja1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        en1.setEditable(false);
        en1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        en1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        en1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en1ActionPerformed(evt);
            }
        });
        PanelCaja1.add(en1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 150, -1));

        clientenomb1.setEditable(false);
        clientenomb1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja1.add(clientenomb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, -1));

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel2.setText("Cliente:");
        PanelCaja1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel3.setText("Estado Caja:");
        PanelCaja1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        estado1.setEditable(false);
        estado1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja1.add(estado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, -1));

        c1.setEditable(false);
        c1.setBackground(new java.awt.Color(204, 204, 204));
        c1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja1.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 58, -1));

        jLabel10.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel10.setText("CAJA:");
        PanelCaja1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel13.setText("ID_CAJA");
        PanelCaja1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        ic1.setEditable(false);
        ic1.setBackground(new java.awt.Color(204, 204, 204));
        ic1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja1.add(ic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 58, -1));

        edoc1.setEditable(false);
        edoc1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edoc1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edoc1ActionPerformed(evt);
            }
        });
        PanelCaja1.add(edoc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        cliendoc1.setEditable(false);
        cliendoc1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja1.add(cliendoc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));

        jPanel1.add(PanelCaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 190));

        PanelCaja2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelCaja2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel16.setText("Encargado de Caja:");
        PanelCaja2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel17.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel17.setText("Cliente:");
        PanelCaja2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel18.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel18.setText("Estado Caja:");
        PanelCaja2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        estado2.setEditable(false);
        estado2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja2.add(estado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, -1));

        c2.setEditable(false);
        c2.setBackground(new java.awt.Color(204, 204, 204));
        c2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja2.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 58, -1));

        jLabel19.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel19.setText("CAJA:");
        PanelCaja2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel20.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel20.setText("ID_CAJA");
        PanelCaja2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        ic2.setEditable(false);
        ic2.setBackground(new java.awt.Color(204, 204, 204));
        ic2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja2.add(ic2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 58, -1));

        edoc2.setEditable(false);
        edoc2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edoc2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edoc2ActionPerformed(evt);
            }
        });
        PanelCaja2.add(edoc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        en2.setEditable(false);
        en2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        en2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        en2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en2ActionPerformed(evt);
            }
        });
        PanelCaja2.add(en2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 150, -1));

        cliendoc2.setEditable(false);
        cliendoc2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja2.add(cliendoc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));

        clientenomb2.setEditable(false);
        clientenomb2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja2.add(clientenomb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, -1));

        jPanel1.add(PanelCaja2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 280, 190));

        PanelCaja3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelCaja3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel21.setText("Encargado de Caja:");
        PanelCaja3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel22.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel22.setText("Cliente:");
        PanelCaja3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel23.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel23.setText("Estado Caja:");
        PanelCaja3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        estado3.setEditable(false);
        estado3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja3.add(estado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, -1));

        c3.setEditable(false);
        c3.setBackground(new java.awt.Color(204, 204, 204));
        c3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja3.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 58, -1));

        jLabel24.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel24.setText("CAJA:");
        PanelCaja3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel25.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel25.setText("ID_CAJA");
        PanelCaja3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        ic3.setEditable(false);
        ic3.setBackground(new java.awt.Color(204, 204, 204));
        ic3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja3.add(ic3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 58, -1));

        edoc3.setEditable(false);
        edoc3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edoc3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edoc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edoc3ActionPerformed(evt);
            }
        });
        PanelCaja3.add(edoc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        en3.setEditable(false);
        en3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        en3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        en3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en3ActionPerformed(evt);
            }
        });
        PanelCaja3.add(en3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 150, -1));

        cliendoc3.setEditable(false);
        cliendoc3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja3.add(cliendoc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));

        clientenomb3.setEditable(false);
        clientenomb3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelCaja3.add(clientenomb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, -1));

        jPanel1.add(PanelCaja3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 280, 190));

        panelColas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "COLAS"));
        panelColas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tb1);

        panelColas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 270, 140));

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel4.setText("Clientes en Cola");
        panelColas.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tb2);

        panelColas.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 270, 140));

        jLabel5.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel5.setText("Clientes en Cola");
        panelColas.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        tb3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tb3);

        panelColas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 270, 140));

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel6.setText("Clientes en Cola");
        panelColas.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

        jPanel1.add(panelColas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 870, 190));

        tregistro.setEditable(false);
        tregistro.setBackground(new java.awt.Color(102, 102, 102));
        tregistro.setColumns(20);
        tregistro.setForeground(java.awt.Color.yellow);
        tregistro.setRows(5);
        jScrollPane4.setViewportView(tregistro);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 410, 130));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Registro de Hilos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Duvan Jose Botello Ramirez");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, -1, -1));

        jLabel9.setFont(new java.awt.Font("Swis721 Blk BT", 1, 18)); // NOI18N
        jLabel9.setText("Manejo de Hilos");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Presentado a : Pedro Ariza");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, -1, -1));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel12.setText("Universidad De Pamplona");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 540, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/java.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 490, 80, 70));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/java.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 80, 70));

        MenuConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/consultas.png"))); // NOI18N
        MenuConsultas.setText("CONSULTAS");
        MenuConsultas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clientes.png"))); // NOI18N
        jMenuItem2.setText("Listado Clientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem2);
        MenuConsultas.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caja_2.png"))); // NOI18N
        jMenuItem3.setText("Ventas Por caja");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem3);
        MenuConsultas.add(jSeparator2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/v_caja.png"))); // NOI18N
        jMenuItem4.setText("Caja que más clientes atendió");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem4);
        MenuConsultas.add(jSeparator3);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/producto.png"))); // NOI18N
        jMenuItem5.setText("Productos más consumidos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem5);
        MenuConsultas.add(jSeparator4);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cliente_2.png"))); // NOI18N
        jMenuItem6.setText("Cliente que más consumió productos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem6);
        MenuConsultas.add(jSeparator5);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categoria.png"))); // NOI18N
        jMenuItem7.setText("Producto más consumido Por Categoria");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem7);
        MenuConsultas.add(jSeparator6);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vendio.png"))); // NOI18N
        jMenuItem8.setText("Categoria que más producto vendió en pesos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem8);

        jMenuBar1.add(MenuConsultas);

        MenuSimulacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simulador.png"))); // NOI18N
        MenuSimulacion.setText("SIMULACION");
        MenuSimulacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar.png"))); // NOI18N
        jMenuItem1.setText("INICIAR SIMULACION");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuSimulacion.add(jMenuItem1);
        MenuSimulacion.add(jSeparator7);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_NUMPAD5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop.png"))); // NOI18N
        jMenuItem9.setText("DETENER SIMULACION");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        MenuSimulacion.add(jMenuItem9);

        jMenuBar1.add(MenuSimulacion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            int numClientes = colasDao.numRegistros("clientes") - 1;
            int NumCola1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Total de Clientes Diponibles: " + numClientes
                    + "\n ¿Cuantos Clientes Desea Encolar En la Primera Fila.?"));
            int NumCola2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Total de Clientes Diponibles: " + (numClientes - NumCola1)
                    + "\n ¿Cuantos Clientes Desea Encolar En la Segunda Fila.?"));
            int NumCola3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Total de Clientes Diponibles: " + (numClientes - NumCola1 - NumCola2)
                    + "\n ¿Cuantos Clientes Desea Encolar En la Segunda Fila.?"));

            if (colasDao.llenarCola(cola1, cola2, cola3, NumCola1, NumCola2, NumCola3)) {
                ActualizarEstadosCajas();
                MetodosDao.AsignarCajaYEmpleado(c1, c2, c3, edoc1, edoc2, edoc3, en1, en2, en3, ic1, ic2, ic3);
                MostrarTabla();
                if (Simulacion == true) {
                    HiloCajas.start();
                    HijoVenderCaja1.start();
                    HijoStockMinimo.start();
                    HiloSutir.start();
                    hiloEncolar.start();
                } else {
                    HiloCajas.resume();
                    HijoVenderCaja1.resume();
                    HijoStockMinimo.resume();
                    HiloSutir.resume();
                    hiloEncolar.resume();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Error al Ingresar los Datos.");
        }


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void en1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_en1ActionPerformed

    private void edoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edoc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edoc1ActionPerformed

    private void edoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edoc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edoc2ActionPerformed

    private void en2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_en2ActionPerformed

    private void edoc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edoc3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edoc3ActionPerformed

    private void en3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_en3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/ReporteClientes.jasper";
        try {
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/ReporteXCajas.jasper";

        try {
            int id_cajaa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Id de Caja"));
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            @SuppressWarnings("unchecked")
            Map<String, Object> parametro = new HashMap();
            parametro.put("id_caja2", id_cajaa);
            JasperPrint jprint = JasperFillManager.fillReport(path, parametro, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Ingresar Dato");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/ReporteCajaMasClientes.jasper";
        try {
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/ReporteProductoMasVendido.jasper";
        try {
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/ReporteClienteMasConsumio.jasper";
        try {
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/ProductoMasConsumidoXCategoria.jasper";

        try {
            int id_cajaa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Id de la Categoria"));
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            @SuppressWarnings("unchecked")
            Map<String, Object> parametro = new HashMap();
            parametro.put("id_categoria", id_cajaa);
            JasperPrint jprint = JasperFillManager.fillReport(path, parametro, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Ingresar Dato");
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Conexion con = new Conexion();
        Connection conn = null;
        JasperReport reporte = null;
        String path = "src/reportes/CategoriaMasProductosVendioEnPesos.jasper";
        try {
            conn = con.getConnection();
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

        pararSimulacro();
        cola1.clear();
        cola2.clear();
        cola3.clear();
        Simulacion = false;
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void pararSimulacro() {
        estado2.setBackground(Color.white);
        estado3.setBackground(Color.white);
        estado1.setBackground(Color.white);
        StockMinimo = false;
        EstadoCaja1 = true;
        EstadoCaja2 = true;
        EstadoCaja3 = true;
        tb1_cola.setRowCount(0);
        tb2_cola.setRowCount(0);
        tb3_cola.setRowCount(0);
        c1.setText("");
        c2.setText("");
        c3.setText("");
        ic1.setText("");
        ic2.setText("");
        ic3.setText("");
        edoc1.setText("");
        edoc2.setText("");
        edoc3.setText("");
        en1.setText("");
        en2.setText("");
        en3.setText("");
        cliendoc1.setText("");
        cliendoc2.setText("");
        cliendoc3.setText("");
        clientenomb1.setText("");
        clientenomb2.setText("");
        clientenomb3.setText("");
        labelMensaje.setText("");
        estado1.setText("");
        estado2.setText("");
        estado3.setText("");
        tregistro.setText("");
        HiloCajas.suspend();
        HijoVenderCaja1.suspend();
        HijoStockMinimo.suspend();
        HiloSutir.suspend();
        hiloEncolar.suspend();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuConsultas;
    private javax.swing.JMenu MenuSimulacion;
    private javax.swing.JPanel PanelCaja1;
    private javax.swing.JPanel PanelCaja2;
    private javax.swing.JPanel PanelCaja3;
    private javax.swing.JTextField c1;
    private javax.swing.JTextField c2;
    private javax.swing.JTextField c3;
    public javax.swing.JTextField cliendoc1;
    public javax.swing.JTextField cliendoc2;
    public javax.swing.JTextField cliendoc3;
    public javax.swing.JTextField clientenomb1;
    public javax.swing.JTextField clientenomb2;
    public javax.swing.JTextField clientenomb3;
    private javax.swing.JTextField edoc1;
    private javax.swing.JTextField edoc2;
    private javax.swing.JTextField edoc3;
    private javax.swing.JTextField en1;
    private javax.swing.JTextField en2;
    private javax.swing.JTextField en3;
    public javax.swing.JTextField estado1;
    private javax.swing.JTextField estado2;
    private javax.swing.JTextField estado3;
    private javax.swing.JTextField ic1;
    private javax.swing.JTextField ic2;
    private javax.swing.JTextField ic3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JLabel labelMensaje;
    private javax.swing.JPanel panelColas;
    private javax.swing.JTable tb1;
    private javax.swing.JTable tb2;
    private javax.swing.JTable tb3;
    private javax.swing.JTextArea tregistro;
    // End of variables declaration//GEN-END:variables
}
