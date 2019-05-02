/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content_kasir;

import com.sun.glass.events.KeyEvent;
import static com.sun.webkit.perf.WCGraphicsPerfLogger.reset;
import inc.config;
import static inc.config.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.digester.Digester;
//import org.apache.commons.collections.*;
import net.sf.jasperreports.view.*;
/**
 *
 * @author fajarsubeki
 */
public class data_pasok extends javax.swing.JFrame {
    
    Connection conn;
    Statement st;
    PreparedStatement pr;
    ResultSet rs;
    public ResultSet rs6;
    public ResultSet rs7;
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    int idHp, idDist;
    
     private void tampil(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("ID Pasok");
        model.addColumn("ID Distributor");
        model.addColumn("ID HP");
        model.addColumn("Jumlah");
        model.addColumn("Tanggal");
        
        try{
            String sql = "SELECT tb_pasok.id_pasok, tb_distributor.nama_distributor, tb_hp.nama_hp, tb_pasok.jumlah, tb_pasok.tanggal"
                    + " FROM tb_pasok, tb_distributor, tb_hp WHERE tb_distributor.id_distributor=tb_pasok.id_distributor"
                    + " AND tb_hp.id_hp=tb_pasok.id_hp";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString("id_pasok"), rs.getString("nama_distributor"), rs.getString("nama_hp"), rs.getString("jumlah"), rs.getString("tanggal")
                });
            }
            tdata.setModel(model);
        } catch ( Exception e) {    
            System.out.println(e.getMessage());
        }
    }
    
    public void kosong(){
        tjumlah.setText("");
        ttanggal.setText(date);
        cdist.setSelectedItem("Pilih Distributor");
        chp.setSelectedItem("Pilih Hp");
        kode();
    }

    /**
     * Creates new form data_pasokk
     */
    public data_pasok() {
        initComponents();
        tampil();
        ttanggal.setText(date);
        tid.setEnabled(false);
        ttanggal.setEnabled(false);
        bsimpan.setEnabled(false);
        bhapus.setEnabled(false);
        brefresh.setEnabled(false);
        bselesai.setEnabled(false);
        cdist.setEnabled(false);
        chp.setEnabled(false);
        tjumlah.setEnabled(false);
        //load_combo();
    }
    
    public void kode(){
        try {
            String sql = "SELECT * FROM tb_pasok ORDER BY id_pasok DESC";
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                String kode_otomatis = rs.getString("id_pasok").substring(1);
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
                tid.setText("P"+nol+an);
            }else{
                tid.setText("P00001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void load_combo(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_distributor");
            cdist.addItem("Pilih Distributor");
            while (rs.next()) {
                cdist.addItem(rs.getString("id_distributor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_hp'");
            chp.addItem("Pilih Hp");
            while (rs.next()) {
                chp.addItem(rs.getString("id_hp"));
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bselesai = new javax.swing.JButton();
        tjumlah = new javax.swing.JTextField();
        tid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cdist = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        brefresh = new javax.swing.JButton();
        chp = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        ttanggal = new javax.swing.JTextField();
        bnew = new javax.swing.JButton();
        PRETRID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tdata = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI Pemasukan");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Transaksi"));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        bselesai.setBackground(new java.awt.Color(255, 255, 255));
        bselesai.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bselesai.setForeground(new java.awt.Color(0, 153, 153));
        bselesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save Close_25px.png"))); // NOI18N
        bselesai.setText("Pasok Selesai");
        bselesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bselesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bselesaiMouseClicked(evt);
            }
        });
        bselesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bselesaiActionPerformed(evt);
            }
        });

        tjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjumlahActionPerformed(evt);
            }
        });
        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });

        tid.setEditable(false);
        tid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HP");

        cdist.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cdistPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cdist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdistActionPerformed(evt);
            }
        });
        cdist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cdistKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID");

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Jumlah");

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Distributor");

        bsimpan.setBackground(new java.awt.Color(255, 255, 255));
        bsimpan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bsimpan.setForeground(new java.awt.Color(0, 153, 153));
        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Ok_25px.png"))); // NOI18N
        bsimpan.setText("Tambah Stok");
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
        bhapus.setText("Hapus Stok");
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
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

        chp.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                chpPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        chp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chpActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tanggal");

        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });
        ttanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ttanggalKeyTyped(evt);
            }
        });

        bnew.setBackground(new java.awt.Color(255, 255, 255));
        bnew.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bnew.setForeground(new java.awt.Color(0, 153, 153));
        bnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add New_25px.png"))); // NOI18N
        bnew.setText("Pemasokan Baru");
        bnew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnewActionPerformed(evt);
            }
        });

        PRETRID.setFont(new java.awt.Font("Bauhaus", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bselesai, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(chp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PRETRID))
                                .addComponent(cdist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PRETRID))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bselesai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bnew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi"));

        tdata.setModel(new javax.swing.table.DefaultTableModel(
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
        tdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tdataMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tdataMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tdata);

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Distributor");

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Pencarian");

        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcariKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(tcari)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(194, 194, 194)
                    .addComponent(jLabel12)
                    .addContainerGap(195, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(148, 148, 148)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(149, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(282, 282, 282))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bselesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bselesaiMouseClicked

    }//GEN-LAST:event_bselesaiMouseClicked

    private void bselesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bselesaiActionPerformed
        // TODO add your handling code here:
        bsimpan.setEnabled(false);
        bnew.setEnabled(true);
        brefresh.setEnabled(false);
        bselesai.setEnabled(false);
        cdist.setEnabled(false);
        chp.setEnabled(false);
        tjumlah.setEnabled(false);
        try {
            String sql = "UPDATE tb_pasok SET id_hp='"+chp.getSelectedItem().toString()+"' WHERE id_pasok = '"+tid.getText()+"'";
            pr = conn.prepareStatement(sql);
            pr.executeUpdate();
            pr.close();
            JOptionPane.showMessageDialog(null, "Pasok Selesai","Informasi", JOptionPane.INFORMATION_MESSAGE);
            ttanggal.setEnabled(false);
            bsimpan.setEnabled(false);
            bhapus.setEnabled(false);
            brefresh.setEnabled(false);
            bselesai.setEnabled(false);
            cdist.setEnabled(false);
            chp.setEnabled(false);
            tjumlah.setEnabled(false);
            //jTextField5.setVisible(false);
            tampil();
            kode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_bselesaiActionPerformed

    private void tjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tjumlahActionPerformed

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tjumlah.getText().length() >= 2){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Jumlah", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tjumlahKeyTyped

    private void tidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidActionPerformed

    private void tidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tidKeyTyped

    private void cdistPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cdistPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cdistPopupMenuWillBecomeInvisible

    private void cdistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdistActionPerformed

    }//GEN-LAST:event_cdistActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:

        if(cdist.getSelectedItem().toString().equals("") || chp.getSelectedItem().toString().equals("") || tjumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masih ada data yang masih kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
            st = conn.createStatement();
            // INTO tb_distributor VALUES (NULL, '"+tnama.getText()+"', '"+talamat.getText()+"', '"+thp.getText()+"')
            st.executeUpdate("insert into tb_pasok set id_pasok='" + tid.getText() + "', id_distributor='" + cdist.getSelectedItem() + "', id_hp='" + chp.getSelectedItem() +"', jumlah='" + tjumlah.getText() + "', tanggal='" + ttanggal.getText() + "'");
                    
            JOptionPane.showMessageDialog(null, "Data Tersimpan","Informasi", JOptionPane.INFORMATION_MESSAGE);
            tampil();
            kosong();
            kode();
            bsimpan.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        if(cdist.getSelectedItem().toString().equals("") || chp.getSelectedItem().toString().equals("") || tjumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Data masih kosong","Peringatan",JOptionPane.WARNING_MESSAGE);
        } else {
            try{
                int jawab;
                if((jawab = JOptionPane.showConfirmDialog(null, "Ingin Menghapus ?", "Konrimasi", JOptionPane.YES_NO_OPTION)) == 0){
                    String sql = "DELETE FROM  tb_pasok WHERE id_pasok = '"+tid.getText()+"'";
                    st = conn.createStatement();
                    st.executeUpdate(sql);
                    kosong();
                    tampil();
                    JOptionPane.showMessageDialog(null, "Berhasil Di Hapus");
                    bhapus.setEnabled(false);
                    bsimpan.setEnabled(true);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void brefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brefreshActionPerformed
        // TODO add your handling code here:
        kosong();
        bhapus.setEnabled(false);
        bsimpan.setEnabled(true);
    }//GEN-LAST:event_brefreshActionPerformed

    private void chpPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_chpPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        
    }//GEN-LAST:event_chpPopupMenuWillBecomeInvisible

    private void chpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chpActionPerformed

    }//GEN-LAST:event_chpActionPerformed

    private void tdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tdataMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tdataMouseClicked

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         try{
            String sql = "SELECT * FROM tb_distributor";
            conn = config.Conn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                cdist.addItem(rs.getString("id_distributor"));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        try{
            String sql = "SELECT * FROM tb_hp";
            conn = config.Conn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                chp.addItem(rs.getString("id_hp"));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Distributor");
        model.addColumn("Nama HP");
        model.addColumn("Jumlah");
        model.addColumn("Tanggal");
        try{
            String sql = "SELECT * FROM tb_pasok WHERE id_pasok LIKE '%"+tcari.getText()+"%' OR id_distributor LIKE '%"+tcari.getText()+"%' OR id_hp LIKE '%"+tcari.getText()+"%' OR jumlah LIKE '%"+tcari.getText()+"%' OR tanggal LIKE '%"+tcari.getText()+"%'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{ rs.getString("id_pasok"), rs.getString("id_distributor"), rs.getString("id_hp"), rs.getString("jumlah"), rs.getString("tanggal")
                });
            }
            tdata.setModel(model);
        } catch ( Exception e) {    
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tcariKeyReleased

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggalActionPerformed

    private void ttanggalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttanggalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggalKeyTyped

    private void bnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnewActionPerformed
        // TODO add your handling code here:
        bnew.setEnabled(false);
        bhapus.setEnabled(false);
        bsimpan.setEnabled(true);
        brefresh.setEnabled(true);
        bselesai.setEnabled(true);
        cdist.setEnabled(true);
        chp.setEnabled(true);
        tjumlah.setEnabled(true);
        kode();
    }//GEN-LAST:event_bnewActionPerformed

    private void tdataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tdataMousePressed
        // TODO add your handling code here:
        try {
            int bar = tdata.getSelectedRow();
            String a = tdata.getValueAt(bar, 0).toString();
            String b = tdata.getValueAt(bar, 1).toString();
            String c = tdata.getValueAt(bar, 2).toString();
            String d = tdata.getValueAt(bar, 3).toString();
            String e = tdata.getValueAt(bar, 4).toString();
            
            tid.setText(a);
            cdist.setSelectedItem(b);
            chp.setSelectedItem(c);
            tjumlah.setText(d);
            ttanggal.setText(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bsimpan.setEnabled(false);
        bhapus.setEnabled(true);
    }//GEN-LAST:event_tdataMousePressed

    private void cdistKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cdistKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cdistKeyReleased

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
            java.util.logging.Logger.getLogger(data_pasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_pasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_pasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_pasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_pasok().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PRETRID;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bnew;
    private javax.swing.JButton brefresh;
    private javax.swing.JButton bselesai;
    private javax.swing.JButton bsimpan;
    private javax.swing.JComboBox<String> cdist;
    private javax.swing.JComboBox<String> chp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tcari;
    private javax.swing.JTable tdata;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField ttanggal;
    // End of variables declaration//GEN-END:variables
}
