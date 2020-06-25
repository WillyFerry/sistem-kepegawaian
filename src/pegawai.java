
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class pegawai extends javax.swing.JFrame {

    public static String sql;
    private DefaultTableModel tabelpegawai;
    int id;
    Connection c = koneksi.getKoneksi();
    private Object JasperFillManager;
    
    /**
     * Creates new form pegawai
     */
    public pegawai() {
        initComponents();
        tabelpegawai = new DefaultTableModel();
        tabelpegawai.addColumn("Id Pegawai");
        tabelpegawai.addColumn("Nama Lengkap");
        tabelpegawai.addColumn("Bidang");
        tabelpegawai.addColumn("Jabatan");
        tabelpegawai.addColumn("Golongan");
        loadsearch();
        tbPegawai.setModel(tabelpegawai);
        loadData();
    }
    
    private void HapusTeks() {
        txt_NipPegawai.setText("");
        txt_NamaLengkapPegawai.setText("");
        txt_Alamat.setText("");
        txt_AsalKotaPegawai.setText("");
        txt_NoHpPegawai.setText("");
        txt_EmailPegawai.setText("");
        txt_TglLahirPegawai.setText("");
        txt_TempatLahirPegawai.setText("");
        txt_TglGabungPegawai.setText("");
        btng_JenisKelamin.clearSelection();
    }
    
    public void loadComboBox(){
        combo_BidangPekerjaan.removeAllItems();
        combo_Jabatan.removeAllItems();
        combo_Golongan.removeAllItems();
        try {
            Statement s = c.createStatement();
            sql = "select * from bagian";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                combo_BidangPekerjaan.addItem(r.getString("nama_bagian"));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            Statement s = c.createStatement();
            sql = "select * from jabatan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                combo_Jabatan.addItem(r.getString("nama_jabatan"));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            Statement s = c.createStatement();
            sql = "select * from golongan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                combo_Golongan.addItem(r.getString("nama_golongan"));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public String getNamaBagian(int id)
    {
        String nama = "";
        try {
            Statement s = c.createStatement();
            sql = "select * from bagian";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if(r.getInt("id_bagian")  == id)
                {
                   nama = r.getString("nama_bagian"); 
                }
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return nama;
    }
    
    public String getNamaJabatan(int id)
    {
        String nama = "";
        try {
            Statement s = c.createStatement();
            sql = "select * from jabatan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if(r.getInt("id_jabatan")  == id)
                {
                   nama = r.getString("nama_jabatan"); 
                }
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return nama;
    }
    public String getNamaGolongan(int id)
    {
        String nama = "";
        try {
            Statement s = c.createStatement();
            sql = "select * from golongan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if(r.getInt("id_golongan")  == id)
                {
                   nama = r.getString("nama_golongan"); 
                }
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return nama;
    }
    
    public int getNamaBagian(String nama)
    {
        int id = 0;
        try {
            Statement s = c.createStatement();
            sql = "select * from bagian";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if(r.getString("nama_bagian").equals(nama))
                {
                   id = r.getInt("id_bagian"); 
                }
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return id;
    }
    
    public int getNamaJabatan(String nama)
    {
        int id = 0;
        try {
            Statement s = c.createStatement();
            sql = "select * from jabatan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if(r.getString("nama_jabatan").equals(nama))
                {
                   id = r.getInt("id_jabatan"); 
                }
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return id;
    }
    
    public int getNamaGolongan(String nama)
    {
        int id = 0;
        try {
            Statement s = c.createStatement();
            sql = "select * from golongan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                if(r.getString("nama_golongan").equals(nama))
                {
                   id = r.getInt("id_golongan"); 
                }
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return id;
    }
    
    public void loadsearch() {
        tabelpegawai.getDataVector().removeAllElements();
        tabelpegawai.fireTableDataChanged();
        try {
            Statement s = c.createStatement();
            sql = "select * from pegawai where id_pegawai like '" + tsearch.getText() + "%' || nama_lengkap like '" + tsearch.getText() + "%'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[5];
                o[0] = r.getString("id_pegawai");
                o[1] = r.getString("nama_lengkap");
                o[2] = getNamaBagian(r.getInt("id_bagian"));
                o[3] = getNamaJabatan(r.getInt("id_jabatan"));
                o[4] = getNamaGolongan(r.getInt("id_golongan"));
                tabelpegawai.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getLatestId(){
        int latestId = 1;
        try {
            Statement s = c.createStatement();
            sql = "select id_pegawai from pegawai order by id_pegawai desc limit 1";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                latestId = r.getInt("id_pegawai") + 1;
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return latestId;
    }
    
    public void loadData() {
        tabelpegawai.getDataVector().removeAllElements();
        tabelpegawai.fireTableDataChanged();
        try {
            Statement s = c.createStatement();
            sql = "select * from pegawai";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[5];
                o[0] = r.getString("id_pegawai");
                o[1] = r.getString("nama_lengkap");
                o[2] = getNamaBagian(r.getInt("id_bagian"));
                o[3] = getNamaJabatan(r.getInt("id_jabatan"));
                o[4] = getNamaGolongan(r.getInt("id_golongan"));
                tabelpegawai.addRow(o);
            }
            r.close();
            s.close();
            txt_IdPegawai.setText(Integer.toString(getLatestId()));
            loadComboBox();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
        btnSimpan.setEnabled(true);
    }
    
    public void loadPerData(int pegawai_id) {
        HapusTeks();
        try {
            Statement s = c.createStatement();
            sql = "select * from pegawai where id_pegawai = '" + pegawai_id + "'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                txt_IdPegawai.setText(Integer.toString(pegawai_id));
                txt_NipPegawai.setText(r.getString("nip"));
                txt_NamaLengkapPegawai.setText(r.getString("nama_lengkap"));
                txt_Alamat.setText(r.getString("alamat"));
                txt_AsalKotaPegawai.setText(r.getString("asal_kota"));
                txt_NoHpPegawai.setText(r.getString("no_hp"));
                txt_EmailPegawai.setText(r.getString("email"));
                txt_TglLahirPegawai.setText(r.getString("tanggal_lahir"));
                txt_TempatLahirPegawai.setText(r.getString("tempat_lahir"));
                txt_TglGabungPegawai.setText(r.getString("tanggal_bergabung"));
                if(r.getString("jenis_kelamin").equals("laki-laki")){
                    rb_LakiLaki.setSelected(true);
                    rb_Perempuan.setSelected(false);
                }
                else{
                    rb_LakiLaki.setSelected(false);
                    rb_Perempuan.setSelected(true);
                }
                combo_BidangPekerjaan.setSelectedItem(getNamaBagian(r.getInt("id_bagian")));
                combo_Jabatan.setSelectedItem(getNamaJabatan(r.getInt("id_jabatan")));
                combo_Golongan.setSelectedItem(getNamaGolongan(r.getInt("id_golongan")));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
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

        btng_JenisKelamin = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        txt_NipPegawai = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_AlamatPegawai = new javax.swing.JScrollPane();
        txt_Alamat = new javax.swing.JTextArea();
        txt_NamaLengkapPegawai = new javax.swing.JTextField();
        txt_NoHpPegawai = new javax.swing.JTextField();
        txt_TempatLahirPegawai = new javax.swing.JTextField();
        txt_IdPegawai = new javax.swing.JTextField();
        rb_LakiLaki = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rb_Perempuan = new javax.swing.JRadioButton();
        txt_TglLahirPegawai = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_EmailPegawai = new javax.swing.JTextField();
        txt_TglGabungPegawai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_AsalKotaPegawai = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPegawai = new javax.swing.JTable();
        tsearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        combo_BidangPekerjaan = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        combo_Jabatan = new javax.swing.JComboBox();
        combo_Golongan = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 255));
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(720, 100));

        jToggleButton2.setBackground(new java.awt.Color(0, 190, 255));
        jToggleButton2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jToggleButton2.setText("Pegawai");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setBackground(new java.awt.Color(204, 204, 255));
        jToggleButton3.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jToggleButton3.setText("Bagian");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setBackground(new java.awt.Color(204, 204, 255));
        jToggleButton4.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jToggleButton4.setText("Golongan Gaji");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jButton1.setText("Logout");
        jButton1.setMaximumSize(new java.awt.Dimension(99, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(99, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(99, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jLabel1.setText("Sistem Kepegawaian");

        jToggleButton5.setBackground(new java.awt.Color(204, 204, 255));
        jToggleButton5.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jToggleButton5.setText("Jabatan");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(618, 618, 618))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(358, 529));

        txt_NipPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_NipPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NipPegawaiActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel13.setText("Tempat Lahir");

        txt_Alamat.setColumns(20);
        txt_Alamat.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_Alamat.setRows(5);
        txt_AlamatPegawai.setViewportView(txt_Alamat);

        txt_NamaLengkapPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_NamaLengkapPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NamaLengkapPegawaiActionPerformed(evt);
            }
        });

        txt_NoHpPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_NoHpPegawai.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_TempatLahirPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N

        txt_IdPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_IdPegawai.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_IdPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdPegawaiActionPerformed(evt);
            }
        });

        btng_JenisKelamin.add(rb_LakiLaki);
        rb_LakiLaki.setText("Laki-Laki");

        jLabel14.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel14.setText("Tanggal Gabung (YYYY-MM-DD)");

        jLabel12.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel12.setText("Tanggal Lahir(YYYY-MM-DD)");

        btng_JenisKelamin.add(rb_Perempuan);
        rb_Perempuan.setText("Perempuan");

        txt_TglLahirPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel11.setText("Asal Kota");

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel10.setText("Email");

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel7.setText("Alamat");

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 18)); // NOI18N
        jLabel3.setText("Form Pegawai");

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel9.setText("No Hp");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel4.setText("Nip");

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel2.setText("Id Pegawai");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel5.setText("Nama Lengkap");

        txt_EmailPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_EmailPegawai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_EmailPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailPegawaiActionPerformed(evt);
            }
        });

        txt_TglGabungPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_TglGabungPegawai.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel8.setText("Jenis Kelamin");

        txt_AsalKotaPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        txt_AsalKotaPegawai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_AsalKotaPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AsalKotaPegawaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_IdPegawai)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_NipPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_NamaLengkapPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_AlamatPegawai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rb_LakiLaki)
                                        .addGap(41, 41, 41)
                                        .addComponent(rb_Perempuan)))))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_EmailPegawai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_AsalKotaPegawai, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(64, 64, 64))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_TglLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txt_TempatLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_NoHpPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_TglGabungPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_NipPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_IdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_NamaLengkapPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_AlamatPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_LakiLaki)
                    .addComponent(rb_Perempuan)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_AsalKotaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_EmailPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TglLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TempatLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_NoHpPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TglGabungPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));

        tbPegawai.setBackground(new java.awt.Color(204, 204, 255));
        tbPegawai.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        tbPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tbPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPegawai);

        tsearch.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        tsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsearchActionPerformed(evt);
            }
        });
        tsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tsearchKeyReleased(evt);
            }
        });

        jLabel6.setText("Search");

        combo_BidangPekerjaan.setBackground(new java.awt.Color(204, 204, 255));
        combo_BidangPekerjaan.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        combo_BidangPekerjaan.setForeground(new java.awt.Color(204, 204, 255));
        combo_BidangPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_BidangPekerjaanActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel16.setText("Bidang Pekerjaan");

        combo_Jabatan.setBackground(new java.awt.Color(204, 204, 255));
        combo_Jabatan.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        combo_Jabatan.setForeground(new java.awt.Color(204, 204, 255));

        combo_Golongan.setBackground(new java.awt.Color(204, 204, 255));
        combo_Golongan.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
        combo_Golongan.setForeground(new java.awt.Color(204, 204, 255));
        combo_Golongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_GolonganActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel15.setText("Jabatan");

        jLabel17.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel17.setText("Golongan");

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpan.setBackground(new java.awt.Color(204, 204, 255));
        btnSimpan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(204, 204, 255));
        btnHapus.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(204, 204, 255));
        btnUpdate.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(204, 204, 255));
        btnReset.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(combo_Golongan, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_Jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_BidangPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(combo_BidangPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_Jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_Golongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(959, 719));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        new bagian().setVisible(true);
        dispose();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        new golongan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        new jabatan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void tbPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawaiMouseClicked
        int i = tbPegawai.getSelectedRow();
        txt_IdPegawai.setText((String) tbPegawai.getValueAt(i, 0));
        int idPegawai = Integer.parseInt(txt_IdPegawai.getText());
        loadPerData(idPegawai);
        btnSimpan.setEnabled(false);
        txt_IdPegawai.setEditable(false);
    }//GEN-LAST:event_tbPegawaiMouseClicked

    private void txt_IdPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdPegawaiActionPerformed

    private void txt_NipPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NipPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NipPegawaiActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            int comboBagian = getNamaBagian((String) combo_BidangPekerjaan.getSelectedItem());
            int comboJabatan = getNamaJabatan((String) combo_Jabatan.getSelectedItem());
            int comboGolongan = getNamaGolongan((String) combo_Golongan.getSelectedItem());
            
            String jenis_kelamin = "";
            if(rb_LakiLaki.isSelected()){
                jenis_kelamin = "laki-laki";
            }
            else{
                jenis_kelamin = "perempuan";
            }
            
            sql = "INSERT INTO pegawai VALUES("
                    + "'" + txt_IdPegawai.getText() + "',"
                    + "'" + txt_NipPegawai.getText() + "',"
                    + "'" + txt_NamaLengkapPegawai.getText() +"',"
                    + "'" + txt_Alamat.getText() + "',"
                    + "'" + jenis_kelamin +"',"
                    + "'" + txt_NoHpPegawai.getText() +"',"
                    + "'" + txt_EmailPegawai.getText() + "',"
                    + "'" + txt_AsalKotaPegawai.getText() + "',"
                    + "'" + txt_TglLahirPegawai.getText() +"',"
                    + "'" + txt_TempatLahirPegawai.getText() + "',"
                    + "'" + txt_TglGabungPegawai.getText() +"',"
                    + "'" + comboBagian +"',"
                    + "'" + comboGolongan + "',"
                    + "'" + comboJabatan +"'"
                    + ")";
            PreparedStatement p = c.prepareStatement(sql);
            p.execute();
            p.close();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (SQLException e) {
        }
        loadData();
        HapusTeks();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        try {
            int jawab;
            if ((jawab = JOptionPane.showConfirmDialog(null, "Ingin menghapus data?", "konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
                //                st = c.createStatement();
                sql = "delete from pegawai where id_pegawai ='" + txt_IdPegawai.getText() + "'";
                PreparedStatement p = c.prepareStatement(sql);
                p.execute();
                p.close();
            }
        } catch (SQLException e) {
        }
        loadData();
        HapusTeks();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        HapusTeks();
        loadData();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            int comboBagian = getNamaBagian((String) combo_BidangPekerjaan.getSelectedItem());
            int comboJabatan = getNamaJabatan((String) combo_Jabatan.getSelectedItem());
            int comboGolongan = getNamaGolongan((String) combo_Golongan.getSelectedItem());
            
            String jenis_kelamin = "";
            if(rb_LakiLaki.isSelected()){
                jenis_kelamin = "laki-laki";
            }
            else{
                jenis_kelamin = "perempuan";
            }
            
            Connection c = koneksi.getKoneksi();
            sql = "update pegawai set nip = ?, nama_lengkap = ?, alamat = ?, jenis_kelamin = ?, no_hp = ?, email = ?, asal_kota = ?, tanggal_lahir = ?, tempat_lahir = ?, tanggal_bergabung = ?, id_bagian = ?, id_golongan = ?, id_jabatan = ? where id_pegawai = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, txt_NipPegawai.getText());
            p.setString(2, txt_NamaLengkapPegawai.getText());
            p.setString(3, txt_Alamat.getText());
            p.setString(4, jenis_kelamin);
            p.setString(5, txt_NoHpPegawai.getText());
            p.setString(6, txt_EmailPegawai.getText());
            p.setString(7, txt_AsalKotaPegawai.getText());
            p.setString(8, txt_TglLahirPegawai.getText());
            p.setString(9, txt_TempatLahirPegawai.getText());
            p.setString(10,txt_TglGabungPegawai.getText());
            p.setInt(11, comboBagian);
            p.setInt(12, comboGolongan);
            p.setInt(13, comboJabatan);
            p.setString(14, txt_IdPegawai.getText());
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Update Berhasil");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadData();
        HapusTeks();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tsearchActionPerformed

    private void tsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tsearchKeyReleased
        loadsearch();
    }//GEN-LAST:event_tsearchKeyReleased

    private void txt_NamaLengkapPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NamaLengkapPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NamaLengkapPegawaiActionPerformed

    private void txt_EmailPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailPegawaiActionPerformed

    private void txt_AsalKotaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AsalKotaPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AsalKotaPegawaiActionPerformed

    private void combo_GolonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_GolonganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_GolonganActionPerformed

    private void combo_BidangPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_BidangPekerjaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_BidangPekerjaanActionPerformed

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
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup btng_JenisKelamin;
    private javax.swing.JComboBox combo_BidangPekerjaan;
    private javax.swing.JComboBox combo_Golongan;
    private javax.swing.JComboBox combo_Jabatan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JRadioButton rb_LakiLaki;
    private javax.swing.JRadioButton rb_Perempuan;
    private javax.swing.JTable tbPegawai;
    private javax.swing.JTextField tsearch;
    private javax.swing.JTextArea txt_Alamat;
    private javax.swing.JScrollPane txt_AlamatPegawai;
    private javax.swing.JTextField txt_AsalKotaPegawai;
    private javax.swing.JTextField txt_EmailPegawai;
    private javax.swing.JTextField txt_IdPegawai;
    private javax.swing.JTextField txt_NamaLengkapPegawai;
    private javax.swing.JTextField txt_NipPegawai;
    private javax.swing.JTextField txt_NoHpPegawai;
    private javax.swing.JTextField txt_TempatLahirPegawai;
    private javax.swing.JTextField txt_TglGabungPegawai;
    private javax.swing.JTextField txt_TglLahirPegawai;
    // End of variables declaration//GEN-END:variables
}
