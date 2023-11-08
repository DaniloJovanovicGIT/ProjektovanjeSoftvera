/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Delo;
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
class ObradaZahtevaKlijenta extends Thread {

    Socket socket;
    
    public ObradaZahtevaKlijenta(Socket socket) {
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
                    Korisnik ulogovani = Kontroler.getInstance().login(k);
                    if (ulogovani == null) {
                        odgovor.setPoruka("Neuspesno prijavljivanje");
                    } else {
                        odgovor.setParametar(ulogovani);
                    }
                    break;
                case Operacija.VRATI_FESTIVALE:
                    
                    ArrayList<Festival> listaFestivala = Kontroler.getInstance().vratiFestivale();
                    if (listaFestivala == null) {
                        odgovor.setPoruka("Neuspesno vracanje festivala");
                    } else {
                        odgovor.setParametar(listaFestivala);
                    }
                    break;
                case Operacija.VRATI_DELA:
                    
                    ArrayList<Delo> listaDela = Kontroler.getInstance().vratiDela();
                    if (listaDela == null) {
                        odgovor.setPoruka("Neuspesno vracanje dela");
                    } else {
                        odgovor.setParametar(listaDela);
                    }
                    break;
                case Operacija.IZMENI_FESTIVAL:
                    Festival festival = (Festival) zahtev.getParametar();
                    boolean izmenjenFestival = Kontroler.getInstance().izmeniFestival(festival);
                    if (izmenjenFestival == false) {
                        odgovor.setPoruka("Neuspesna izmena festivala");
                    } else {
                        odgovor.setParametar(izmenjenFestival);
                    }
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
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void posaljiOdgovor(Odgovor odgovor) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(odgovor);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
