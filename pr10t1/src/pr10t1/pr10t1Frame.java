package pr10t1;

import java.awt.List;
import javax.swing.SwingWorker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class pr10t1Frame extends javax.swing.JFrame {

    /**
     * Creates new form pr10t1Frame
     */
    private class Tarea extends SwingWorker<Void, Integer> { 
        @Override
        protected Void doInBackground() {
            boolean looping = true;
            Integer cuenta = 0;
            while(looping && !isCancelled()){
                try{
                    Thread.sleep(100);
                } catch(InterruptedException e){}
                cuenta ++;
                publish(cuenta);
                if(cuenta >= 100) looping = false;
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> lista) {
            int last = lista.size(); 
            progressBar.setValue(lista.get(last-1).intValue()); //To change body of generated methods, choose Tools | Templates.
        }



        @Override
        public void done() {
            cancelButton.setEnabled(false); 
            acceptButton.setEnabled(true); 
            informationLabel.setText("Tarea Finalizada!"); 
            progresoDialog.setCursor(null);
        }
    }
    
    public pr10t1Frame() {
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

        progresoDialog = new javax.swing.JDialog();
        informationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        acceptButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        authorLabel = new javax.swing.JLabel();
        runButton = new javax.swing.JButton();

        informationLabel.setText(" ");

        acceptButton.setText("Aceptar");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout progresoDialogLayout = new javax.swing.GroupLayout(progresoDialog.getContentPane());
        progresoDialog.getContentPane().setLayout(progresoDialogLayout);
        progresoDialogLayout.setHorizontalGroup(
            progresoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progresoDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(progresoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(progresoDialogLayout.createSequentialGroup()
                        .addComponent(informationLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(progresoDialogLayout.createSequentialGroup()
                        .addComponent(acceptButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        progresoDialogLayout.setVerticalGroup(
            progresoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progresoDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(informationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(progresoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Demo concurrencia");

        authorLabel.setText("Héctor Garbisu DIU 2015");

        runButton.setText("Run in Background");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(authorLabel)
                    .addComponent(runButton))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(runButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(authorLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        progresoDialog.setVisible(true);
        progresoDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        acceptButton.setEnabled(false);
        cancelButton.setEnabled(true); 
        informationLabel.setText("Realizando Tarea...");
        progressBar.setValue(0); 
        trabajo = new Tarea();
        trabajo.execute();
        runButton.setEnabled(false);
    }//GEN-LAST:event_runButtonActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        progresoDialog.setVisible(false);
        runButton.setEnabled(true);
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        trabajo.cancel(true);
        informationLabel.setText("Tarea cancelada por el Usuario");
    }//GEN-LAST:event_cancelButtonActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pr10t1Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pr10t1Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pr10t1Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pr10t1Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pr10t1Frame().setVisible(true);
            }
        });
    }
    private Tarea trabajo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel informationLabel;
    private javax.swing.JDialog progresoDialog;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton runButton;
    // End of variables declaration//GEN-END:variables
}
