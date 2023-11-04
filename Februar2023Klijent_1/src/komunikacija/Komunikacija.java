/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
public class Komunikacija {
    Socket soket;
    private static Komunikacija komunikacija;

    private Komunikacija() {
        try {
            soket = new Socket("localhost",9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Komunikacija getKomunikacija() {
       if(komunikacija==null){
       komunikacija = new Komunikacija();
       }
       return komunikacija;
    }
    
    public Odgovor primiOdgovor() {
        try {
            ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
            return (Odgovor) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void posaljiZahtev(Zahtev zahtev) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
            oos.writeObject(zahtev);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
}
