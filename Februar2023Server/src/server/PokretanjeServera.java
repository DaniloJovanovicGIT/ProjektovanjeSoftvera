/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class PokretanjeServera extends Thread {

    ServerSocket ss;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);

            while (true) {
                Socket klijent = ss.accept();
                ObradaZahtevaKlijenta nk = new ObradaZahtevaKlijenta(klijent);
                nk.start();
            }
        } catch (IOException ex) {
            System.err.println("Server je zaustavljen");
        }
    }

    public void zaustaviServer() {
        //odjavi sve korisnike
        try {
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
