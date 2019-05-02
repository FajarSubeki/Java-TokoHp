/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content_kasir;

import com.sun.glass.events.KeyEvent;
import inc.config;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import javax.swing.text.NumberFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import views.login;

/**
 *
 * @author fajarsubeki
 */
public class data_penjualan extends javax.swing.JFrame {

    Connection conn = config.Conn();
    Statement st;
    ResultSet rs;
    PreparedStatement pr;
    public DefaultTableModel JTPenjualan;
    public DefaultTableModel JTHp;
    public ResultSet rs1;
    public ResultSet rs2;
    public ResultSet rs3;
    public ResultSet rs4;
    public ResultSet rs6;
    public ResultSet rs7;
    public String id_barang;
    public Integer ppn_barang;
    public Integer stok_barang;
    public Integer harga_barang;
    public Integer id_hp;
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    
    int idHp, idKasir;
    JFormattedTextField ftextfield;
    NumberFormat numformat = NumberFormat.getInstance();
    NumberFormatter numformatter;
   
    /**  * Creates new form data_penjualan
     */
    public data_penjualan() {
        initComponents();
        tampil_hp();
        tampil();
        kasir();
        tidkas.setText(String.valueOf(login.getId2()));
        tnamakas.setText(String.valueOf(login.getId()));
        this.tidp.setEditable(false);
        tnamakas.setEnabled(false);
        tidkas.setEditable(false);
        ttk.setEnabled(false);
        tkembali.setEnabled(false);
        ttotal.setEnabled(false);
        ttanggal.setEnabled(false);
        ttanggal.setText(date);
        tidh.setEditable(false);
        thj.setEditable(false);
        tnama.setEditable(false);
        tdiskon.setEditable(false);
        tppn.setEditable(false);
        tjumlah.setEditable(false);
        tbyr.setEditable(false);
        bbeli.setEnabled(false);
        bbatal.setEnabled(false);
        bselesai.setEnabled(false);
        jTextField17.setVisible(false);
        
    }
    
