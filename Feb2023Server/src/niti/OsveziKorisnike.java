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
public class OsveziKorisnike extends Thread {

    ServerskaForma sf;

    public OsveziKorisnike(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {
            sf.popuniKorisnike();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziKorisnike.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
