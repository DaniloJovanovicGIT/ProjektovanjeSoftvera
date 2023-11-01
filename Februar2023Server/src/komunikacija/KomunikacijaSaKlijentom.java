/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
public class KomunikacijaSaKlijentom {
    public static KomunikacijaSaKlijentom instance;

    private KomunikacijaSaKlijentom() {
    }

    public static KomunikacijaSaKlijentom getInstance() {
        if(instance==null){
        instance = new KomunikacijaSaKlijentom();
        }
        return instance;
    }
    
     public Zahtev primiZahtev(Socket klijent) {
        try {
            ObjectInputStream ois = new ObjectInputStream(klijent.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Klijentski soket je zatvoren");
        }
        return null;
    }

    public void posaljiOdgovor(Socket klijent,Odgovor odgovor) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(klijent.getOutputStream());
            oos.writeObject(odgovor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
