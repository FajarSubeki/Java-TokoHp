/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content_kasir;

import com.sun.glass.events.KeyEvent;
import inc.config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fajarsubeki
 */
public class data_hp extends javax.swing.JFrame {
    
    Connection conn = config.Conn();
    Statement st;
    PreparedStatement pr;
    ResultSet rs;
    
    private void tampil(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("NO");;
        model.addColumn("ID");
        model.addColumn("Nama HP");
        model.addColumn("Sisop");
        model.addColumn("Ukuran");
        model.addColumn("RAM");
        model.addColumn("ROM");
        model.addColumn("Warna");
        model.addColumn("Baterai");
        model.addColumn("Kamera");
        model.addColumn("Tipe Slot");
        model.addColumn("Jaringan");
        model.addColumn("Harga Pokok");
        model.addColumn("Harga Jual");
        model.addColumn("PPN");
        model.addColumn("Diskon");
        model.addColumn("Stok");
        
        try{
            String sql = "SELECT * FROM tb_hp";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
            int no = 0;
            while(rs.next()){
                no++;
                model.addRow(new Object[]{
                    no, rs.getString("id_hp"), rs.getString("nama_hp"), rs.getString("sisop"), rs.getString("ukuran"), rs.getString("ram")
                        , rs.getString("rom"), rs.getString("warna"), rs.getString("kapasitas_baterai"), rs.getString("rear_kamera")
                        , rs.getString("tipe_slot"), rs.getString("jaringan"), rs.getString("harga_pokok"), rs.getString("harga_jual")
                        , rs.getString("ppn"), rs.getString("diskon"), rs.getString("stok")
                });
            }
            tdata.setModel(model);
        } catch ( Exception e) {    
            System.out.println(e.getMessage());
        }
    }
    
    
    public void setAttributes(){
        rad_3g.setActionCommand("3G");
        rad_4g.setActionCommand("4G");
    }

    /**
     * Creates new form data_hpp
     */
    public data_hp() {
        initComponents();
        setAttributes();
        kode();
        tppn.setEnabled(false);
        tstok.setEnabled(false);
        tppn.setText("10");
        tstok.setText("0");
    }
    
