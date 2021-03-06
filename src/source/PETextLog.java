/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Font;
import java.io.RandomAccessFile;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollBar;
import util.MPSoCConfig;

/**
 *
 * @author mruaro
 */
public class PETextLog extends javax.swing.JPanel {

    
    private int PE;
    private MPSoCConfig mPSoCConfig;
    private boolean readLogEnabled;
    
    public PETextLog(int PE, MPSoCConfig mPSoCConfig) {
        this.PE = PE;
        this.mPSoCConfig = mPSoCConfig;
        this.readLogEnabled = false;
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(15);
    }

    
    public void readLogThread(){
        new Thread() {
            @Override
            public void run() {
                while (readLogEnabled) {
                    try {
                        updateLog();
                        
                        
                        
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TaskMappingFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        continuousReadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        readLogButton = new javax.swing.JButton();

        continuousReadButton.setText("Continuous Read");
        continuousReadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuousReadButtonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        readLogButton.setText("Read Log Once");
        readLogButton.setMaximumSize(new java.awt.Dimension(122, 38));
        readLogButton.setMinimumSize(new java.awt.Dimension(122, 38));
        readLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readLogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(readLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continuousReadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continuousReadButton)
                    .addComponent(readLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void continuousReadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuousReadButtonActionPerformed
        if (!readLogEnabled){
            continuousReadButton.setFont(new Font ("Ubuntu", Font.BOLD, 15));
            continuousReadButton.setText("Reading...");
            continuousReadButton.setSelected(true);
            continuousReadButton.setOpaque(true);
            readLogEnabled = true;
            readLogThread();
        } else {
            continuousReadButton.setFont(new Font ("Ubuntu", Font.PLAIN, 15));
            continuousReadButton.setSelected(false);
            continuousReadButton.setOpaque(false);
            readLogEnabled = false;
            continuousReadButton.setText("Continuous Read");
        }
    }//GEN-LAST:event_continuousReadButtonActionPerformed

    private void readLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readLogButtonActionPerformed
        updateLog();
    }//GEN-LAST:event_readLogButtonActionPerformed

    private void updateLog(){
       try {
            //int PE_xy = mPSoCConfig.ham_to_xy_addr(PE);
            String x = Integer.toString(PE >> 8);
            String y = Integer.toString(PE & 0xFF);
            String router_name = x+"x"+y;
                
            RandomAccessFile rf = new RandomAccessFile(mPSoCConfig.getTestcasePath() + "/debug/log/log_"+router_name+".log", "r");
            String line;
            String text = "";
            
            while ((line = rf.readLine()) != null) {
                text+=line+"\n";
            }

            jTextArea1.setText(text);
            //Set the scrool bar at the botoom while running
            JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
            vertical.setValue( vertical.getMaximum() );
            jScrollPane1.revalidate();
            this.revalidate();

        } catch (Exception e) {
        }
    }
    
    public void disablePELogReading(){
        readLogEnabled = false;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continuousReadButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton readLogButton;
    // End of variables declaration//GEN-END:variables
}
