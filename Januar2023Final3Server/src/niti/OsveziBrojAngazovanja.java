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
public class OsveziBrojAngazovanja extends Thread{
    ServerskaForma sf;

    public OsveziBrojAngazovanja(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {            
            sf.osveziBrojAngazovanja();
            System.out.println("Osvezio angazovanja");
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziBrojAngazovanja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