    public void tampil_hp(){
        Object[] tampil_hp={"ID HP","Stok","Nama HP","Harga Jual","PPN","Diskon"};
        JTHp = new DefaultTableModel(null, tampil_hp){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        thp.setModel(JTHp);
    }
    
    public void tampil(){
        Object[] tampil ={"ID Penjualan","ID HP","Nama HP" ,"ID Kasir","Nama Kasir","Harga Jual","PPN","Diskon","Jumlah","Total","Tanggal"};
        JTPenjualan = new DefaultTableModel(null, tampil){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tpenjualan.setModel(JTPenjualan);
    }
    
    public void tampil_data_hp(){
        try{
            st = conn.createStatement();
            JTHp.getDataVector().removeAllElements();
            JTHp.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tb_hp WHERE stok >= 1 AND session = '0' AND hapus = '0'");
            while (rs.next()) {
                Object[]data={
                    rs.getString("id_hp"),
                    rs.getString("stok"),
                    rs.getString("nama_hp"),
                    rs.getString("harga_jual"),
                    rs.getString("ppn"),
                    rs.getString("diskon")
                };
                JTHp.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void show_data_hp(){
        try{
            int bar = thp.getSelectedRow();
            String a = thp.getValueAt(bar, 0).toString();
            String b = thp.getValueAt(bar, 1).toString();
            String c = thp.getValueAt(bar, 2).toString();
            String d = thp.getValueAt(bar, 3).toString();
            String e = thp.getValueAt(bar, 4).toString();
            String f = thp.getValueAt(bar, 5).toString();
            
            tidh.setText(a);
            jTextField17.setText(b);
            tnama.setText(c);
            thj.setText(d);
            tppn.setText(e);
            tdiskon.setText(f);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void tampil_data(){
        
        try{
            st = conn.createStatement();
            JTPenjualan.getDataVector().removeAllElements();
            JTPenjualan.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM query_penampung");
            while (rs.next()) {
                Object []data={
                    rs.getString("id_penjualan"),
                    rs.getString("id_hp"),
                    rs.getString("nama_hp"),
                    rs.getString("id_kasir"),
                    rs.getString("nama"),
                    rs.getString("harga_jual"),
                    rs.getString("ppn"),
                    rs.getString("diskon"),
                    rs.getString("jumlah"),
                    rs.getString("total"),
                    rs.getString("tanggal")
                };
                JTPenjualan.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void kode(){
        try {
            String sql = "SELECT * FROM tb_penjualan ORDER BY id_penjualan DESC";
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                String kode_otomatis = rs.getString("id_penjualan").substring(1);
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
                tidp.setText("P"+nol+an);
            }else{
                tidp.setText("P00001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    public void show_data(){
        try{
            int bar = tpenjualan.getSelectedRow();
            String a = tpenjualan.getValueAt(bar, 0).toString();
            String b = tpenjualan.getValueAt(bar, 1).toString();
            String c = tpenjualan.getValueAt(bar, 2).toString();
            String d = tpenjualan.getValueAt(bar, 3).toString();
            String e = tpenjualan.getValueAt(bar, 4).toString();
            String f = tpenjualan.getValueAt(bar, 5).toString();
            String g = tpenjualan.getValueAt(bar, 6).toString();
            String h = tpenjualan.getValueAt(bar, 7).toString();
            String i = tpenjualan.getValueAt(bar, 8).toString();
            String j = tpenjualan.getValueAt(bar, 9).toString();
            String k = tpenjualan.getValueAt(bar, 10).toString();
            
            tidp.setText(a);
            tidh.setText(b);
            tnama.setText(c);
            thj.setText(f);
            tppn.setText(g);
            tdiskon.setText(h);
            tjumlah.setText(i);
            ttotal.setText(j);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void kasir(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_kasir");
            while(rs.next()){
                tidkas.setText(rs.getString("id_kasir"));
                tnamakas.setText(rs.getString("nama"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void bersih(){
        tidh.setText("");
        tnama.setText("");
        thj.setText("");
        tppn.setText("");
        tdiskon.setText("");
        tjumlah.setText("");
        ttotal.setText("");
    }
    
    public void simpan(){
        try {
            st = conn.createStatement();
            st.executeUpdate("INSERT INTO `db_conter_hp`.`tb_tampung` (`id_penjualan`, `id_hp`, `id_kasir`, `jumlah`, `total`, `tanggal`) VALUES ('"+tidp.getText()+"', '"+tidh.getText()+"', '"+tidkas.getText()+"', '"+tjumlah.getText()+"', '"+ttotal.getText()+"', '"+ttanggal.getText()+"');");
            st.executeUpdate("INSERT INTO `db_conter_hp`.`tb_penjualan` (`id_penjualan`, `id_hp`, `id_kasir`, `jumlah`, `total`, `tanggal`) VALUES ('"+tidp.getText()+"', '"+tidh.getText()+"', '"+tidkas.getText()+"', '"+tjumlah.getText()+"', '"+ttotal.getText()+"', '"+ttanggal.getText()+"');");
            JOptionPane.showMessageDialog(null, "Beli Berhasil","Informasi", JOptionPane.INFORMATION_MESSAGE);
            tampil_data();
            bersih();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hitu(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT id_penjualan,SUM(total) as total_semua FROM tb_tampung group by id_penjualan");
            while (rs.next()) {
                ttk.setText(rs.getString("total_semua"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hapus(){
        try {
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM tb_tampung WHERE id_hp = '"+tidh.getText()+"'");
            st.executeUpdate("DELETE FROM tb_penjualan WHERE id_hp = '"+tidh.getText()+"'");
            JOptionPane.showMessageDialog(null, "Pembelian Dibatalkan","Informasi", JOptionPane.INFORMATION_MESSAGE);
            tampil_data();
            tampil_data_hp();
            bersih();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cari(){
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID HP");
        model.addColumn("Stok");
        model.addColumn("Nama HP");
        model.addColumn("Harga Jual");
        model.addColumn("PPN");
        model.addColumn("Diskon");
        try{
            String sql = "SELECT * FROM tb_hp WHERE nama_hp LIKE '%"+tcari.getText()+"%'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                model.addRow(new Object[]{
                 rs.getString("id_hp"), rs.getString("stok"), rs.getString("nama_hp"), rs.getString("harga_jual"),rs.getString("ppn"),rs.getString("diskon")
                });
            }
            thp.setModel(model);
        } catch ( Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cari2(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Penjualan");
        model.addColumn("ID HP");
        model.addColumn("Nama HP");
        model.addColumn("ID Kasir");
        model.addColumn("Harga Jual");
        model.addColumn("PPN");
        model.addColumn("Diskon");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        model.addColumn("Tanggal");
        
        try{
            String sql = "SELECT * FROM query_penampung WHERE nama_hp LIKE '%"+tcari.getText()+"%'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Object []data={
                    rs.getString("id_penjualan"),
                    rs.getString("id_hp"),
                    rs.getString("nama_hp"),
                    rs.getString("id_kasir"),
                    rs.getString("nama"),
                    rs.getString("harga_jual"),
                    rs.getString("ppn"),
                    rs.getString("diskon"),
                    rs.getString("jumlah"),
                    rs.getString("total"),
                    rs.getString("tanggal")
                };
            while(rs.next()){
                model.addRow(new Object[]{
                 rs.getString("id_penjualan"), rs.getString("id_hp"), rs.getString("nama_hp"), rs.getString("id_kasir"),rs.getString("nama"),rs.getString("harga_jual"),rs.getString("ppn"),rs.getString("diskon"),rs.getString("jumlah"),rs.getString("total"),rs.getString("tanggal")
                });
            }
            thp.setModel(model);
        } catch ( Exception e) {
            System.out.println(e.getMessage());
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
        jLabel8 = new javax.swing.JLabel();
        tidp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tjumlah = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ttotal = new javax.swing.JTextField();
        bbeli = new javax.swing.JButton();
        bselesai = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tidh = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        thj = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tppn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tdiskon = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        bnew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        thp = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        tidkas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ttanggal = new javax.swing.JTextField();
        tnamakas = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpenjualan = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        tcari1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ttk = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tbyr = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tkembali = new javax.swing.JTextField();
        bbatal = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        bcetak = new javax.swing.JButton();
        bpdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI PENJUALAN");

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Penjualan");

        tidp.setEditable(false);
        tidp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tidp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidpActionPerformed(evt);
            }
        });
        tidp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tidpKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Jumlah");

        tjumlah.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjumlahActionPerformed(evt);
            }
        });
        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tjumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total");

        ttotal.setEditable(false);
        ttotal.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ttotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalActionPerformed(evt);
            }
        });
        ttotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ttotalKeyTyped(evt);
            }
        });

        bbeli.setBackground(new java.awt.Color(255, 255, 255));
        bbeli.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bbeli.setForeground(new java.awt.Color(0, 153, 153));
        bbeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Buy_25px.png"))); // NOI18N
        bbeli.setText("Beli");
        bbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbeliActionPerformed(evt);
            }
        });

        bselesai.setBackground(new java.awt.Color(255, 255, 255));
        bselesai.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bselesai.setForeground(new java.awt.Color(0, 153, 153));
        bselesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save Close_25px.png"))); // NOI18N
        bselesai.setText("Selesai");
        bselesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bselesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bselesaiActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pencarian");

        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID HP");

        tidh.setEditable(false);
        tidh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tidh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidhActionPerformed(evt);
            }
        });
        tidh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tidhKeyTyped(evt);
            }
        });

        tnama.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });
        tnama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tnamaKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama HP");

        thj.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        thj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thjActionPerformed(evt);
            }
        });
        thj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thjKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Harga Jual");

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

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("PPN");

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

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Diskon");

