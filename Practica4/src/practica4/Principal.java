/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class Principal extends javax.swing.JFrame implements KeyListener, ActionListener {

    private JMenuBar barraMenu;
    private JTextArea areaTexto;
    private JLabel tamanioLabel;
    private JMenu archivo;
    private JMenu edicion;
    private JMenu aplicacion;
    private JMenuItem abrir;
    private JMenuItem cerrar;
    private JMenuItem colorFuente;
    private JMenuItem ayuda;
    private JMenuItem estadisticas;
    private JMenuItem salir;


    public Principal() {
        initComponents();
        GestorBD.setLogin("root");
        GestorBD.setPasswd("");
        setFrame();
        setLayout();
        setMenuBarra();
    }

    private void setFrame() {
        this.setTitle("Práctica4");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pulsarSalir();
            }
        });
        
    }

    private void setLayout() {
        this.getContentPane().setLayout(new BorderLayout());

        barraMenu = new JMenuBar();
        areaTexto = new JTextArea();
        tamanioLabel = new JLabel();

        this.getContentPane().add(barraMenu, BorderLayout.PAGE_START);
        this.getContentPane().add(areaTexto, BorderLayout.CENTER);
        this.getContentPane().add(tamanioLabel, BorderLayout.PAGE_END);

        areaTexto.setEditable(false);
        areaTexto.addKeyListener(this);

    }

    private void setMenuBarra() {
        archivo = new JMenu();
        edicion = new JMenu();
        aplicacion = new JMenu();

        archivo.setText("Archivo");
        edicion.setText("Edición");
        aplicacion.setText("Aplicación");

        archivo.setMnemonic(KeyEvent.VK_A);
        edicion.setMnemonic(KeyEvent.VK_E);

        barraMenu.add(archivo);
        barraMenu.add(edicion);
        barraMenu.add(aplicacion);

        abrir = new JMenuItem();
        cerrar = new JMenuItem();
        colorFuente = new JMenuItem();
        ayuda = new JMenuItem();
        estadisticas = new JMenuItem();
        salir = new JMenuItem();

        abrir.setText("Abrir");
        cerrar.setText("Cerrar");
        colorFuente.setText("Color fuente");
        ayuda.setText("Ayuda");
        estadisticas.setText("Estadísticas");
        salir.setText("Salir");

        abrir.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        cerrar.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        colorFuente.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        ayuda.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        estadisticas.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        salir.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        cerrar.setEnabled(false);
        colorFuente.setEnabled(false);

        archivo.addActionListener(this);
        edicion.addActionListener(this);
        aplicacion.addActionListener(this);

        abrir.addActionListener(this);
        cerrar.addActionListener(this);
        colorFuente.addActionListener(this);
        ayuda.addActionListener(this);
        estadisticas.addActionListener(this);
        salir.addActionListener(this);

        archivo.add(abrir);
        archivo.add(cerrar);
        edicion.add(colorFuente);
        aplicacion.add(ayuda);
        aplicacion.add(estadisticas);
        aplicacion.add(salir);

    }

    private void pulsarAbrir() {

        JFileChooser chooser = new JFileChooser();

        chooser.setMultiSelectionEnabled(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        chooser.setAcceptAllFileFilterUsed(false);

        int resp = chooser.showOpenDialog(this);

        if (resp == JFileChooser.APPROVE_OPTION) {
            cerrar.setEnabled(true);
            colorFuente.setEnabled(false);
        
            File f = chooser.getSelectedFile();
            
            this.setTitle("Práctica4 - " + f.getName());
            areaTexto.setText("");
            tamanioLabel.setText("Tamaño: " + f.length() + " bytes");
            leerFichero(f.getAbsolutePath());
        }
//        } else if (resp == JFileChooser.CANCEL_OPTION) {
//            System.out.println("Cancelar");
//        } else if (resp == JFileChooser.ERROR_OPTION) {
//            System.out.println("Error");
//        }
    }

    private void leerFichero(String ruta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ruta));
            String frase;
            do {
                frase = br.readLine();
                if (frase != null) {
                    areaTexto.append(frase + "\n");
                }
            } while (frase != null);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void pulsarCerrar() {
        this.setTitle("Práctica4");
        areaTexto.setText("");
        tamanioLabel.setText("");
        cerrar.setEnabled(false);
        colorFuente.setEnabled(false);
    }

    private void pulsarColorFuente() {

    }

    private void pulsarAyuda() {

    }
    
    private void pulsarEstadisticas() {
        try {
            Connection con = GestorBD.abrirConexion();
            String sql = "SELECT COUNT(*) FROM logayuda WHERE contenido=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            int contenido = 0;
            int dinamicamente = 0;
            
            while (rs.next()) {                
                contenido = rs.getInt(1);
            }
            
            sql = "SELECT COUNT(*) FROM logayuda WHERE buscar=1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                dinamicamente = rs.getInt(1);
            }
            
            JOptionPane.showMessageDialog(this, "Consultas realizadas por contenido: " + contenido + "\nConsultas realizadas dinámicamente: " + dinamicamente);
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            GestorBD.cerrarConexion();
        }
    }

    private void pulsarSalir() {
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea finalizar el programa?", "Informacion.", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String pulsado = ae.getActionCommand();
        if (pulsado.equals(abrir.getText())) {
            pulsarAbrir();
        } else if (pulsado.equals(cerrar.getText())) {
            pulsarCerrar();
        } else if (pulsado.equals(colorFuente.getText())) {
            pulsarColorFuente();
        } else if (pulsado.equals(ayuda.getText())) {
            pulsarAyuda();
        } else if (pulsado.equals(estadisticas.getText())) {
            pulsarEstadisticas();
        } else if (pulsado.equals(salir.getText())) {
            pulsarSalir();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_F1) {
            JOptionPane.showMessageDialog(this, "Pulsado F1");
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
