/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.ServerskaForma;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class OsveziBrojProfPoZvanju extends Thread{
    ServerskaForma sf;

    public OsveziBrojProfPoZvanju() {
    }

    public OsveziBrojProfPoZvanju(ServerskaForma sf) {
        this.sf = sf;
    }
    
     public void run() {
        while (true) {            
            sf.osveziBrojProfPoZvanju();
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsvezavanjeBrojAngazovanjaProf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
