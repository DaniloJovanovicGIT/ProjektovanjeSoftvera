/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class OsveziBrojprofesora extends Thread {

    ServerskaForma sf;

    public OsveziBrojprofesora() {
    }

    public OsveziBrojprofesora(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {
            sf.popuniBrojProfesora();
            System.out.println("Osvezeno");

            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziBrojprofesora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
