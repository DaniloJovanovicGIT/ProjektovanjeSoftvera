/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import transfer.Odgovor;
import transfer.Operacija;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
class ObradaZahtevaKlijenta extends Thread {

    Socket socket;

    ObradaZahtevaKlijenta(Socket klijent) {
        socket = klijent;
    }

    @Override
    public void run() {
        while (true) {
            Zahtev zahtev = primiZahtev();
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
                case Operacija.LOGIN:
                    Korisnik k = (Korisnik) zahtev.getParametar();
                    Korisnik ulogovani = Kontroler.getInstanca().ulogujKorinsika(k);
                    if (ulogovani != null) {
                        odgovor.setParametar(ulogovani);
                    } else {
                        odgovor.setPoruka("Neuspesno prijavljivanje");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private Zahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
