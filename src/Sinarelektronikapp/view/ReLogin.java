package Sinarelektronikapp.view;

import Sinarelektronikapp.AppConstant;
import Sinarelektronikapp.config.ActiveUser;
import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.config.UserLevel;
import Sinarelektronikapp.util.AES;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Fauzi
 */
public class ReLogin extends javax.swing.JFrame {

    private JPanel panelTop;
    private JPanel panelBottom;    
    private JButton btLogin;
    private JButton btReset;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JPanel panelTopRight;
    private JPanel panelTopLeft;
    private JPasswordField txtPassword;
    private JTextField txtUserName;

    /**
     * Creates new form ReLogin
     */
    public ReLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        panelTop = new javax.swing.JPanel();
        panelTopLeft = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        panelTopRight = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        panelBottom = new javax.swing.JPanel();
        btLogin = new javax.swing.JButton();
        btReset = new javax.swing.JButton();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Login Ulang");
        this.setPreferredSize(new java.awt.Dimension(300, 150));
        this.setResizable(false);

        panelTop.setPreferredSize(new java.awt.Dimension(200, 200));
        panelTop.setLayout(new java.awt.BorderLayout());

        panelTopLeft.setLayout(new java.awt.GridLayout(2, 0));

        lblUserName.setText("User Name :");
        panelTopLeft.add(lblUserName);

        lblPassword.setText("Password :");
        panelTopLeft.add(lblPassword);

        panelTop.add(panelTopLeft, java.awt.BorderLayout.WEST);

        panelTopRight.setPreferredSize(new java.awt.Dimension(100, 100));
        panelTopRight.setLayout(new java.awt.GridLayout(2, 0));

        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });       
        panelTopRight.add(txtUserName);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        panelTopRight.add(txtPassword);

        panelTop.add(panelTopRight, java.awt.BorderLayout.CENTER);

        this.getContentPane().add(panelTop, java.awt.BorderLayout.CENTER);

        panelBottom.setPreferredSize(new java.awt.Dimension(100, 30));
        panelBottom.setLayout(new java.awt.GridLayout(1, 2));

        btLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/login2.png"))); // NOI18N
        btLogin.setMnemonic('L');
        btLogin.setFocusable(false);
        btLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btLogin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btLoginActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(ReLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panelBottom.add(btLogin);

        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/reset.png"))); // NOI18N
        btReset.setMnemonic('R');
        btReset.setFocusable(false);
        btReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        panelBottom.add(btReset);

        getContentPane().add(panelBottom, java.awt.BorderLayout.PAGE_END);

        pack();
    }

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
        txtPassword.requestFocus();
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
        btLogin.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed

    private Connection connection;
	

    HostName ip = new HostName();
	
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada jaringan karena = "+ex, "Peringatan", JOptionPane.WARNING_MESSAGE);
        }        
    }
    
    public ActiveUser activeUser = new ActiveUser();
    
    public void resetLogin(){
        txtUserName.setText("");
        txtPassword.setText("");        
        txtUserName.requestFocus();
    }    
    
    MainFrame mainFrame ;
    
    public UserLevel userLevel = new UserLevel();
    
    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        koneksi();
        String userNameF = txtUserName.getText().trim();
        String passwordF = txtPassword.getText().trim();        
               

        activeUser.setUserName(userNameF);
        activeUser.Filling();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nama, password, level FROM user WHERE nama='" + userNameF + "'");

            String decryptedPassword = null;

            if (rs.next()) {
                try {
                    decryptedPassword = AES.decrypt(rs.getString("password"), AppConstant.CONFIG_PASSWORD);
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(ReLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (decryptedPassword.equals(passwordF)) {

                    //insertUser(rs.getString("nama"));
                    String levelLogin = "";
                    levelLogin = rs.getString("level");
                    userLevel.setUserLevel(levelLogin);
                    userLevel.Filling();
                    //insertUser(rs.getString("nama"));
                    mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    mainFrame.setAfterLoadAwal();
                    switch (levelLogin) {
                        case "Pemilik Toko":
                            mainFrame.setBtUser(true);
                            mainFrame.setBesar(true);
                            mainFrame.setTransaksiBk(true);
                            mainFrame.setTransaksiBb(true);
                            mainFrame.setReportBk(true);
                            mainFrame.setReportBb(true);
                            mainFrame.setMaintenance(true);
                            break;
                        case "Administrator":
                            mainFrame.setBtUser(false);
                            mainFrame.setBesar(false);
                            mainFrame.setTransaksiBk(true);
                            mainFrame.setTransaksiBb(false);
                            mainFrame.setReportBk(true);
                            mainFrame.setReportBb(false);
                            mainFrame.setMaintenance(true);
                            break;
                        case "Karyawan":
                            mainFrame.setBtUser(false);
                            mainFrame.setBesar(false);
                            mainFrame.setTransaksiBk(false);
                            mainFrame.setTransaksiBb(false);
                            mainFrame.setReportBk(false);
                            mainFrame.setReportBb(false);
                            mainFrame.setMaintenance(false);
                            break;
                        default:;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "salah password");
                    resetLogin();
                }
            } else {
                JOptionPane.showMessageDialog(null, "salah username");
                resetLogin();
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "terjadi kesalahan pada " + exception);
        } finally {
            if (statement != null) {
                statement.close();
            }           
            if (connection != null) {
                connection.close();
            }
            this.dispose();
        }
    }

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {                                        
        resetLogin();
    }
}
