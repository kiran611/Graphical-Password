/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class RegistrationForm extends javax.swing.JFrame {

    private final int USERS_STATUS_ACTIVE = 1;
    private final int USERS_STATUS_BLOCKED = 2;

    private int cnt = -1;
    private DrawShape dsh = new DrawShape();

    private boolean fileflag = false;
    private String photofile = "";

    private String saveMode;

    private String username;
    private String[] Shapes = {"Circle", "Up Triangle", "Rectangle", "Down Triangle", "Diamond", "Left Triangle", "Pentagon", "Right Triangle"};
    private int[][][] shapesCord = {{{0, 0}, {60, 60}},
    {{30, 0}, {0, 60}, {60, 60}, {30, 0}},
    {{60, 0}, {0, 0}, {0, 60}, {60, 60}, {60, 0}},
    {{60, 0}, {0, 0}, {30, 60}, {60, 0}},
    {{30, 0}, {0, 30}, {30, 60}, {60, 30}, {30, 0}},
    {{60, 0}, {0, 30}, {60, 60}, {60, 0}},
    {{30, 0}, {0, 15}, {15, 60}, {45, 60}, {60, 15}, {30, 0}},
    {{0, 0}, {0, 60}, {60, 30}, {0, 0}}};

    private class DrawShape extends JComponent {

        public DrawShape() {
        }

        @Override
        public void paint(Graphics g) {
            //System.out.println("Cnt = " + cnt);            
            if (cnt == -1) {
                return;
            }
            Graphics2D graph = (Graphics2D) g;
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graph.setStroke(new BasicStroke(1));
            graph.setPaint(Color.BLACK);
            if (cnt == 0) {
                graph.draw(new java.awt.geom.Ellipse2D.Double(0, 0, 60, 60));
            } else {
                Path2D sh = new Path2D.Double();
                sh.moveTo(shapesCord[cnt][0][0], shapesCord[cnt][0][1]);
                for (int j = 1; j < shapesCord[cnt].length; j++) {
                    sh.lineTo(shapesCord[cnt][j][0], shapesCord[cnt][j][1]);
                }
                graph.draw(sh);
            }
        }
    }

    public String getSaveMode() {
        return saveMode;
    }

    public void setSaveMode(String saveMode) {
        this.saveMode = saveMode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotofile() {
        return photofile;
    }

    public void setPhotofile(String photofile) {
        this.photofile = photofile;
    }

    public boolean isFileflag() {
        return fileflag;
    }

    public void setFileflag(boolean fileflag) {
        this.fileflag = fileflag;
    }

    /**
     * Creates new form LoginFrame
     */
    public RegistrationForm(String mode) {
        this.saveMode = mode;
        initComponents();
        dsh.setBounds(0, 0, 100, 100);
        dsh.setLocation(5, 5);
        jPanel3.add(dsh);
        setLocationRelativeTo(null);

        //disabled 
        comboShapes.setEnabled(false);
        radColors.setSelected(true);
    }

    public RegistrationForm(String mode, String username) {
        this.saveMode = mode;
        this.username = username;
        initComponents();
        dsh.setBounds(0, 0, 100, 100);
        dsh.setLocation(5, 5);
        jPanel3.add(dsh);
        setLocationRelativeTo(null);
        //disabled 
        comboShapes.setEnabled(false);
        if (saveMode.equals("update")) {
            loadProfile();
            txtName.setEditable(false);
            jLabel1.setText("Edit Profile / Change Password");
            butSave.setText("Update");
            txtUsername.setEditable(false);
        }
    }

    private void loadProfile() {
        try {
            Connection con = null;
            con = DBConnection.getDBConnection();
            if (con == null) {
                return;
            }

            PreparedStatement st = con.prepareStatement("Select * From users Where username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                txtName.setText(rs.getString("uname"));
                txtDOB.setText(rs.getString("DOB"));
                txtMobile.setText(rs.getString("mobileno"));
                txtEmail.setText(rs.getString("emailid"));
                txtUsername.setText(rs.getString("username"));
                if (rs.getString("type").equals("Color")) {
                    radColors.setSelected(true);
                    comboColor.setSelectedItem(rs.getString("colorscheme"));                    
                    comboColor.setEnabled(true);
                } else if (rs.getString("type").equals("Shape")) {
                    radShapes.setSelected(true);
                    comboShapes.setSelectedItem(rs.getString("colorscheme"));
                    comboShapes.setEnabled(true);
                }
                comboQuestion.setSelectedItem(rs.getString("squestion"));
                txtAnswer.setText(rs.getString("sanswer"));
                photofile = rs.getString("photofile");
                if (!rs.getString("photofile").equals("")) {
                    jLabel6.setIcon(new javax.swing.ImageIcon("photos//" + rs.getString("photofile")));
                }
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "DB Error", JOptionPane.ERROR_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        butSave = new javax.swing.JButton();
        txtDOB = new javax.swing.JTextField();
        txtMobile = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        comboColor = new javax.swing.JComboBox<String>();
        panelColor = new javax.swing.JPanel();
        comboQuestion = new javax.swing.JComboBox<String>();
        txtPassword = new javax.swing.JPasswordField();
        butCancel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        butUpload = new javax.swing.JButton();
        butCapture = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        radColors = new javax.swing.JRadioButton();
        radShapes = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        comboShapes = new javax.swing.JComboBox<String>();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/images/logo_new.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Registration Form");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Name :");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Date of Birth :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Mobile No. :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Email Id. :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Username :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Password :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Color Scheme :");

        butSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butSave.setText("Save");
        butSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSaveActionPerformed(evt);
            }
        });

        txtDOB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtMobile.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Security Question :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Answer :");

        txtAnswer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        comboColor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboColor.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {"-- Select Color --", "BLUE", "CYAN", "GREEN", "MAGENTA", "BLACK", "PINK", "RED", "YELLOW" }));
        comboColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboColorItemStateChanged(evt);
            }
        });

        panelColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelColorLayout = new javax.swing.GroupLayout(panelColor);
        panelColor.setLayout(panelColorLayout);
        panelColorLayout.setHorizontalGroup(
            panelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelColorLayout.setVerticalGroup(
            panelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        comboQuestion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboQuestion.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {"-- Select Question --", "What is your mothers name ?", "What is your Favorite Color ?","What is your nick name?", "What is your father's middle name?", "Where were you born?"}));
        comboQuestion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboQuestionItemStateChanged(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        butCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butCancel.setText("Cancel");
        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/images/photo.png"))); // NOI18N

        butUpload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butUpload.setText("Clear");
        butUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butUploadActionPerformed(evt);
            }
        });

        butCapture.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butCapture.setText("Capture");
        butCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCaptureActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("(yyyy-mm-dd)");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Type :");

        radColors.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(radColors);
        radColors.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        radColors.setText("Colors");
        radColors.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radColorsItemStateChanged(evt);
            }
        });

        radShapes.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(radShapes);
        radShapes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        radShapes.setText("Shapes");
        radShapes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radShapesItemStateChanged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Shapes :");

        comboShapes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboShapes.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {"-- Select Shape --", "Circle","Up Triangle","Rectangle","Down Triangle","Diamond","Left Triangle","Pentagon","Right Triangle" }));
        comboShapes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboShapesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboShapes, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(127, 127, 127))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comboColor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(panelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtMobile, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                                                .addComponent(txtEmail)
                                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel7))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(102, 102, 102)
                                                    .addComponent(butSave, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(31, 31, 31)
                                                    .addComponent(butCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(radColors)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(radShapes)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(butUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(butCapture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboQuestion, 0, 288, Short.MAX_VALUE)
                            .addComponent(txtAnswer))
                        .addContainerGap(320, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butUpload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(radColors)
                    .addComponent(radShapes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(comboColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butCapture))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(comboShapes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comboQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSave)
                    .addComponent(butCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSaveActionPerformed
        // TODO add your handling code here:

        //Form Validations        
        if (txtName.getText().length() < 3) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Name !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtDOB.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Date of Birth !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtMobile.getText().matches("\\d{10}") || txtMobile.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Mobile No !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!txtEmail.getText().matches(EMAIL_REGEX) || txtMobile.getText().length() < 3) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Email ID !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Form Validations        
        if (txtUsername.getText().length() < 2) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Username !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Form Validations        
        if (txtPassword.getPassword().length < 2 || txtPassword.getPassword().length > 16) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Password - Max 16 Chars ...!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (radColors.isSelected()) {
            //Form Validations        
            if (comboColor.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please Select Color Scheme !!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (radShapes.isSelected()) {
            //Form Validations        
            if (comboShapes.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please Select Shape !!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        //Form Validations        
        if (comboQuestion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Security Question !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtAnswer.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Security Question Answer !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        try {
            conn = DBConnection.getDBConnection();
            if (conn == null) {
                return;
            }
            if (saveMode.equals("new")) {
                //check for duplicate username
                PreparedStatement st1 = conn.prepareStatement("Select * From users Where username=?");
                st1.setString(1, txtUsername.getText());
                ResultSet rs = st1.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Username already found in database !! Please try another !!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //System.out.println("\nDatabase Connection Established...");
                PreparedStatement st = conn.prepareStatement("insert into users (uname, dob, mobileno, emailid, username, password, type, colorscheme, squestion, sanswer, photofile, status) values (?,?,?,?,?,?,?,?,?,?,?,?)");
                st.setString(1, txtName.getText());
                java.sql.Date d = java.sql.Date.valueOf(txtDOB.getText());
                st.setDate(2, d);
                st.setString(3, txtMobile.getText());
                st.setString(4, txtEmail.getText());
                st.setString(5, txtUsername.getText());
                st.setString(6, new String(txtPassword.getPassword()));
                if (radColors.isSelected()) {
                    st.setString(7, "Color");
                    st.setString(8, comboColor.getSelectedItem().toString());
                } else if (radShapes.isSelected()) {
                    st.setString(7, "Shape");
                    st.setString(8, comboShapes.getSelectedItem().toString());
                } else {
                    st.setString(7, "");
                    st.setString(8, "");
                }
                st.setString(9, comboQuestion.getSelectedItem().toString());
                st.setString(10, txtAnswer.getText());
                st.setString(11, getPhotofile());
                st.setInt(12, USERS_STATUS_ACTIVE);
                int ans = st.executeUpdate();
                JOptionPane.showMessageDialog(this, "User Registerd Successfully !! Please Login !!");
                conn.close();

                //copy file from temp to photos
                File source = new File("temp/" + getPhotofile());
                File dest = new File("photos/" + getPhotofile());
                Files.copy(source.toPath(), dest.toPath());

                LoginFrame fm = new LoginFrame();
                fm.setVisible(true);

            } else if (saveMode.equals("update")) {
                //System.out.println("\nDatabase Connection Established...");
                PreparedStatement st = conn.prepareStatement("Update users Set dob=?, mobileno=?, emailid=?, password=?, type=?, colorscheme=?, "
                        + "squestion=?, sanswer=?, photofile=? Where username = ?");
                java.sql.Date d = java.sql.Date.valueOf(txtDOB.getText());
                st.setDate(1, d);
                st.setString(2, txtMobile.getText());
                st.setString(3, txtEmail.getText());
                st.setString(4, new String(txtPassword.getPassword()));
                if (radColors.isSelected()) {
                    st.setString(5, "Color");
                    st.setString(6, comboColor.getSelectedItem().toString());
                } else if (radShapes.isSelected()) {
                    st.setString(5, "Shape");
                    st.setString(6, comboShapes.getSelectedItem().toString());
                } else {
                    st.setString(5, "");
                    st.setString(6, "");
                }
                st.setString(7, comboQuestion.getSelectedItem().toString());
                st.setString(8, txtAnswer.getText());
                st.setString(9, getPhotofile());
                st.setString(10, txtUsername.getText());

                int ans = st.executeUpdate();
                JOptionPane.showMessageDialog(this, "User Updated Successfully !!");
                conn.close();
                try {
                    //copy file from temp to photos
                    File source = new File("temp/" + getPhotofile());
                    File dest = new File("photos/" + getPhotofile());
                    Files.copy(source.toPath(), dest.toPath());
                } catch (Exception ex) {
                }

                WelcomeFrame fm = new WelcomeFrame(username);
                fm.setVisible(true);
            }
            this.dispose();

        } catch (Exception ex) {
            System.err.println("Cannot connect to database server");
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_butSaveActionPerformed

    private void comboColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboColorItemStateChanged
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(this, comboColor.getSelectedItem().toString());
        String c = comboColor.getSelectedItem().toString();
        Color color = Color.WHITE;
        //olor.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.PINK, Color.RED, Color.YELLOW
        if (c.equals("BLUE")) {
            color = Color.BLUE;
        } else if (c.equals("CYAN")) {
            color = Color.CYAN;
        } else if (c.equals("GREEN")) {
            color = Color.GREEN;
        } else if (c.equals("MAGENTA")) {
            color = Color.MAGENTA;
        } else if (c.equals("BLACK")) {
            color = Color.BLACK;
        } else if (c.equals("PINK")) {
            color = Color.PINK;
        } else if (c.equals("RED")) {
            color = Color.RED;
        } else if (c.equals("YELLOW")) {
            color = Color.YELLOW;
        }
        panelColor.setBackground(color);
    }//GEN-LAST:event_comboColorItemStateChanged

    private void comboQuestionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboQuestionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboQuestionItemStateChanged

    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
        // TODO add your handling code here:
        if (saveMode.equals("new")) {
            LoginFrame fm = new LoginFrame();
            fm.setVisible(true);
        } else if (saveMode.equals("update")) {
            WelcomeFrame fm = new WelcomeFrame(username);
            fm.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_butCancelActionPerformed

    private void butUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butUploadActionPerformed
        // TODO add your handling code here:
        setPhotofile("");
        setFileflag(false);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/images/photo.png"))); // NOI18N                

    }//GEN-LAST:event_butUploadActionPerformed
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    private void butCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCaptureActionPerformed
        // TODO add your handling code here:    
        setFileflag(false);
        setPhotofile("");
        frmCaptureImage fm = new frmCaptureImage(this);
        fm.setVisible(true);
        //JOptionPane.showMessageDialog(this,"" + this.fileflag);
        //resize image                       
        try {
            if (fileflag == true) {
                File file = new File("temp/" + getPhotofile());
                Image img = null;
                img = ImageIO.read(file);
                BufferedImage tempJPG = null;
                tempJPG = resizeImage(img, 128, 128);
                ImageIO.write(tempJPG, "jpg", file);
                jLabel6.setIcon(new javax.swing.ImageIcon("temp/" + getPhotofile())); // NOI18N      
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_butCaptureActionPerformed

    private void comboShapesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboShapesItemStateChanged
        // TODO add your handling code here:
        //get Shape
        //JOptionPane.showMessageDialog(this,comboShapes.getSelectedItem());
        if (comboShapes.getSelectedIndex() > 0) {
            cnt = 0;
            for (int i = 0; i < Shapes.length; i++) {
                if (Shapes[i].equals(comboShapes.getSelectedItem())) {
                    cnt = i;
                    dsh.repaint();
                    return;
                }
            }
        }
    }//GEN-LAST:event_comboShapesItemStateChanged

    private void radColorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radColorsItemStateChanged
        // TODO add your handling code here:
        if (radColors.isSelected()) {
            comboColor.setEnabled(true);
            comboColor.setSelectedIndex(0);
            comboShapes.setEnabled(false);
            comboShapes.setSelectedIndex(0);
            cnt = -1;
        }
    }//GEN-LAST:event_radColorsItemStateChanged

    private void radShapesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radShapesItemStateChanged
        // TODO add your handling code here:
        if (radShapes.isSelected()) {
            comboShapes.setEnabled(true);
            comboShapes.setSelectedIndex(0);
            comboColor.setEnabled(false);
            comboColor.setSelectedIndex(0);
            cnt = -1;
        }
    }//GEN-LAST:event_radShapesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butCapture;
    private javax.swing.JButton butSave;
    private javax.swing.JButton butUpload;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboColor;
    private javax.swing.JComboBox<String> comboQuestion;
    private javax.swing.JComboBox<String> comboShapes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel panelColor;
    private javax.swing.JRadioButton radColors;
    private javax.swing.JRadioButton radShapes;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
