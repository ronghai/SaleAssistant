/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ronghai.sa.view.frame;

import java.awt.Container;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import me.ronghai.sa.bean.DataWrapperBean;
import me.ronghai.sa.util.SaleAssistantConstants;
import me.ronghai.sa.util.SaleAssistantResource;
import static me.ronghai.sa.core.dispatcher.SaleAssistansDispatcher.doDispatch;
import static me.ronghai.sa.util.SaleAssistantConstants.MAC_OS_X;
import me.ronghai.sa.view.action.callback.DispatcherCallBack;

/**
 *
 * @author L5M
 */
public class SaleAssistantFrame extends javax.swing.JFrame implements DispatcherCallBack {
    private static final Logger logger =   Logger.getLogger(SaleAssistantFrame.class.getName()) ;
    private static final ResourceBundle resourceBundle = SaleAssistantResource.getResourceBundle();
    /**
     * Creates new form SaleAssistant
     */
    public SaleAssistantFrame() {
        initComponents();
        _initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void _initComponents(){
        if(!MAC_OS_X){
            this.fileMenu.add(this.aboutMenuItem);
        }
        
        if(!MAC_OS_X){
            this.fileMenu.add(this._s);
            this.fileMenu.add(this.exitMenuItem);
        }
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

        _s = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        orderMenu = new javax.swing.JMenu();
        addOrderMenuItem = new javax.swing.JMenuItem();
        purchaseOrderMenuItem = new javax.swing.JMenuItem();
        shippingMenuItem = new javax.swing.JMenuItem();
        basicInfoMenu = new javax.swing.JMenu();
        clientMenuItem = new javax.swing.JMenuItem();
        categoryMenuItem = new javax.swing.JMenuItem();
        productMenuItem = new javax.swing.JMenuItem();
        carrierMenuItem = new javax.swing.JMenuItem();
        merchantMenuItem = new javax.swing.JMenuItem();
        accountMenuItem = new javax.swing.JMenuItem();
        statisticsMenu = new javax.swing.JMenu();
        saleStatisticsMenuItem = new javax.swing.JMenuItem();
        clientRankingMenuItem = new javax.swing.JMenuItem();
        toolMenu = new javax.swing.JMenu();
        priceCalcMenuItem = new javax.swing.JMenuItem();
        settingMenu = new javax.swing.JMenu();
        currencyMenuItem = new javax.swing.JMenuItem();
        updateExRateMenuItem = new javax.swing.JMenuItem();
        propertiesMenuItem = new javax.swing.JMenuItem();

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText(resourceBundle.getString("Exit")); // NOI18N
        exitMenuItem.setActionCommand("saleAssistantController.exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });

        aboutMenuItem.setText(resourceBundle.getString("About")); // NOI18N
        aboutMenuItem.setActionCommand("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(SaleAssistantConstants.MAX_DIMENSION);
        setMinimumSize(SaleAssistantConstants.MIN_DIMENSION);
        setPreferredSize(SaleAssistantConstants.MIN_DIMENSION);

        fileMenu.setText(resourceBundle.getString("File")); // NOI18N
        fileMenu.setActionCommand("File");
        mainMenu.add(fileMenu);

        orderMenu.setText(resourceBundle.getString("Order")); // NOI18N
        orderMenu.setActionCommand("Order");

        addOrderMenuItem.setText(resourceBundle.getString("Add New Order")); // NOI18N
        addOrderMenuItem.setActionCommand("addOrder");
        addOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        orderMenu.add(addOrderMenuItem);

        purchaseOrderMenuItem.setText(resourceBundle.getString("Purchase Order")); // NOI18N
        purchaseOrderMenuItem.setActionCommand("purchaseOrder");
        purchaseOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        orderMenu.add(purchaseOrderMenuItem);

        shippingMenuItem.setText(resourceBundle.getString("Shipping")); // NOI18N
        shippingMenuItem.setToolTipText("");
        shippingMenuItem.setActionCommand("me.ronghai.sa.panel.ShippingManagementPanel");
        shippingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        orderMenu.add(shippingMenuItem);

        mainMenu.add(orderMenu);

        basicInfoMenu.setText(resourceBundle.getString("Basic Info")); // NOI18N
        basicInfoMenu.setActionCommand("BasicInfo");

        clientMenuItem.setText(resourceBundle.getString("Client Management")); // NOI18N
        clientMenuItem.setActionCommand("clientController.init");
        clientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        basicInfoMenu.add(clientMenuItem);

        categoryMenuItem.setText(resourceBundle.getString("Category Management")); // NOI18N
        categoryMenuItem.setActionCommand("me.ronghai.sa.panel.CategoryManagementPanel");
        categoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        basicInfoMenu.add(categoryMenuItem);

        productMenuItem.setText(resourceBundle.getString("Product Management")); // NOI18N
        productMenuItem.setToolTipText("");
        productMenuItem.setActionCommand("me.ronghai.sa.panel.ProductManagementPanel");
        productMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        basicInfoMenu.add(productMenuItem);

        carrierMenuItem.setText(resourceBundle.getString("Carrier Management")); // NOI18N
        carrierMenuItem.setActionCommand("carrierController.init");
        carrierMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        basicInfoMenu.add(carrierMenuItem);

        merchantMenuItem.setText(resourceBundle.getString("Merchant Management")); // NOI18N
        merchantMenuItem.setActionCommand("me.ronghai.sa.panel.MerchantManagementPanel");
        merchantMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        basicInfoMenu.add(merchantMenuItem);

        accountMenuItem.setText(resourceBundle.getString("Account Management")); // NOI18N
        accountMenuItem.setActionCommand("me.ronghai.sa.panel.AccountManagementPanel");
        accountMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        basicInfoMenu.add(accountMenuItem);

        mainMenu.add(basicInfoMenu);

        statisticsMenu.setText(resourceBundle.getString("Statistic")); // NOI18N

        saleStatisticsMenuItem.setText(resourceBundle.getString("Sale Statistic")); // NOI18N
        saleStatisticsMenuItem.setActionCommand("me.ronghai.sa.panel.SaleStatisticsPanel");
        saleStatisticsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        statisticsMenu.add(saleStatisticsMenuItem);

        clientRankingMenuItem.setText(resourceBundle.getString("Client Rank")); // NOI18N
        clientRankingMenuItem.setToolTipText("");
        clientRankingMenuItem.setActionCommand("me.ronghai.sa.panel.ClientRankingPanel");
        clientRankingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        statisticsMenu.add(clientRankingMenuItem);

        mainMenu.add(statisticsMenu);

        toolMenu.setText(resourceBundle.getString("Tool")); // NOI18N
        toolMenu.setActionCommand("Tool");

        priceCalcMenuItem.setText(resourceBundle.getString("Price Calculate")); // NOI18N
        priceCalcMenuItem.setActionCommand("priceCalculatorController.init");
        priceCalcMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        toolMenu.add(priceCalcMenuItem);

        mainMenu.add(toolMenu);

        settingMenu.setText(resourceBundle.getString("Setting")); // NOI18N

        currencyMenuItem.setText(resourceBundle.getString("Currecy Management")); // NOI18N
        currencyMenuItem.setActionCommand("me.ronghai.sa.panel.CurrencyManagementPanel");
        currencyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        settingMenu.add(currencyMenuItem);

        updateExRateMenuItem.setText(resourceBundle.getString("Update Exchange Rate")); // NOI18N
        updateExRateMenuItem.setToolTipText("");
        updateExRateMenuItem.setActionCommand("UpdateExRate");
        updateExRateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        settingMenu.add(updateExRateMenuItem);

        propertiesMenuItem.setText(resourceBundle.getString("Properties")); // NOI18N
        propertiesMenuItem.setActionCommand("propertyController.init");
        propertiesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _menuItemActionPerformed(evt);
            }
        });
        settingMenu.add(propertiesMenuItem);

        mainMenu.add(settingMenu);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void _menuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__menuItemActionPerformed
        JMenuItem mi  = (JMenuItem)evt.getSource();
        doDispatch( evt.getActionCommand(), this, null, new DataWrapperBean("actionTitle", mi.getText()), this);
    }//GEN-LAST:event__menuItemActionPerformed
    
