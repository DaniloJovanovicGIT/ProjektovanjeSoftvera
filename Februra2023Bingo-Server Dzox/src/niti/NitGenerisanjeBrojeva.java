/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.ServerskaFroma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class NitGenerisanjeBrojeva extends Thread{
    boolean aktivna;
    ServerskaFroma sf;
    int[] brojevi;

    public NitGenerisanjeBrojeva() {
    }

    public NitGenerisanjeBrojeva(boolean aktivna, ServerskaFroma sf) {
        this.aktivna = aktivna;
        this.sf = sf;
        brojevi = new int[4];
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    
    @Override
    public void run() {
        while (true) {
            System.out.println("nit aktivna");
        if(aktivna)
            try {
                System.out.println("Nit je aktivna");
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitGenerisanjeBrojeva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
