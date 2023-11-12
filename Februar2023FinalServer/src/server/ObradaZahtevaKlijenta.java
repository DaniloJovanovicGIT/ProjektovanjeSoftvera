/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author Danilo
 */
import domen.Korisnik;
import domen.Poruka;
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

class ObradaZahtevaKlijenta extends Thread {

    Socket socket;

    public ObradaZahtevaKlijenta(Socket klijent) {
        socket = klijent;
    }

    @Override
    public void run() {
        while (true) {
            Zahtev zahtev = primiZahtev(socket);
            Odgovor odgovor = new Odgovor();
            odgovor.setOperacija(zahtev.getOperacija());
            switch (zahtev.getOperacija()) {
                case Operacija.LOGIN:
                    Korisnik korisnik = (Korisnik) zahtev.getParametar();
                    Korisnik ulogovani = Kontroler.getInstance().loginKorisnika(korisnik, socket);
                    if (ulogovani == null) {
                        odgovor.setPoruka("Korisnik ne postoji u bazi");
                    } else {
                        odgovor.setPamatear(ulogovani);
                    }
                    break;
                case Operacija.VRATI_ULOGOVANE:
                    ArrayList<String> listaUlogovanih = null;
                    listaUlogovanih = Kontroler.getInstance().vratiUlogovane();
                    if (listaUlogovanih == null) {
                        odgovor.setPoruka("Nije moguce vratit listu ulogovanih");
                    } else {
                        odgovor.setPamatear(listaUlogovanih);
                    }
                    break;
                case Operacija.POSALJI_PORUKU:
                    Poruka poruka = (Poruka) zahtev.getParametar();
                    boolean porukaPoslata = Kontroler.getInstance().sacuvajPoruku(poruka);
                    if (porukaPoslata == false) {
                        odgovor.setPoruka("Nije moguce sacuvati poruku.");
                    } else {
                        odgovor.setPamatear(porukaPoslata);
                        ObavestiNovaPoruka();
                    }
                    break;
                case Operacija.VRATI_PORUKE_ZA_KORISNIKA:
                    Korisnik ulogovaniKorisnik = (Korisnik) zahtev.getParametar();
                    ArrayList<Poruka> listaPoruka = null;
                    listaPoruka = Kontroler.getInstance().vratiPorukeZaKorisnika(ulogovaniKorisnik);
                    if (listaPoruka == null) {
                        odgovor.setPoruka("Nije moguce vratit listu pruka");
                    } else {
                        odgovor.setPamatear(listaPoruka);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(odgovor, socket);
        }
    }

    public Zahtev primiZahtev(Socket socket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void posaljiOdgovor(Odgovor odgovor, Socket socket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(odgovor);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ObavestiNovaPoruka() {
        Kontroler.getInstance().obavestiNovaPruka();
    }

}
