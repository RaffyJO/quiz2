package quiz2;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class JFrameCashier extends javax.swing.JFrame {
    private static Connection connection;
    private DefaultTableModel OrderModel, MenuModel;

    /**
     * Creates new form JFrameCashier
     */
    public JFrameCashier() {
        initComponents();
        initMenu();
        shapeOrder();
    }
    private static void connect(){
        if(connection == null){
            connection = new DBConnection().getDBConn();
        }
    }
    
    // Custom TableCellRenderer to display scaled images from URLs in the table cells
    private static class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value != null) {
                String imageURL = value.toString();
                ImageIcon imageIcon = createImageIcon(imageURL);
                if (imageIcon != null) {
                    Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    label.setIcon(scaledIcon);
                } else {
                    label.setText("Image not found");
                }
            } else {
                label.setText("");
            }
            return label;
        }

        // Method to create an ImageIcon from the URL
        private ImageIcon createImageIcon(String imageURL) {
            try {
                URL url = new URL(imageURL);
                Image image = ImageIO.read(url);
                return new ImageIcon(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    
    public void initMenu(){
        menuTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuTable.setFont(new Font("Arial", Font.PLAIN,14));
        menuTable.getTableHeader().setFont(new Font("Arial",Font.BOLD, 16)) ;
        MenuModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
            
        };
        
        MenuModel.addColumn("Id");
        MenuModel.addColumn("Image");
        MenuModel.addColumn("Name");
        MenuModel.addColumn("Category");
        MenuModel.addColumn("Price");
        this.menuTable.setModel(MenuModel);
        try {
            
            String sql = "select * from product";
            connect();
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery(sql);
            
            while(result.next()){
                Object[] o = new Object[5];
                String imagePath = result.getString("img_dir");
                if (imagePath == null) imagePath = "img/QuestionMark.png";
                
                String imageCompletePath = getClass().getResource("../" + imagePath).toExternalForm();
                
                System.out.print(imagePath);
                
//                URL imageUrl = getClass().getResource("../assets/1_Ayam Goreng.jpg");
                
                o[0] = result.getInt("product_id");
                o[1] = new ImageIcon(imageCompletePath);
                o[2] = result.getString("product_name");
                o[3] = result.getString("category");
                o[4] = result.getInt("price");
                MenuModel.addRow(o);
            }
            result.close();
            state.close();
            
            menuTable.removeColumn(menuTable.getColumn("Id"));
            
            menuTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer()); // Set renderer for the image column
            menuTable.getColumnModel().getColumn(0).setWidth(100);
           
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFrameCashier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void shapeOrder(){
        orderTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderTable.setFont(new Font("Arial", Font.PLAIN,14));
        orderTable.getTableHeader().setFont(new Font("Arial",Font.BOLD, 16)) ;
        OrderModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
        };
        OrderModel.addColumn("Id");
        OrderModel.addColumn("Name");
        OrderModel.addColumn("Price");
        OrderModel.addColumn("Qty");
        OrderModel.addColumn("Total");
        this.orderTable.setModel(OrderModel);
        
        orderTable.removeColumn(orderTable.getColumn("Id"));
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EditDialog = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        editField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        itemCount = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        cashLabel = new javax.swing.JLabel();
        changeLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        orderEdit = new javax.swing.JButton();
        orderDecr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        orderInc = new javax.swing.JButton();
        orderRemove = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        jLabel6.setText("Change Quantity");

        editField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFieldActionPerformed(evt);
            }
        });

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(editField, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jButton1)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(editField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout EditDialogLayout = new javax.swing.GroupLayout(EditDialog.getContentPane());
        EditDialog.getContentPane().setLayout(EditDialogLayout);
        EditDialogLayout.setHorizontalGroup(
            EditDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EditDialogLayout.setVerticalGroup(
            EditDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Orders");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        jLabel2.setText("ITEMS:");

        jLabel3.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        jLabel3.setText("TOTAL:");

        jLabel4.setFont(new java.awt.Font("Stencil", 1, 24)); // NOI18N
        jLabel4.setText("CASH:");

        jLabel5.setFont(new java.awt.Font("Stencil", 1, 24)); // NOI18N
        jLabel5.setText("CHANGE:");

        itemCount.setFont(new java.awt.Font("Stencil", 0, 36)); // NOI18N
        itemCount.setText("0");

        totalLabel.setFont(new java.awt.Font("Stencil", 0, 36)); // NOI18N
        totalLabel.setText("0");

        cashLabel.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        cashLabel.setText("0");

        changeLabel.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        changeLabel.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(itemCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashLabel)
                    .addComponent(totalLabel)
                    .addComponent(changeLabel))
                .addGap(73, 73, 73))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(itemCount)
                    .addComponent(totalLabel))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cashLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(changeLabel))
                .addGap(25, 25, 25))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 255));
        jButton5.setText("New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 51, 255));
        jButton6.setText("Pay");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        orderEdit.setText("Edit");
        orderEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderEditActionPerformed(evt);
            }
        });

        orderDecr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderDecr.setText("-");
        orderDecr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderDecrActionPerformed(evt);
            }
        });

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Price", "Qty", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderTable);

        orderInc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderInc.setText("+");
        orderInc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderIncActionPerformed(evt);
            }
        });

        orderRemove.setText("Remove");
        orderRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(orderDecr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(orderRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(orderEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderInc, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(orderRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(orderEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(orderInc, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(orderDecr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image", "Name", "Category", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuTable.setRowHeight(100);
        menuTable.getTableHeader().setReorderingAllowed(false);
        menuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(menuTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Menu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Ganti teks
        itemCount.setText("0");
        totalLabel.setText("0");
        changeLabel.setText("0");
        cashLabel.setText("0");
        
        //Kosongi orderTable
        for(int i = 0; i < OrderModel.getRowCount(); i += 0){
            OrderModel.removeRow(i);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void menuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTableMouseClicked
        // TODO add your handling code here:
        int row = menuTable.getSelectedRow(); 
        
        int id = Integer.parseInt(MenuModel.getValueAt(row, 0).toString());
        String name = (String) MenuModel.getValueAt(row, 2);
        int price = Integer.parseInt(MenuModel.getValueAt(row, 4).toString());
        
        boolean isExist = false;
        int indexChange = 0;
        
        for(int i = 0; i < OrderModel.getRowCount(); i++){
            if((int) OrderModel.getValueAt(i, 0) == id ){
                isExist = true;
                indexChange = i;
            }
        }
        //Oper objek menu ke order
        if(isExist){
            OrderModel.setValueAt((int) OrderModel.getValueAt(indexChange, 3)+ 1, indexChange, 3); //Ganti Qty
            OrderModel.setValueAt(
                    (int) OrderModel.getValueAt(indexChange, 2) * (int) OrderModel.getValueAt(indexChange, 3)
                    , indexChange, 4); //Ganti Total
            modifyItemCount(1);
            modifyTotal(price); // Gk perlu nyari lagi kalo sudah pasti sama
        }
        else{ //Jika entry belum ada
            Object[] o = new Object[5];
            o[0] = id;
            o[1] = name;
            o[2] = price;
            o[3] = 1;
            o[4] = (int) o[3] * (int) o[2];
            OrderModel.addRow(o);
            modifyItemCount(1);
            modifyTotal(price * 1);
        }
    }//GEN-LAST:event_menuTableMouseClicked

    private void orderIncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderIncActionPerformed
        // TODO add your handling code here:
        int row = orderTable.getSelectedRow();
        
        if(row != -1){
            OrderModel.setValueAt((int) OrderModel.getValueAt(row, 3)+ 1, row, 3); //Ganti Qty
                OrderModel.setValueAt(
                        (int) OrderModel.getValueAt(row, 2) * (int) OrderModel.getValueAt(row, 3)
                        , row, 4);
                modifyItemCount(1);
                modifyTotal((int) OrderModel.getValueAt(row, 2) * 1);
        }
        
    }//GEN-LAST:event_orderIncActionPerformed

    private void orderRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderRemoveActionPerformed
        // TODO add your handling code here:
        int row = orderTable.getSelectedRow();
        
        if (row == -1) return;
        
        System.err.println(OrderModel.getRowCount());
        
        modifyTotal((int) OrderModel.getValueAt(row, 2) * (int) OrderModel.getValueAt(row, 3) * -1);
        OrderModel.removeRow(row);
        
//        modifyItemCount((int) OrderModel.getValueAt(row, 3) * -1);
        itemCount.setText(Integer.toString(OrderModel.getRowCount()));   
    }//GEN-LAST:event_orderRemoveActionPerformed

    private void orderDecrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderDecrActionPerformed
        // TODO add your handling code here:
        int row = orderTable.getSelectedRow();
        
        if(row != -1){
            if((int) OrderModel.getValueAt(row, 3) == 1){
                modifyTotal((int) OrderModel.getValueAt(row, 2) * -1);
                OrderModel.removeRow(row);
                modifyItemCount(-1);
            }
            else{
                OrderModel.setValueAt((int) OrderModel.getValueAt(row, 3)- 1, row, 3); //Ganti Qty
                OrderModel.setValueAt(
                        (int) OrderModel.getValueAt(row, 2) * (int) OrderModel.getValueAt(row, 3)
                        , row, 4);
                modifyTotal((int) OrderModel.getValueAt(row, 2) * -1);
                modifyItemCount(-1);
            }
        }
    }//GEN-LAST:event_orderDecrActionPerformed

    private void orderEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderEditActionPerformed
        // TODO add your handling code here:
        EditDialog.setVisible(true);
    }//GEN-LAST:event_orderEditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void editFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editFieldActionPerformed

    private Object[][] getTableData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        return data;
    }
    
    public void modifyItemCount(int n){
        itemCount.setText(Integer.toString(OrderModel.getRowCount()));
//        for (int i = 1; i <= OrderModel.getRowCount(); i++) {
//         itemCount.setText(Integer.toString(i));   
//        }
    }
    public void modifyTotal(int n){
        int temp = Integer.parseInt(totalLabel.getText());
        temp += n;
        totalLabel.setText(String.valueOf(temp));
    }
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
            java.util.logging.Logger.getLogger(JFrameCashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCashier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog EditDialog;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JTextField editField;
    private javax.swing.JLabel itemCount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable menuTable;
    private javax.swing.JButton orderDecr;
    private javax.swing.JButton orderEdit;
    private javax.swing.JButton orderInc;
    private javax.swing.JButton orderRemove;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}

class ImageRenderer extends DefaultTableCellRenderer {
    JLabel lbl = new JLabel();
    String path = "/home/tereza/Codes/Java/NETBEANS/quiz2/src/img/1_Ayam Goreng.jpg";

  ImageIcon icon = new ImageIcon(getClass().getResource(path));

    @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
    lbl.setText((String) value);
    lbl.setIcon(icon);
    return lbl;
  }
}