     private javax.swing.GroupLayout _getContentPaneLayout() {
        Container container =  getContentPane();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(container);
        container.removeAll();
        container.setLayout(layout);
        return layout;
     }
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator _s;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem accountMenuItem;
    private javax.swing.JMenuItem addOrderMenuItem;
    private javax.swing.JMenu basicInfoMenu;
    private javax.swing.JMenuItem carrierMenuItem;
    private javax.swing.JMenuItem categoryMenuItem;
    private javax.swing.JMenuItem clientMenuItem;
    private javax.swing.JMenuItem clientRankingMenuItem;
    private javax.swing.JMenuItem currencyMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem merchantMenuItem;
    private javax.swing.JMenu orderMenu;
    private javax.swing.JMenuItem priceCalcMenuItem;
    private javax.swing.JMenuItem productMenuItem;
    private javax.swing.JMenuItem propertiesMenuItem;
    private javax.swing.JMenuItem purchaseOrderMenuItem;
    private javax.swing.JMenuItem saleStatisticsMenuItem;
    private javax.swing.JMenu settingMenu;
    private javax.swing.JMenuItem shippingMenuItem;
    private javax.swing.JMenu statisticsMenu;
    private javax.swing.JMenu toolMenu;
    private javax.swing.JMenuItem updateExRateMenuItem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void callback(String action, DataWrapperBean inputWrapper , DataWrapperBean outputWrapper) {
        if(outputWrapper == null ) return;
        Object _panel = outputWrapper.get("panel");
        if (_panel != null) {
            JPanel panel = (JPanel) _panel;
            javax.swing.GroupLayout layout = _getContentPaneLayout();
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(10, 10, 10))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(10, 10, 10))
            );
            if(inputWrapper != null){
                this.setTitle(inputWrapper.get("actionTitle")+"");
            }
        }
    }
}
