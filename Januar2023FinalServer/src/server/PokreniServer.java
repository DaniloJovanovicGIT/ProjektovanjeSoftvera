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
public class PokreniServer extends Thread{
    ServerSocket ss;
    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            while (true) {
                Socket klijent = ss.accept();
                ObradaZahtevaKlijenta ozk = new ObradaZahtevaKlijenta(klijent);
                ozk.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
