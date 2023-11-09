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
public class OsveziBrojAngazovanja extends Thread{
    ServerskaForma sf;

    public OsveziBrojAngazovanja() {
    }

    public OsveziBrojAngazovanja(ServerskaForma sf) {
        this.sf = sf;
    }
    
    @Override
    public void run() {
        while (true) {            
            sf.popuniBrojAngaozvanja();
            System.out.println("Osvezeno");
             try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziBrojprofesora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