        bnew.setBackground(new java.awt.Color(255, 255, 255));
        bnew.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bnew.setForeground(new java.awt.Color(0, 153, 153));
        bnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add New_25px.png"))); // NOI18N
        bnew.setText("Beli Baru");
        bnew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnewActionPerformed(evt);
            }
        });

        thp.setModel(new javax.swing.table.DefaultTableModel(
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
        thp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                thpMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(thp);

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Kasir");

        tidkas.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Nama");

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tanggal");

        ttanggal.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        tnamakas.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tnamakas.setForeground(new java.awt.Color(255, 0, 0));

        tpenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tpenjualanMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tpenjualan);

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pencarian");

        tcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcari1ActionPerformed(evt);
            }
        });
        tcari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcari1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcari1KeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Keseluruhan");

        ttk.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Bayar");

        tbyr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbyrActionPerformed(evt);
            }
        });
        tbyr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbyrKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbyrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbyrKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Kembalian");

        tkembali.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkembaliActionPerformed(evt);
            }
        });

        bbatal.setBackground(new java.awt.Color(255, 255, 255));
        bbatal.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bbatal.setForeground(new java.awt.Color(0, 153, 153));
        bbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cancel_25px.png"))); // NOI18N
        bbatal.setText("Batal");
        bbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        jTextField17.setText("jTextField17");

        bcetak.setBackground(new java.awt.Color(255, 255, 255));
        bcetak.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bcetak.setForeground(new java.awt.Color(0, 153, 153));
        bcetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Print_25px.png"))); // NOI18N
        bcetak.setText("Laporan Admin");
        bcetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakActionPerformed(evt);
            }
        });

        bpdf.setBackground(new java.awt.Color(255, 255, 255));
        bpdf.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bpdf.setForeground(new java.awt.Color(0, 153, 153));
        bpdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Print_25px.png"))); // NOI18N
        bpdf.setText("Laporan Excel");
        bpdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(bbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(84, 974, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(ttk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(tbyr, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel21)
                                        .addGap(33, 33, 33)
                                        .addComponent(tkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(bselesai, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tidh, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(thj, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tppn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tidp, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(bnew)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tidkas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tnamakas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 135, Short.MAX_VALUE)
                                .addComponent(bpdf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bcetak)
                                .addGap(14, 14, 14))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bnew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tidp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tidh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(thj, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tppn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tdiskon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(tidkas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tnamakas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 43, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbyr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bselesai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tidpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidpActionPerformed

    private void tidpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidpKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tidpKeyTyped

    private void tjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tjumlahActionPerformed

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (! (Character.isDigit(a) || (a==KeyEvent.VK_BACKSPACE) || (a==KeyEvent.VK_DELETE) )) {
            JOptionPane.showMessageDialog(null,"Maaf Kolom Harus Diisi Dengan Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();
        } else if (tjumlah.getText().length() > 2){
            JOptionPane.showMessageDialog(null, "Jangan Terlalu Besar Memasukan Jumlah", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_tjumlahKeyTyped

    private void ttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalActionPerformed

    private void ttotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttotalKeyTyped
        // TODO add your handling code here:
        char a= evt.getKeyChar();
        if (ttotal.getText().length() > 7){
            JOptionPane.showMessageDialog(null, "Terlalu Besar Memasukan Jumlah", "Peringatan", JOptionPane.WARNING_MESSAGE);evt.consume();;
        }
    }//GEN-LAST:event_ttotalKeyTyped

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_tcariKeyPressed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
//        
    }//GEN-LAST:event_formWindowOpened

    private void tidhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidhActionPerformed

    private void tidhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidhKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tidhKeyTyped

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaActionPerformed

    private void tnamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnamaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaKeyTyped

    private void thjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thjActionPerformed

    private void thjKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thjKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_thjKeyTyped

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

    private void bselesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bselesaiActionPerformed
        // TODO add your handling code here:
        try {
            int a = Integer.valueOf(ttk.getText());
            int b = Integer.valueOf(tbyr.getText());
            if (a > b) {
                JOptionPane.showMessageDialog(null, "Maaf Uang Anda Tidak Cukup","Informasi", JOptionPane.ERROR_MESSAGE);
            }else if(tbyr.getText().equals("")||tbyr.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Maaf Masukan Uang Terlebih Dahulu","Informasi", JOptionPane.ERROR_MESSAGE);
            }else if(ttk.getText().equals("")||ttk.getText().equals(" ")){
                JOptionPane.showMessageDialog(null, "Maaf Beli HP Terlebih Dahulu","Informasi", JOptionPane.ERROR_MESSAGE);
            }else{
                st = conn.createStatement();
                st.executeUpdate("INSERT INTO `db_conter_hp`.`tb_uang` (`id_penjualan`, `id_kasir`, `total`, `bayar`, `kembalian`) VALUES ('"+tidp.getText()+"', '"+tidkas.getText()+"', '"+ttk.getText()+"', '"+tbyr.getText()+"', '"+tkembali.getText()+"');");
                JOptionPane.showMessageDialog(null, "Pembayaran Berhasil","Informasi", JOptionPane.INFORMATION_MESSAGE);
                //Desktop.getDesktop().browse(new URL("http://localhost/laporan/struk.php").toURI());
                bersih();
                ttk.setText("");
                tbyr.setText("");
                tkembali.setText("");
                bselesai.setEnabled(false);
                bselesai.setEnabled(true);
                bbeli.setEnabled(false);
                bselesai.setEnabled(false);
                bnew.setEnabled(true);
                tidh.setEditable(false);
                thj.setEditable(false);
                tnama.setEditable(false);
                tdiskon.setEditable(false);
                tppn.setEditable(false);
                tjumlah.setEditable(false);
                tbyr.setEditable(false);
                bbeli.setEnabled(false);
                bbatal.setEnabled(false);
                bselesai.setEnabled(false);
                jTextField17.setVisible(false);
                tampil_data();
                String sql = "UPDATE tb_hp SET session = '0'";
                pr = conn.prepareStatement(sql);
                pr.executeUpdate();
                pr.close();
                
            }
            String reportSource = null;
            String reportDest = null;

           try{
            reportSource = "E:\\Kodingan\\Java\\tokoHP\\src\\content_kasir\\struk.jrxml";
            reportDest = "E:\\Kodingan\\Java\\tokoHP\\src\\content_kasir\\struk.jasper";
            HashMap parameter = new HashMap();
            parameter.put("id_penjualann", tidp.getText());
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
           }catch(JRException e){
               e.printStackTrace();
           }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_bselesaiActionPerformed

    private void bbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbeliActionPerformed
        // TODO add your handling code here:
        int a = Integer.valueOf(tjumlah.getText());
        int b = Integer.valueOf(jTextField17.getText());
        if (a < b) {
            JOptionPane.showMessageDialog(null, "Jumlah Kebanyakan","Informasi",JOptionPane.INFORMATION_MESSAGE);
        }else if(tjumlah.getText().equals("") ||tjumlah.getText().equals(" ")){
            JOptionPane.showMessageDialog(null, "Jumlah Kosong","Informasi",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                String sql = "UPDATE tb_hp SET session = '1' WHERE id_hp = '"+tidh.getText()+"'";
                pr = conn.prepareStatement(sql);
                pr.executeUpdate();
                pr.close();
                simpan();
                tampil_data_hp();
                //tampil_data();
                bbatal.setEnabled(true);
                hitu();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_bbeliActionPerformed

    private void bnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnewActionPerformed
        // TODO add your handling code here:
        try {
            st = conn.createStatement();
//            st.executeUpdate("TRUNCATE tb_uang");
            st.executeUpdate("TRUNCATE tb_tampung");
            tampil_data_hp();
            tampil_data();
            kode();
            tjumlah.setEditable(true);
            tbyr.setEditable(true);
            bbeli.setEnabled(false);
            bselesai.setEnabled(true);
            bnew.setEnabled(false);
        } catch (Exception e) {
           
        }
    }//GEN-LAST:event_bnewActionPerformed

    private void thpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thpMousePressed
        show_data_hp();
        bbeli.setEnabled(true);
    }//GEN-LAST:event_thpMousePressed

    private void tpenjualanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpenjualanMousePressed
        bbeli.setEnabled(true);
        bbatal.setEnabled(false);
        show_data();
    }//GEN-LAST:event_tpenjualanMousePressed

    private void tcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcari1ActionPerformed

    private void tcari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcari1KeyPressed
        // TODO add your handling code here:
        cari2();
    }//GEN-LAST:event_tcari1KeyPressed

    private void tcari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcari1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tcari1KeyReleased

    private void tbyrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbyrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbyrActionPerformed

    private void tbyrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbyrKeyPressed

    }//GEN-LAST:event_tbyrKeyPressed

    private void tbyrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbyrKeyReleased
        int n;
        int a = Integer.valueOf(ttk.getText());
        if (!tbyr.getText().equals("")) {
            int b = Integer.parseInt(tbyr.getText());
            int e;
            e = b-a;
            tkembali.setText(String.valueOf(e));
        }
        else {
            n = 0;
        }
       
    }//GEN-LAST:event_tbyrKeyReleased

    private void tbyrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbyrKeyTyped
        char a = evt.getKeyChar();
        if (!(Character.isDigit(a))) {
            evt.consume();
        }
        if (tbyr.getText().length() >=8) {
            evt.consume();
        }
    }//GEN-LAST:event_tbyrKeyTyped

    private void tkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkembaliActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        // TODO add your handling code here:
        bbeli.setEnabled(true);
        bbatal.setEnabled(false);
        hapus();
        hitu();
    }//GEN-LAST:event_bbatalActionPerformed

    private void tjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyReleased
        // TODO add your handling code here:
        int n1, n2,n3,n4,c,diskon,ppn,jumlah;
        String d;
        n1 = Integer.parseInt(thj.getText());
        if (!tjumlah.getText().equals("")) {
            n2 = Integer.parseInt(tjumlah.getText());
        }
        else {
            n2 = 0;
        }
        n4 = Integer.parseInt(tdiskon.getText());
        diskon = (n1 * n4)/100;
        ppn = (n1 * 10)/100;
        jumlah = n1 * n2;
        c = jumlah + ppn - diskon;
        d = String.valueOf(c);
        ttotal.setText(d);
    }//GEN-LAST:event_tjumlahKeyReleased

    private void bcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakActionPerformed
        // TODO add your handling code here:
        String reportSource = null;
        String reportDest = null;
        
       try{
        reportSource = "E:\\Kodingan\\Java\\tokoHP\\src\\content_kasir\\laporantransaksi.jrxml";
        reportDest = "E:\\Kodingan\\Java\\tokoHP\\src\\content_kasir\\laporantransaksi.jasper";
        HashMap parameter = new HashMap();
        parameter.put("id_kasir", tidkas.getText());
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
        JasperViewer.viewReport(jasperPrint,false);
       }catch(JRException e){
           e.printStackTrace();
       }
    }//GEN-LAST:event_bcetakActionPerformed

    private void bpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpdfActionPerformed
        // TODO add your handling code here:
        config ks = new config();
        rs = ks.getTampil("SELECT * FROM tb_pasok");
        try{
            FileWriter fw = new FileWriter("E:/file.csv");
            fw.write("ID Pasok");fw.append(",");
            fw.write("ID Distributor");fw.append(",");
            fw.write("ID HP");fw.append(",");
            fw.write("Jumlah");fw.append(",");
            fw.write("Tanggal");
            while(rs.next())
            {
                fw.write(rs.getString("id_pasok"));fw.append(",");
                fw.write(rs.getString("id_distributor"));fw.append(",");
                fw.write(rs.getString("id_hp"));fw.append(",");
                fw.write(rs.getString("jumlah"));fw.append(",");
                fw.write(rs.getString("tanggal"));fw.append(",");
                fw.write("\n");
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Berhasil Simpan Report Excel");
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_bpdfActionPerformed

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
            java.util.logging.Logger.getLogger(data_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bbeli;
    private javax.swing.JButton bcetak;
    private javax.swing.JButton bnew;
    private javax.swing.JButton bpdf;
    private javax.swing.JButton bselesai;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField tbyr;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tcari1;
    private javax.swing.JTextField tdiskon;
    private javax.swing.JTextField thj;
    private javax.swing.JTable thp;
    private javax.swing.JTextField tidh;
    private javax.swing.JTextField tidkas;
    private javax.swing.JTextField tidp;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField tkembali;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnamakas;
    private javax.swing.JTable tpenjualan;
    private javax.swing.JTextField tppn;
    private javax.swing.JTextField ttanggal;
    private javax.swing.JTextField ttk;
    private javax.swing.JTextField ttotal;
    // End of variables declaration//GEN-END:variables
}
