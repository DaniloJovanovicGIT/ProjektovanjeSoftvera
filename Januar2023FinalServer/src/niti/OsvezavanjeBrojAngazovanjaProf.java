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
public class OsvezavanjeBrojAngazovanjaProf extends Thread{
    ServerskaForma sf;

    public OsvezavanjeBrojAngazovanjaProf(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {            
            sf.osveziBrojAngazovanjaProf();
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsvezavanjeBrojAngazovanjaProf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