    public void kode(){
        try {
            String sql = "SELECT * FROM tb_hp ORDER BY id_hp DESC";
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                String kode_otomatis = rs.getString("id_hp").substring(1);
                String an = ""+(Integer.parseInt(kode_otomatis)+1);
                String nol = "";
                if (an.length() == 1) {
                    nol = "0000";
                }else if(an.length() == 2){
                    nol = "000";
                }else if(an.length() == 3){
                    nol = "00";
                }else if(an.length() == 4){
                    nol = "0";
                }else if(an.length() == 5){
                    nol = "";
                }
                tid.setText("H"+nol+an);
            }else{
                tid.setText("H00001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void kosong(){
        tid.setText("");
        thp.setText("");
        tsisop.setText("");
        tukuran.setText("");
        tram.setText("");
        trom.setText("");
        cbwarna.setSelectedIndex(0);
        tkapasitas.setText("");
        tcamera.setText("");
        tslot.setText("");;
        bjaringan.clearSelection();
        tpokok.setText("");
        tjual.setText("");
        tppn.setText("10");
        tdiskon.setText("0");
        tid.setEnabled(false);
        tstok.setText("0");
        kode();
    }
    
    public void simpan(){
        try {
            st = conn.createStatement();
            st.executeUpdate("INSERT INTO tb_hp VALUES("
                    + "'"+tid.getText()+"',"
                    + "'"+thp.getText()+"',"
                    + "'"+tsisop.getText()+"',"
                    + "'"+tukuran.getText()+"',"
                    + "'"+tram.getText()+"',"
                    + "'"+trom.getText()+"',"
                    + "'"+cbwarna.getSelectedItem().toString()+"',"
                    + "'"+tkapasitas.getText()+"',"
                    + "'"+tcamera.getText()+"',"
                    + "'"+tslot.getText()+"',"
                    + "'"+bjaringan.getSelection().getActionCommand()+"',"
                    + "'"+tpokok.getText()+"',"
                    + "'"+tjual.getText()+"',"
                    + "'"+tppn.getText()+"',"
                    + "'"+tdiskon.getText()+"',"
                    + "'"+tstok.getText()+"',"
                    + "'0','0')");
            JOptionPane.showMessageDialog(null, "Data Tersimpan","Informasi", JOptionPane.INFORMATION_MESSAGE);
            tampil();
            kosong();
            kode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bjaringan = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        thp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tsisop = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tukuran = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tram = new javax.swing.JTextField();
        trom = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tkapasitas = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tcamera = new javax.swing.JTextField();
        tslot = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tpokok = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tjual = new javax.swing.JTextField();
        tppn = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tdiskon = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tdata = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbwarna = new javax.swing.JComboBox<>();
        rad_3g = new javax.swing.JRadioButton();
        rad_4g = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        tstok = new javax.swing.JTextField();
        brefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA HANDPHONE");

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID");

        tid.setEditable(false);
        tid.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tid.setEnabled(false);
        tid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidActionPerformed(evt);
            }
        });
        tid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tidKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama HP");

        thp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        thp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thpActionPerformed(evt);
            }
        });
        thp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thpKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Sistem Operasi");

        tsisop.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tsisop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsisopActionPerformed(evt);
            }
        });
        tsisop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tsisopKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ukuran");

        tukuran.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tukuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tukuranActionPerformed(evt);
            }
        });
        tukuran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tukuranKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("RAM");

        tram.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tramActionPerformed(evt);
            }
        });
        tram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tramKeyTyped(evt);
            }
        });

        trom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        trom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tromActionPerformed(evt);
            }
        });
        trom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tromKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ROM");

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Warna");

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Kapasitas Baterai");

        tkapasitas.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tkapasitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkapasitasActionPerformed(evt);
            }
        });
        tkapasitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tkapasitasKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Camera Rear");

        tcamera.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tcamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcameraActionPerformed(evt);
            }
        });
        tcamera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcameraKeyTyped(evt);
            }
        });

        tslot.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tslot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tslotActionPerformed(evt);
            }
        });
        tslot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tslotKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tipe Slot");

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Jaringan");

        tpokok.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tpokok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpokokActionPerformed(evt);
            }
        });
        tpokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tpokokKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Harga Pokok");

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Harga Jual");

        tjual.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjualActionPerformed(evt);
            }
        });
        tjual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjualKeyTyped(evt);
            }
        });

        tppn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tppn.setForeground(new java.awt.Color(255, 0, 0));
        tppn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tppn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tppnActionPerformed(evt);
            }
        });
        tppn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tppnKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("PPN");

        tdiskon.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tdiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdiskonActionPerformed(evt);
            }
        });
        tdiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tdiskonKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Diskon");

        bsimpan.setBackground(new java.awt.Color(255, 255, 255));
        bsimpan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bsimpan.setForeground(new java.awt.Color(0, 153, 153));
        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Ok_25px.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bhapus.setBackground(new java.awt.Color(255, 255, 255));
        bhapus.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bhapus.setForeground(new java.awt.Color(0, 153, 153));
        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete_25px.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bubah.setBackground(new java.awt.Color(255, 255, 255));
        bubah.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bubah.setForeground(new java.awt.Color(0, 153, 153));
        bubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit_25px.png"))); // NOI18N
        bubah.setText("Ubah");
        bubah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cari");

        tcari.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcariKeyTyped(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        tdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "ID", "Nama HP", "Sisop", "Ukuran", "RAM", "ROM", "Warna", "Baterai", "Kamera", "Tipe Slot", "Jaringan", "Harga Pokok", "Harga Jual", "PPN", "Diskon", "Stok"
            }
        ));
        tdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tdataMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tdataMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tdata);

        jLabel24.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Inches");

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("GB");

        jLabel26.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("GB");

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("mAh");

        jLabel28.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("MP");

        cbwarna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gold", "Black", "White", "Pink", "Blue", "Red", "Yellow", "Silver" }));
        cbwarna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbwarnaActionPerformed(evt);
            }
        });

        bjaringan.add(rad_3g);
        rad_3g.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        rad_3g.setForeground(new java.awt.Color(255, 255, 255));
        rad_3g.setText("3G");

        bjaringan.add(rad_4g);
        rad_4g.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        rad_4g.setForeground(new java.awt.Color(255, 255, 255));
        rad_4g.setText("4G");

        jLabel29.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Stok");

        tstok.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tstok.setForeground(new java.awt.Color(255, 0, 0));
        tstok.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tstokActionPerformed(evt);
            }
        });
        tstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tstokKeyTyped(evt);
            }
        });

        brefresh.setBackground(new java.awt.Color(255, 255, 255));
        brefresh.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        brefresh.setForeground(new java.awt.Color(0, 153, 153));
        brefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh_25px.png"))); // NOI18N
        brefresh.setText("Refresh");
        brefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        brefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thp, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tsisop, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tkapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbwarna, javax.swing.GroupLayout.Alignment.LEADING, 0, 101, Short.MAX_VALUE)
                                    .addComponent(trom, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tram, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tukuran, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26))))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel29))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rad_3g)
                                .addGap(18, 18, 18)
                                .addComponent(rad_4g))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(tcamera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                                .addComponent(tslot, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tpokok, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tjual, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel28)
                                            .addGap(34, 34, 34))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tppn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(18, 18, 18)
                                        .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(bhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(brefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcamera, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bubah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(brefresh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(thp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tslot, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tsisop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rad_3g)
                            .addComponent(rad_4g))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tram, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tpokok, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tjual, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tppn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(trom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbwarna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tkapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidActionPerformed

    private void tidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tidKeyTyped

    private void thpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thpActionPerformed

    private void thpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thpKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (thp.getText().length() > 30){
            JOptionPane.showMessageDialog(null, "Terlalu Panjang Memasukan Nama", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_thpKeyTyped

    private void tsisopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsisopActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tsisopActionPerformed

    private void tsisopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tsisopKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (tsisop.getText().length() > 35){
            JOptionPane.showMessageDialog(null, "Terlalu Panjang Memasukan Nama", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tsisopKeyTyped

    private void tukuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tukuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tukuranActionPerformed

    private void tukuranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tukuranKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) || (a==KeyEvent.VK_PERIOD) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tukuran.getText().length() > 2){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tukuranKeyTyped

    private void tramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tramActionPerformed

    private void tramKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tramKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tram.getText().length() >= 3){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tramKeyTyped

    private void tromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tromActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tromActionPerformed

    private void tromKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tromKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (trom.getText().length() >= 3){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tromKeyTyped

    private void tkapasitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkapasitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkapasitasActionPerformed

    private void tkapasitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkapasitasKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tkapasitas.getText().length() >= 4){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Kapasitas", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }

    }//GEN-LAST:event_tkapasitasKeyTyped

    private void tcameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcameraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcameraActionPerformed

    private void tcameraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcameraKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tcamera.getText().length() >= 2){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tcameraKeyTyped

    private void tslotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tslotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tslotActionPerformed

    private void tslotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tslotKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if ((Character.isDigit(a))) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tslot.getText().length() >= 12){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Panjang Memasukan data", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tslotKeyTyped

    private void tpokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpokokActionPerformed

    private void tpokokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpokokKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tpokok.getText().length() >= 8){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tpokokKeyTyped

    private void tjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tjualActionPerformed

    private void tjualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjualKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tjual.getText().length() >= 8){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tjualKeyTyped

    private void tppnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tppnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tppnActionPerformed

    private void tppnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tppnKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tppnKeyTyped

    private void tdiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tdiskonActionPerformed

    private void tdiskonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdiskonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tdiskonKeyTyped

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here
        
//        int a = Integer.valueOf(tpokok.getText());
//        int b = Integer.valueOf(tjual.getText());
//        int h = Integer.valueOf(tppn.getText());
//        int j = Integer.valueOf(tdiskon.getText());
        if(thp.getText().equals("") || tsisop.getText().equals("") || tukuran.getText().equals("") || tram.getText().equals("")
            || trom.getText().equals("") || cbwarna.getSelectedItem().toString().equals("") || tkapasitas.getText().equals("") || tcamera.getText().equals("")
            || tslot.getText().equals("") || (bjaringan.getSelection() == null) || tpokok.getText().equals("")
            || tjual.getText().equals("") || tppn.getText().equals("") || tdiskon.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masih ada data yang masih kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
//        }else if(b <= a){
//                JOptionPane.showMessageDialog(null, "Harga Jual Tidak Boleh Kurang Dari Harga Pokok ","Information", JOptionPane.ERROR_MESSAGE);
//        }else if(j >= h){
//                JOptionPane.showMessageDialog(null, "Diskon Kebesaran ","Information", JOptionPane.ERROR_MESSAGE);
//        }
        }else {
            simpan();
        }

    }//GEN-LAST:event_bsimpanActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        if(thp.getText().equals("") || tsisop.getText().equals("") || tukuran.getText().equals("") || tram.getText().equals("")
            || trom.getText().equals("") || cbwarna.getSelectedItem().toString().equals("") || tkapasitas.getText().equals("") || tcamera.getText().equals("")
            || tslot.getText().equals("") || (bjaringan.getSelection() == null) || tpokok.getText().equals("")
            || tjual.getText().equals("") || tppn.getText().equals("") || tdiskon.getText().equals("") || tstok.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masih ada data yang masih kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            try{
                int jawab;
                if((jawab = JOptionPane.showConfirmDialog(null, "Ingin Menghapus ?", "Konrimasi", JOptionPane.YES_NO_OPTION)) == 0){
                    String sql = "DELETE FROM tb_hp WHERE id_hp='"+tid.getText()+"'";
                    st = conn.createStatement();
                    st.executeUpdate(sql);
                    kosong();
                    tampil();
                    JOptionPane.showMessageDialog(null, "Berhasil Terhapus");
                    kode();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        // TODO add your handling code here:
        if(thp.getText().equals("") || tsisop.getText().equals("") || tukuran.getText().equals("") || tram.getText().equals("")
            || trom.getText().equals("") || cbwarna.getSelectedItem().toString().equals("") || tkapasitas.getText().equals("") || tcamera.getText().equals("")
            || tslot.getText().equals("") || (bjaringan.getSelection() == null) || tpokok.getText().equals("")
            || tjual.getText().equals("") || tppn.getText().equals("") || tdiskon.getText().equals("") || tstok.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masih ada data yang masih kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            try{
                
                String sql = "UPDATE tb_hp SET nama_hp= '"+thp.getText()+"', sisop='"+tsisop.getText()+"', ukuran='"+tukuran.getText()+"', ram='"+tram.getText()+"'"
                + ", rom='"+ trom.getText()+"', warna='"+cbwarna.getSelectedItem().toString()+"', kapasitas_baterai='"+tkapasitas.getText()+"', rear_kamera='"+tcamera.getText()+"', tipe_slot='"+tslot.getText()+"'"
                + ", jaringan='" + bjaringan.getSelection().getActionCommand() +"', harga_pokok='" + tpokok.getText()+"', harga_jual='"+tjual.getText()+"', ppn='"+tppn.getText()+"', diskon='"+tdiskon.getText()+"', stok='"+tstok.getText()+"' WHERE id_hp='"+tid.getText()+"'";
                st = conn.createStatement();
                st.executeUpdate(sql);
                kosong();
                tampil();
                JOptionPane.showMessageDialog(null, "Berhasil Diupdate");
                kode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_bubahActionPerformed

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("ID");
        model.addColumn("Nama HP");
        model.addColumn("Sisop");
        model.addColumn("Ukuran");
        model.addColumn("RAM");
        model.addColumn("ROM");
        model.addColumn("Warna");
        model.addColumn("Baterai");
        model.addColumn("Kamera");
        model.addColumn("Tipe Slot");
        model.addColumn("Jaringan");
        model.addColumn("Harga Pokok");
        model.addColumn("Harga Jual");
        model.addColumn("PPN");
        model.addColumn("Diskon");
        model.addColumn("Stok");
        try{
            String sql = "SELECT * FROM tb_hp WHERE id_hp LIKE '%"+tcari.getText()+"%' OR nama_hp LIKE '%"+tcari.getText()+"%' OR sisop LIKE '%"+tcari.getText()+"%' OR ukuran LIKE '%"+tcari.getText()+"%' OR ram LIKE '%"+tcari.getText()+"%'"
            + " OR rom LIKE '%"+tcari.getText()+"%' OR warna LIKE '%"+tcari.getText()+"%' OR kapasitas_baterai LIKE '%"+tcari.getText()+"%' OR rear_kamera  LIKE '%"+tcari.getText()+"%' OR tipe_slot LIKE '%"+tcari.getText()+"%'"
            + " OR jaringan LIKE '%"+tcari.getText()+"%' OR stok LIKE '%"+tcari.getText()+"%' OR harga_pokok LIKE '%"+tcari.getText()+"%' OR harga_jual LIKE '%"+tcari.getText()+"%' OR ppn LIKE '%"+tcari.getText()+"%' OR diskon LIKE '%"+tcari.getText()+"%'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            int no = 0;
            while(rs.next()){
                no++;
                model.addRow(new Object[]{
                    no, rs.getString("id_hp"), rs.getString("nama_hp"), rs.getString("sisop"), rs.getString("ukuran"), rs.getString("ram")
                    , rs.getString("rom"), rs.getString("warna"), rs.getString("kapasitas_baterai"), rs.getString("rear_kamera")
                    , rs.getString("tipe_slot"), rs.getString("jaringan"), rs.getString("harga_pokok"), rs.getString("harga_jual")
                    , rs.getString("ppn"), rs.getString("diskon")
                });
            }
            tdata.setModel(model);

        } catch ( Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tcariKeyPressed

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyTyped

    private void tdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tdataMouseClicked
        // TODO add your handling code here:
        int baris = tdata.getSelectedRow();
        tid.setText(tdata.getModel().getValueAt(baris, 1).toString());
        thp.setText(tdata.getModel().getValueAt(baris, 2).toString());
        tsisop.setText(tdata.getModel().getValueAt(baris, 3).toString());
        tukuran.setText(tdata.getModel().getValueAt(baris, 4).toString());
        tram.setText(tdata.getModel().getValueAt(baris, 5).toString());
        trom.setText(tdata.getModel().getValueAt(baris, 6).toString());
        cbwarna.setSelectedItem(tdata.getModel().getValueAt(baris, 7));
        tkapasitas.setText(tdata.getModel().getValueAt(baris, 8).toString());
        tcamera.setText(tdata.getModel().getValueAt(baris, 9).toString());
        tslot.setText(tdata.getModel().getValueAt(baris, 10).toString());
        //        bjaringan.clearSelection();
        switch (tdata.getModel().getValueAt(baris, 11).toString()) {
            case "3G" :
            rad_3g.setSelected(true);
            break;
            case "4G" :
            rad_4g.setSelected(true);
            break;
        }
        tpokok.setText(tdata.getModel().getValueAt(baris, 12).toString());
        tjual.setText(tdata.getModel().getValueAt(baris, 13).toString());
        tppn.setText(tdata.getModel().getValueAt(baris, 14).toString());
        tdiskon.setText(tdata.getModel().getValueAt(baris, 15).toString());
        tstok.setText(tdata.getModel().getValueAt(baris, 16).toString());
    }//GEN-LAST:event_tdataMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void cbwarnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbwarnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbwarnaActionPerformed

    private void tstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tstokActionPerformed

    private void tstokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tstokKeyTyped
        // TODO add your handling code here:
        if (tstok.getText().length() >= 2){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Stok", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tstokKeyTyped

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void tdataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tdataMousePressed
        // TODO add your handling code here:
        bubah.setEnabled(true);
        bhapus.setEnabled(true);
        bsimpan.setEnabled(false);
    }//GEN-LAST:event_tdataMousePressed

    private void brefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brefreshActionPerformed
        // TODO add your handling code here:
        bubah.setEnabled(false);
        bhapus.setEnabled(false);
        bsimpan.setEnabled(true);
        
        kosong();
    }//GEN-LAST:event_brefreshActionPerformed

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
            java.util.logging.Logger.getLogger(data_hp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_hp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_hp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_hp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_hp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bhapus;
    private javax.swing.ButtonGroup bjaringan;
    private javax.swing.JButton brefresh;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbwarna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rad_3g;
    private javax.swing.JRadioButton rad_4g;
    private javax.swing.JTextField tcamera;
    private javax.swing.JTextField tcari;
    private javax.swing.JTable tdata;
    private javax.swing.JTextField tdiskon;
    private javax.swing.JTextField thp;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tjual;
    private javax.swing.JTextField tkapasitas;
    private javax.swing.JTextField tpokok;
    private javax.swing.JTextField tppn;
    private javax.swing.JTextField tram;
    private javax.swing.JTextField trom;
    private javax.swing.JTextField tsisop;
    private javax.swing.JTextField tslot;
    private javax.swing.JTextField tstok;
    private javax.swing.JTextField tukuran;
    // End of variables declaration//GEN-END:variables
}
