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
            System.out.println("Server pokrenut");
            while (true) {
                Socket klijent = ss.accept();
                System.out.println("Klijent se povezao");
                ObradaZahtevaKlijenta ozk = new ObradaZahtevaKlijenta(klijent);
                ozk.start();
            }
        } catch (IOException ex) {
            System.err.println("Server ugasen");
        }
    }

    public void zaustaviServer() throws IOException{
        ss.close();
    }
}
