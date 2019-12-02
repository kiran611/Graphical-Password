/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.opencv.core.Core;

/**
 *
 * @author admin
 */
public class LoginFrame extends javax.swing.JFrame {

    private int cnt = 0, cPos = 0;
    private String ctype = "";
    private DrawSphere ds = new DrawSphere();
    private DrawShapes dsh = new DrawShapes();
    private String[] Shapes = {"Circle", "Up Triangle", "Rectangle", "Down Triangle", "Diamond", "Left Triangle", "Pentagon", "Right Triangle"};
    private Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.PINK, Color.RED, Color.YELLOW};
    private char[] cset = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', '.', '@', '$', '#', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char[] lowerChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private char[] upperChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private Font font = new Font("Times New Roman", Font.BOLD, 14);
    private int[][] upperCord = {{180, 80}, {130, 30}, {60, 30}, {20, 70}, {10, 130}, {50, 180}, {120, 190}, {175, 140}};
    private int[][] lowerCord = {{145, 90}, {115, 65}, {80, 65}, {50, 85}, {50, 125}, {75, 150}, {110, 150}, {145, 125}};
    private int[][][] shapesCord = {{{0, 0}, {60, 60}},
    {{30, 0}, {0, 60}, {60, 60}, {30, 0}},
    {{60, 0}, {0, 0}, {0, 60}, {60, 60}, {60, 0}},
    {{60, 0}, {0, 0}, {30, 60}, {60, 0}},
    {{30, 0}, {0, 30}, {30, 60}, {60, 30}, {30, 0}},
    {{60, 0}, {0, 30}, {60, 60}, {60, 0}},
    {{30, 0}, {0, 15}, {15, 60}, {45, 60}, {60, 15}, {30, 0}},
    {{0, 0}, {0, 60}, {60, 30}, {0, 0}}};

    private class DrawSphere extends JComponent {

        @Override
        public void paint(Graphics g) {
            System.out.println("Inside paint ...");
            Graphics2D graph = (Graphics2D) g;
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graph.setStroke(new BasicStroke(2));
            graph.setFont(font);
            for (int i = 0; i < 8; i++) {
                graph.setPaint(colors[cnt]);
                cnt = (cnt + 1) % colors.length;
                Shape arch2d = new Arc2D.Double(0, 0, 200, 200, i * 45, 40, Arc2D.PIE);
                graph.draw(arch2d);
                graph.setPaint(Color.BLACK);
                graph.drawString(new Character(upperChars[i]).toString(), upperCord[i][0], upperCord[i][1]);
                graph.drawString(new Character(lowerChars[i]).toString(), lowerCord[i][0], lowerCord[i][1]);
            }
        }
    }

    private class DrawShapes extends JComponent {

        public DrawShapes() {

        }

        @Override
        public void paint(Graphics g) {
            System.out.println("Inside paint ...");
            Graphics2D graph = (Graphics2D) g;
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graph.setStroke(new BasicStroke(1));
            graph.setFont(font);
            graph.setPaint(Color.BLACK);
            int x = 0, y = 0;
            for (int i = 0; i < shapesCord.length; i++) {
                //graph.setPaint(colors[cnt]);
                graph.setPaint(Color.BLUE);
                if (cnt == 0) {
                    graph.draw(new java.awt.geom.Ellipse2D.Double(x + 0, 0, 60, 60));
                } else {
                    Path2D sh = new Path2D.Double();
                    sh.moveTo(x + shapesCord[cnt][0][0], y + shapesCord[cnt][0][1]);
                    for (int j = 1; j < shapesCord[cnt].length; j++) {
                        sh.lineTo(x + shapesCord[cnt][j][0], y + shapesCord[cnt][j][1]);
                    }
                    graph.draw(sh);
                }
                graph.setPaint(Color.BLACK);
                graph.drawString(new Character(upperChars[i]).toString(), x + 25, 40);
                graph.drawString(new Character(lowerChars[i]).toString(), x + 25, 80);
                x += 65;

                cnt = (cnt + 1) % colors.length;
            }
        }
    }

    /**
     *
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);
        panelSphere.setVisible(false);
        pack();
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
        jLabel5 = new javax.swing.JLabel();
        panelSphere = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        butLogin = new javax.swing.JButton();
        innerPanel = new javax.swing.JPanel();
        labAnticlock = new javax.swing.JLabel();
        labClock = new javax.swing.JLabel();
        butGetUpper = new javax.swing.JButton();
        butGetLower = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        butClear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        butExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/images/logo_new.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelSphere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Graphical Sphere");

        butLogin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butLogin.setText("Login");
        butLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLoginActionPerformed(evt);
            }
        });

        innerPanel.setBackground(new java.awt.Color(255, 255, 255));
        innerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labAnticlock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/images/rsz_anticlock.png"))); // NOI18N
        labAnticlock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labAnticlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labAnticlockMouseClicked(evt);
            }
        });

        labClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/images/rsz_clockwise.png"))); // NOI18N
        labClock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labClock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labClockMouseClicked(evt);
            }
        });

        butGetUpper.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butGetUpper.setText("Get Upper");
        butGetUpper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGetUpperActionPerformed(evt);
            }
        });

        butGetLower.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        butGetLower.setText("Get Lower");
        butGetLower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGetLowerActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Password :");

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("Forgot Password ?");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout innerPanelLayout = new javax.swing.GroupLayout(innerPanel);
        innerPanel.setLayout(innerPanelLayout);
        innerPanelLayout.setHorizontalGroup(
            innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(innerPanelLayout.createSequentialGroup()
                .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(innerPanelLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(labAnticlock)
                        .addGap(18, 18, 18)
                        .addComponent(butGetUpper)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(butGetLower))
                    .addGroup(innerPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labClock)
                .addGap(121, 121, 121))
            .addGroup(innerPanelLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        innerPanelLayout.setVerticalGroup(
            innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, innerPanelLayout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labClock)
                    .addGroup(innerPanelLayout.createSequentialGroup()
                        .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(butGetUpper)
                                .addComponent(butGetLower))
                            .addComponent(labAnticlock))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(6, 6, 6))
        );

        butClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butClear.setText("Clear");
        butClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSphereLayout = new javax.swing.GroupLayout(panelSphere);
        panelSphere.setLayout(panelSphereLayout);
        panelSphereLayout.setHorizontalGroup(
            panelSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSphereLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(237, 237, 237))
            .addGroup(panelSphereLayout.createSequentialGroup()
                .addGroup(panelSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSphereLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(butLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butClear, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSphereLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(innerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelSphereLayout.setVerticalGroup(
            panelSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSphereLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(innerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(panelSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butLogin)
                    .addComponent(butClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Login to Application");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Username :");

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnSubmit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnRegister.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRegister.setText("New User - Registration");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        butExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butExit.setText("Exit");
        butExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(246, 246, 246))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butExit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnRegister)
                    .addComponent(butExit))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelSphere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSphere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshSphere() {
        if (ctype.equals("Color")) {
            ds.repaint();
        } else if (ctype.equals("Shape")) {
            dsh.repaint();
        }
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

        String pwd = "";
        String color = "";

        if (txtUsername.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please Enter Username !!", "Error !!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection conn = null;
        try {
            conn = DBConnection.getDBConnection();
            if (conn == null) {
                return;
            }
            //JOptionPane.showMessageDialog(this, "Database Connection Success ...");
            PreparedStatement st = conn.prepareStatement("Select * From users Where username = ?");
            st.setString(1, txtUsername.getText());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                pwd = rs.getString("password");
                color = rs.getString("colorscheme");
                ctype = rs.getString("type");

                //reconstruct lower and upperchar arrays
                char[] arr = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
                for (int i = 0; i < pwd.length(); i++) {
                    //get random no
                    boolean flag = true;
                    while (flag) {
                        int j = (int) (Math.random() * 16);
                        if (arr[j] == ' ') {
                            arr[j] = pwd.charAt(i);
                            break;
                        }
                    }
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == ' ') {
                        int j = (int) (Math.random() * cset.length);
                        arr[i] = cset[j];
                    }
                }
                for (int i = 0; i < 8; i++) {
                    upperChars[i] = arr[i];
                    System.out.println(i + " = " + arr[i]);
                }
                for (int i = 0; i < 8; i++) {
                    lowerChars[i] = arr[8 + i];
                    System.out.println(i + " = " + arr[8 + i]);
                }
                cPos = 0;
                if (ctype.equals("Color")) {

                    //olor.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.PINK, Color.RED, Color.YELLOW
                    if (color.equals("BLUE")) {
                        cPos = 0;
                    } else if (color.equals("CYAN")) {
                        cPos = 1;
                    } else if (color.equals("GREEN")) {
                        cPos = 2;
                    } else if (color.equals("MAGENTA")) {
                        cPos = 3;
                    } else if (color.equals("BLACK")) {
                        cPos = 4;
                    } else if (color.equals("PINK")) {
                        cPos = 5;
                    } else if (color.equals("RED")) {
                        cPos = 6;
                    } else if (color.equals("YELLOW")) {
                        cPos = 7;
                    }
                } else if (ctype.equals("Shape")) {
                    int i = 0;
                    while (i < Shapes.length) {
                        if (color.equals(Shapes[i])) {
                            cPos = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username Not Found in Database !!", "Error !!", JOptionPane.ERROR_MESSAGE);
                conn.close();
                return;
            }
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        /* ************** Wheel ******************* */
        cnt = 0;
        if (ctype.equals("Color")) {
            ds.setBounds(0, 0, 600, 600);
            ds.setLocation(180, 20);
            //labCnt.setText(new Integer(cnt).toString());
            //labPassword.setText("");
            innerPanel.add(ds);
        } else {
            /* ***********Shapes**************** */
            dsh.setBounds(0, 0, 600, 500);
            dsh.setLocation(20, 100);
            innerPanel.add(dsh);
        }
        panelSphere.setVisible(true);
        pack();

    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        RegistrationForm frm = new RegistrationForm("new");
        frm.setVisible(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void labAnticlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labAnticlockMouseClicked
        // TODO add your handling code here:
        if (cnt == 0) {
            cnt = colors.length - 1;
        } else {
            cnt = (cnt - 1) % colors.length;
        }
        cPos = (cPos + 1) % colors.length;
        refreshSphere();
        //labCnt.setText(Integer.toString(cnt));
    }//GEN-LAST:event_labAnticlockMouseClicked

    private void labClockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labClockMouseClicked
        cnt = (cnt + 1) % colors.length;
        if (cPos == 0) {
            cPos = colors.length - 1;
        } else {
            cPos = (cPos - 1) % colors.length;
        }
        refreshSphere();
        //labCnt.setText(Integer.toString(cnt));
    }//GEN-LAST:event_labClockMouseClicked

    private void butGetUpperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGetUpperActionPerformed
        // TODO add your handling code here:
        char ch = upperChars[cPos];
        //labPassword.setText(labPassword.getText() + new Character(ch).toString());
        String pwd = new String(txtPassword.getPassword()) + new Character(ch).toString();
        txtPassword.setText(pwd);
    }//GEN-LAST:event_butGetUpperActionPerformed

    private void butGetLowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGetLowerActionPerformed
        // TODO add your handling code here:
        char ch = lowerChars[cPos];
        //labPassword.setText(labPassword.getText() + new Character(ch).toString());
        String pwd = new String(txtPassword.getPassword()) + new Character(ch).toString();
        txtPassword.setText(pwd);
    }//GEN-LAST:event_butGetLowerActionPerformed

    private void butClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butClearActionPerformed
        // TODO add your handling code here:
        txtPassword.setText("");
        //labPassword.setText("");
    }//GEN-LAST:event_butClearActionPerformed

    private void butLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLoginActionPerformed
        // TODO add your handling code here:
        if (txtPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please Enter Password !!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Connection conn = null;
            conn = DBConnection.getDBConnection();
            if (conn == null) {
                return;
            }
            //JOptionPane.showMessageDialog(this, "Database Connection Success ...");
            PreparedStatement st = conn.prepareStatement("Select * From users Where username = ? and password = ?");
            st.setString(1, txtUsername.getText());
            st.setString(2, new String(txtPassword.getPassword()));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                WelcomeFrame fm = new WelcomeFrame(txtUsername.getText());
                fm.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sorry Password Not Match !! Try Again", "Error !!", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                conn.close();
                return;
            }
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }


    }//GEN-LAST:event_butLoginActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        ForgotPwdForm frm = new ForgotPwdForm();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void butExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_butExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // load native library of opencv
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton butClear;
    private javax.swing.JButton butExit;
    private javax.swing.JButton butGetLower;
    private javax.swing.JButton butGetUpper;
    private javax.swing.JButton butLogin;
    private javax.swing.JPanel innerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labAnticlock;
    private javax.swing.JLabel labClock;
    private javax.swing.JPanel panelSphere;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
