/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quiz2;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class JDialogInvoice extends javax.swing.JFrame {
    DefaultTableModel InvoiceModel,DetailModel = new DefaultTableModel();
    private static Connection connection;
    
    public int SESSION_USERID;
    
    public void setSession(int uid){
        this.SESSION_USERID = uid;
    }
    public int getSession(){
        return this.SESSION_USERID;
    }
    
    /**
     * Creates new form JDialogInvoice
     */
    public JDialogInvoice() {
        initComponents();
        initInvoice();
    }
    
    private static void connect(){
        if(connection == null){
            connection = new DBConnection().getDBConn();
        }
    }
    
    public void initInvoice(){
        //Table Display Customization
        InvoiceTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        InvoiceTable.setFont(new Font("Arial", Font.PLAIN, 18));
        detailBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        InvoiceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        
        //Table Data Model
        InvoiceModel = new DefaultTableModel(){
          @Override
          public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            } 
        };
        InvoiceModel.addColumn("Transaction Id");
        InvoiceModel.addColumn("User");
        InvoiceModel.addColumn("Timestamp");
        InvoiceModel.addColumn("Item Amount");
        InvoiceModel.addColumn("Total");
        
        this.InvoiceTable.setModel(InvoiceModel);
        
        //Table Special Listener
        this.InvoiceTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    //Spawn Dialog
                    int row = InvoiceTable.getSelectedRow();
                    int transcId = (int) InvoiceModel.getValueAt(row, 0);
                    getDetail(transcId);
                }
            }
        });
        
        //Populate Table
        fillInvoice();
    }
    
    public void fillInvoice(){ //Untuk refresh tabel invoice
        try{
            String sql = "select t.transaction_id as Id, \n" +
                        "u.username as User, \n" +
                        "t.timestamp as Timestamp, \n" +
                        "td.sumTotal as Total, \n" +
                        "td.rowCount as Qty\n" +
                        "from transaction t \n" +
                        "inner join user u on t.cashier_id = u.id \n" +
                        "inner join (\n" +
                        "select td.transaction_id, sum(td.total) as sumTotal, count(td.transaction_id) as rowCount from transaction_detail td group by td.transaction_id\n" +
                        ") td on t.transaction_id = td.transaction_id;";
            connect();
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery(sql);
            
            
            
            while(result.next()){
                Object[] o = new Object[5];
                o[0] = result.getInt("Id");
                o[1] = result.getString("User");
                o[2] = result.getTimestamp("Timestamp", Calendar.getInstance(Locale.UK));
                o[3] = result.getInt("Qty");
                o[4] = result.getInt("Total");
                InvoiceModel.addRow(o);
            }
            
            result.close();
            state.close();
            
        }
        catch(SQLException e){
            
        }
    }
    public void initDetail(int transaction_id){
        DetailModel = new DefaultTableModel(){
          @Override
          public boolean isCellEditable(int row, int column) {
                return false; 
            } 
        };
        
        DetailModel.addColumn("Id");
        DetailModel.addColumn("Menu");
        DetailModel.addColumn("Price");
        DetailModel.addColumn("Qty");
        DetailModel.addColumn("Total");
        
        DetailTable.setModel(DetailModel);
        
        try{
            String sql = "select td.transaction_id as Id, p.product_name as Menu, p.price as Price, td.qty as Qty, td.total as Total\n" +
                        "from transaction_detail td\n" +
                        "inner join product p on td.product_id = p.product_id where td.transaction_id = "+ transaction_id +";";
            connect();
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery(sql);
            
            while(result.next()){
                Object[] o = new Object[5];
                o[0] = result.getInt("Id");
                o[1] = result.getString("Menu");
                o[2] = result.getInt("Price");
                o[3] = result.getInt("Qty");
                o[4] = result.getInt("Total");
                DetailModel.addRow(o);
            }
            
            result.close();
            state.close();
            
        }catch(SQLException e){
            
        }
    }
    
    public void getDetail(int transaction_id){
        initDetail(transaction_id);
        DetailDialog.setSize(DetailDialog.getPreferredSize());
        DetailDialog.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DetailDialog = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        detailLabel = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DetailTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InvoiceTable = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        detailBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        cashierTab = new javax.swing.JMenu();
        invoiceTab = new javax.swing.JMenu();

        DetailDialog.setModal(true);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(0, 102, 153));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel15.setPreferredSize(new java.awt.Dimension(1308, 40));
        jPanel15.setLayout(new java.awt.BorderLayout());

        detailLabel.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        detailLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailLabel.setText("   Invoice Details");
        jPanel15.add(detailLabel, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel15, java.awt.BorderLayout.NORTH);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel17.setPreferredSize(new java.awt.Dimension(748, 45));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel18.setPreferredSize(new java.awt.Dimension(450, 72));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel17.add(jPanel18, java.awt.BorderLayout.WEST);

        jPanel16.add(jPanel17, java.awt.BorderLayout.NORTH);

        DetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Category", "Description"
            }
        ));
        jScrollPane1.setViewportView(DetailTable);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel16, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout DetailDialogLayout = new javax.swing.GroupLayout(DetailDialog.getContentPane());
        DetailDialog.getContentPane().setLayout(DetailDialogLayout);
        DetailDialogLayout.setHorizontalGroup(
            DetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
            .addGroup(DetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DetailDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        DetailDialogLayout.setVerticalGroup(
            DetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
            .addGroup(DetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DetailDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(0, 102, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel9.setPreferredSize(new java.awt.Dimension(1308, 40));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("   Invoice List");
        jPanel9.add(jLabel7, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.BorderLayout());

        InvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Category", "Description"
            }
        ));
        jScrollPane2.setViewportView(InvoiceTable);

        jPanel11.setPreferredSize(new java.awt.Dimension(748, 45));

        jPanel13.setPreferredSize(new java.awt.Dimension(450, 72));
        jPanel13.setLayout(new java.awt.BorderLayout());

        detailBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        detailBtn.setMnemonic('A');
        detailBtn.setText("View Details");
        detailBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        detailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailBtnActionPerformed(evt);
            }
        });
        jPanel13.add(detailBtn, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1124, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 1308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        cashierTab.setText("Cashier");
        cashierTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashierTabMouseClicked(evt);
            }
        });
        jMenuBar1.add(cashierTab);

        invoiceTab.setText("Invoices");
        invoiceTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoiceTabMouseClicked(evt);
            }
        });
        jMenuBar1.add(invoiceTab);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void detailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailBtnActionPerformed
        int row = InvoiceTable.getSelectedRow();
        
        if(row != -1){
            int transcId = (int) InvoiceModel.getValueAt(row, 0);
            this.getDetail(transcId);
        }
    }//GEN-LAST:event_detailBtnActionPerformed

    private void invoiceTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceTabMouseClicked
        // TODO add your handling code here:
        JDialogInvoice invoice = new JDialogInvoice();
        invoice.setSession(this.getSession());
        invoice.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_invoiceTabMouseClicked

    private void cashierTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashierTabMouseClicked
        // TODO add your handling code here:
        JFrameCashier cashier = new JFrameCashier();
        cashier.setSession(this.getSession());
        cashier.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cashierTabMouseClicked

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
            java.util.logging.Logger.getLogger(JDialogInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDialogInvoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DetailDialog;
    private javax.swing.JTable DetailTable;
    private javax.swing.JTable InvoiceTable;
    private javax.swing.JMenu cashierTab;
    private javax.swing.JButton detailBtn;
    private javax.swing.JLabel detailLabel;
    private javax.swing.JMenu invoiceTab;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
