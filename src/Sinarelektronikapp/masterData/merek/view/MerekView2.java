/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.merek.view;

import Sinarelektronikapp.config.UserLevel;
import Sinarelektronikapp.masterData.merek.controller.MerekController;
import Sinarelektronikapp.masterData.merek.database.merekDatabase;
import Sinarelektronikapp.masterData.merek.entity.merek;
import Sinarelektronikapp.masterData.merek.error.merekException;
import Sinarelektronikapp.masterData.merek.model.event.merekListener;
import Sinarelektronikapp.masterData.merek.model.merekModel;
import Sinarelektronikapp.masterData.merek.model.tabelModelMerek;
import Sinarelektronikapp.masterData.merek.service.merekDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Fauzi
 */
public class MerekView2 extends javax.swing.JPanel implements merekListener, ListSelectionListener{

    /**
     * Creates new form MerekView2
     */

    private static final long serialVersionUID = -1087606205075691549L;
    
    tabelModelMerek modelMerek ;
    
    MerekController controller ;
    
    merekModel model ;
    
    merek merek ;
    
    
    public MerekView2() {
        try {
            modelMerek = new tabelModelMerek();        
            
            model = new merekModel();
            model.setListener(this);
            
            controller = new MerekController();        
            controller.setModel(model);
            
            initComponents();
            
            tabelMerek.getSelectionModel().addListSelectionListener(this);
            tabelMerek.setModel(modelMerek);                               
            try {
                loadID();
            } catch (merekException ex) {
                Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }catch(merekException e){
            JOptionPane.showMessageDialog(null, "Error pada merek exception merek view 2 "+e);
        }
        setLevel();
/*    ActionListener taskPerformer = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
              @Override
              public boolean dispatchKeyEvent(KeyEvent e) {
                  if(e.getID() == KeyEvent.KEY_PRESSED){
                      if(e.getKeyCode() == KeyEvent.VK_F1){
                          tambah();
                      }else if(e.getKeyCode() == KeyEvent.VK_F2){
                          update();
                      }else if(e.getKeyCode() == KeyEvent.VK_F3){
                          delete();
                      }
                  }
                return false;
              }
          });
      }

    };
    // Timer
    new Timer(1000, taskPerformer).start();*/
    }

    public void setLevel(){
        UserLevel userLevel = new UserLevel();
        String levelAktif = userLevel.getUserLevelActive();
        switch(levelAktif){
            case "Pemilik Toko":
                    txtMerek.setEnabled(true);
                    btTambah.setEnabled(true);
                    btDelete.setEnabled(true);
                    btReset.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Administrator":
                    txtMerek.setEnabled(true);
                    btTambah.setEnabled(true);
                    btDelete.setEnabled(true);
                    btReset.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Karyawan":
                    txtMerek.setEnabled(false);
                    btTambah.setEnabled(false);
                    btDelete.setEnabled(false);
                    btReset.setEnabled(false);
                    btUpdate.setEnabled(false);                
                break;                        
            default:;
        }
    }    
    
    public void loadID()throws SQLException, merekException{
        merekDao dao = merekDatabase.getMerekDao();
        int lastid=dao.getLastId()+1;
        txtId.setText(String.valueOf(lastid));
        txtMerek.requestFocus();
    }
    
    public JTable getTabelMerek() {
        return tabelMerek;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtMerek() {
        return txtMerek;
    }
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atas = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kanan = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtMerek = new javax.swing.JTextField();
        tengah = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMerek = new javax.swing.JTable();
        bawah = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btReset = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        atas.setLayout(new java.awt.GridLayout(1, 2));

        kiri.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setText("Id :");
        kiri.add(jLabel1);

        jLabel2.setText("Merek :");
        kiri.add(jLabel2);

        atas.add(kiri);

        kanan.setLayout(new java.awt.GridLayout(2, 0));

        txtId.setEditable(false);
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFocusGained(evt);
            }
        });
        kanan.add(txtId);

        txtMerek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMerekActionPerformed(evt);
            }
        });
        kanan.add(txtMerek);

        atas.add(kanan);

        add(atas, java.awt.BorderLayout.PAGE_START);

        tengah.setLayout(new java.awt.BorderLayout());

        tabelMerek.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelMerek);

        tengah.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(tengah, java.awt.BorderLayout.CENTER);

        bawah.setLayout(new java.awt.GridLayout(1, 4));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/tambah2.png"))); // NOI18N
        btTambah.setMnemonic('T');
        btTambah.setFocusable(false);
        btTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTambah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        bawah.add(btTambah);

        btUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/update2.png"))); // NOI18N
        btUpdate.setMnemonic('U');
        btUpdate.setFocusable(false);
        btUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        bawah.add(btUpdate);

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/hapus.png"))); // NOI18N
        btDelete.setMnemonic('H');
        btDelete.setFocusable(false);
        btDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        bawah.add(btDelete);

        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/reset.png"))); // NOI18N
        btReset.setMnemonic('R');
        btReset.setFocusable(false);
        btReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        bawah.add(btReset);

        add(bawah, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void delete(){
        controller.deleteMerek(this);        
        controller.resetMerek(this);
        try {
            loadID();
        } catch (SQLException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (merekException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:        
        delete();
    }//GEN-LAST:event_btDeleteActionPerformed

    
    private void update(){
        controller.updateMerek(this);
        merekDao dao = null;
        try {
            dao = merekDatabase.getMerekDao();
        } catch (SQLException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        int lastid = 0;
        try {
            lastid = dao.getLastId()+1;
        } catch (merekException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtId.setText(String.valueOf(lastid));
        txtMerek.setText("");        
        txtMerek.requestFocus();          
    }
    
    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btUpdateActionPerformed

    
    private void tambah(){
        controller.insertMerek(this);
        merekDao dao = null;
        try {
            dao = merekDatabase.getMerekDao();
        } catch (SQLException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        int lastid = 0;
        try {
            lastid = dao.getLastId()+1;
        } catch (merekException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtId.setText(String.valueOf(lastid));
        txtMerek.setText("");        
        txtMerek.requestFocus();        
    }
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        tambah();
    }//GEN-LAST:event_btTambahActionPerformed

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained
        try {
            // TODO add your handling code here:
        loadID();
        } catch (SQLException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (merekException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtIdFocusGained

    private void txtMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMerekActionPerformed
        // TODO add your handling code here:
        tambah();
    }//GEN-LAST:event_txtMerekActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        try {
            // TODO add your handling code here:
            controller.resetMerek(this);
            loadID();
        } catch (SQLException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (merekException ex) {
            Logger.getLogger(MerekView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtMerek.requestFocus();
    }//GEN-LAST:event_btResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atas;
    private javax.swing.JPanel bawah;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kiri;
    private javax.swing.JTable tabelMerek;
    private javax.swing.JPanel tengah;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMerek;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(merekModel model) {
        txtId.setText(model.getIdmerek());
        txtMerek.setText(model.getNamamerek());
    }

    @Override
    public void onInsert(merek merek) {
        modelMerek.add(merek);
    }

    @Override
    public void onUpdate(merek merek) {
        int index = tabelMerek.getSelectedRow();
        modelMerek.set(index, merek);
    }

    @Override
    public void onDelete() {
        int index = tabelMerek.getSelectedRow();
        modelMerek.remove(index);
    }
    
    public void loadDatabase() throws SQLException, merekException{
        merekDao dao = merekDatabase.getMerekDao();
        modelMerek.setList(dao.selectAllMerek());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try{
            merek merekEntity = modelMerek.get(tabelMerek.getSelectedRow());
            txtId.setText(merekEntity.getIdmerek());
            txtMerek.setText(merekEntity.getNamamerek());
        }catch(IndexOutOfBoundsException ex){
            
        }
    }
}
