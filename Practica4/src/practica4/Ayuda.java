/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class Ayuda extends javax.swing.JFrame {

    //private JTabbedPane panelPestanias;
    private JPanel panel1;
    private JPanel panel2;
    private JButton boton1;
    private JButton boton2;
    private JTextArea areaTexto1;
    private JTextArea areaTexto2;

    public Ayuda() {
        initComponents();
        setLayout();
    }

    private void setLayout() {
        this.getContentPane().setLayout(new GridBagLayout());

        //panelPestanias = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        boton1 = new JButton();
        boton2 = new JButton();
        areaTexto1 = new JTextArea();
        areaTexto2 = new JTextArea();

        //panelPestanias.addTab("Pestaña 1", boton1);
        //panelPestanias.addTab("Pestaña 2", boton2);
        boton1.setText("Boton 1");
        boton2.setText("Boton 2");
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.VERTICAL;
        this.getContentPane().add(panel1, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        this.getContentPane().add(panel2, cons);
        
        areaTexto1.setText("asdasd");
        areaTexto2.setText("asdasd");
        
        areaTexto1.setEditable(false);
        areaTexto2.setEditable(false);
        
        panel1.add(areaTexto1 , cons);
        //panel1.add(panelPestanias);
        panel2.add(areaTexto2, cons);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ayuda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
