/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view.cliente;

import mvc.controller.Login;
import mvc.model.ClienteDAO;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;
import mvc.view.TelaLogin;

/**
 *
 * @author leonardo
 */
public class TelaPrincipal extends javax.swing.JFrame {
    private Login logado = new Login();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ContaCorrenteDAO correnteDAO = new ContaCorrenteDAO();
    private ContaCorrente correnteCliente = correnteDAO.encontrarConta(logado.getLogado(), clienteDAO);

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuArquivoSair = new javax.swing.JMenuItem();
        menuCC = new javax.swing.JMenu();
        menuCCSaqueDep = new javax.swing.JMenuItem();
        menuCCExtrato = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Banco de Investimentos - Cliente");
        setResizable(false);

        menuArquivo.setText("Arquivo");

        menuArquivoSair.setText("Sair");
        menuArquivoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArquivoSairActionPerformed(evt);
            }
        });
        menuArquivo.add(menuArquivoSair);

        jMenuBar1.add(menuArquivo);

        menuCC.setText("Conta Corrente");

        menuCCSaqueDep.setText("Saque/Deposito");
        menuCCSaqueDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCCSaqueDepActionPerformed(evt);
            }
        });
        menuCC.add(menuCCSaqueDep);

        menuCCExtrato.setText("Consultar extrato");
        menuCCExtrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCCExtratoActionPerformed(evt);
            }
        });
        menuCC.add(menuCCExtrato);

        jMenuBar1.add(menuCC);

        jMenu1.setText("Investimentos");

        jMenu4.setText("Poupança");
        jMenu1.add(jMenu4);

        jMenu5.setText("CDB");
        jMenu1.add(jMenu5);

        jMenu6.setText("Fundos");
        jMenu1.add(jMenu6);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Sobre");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 803, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuCCExtratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCCExtratoActionPerformed
        new TelaConsultarExtratoCorrente().setVisible(true);
    }//GEN-LAST:event_menuCCExtratoActionPerformed

    private void menuCCSaqueDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCCSaqueDepActionPerformed
        new TelaSaqueDepositoCC().setVisible(true);
    }//GEN-LAST:event_menuCCSaqueDepActionPerformed

    private void menuArquivoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArquivoSairActionPerformed
        this.dispose();
        new TelaLogin().setVisible(true);
    }//GEN-LAST:event_menuArquivoSairActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuItem menuArquivoSair;
    private javax.swing.JMenu menuCC;
    private javax.swing.JMenuItem menuCCExtrato;
    private javax.swing.JMenuItem menuCCSaqueDep;
    // End of variables declaration//GEN-END:variables
}
