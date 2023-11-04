/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Festival;
import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
class ObradiZahtevKlijenta extends Thread {

    Socket socket;

    public ObradiZahtevKlijenta(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            Zahtev zahtev = primiZahtev();
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
                case Operacija.LOGIN:
                    Korisnik k = (Korisnik) zahtev.getParametar();
                    boolean uspesno = Kontroler.getInstance().login(k);
                    odgovor.setParametar(uspesno);
                    break;
                case Operacija.VRATI_FESTIVALE:             
                    ArrayList<Festival> listaSvihFestivala = Kontroler.getInstance().vratiSveFestivale();
                    odgovor.setParametar(listaSvihFestivala);
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(odgovor);
        }
    }

    private Zahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradiZahtevKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(Odgovor odgovor) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(odgovor);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradiZahtevKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
