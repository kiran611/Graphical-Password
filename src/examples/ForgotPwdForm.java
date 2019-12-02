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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ForgotPwdForm extends javax.swing.JFrame {

    private String username;
    private String sanswer;

    private int cnt=-1;
    private DrawShape dsh = new DrawShape();
    
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
    
    public String getSanswer() {
        return sanswer;
    }

    public void setSanswer(String sanswer) {
        this.sanswer = sanswer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Creates new form LoginFrame
     */
    public ForgotPwdForm() {
        initComponents();
        
        jPanel4.setVisible(false);
        jPanel3.setVisible(false);
        dsh.setBounds(0, 0, 100, 100);
        dsh.setLocation(5, 5);
        jPanel6.add(dsh);
        setLocationRelativeTo(null);
        pack();
    }

    public ForgotPwdForm(String username) {
        this.username = username;
        initComponents();        
        jPanel4.setVisible(false);
        jPanel3.setVisible(false);
        dsh.setBounds(0, 0, 100, 100);
        dsh.setLocation(5, 5);
        jPanel6.add(dsh);
        setLocationRelativeTo(null);
        pack();
    }

    private void loadProfile() {
        //Form Validations        
        try {
            Connection con = null;
            con = DBConnection.getDBConnection();
            if (con == null) {
                return;
            }
            PreparedStatement st = con.prepareStatement("Select * From users Where username = ?");
            st.setString(1, txtUsername.getText());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                labQuestion.setText(rs.getString("squestion"));
                sanswer = rs.getString("sanswer");
                jPanel3.setVisible(true);
                pack();
            } else {
                JOptionPane.showMessageDialog(this, "Username not Found !!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
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
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        butVerify = new javax.swing.JButton();
        labQuestion = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        comboColor = new javax.swing.JComboBox<String>();
        panelColor = new javax.swing.JPanel();
        butSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        radColors = new javax.swing.JRadioButton();
        radShapes = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        comboShapes = new javax.swing.JComboBox<String>();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        butSubmit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        butCancel = new javax.swing.JButton();

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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Security Question :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Answer :");

        txtAnswer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        butVerify.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butVerify.setText("Verify");
        butVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butVerifyActionPerformed(evt);
            }
        });

        labQuestion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labQuestion.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(butVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labQuestion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(butVerify)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("New Password :");

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Color Scheme :");

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

        butSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butSave.setText("Save");
        butSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSaveActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10)
                                .addGap(12, 12, 12)
                                .addComponent(comboColor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radColors)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radShapes))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(butSave, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboShapes, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(radColors)
                    .addComponent(radShapes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(comboColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboShapes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butSave)
                .addGap(38, 38, 38))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Username :");

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        butSubmit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butSubmit.setText("Submit");
        butSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSubmitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Forgot Password - Recovery");

        butCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butCancel.setText("Cancel");
        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(butSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(butCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSubmit)
                    .addComponent(butCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSaveActionPerformed
        // TODO add your handling code here:

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

        Connection conn = null;
        try {
            conn = DBConnection.getDBConnection();
            if (conn == null) {
                return;
            }
            //System.out.println("\nDatabase Connection Established...");
            PreparedStatement st = conn.prepareStatement("Update users Set password=?, type=?, colorscheme=? Where username = ?");
            st.setString(1, new String(txtPassword.getPassword()));
            if (radColors.isSelected()) {
                    st.setString(2, "Color");
                    st.setString(3, comboColor.getSelectedItem().toString());
                } else if (radShapes.isSelected()) {
                    st.setString(2, "Shape");
                    st.setString(3, comboShapes.getSelectedItem().toString());
                } else {
                    st.setString(2, "");
                    st.setString(3, "");
                }
            st.setString(4, txtUsername.getText());
            st.executeUpdate();            
            conn.close();
            JOptionPane.showMessageDialog(this, "Password Updated Successfully !! Please Login Again !!","Message", JOptionPane.INFORMATION_MESSAGE);            

            LoginFrame fm = new LoginFrame();
            fm.setVisible(true);
            pack();

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

    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
        // TODO add your handling code here:
        LoginFrame fm = new LoginFrame();
        fm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_butCancelActionPerformed

    private void butSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSubmitActionPerformed
        // TODO add your handling code here:
        if (txtUsername.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Please Username!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        loadProfile();
    }//GEN-LAST:event_butSubmitActionPerformed

    private void butVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butVerifyActionPerformed
        // TODO add your handling code here:
        if(txtAnswer.getText().equalsIgnoreCase(sanswer)) {
            jPanel4.setVisible(true);
            
            radColors.setSelected(true);
            comboShapes.setSelectedIndex(0);
            comboShapes.setEnabled(false);
            
            pack();
        }
        else {
            JOptionPane.showMessageDialog(this, "Answer Not Match !! Try Again ", "Error", JOptionPane.ERROR_MESSAGE);
            return ;
        }
    }//GEN-LAST:event_butVerifyActionPerformed

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

    private void comboShapesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboShapesItemStateChanged
        // TODO add your handling code here:
        //get Shape
        JOptionPane.showMessageDialog(this,comboShapes.getSelectedItem());
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butSave;
    private javax.swing.JButton butSubmit;
    private javax.swing.JButton butVerify;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboColor;
    private javax.swing.JComboBox<String> comboShapes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel labQuestion;
    private javax.swing.JPanel panelColor;
    private javax.swing.JRadioButton radColors;
    private javax.swing.JRadioButton radShapes;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
