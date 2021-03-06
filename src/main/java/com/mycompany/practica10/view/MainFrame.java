/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica10.view;

import com.mycompany.practica10.model.ImageHandler;
import java.awt.Cursor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author pabloantoniolopezmartin
 */
public class MainFrame extends javax.swing.JFrame {

    private File folder;
    private List<String> files;
    private String language;
    private static File fichero;
    private JFileChooser fc;
    private JFileChooser fs;
    private FileNameExtensionFilter filter;
    private ImageHandler ih;
    private DefaultListModel tableModel=new DefaultListModel();
    public MainFrame() {
        initComponents();
        setFileChooser();
        filesList.setModel(tableModel);
        filesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        files = new LinkedList<String>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileListScrollPane = new javax.swing.JScrollPane();
        filesList = new javax.swing.JList<>();
        compressButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        filesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        fileListScrollPane.setViewportView(filesList);

        compressButton.setText("Comprimir");
        compressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compressButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        openMenu.setText("Open directory");
        openMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuActionPerformed(evt);
            }
        });
        fileMenu.add(openMenu);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(fileListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(compressButton)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(compressButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
     private void setFileChooser() {
         fc = new JFileChooser();
         fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     }
     
    private void openMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuActionPerformed

        int res = fc.showOpenDialog(null);
        if(res==JFileChooser.APPROVE_OPTION){
            folder = fc.getSelectedFile();
            showFolderContent(folder);
        }
        
    }//GEN-LAST:event_openMenuActionPerformed

    private void compressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compressButtonActionPerformed
        fs = new JFileChooser();
        fs.setSelectedFile(new File(folder.getName()));
        fs.setMultiSelectionEnabled(true);
        int res = fs.showSaveDialog(null);
        if(res==JFileChooser.APPROVE_OPTION){
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            LoadingFrame lf= new LoadingFrame();
            lf.setParentFrame(this);
            lf.setVisible(true);
            ih = new ImageHandler(fs.getSelectedFile().getAbsolutePath(),getSelectedFiles(filesList.getSelectedValuesList()),lf);
            ih.execute();
           
            //ImageHandler.compressImage(fs.getSelectedFile().getAbsolutePath(),getSelectedFiles(filesList.getSelectedValuesList()));
           
           
        }
          this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_compressButtonActionPerformed
    private void showFolderContent(File folder){
        File[] listOfFiles = folder.listFiles();
        List<String> listOfFileNames = new LinkedList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                listOfFileNames.add(listOfFiles[i].getName());
            };
            filesList.setListData(listOfFileNames.toArray(new String[0]));
        
        }
       //  filesList.setListData(folder.list()); Con este método también se añaden las carpetas
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton compressButton;
    private javax.swing.JScrollPane fileListScrollPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JList<String> filesList;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenu;
    // End of variables declaration//GEN-END:variables

    private List<String> getSelectedFiles(List<String> selectedFiles ) {
        List<String> ret = new LinkedList();
        File[] a= folder.listFiles();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < selectedFiles.size(); j++) {
                if(a[i].getName().equals(selectedFiles.get(j))){
                   ret.add(a[i].getAbsolutePath());
                }     
            }   
        }
      
        return ret;
    }
    public void cancelTask(){
        ih.cancel(true);
    }
}
