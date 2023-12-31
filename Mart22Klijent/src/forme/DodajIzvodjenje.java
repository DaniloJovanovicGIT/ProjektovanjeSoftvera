/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import domen.Delo;
import domen.Festival;
import domen.Izvodjenje;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Komunikacija;
import transfer.Odgovor;
import transfer.Operacija;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
public class DodajIzvodjenje extends javax.swing.JDialog {

    Festival festival;

    /**
     * Creates new form DodajIzvodjenje
     */
    public DodajIzvodjenje(Frame parent, boolean modal, Festival festival) {
        super(parent, modal);
        initComponents();
        this.festival = festival;
        popuniDela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbDela = new javax.swing.JComboBox();
        txtVremePocekta = new javax.swing.JTextField();
        txtVremeKraja = new javax.swing.JTextField();
        txtSala = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dodaj izvodjenje");

        jLabel1.setText("Izaberi delo ");

        jLabel2.setText("Datum i vreme pocekta");

        jLabel3.setText("Datum i vreme kraja");

        jLabel4.setText("Sala");

        cmbDela.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Dodaj izvodjenje");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbDela, 0, 185, Short.MAX_VALUE)
                            .addComponent(txtVremePocekta)
                            .addComponent(txtVremeKraja)
                            .addComponent(txtSala))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbDela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtVremePocekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date datumVremeOd = new Date();
        Date datumVremeDo = new Date();
        try {
            datumVremeOd = sdf.parse(txtVremePocekta.getText());
            datumVremeDo = sdf.parse(txtVremeKraja.getText());
            Izvodjenje izvodjenje = new Izvodjenje(festival, (Delo) cmbDela.getSelectedItem(), datumVremeOd, datumVremeOd, txtSala.getText(), "dodato");
            IzmeniFestivalForma iif = (IzmeniFestivalForma) getParent();
            System.out.println("Poslato formi");
            iif.prihvatiIzvodjenje(izvodjenje);
            this.dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Format datuma mora biti dd.MM.yyyy");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbDela;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtSala;
    private javax.swing.JTextField txtVremeKraja;
    private javax.swing.JTextField txtVremePocekta;
    // End of variables declaration//GEN-END:variables

    private void popuniDela() {
        Komunikacija.getInstanca().posaljiZahtev(new Zahtev(Operacija.VRATI_DELA, null));
        Odgovor odgovor = Komunikacija.getInstanca().primiOdgovor();
        cmbDela.removeAllItems();
        if (odgovor.getParametar() != null) {
            ArrayList<Delo> listaDela = (ArrayList<Delo>) odgovor.getParametar();
            for (Delo delo : listaDela) {
                cmbDela.addItem(delo);
            }
        }
    }
}
