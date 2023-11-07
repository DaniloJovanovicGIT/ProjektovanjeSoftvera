/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Angazovanje;
import domen.Korisnik;
import domen.Predmet;
import domen.Profesor;
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
                    Korisnik ulogovani = Kontroler.getInstanca().login(k);
                    if (ulogovani == null) {
                        odgovor.setPoruka("Neuspesno prijavljivanje na sistem");
                    } else {
                        odgovor.setParametar(ulogovani);
                    }
                    break;
                case Operacija.SACUVAJ_PROFESORA:
                    Profesor p = (Profesor) zahtev.getParametar();
                    boolean sacuvano = Kontroler.getInstanca().sacuvajProfesora(p);
                    if (sacuvano == false) {
                        odgovor.setPoruka("Doslo je do greske prilikom cuvanja profesora.");
                    } else {
                        odgovor.setParametar(sacuvano);
                    }
                    break;
                case Operacija.VRATI_PROFESORE:
                    
                    ArrayList<Profesor> profeosori= Kontroler.getInstanca().vratiProfesore();
                    if (profeosori == null) {
                        odgovor.setPoruka("Doslo je do greske prilikom vracanja profesora.");
                    } else {
                        odgovor.setParametar(profeosori);
                    }
                    break;
                case Operacija.SACUVAJ_PREDMET:
                    
                    Predmet predmet = (Predmet) zahtev.getParametar();
                    boolean sacuvanPredmet = Kontroler.getInstanca().sacuvajPredmet(predmet);
                    if (sacuvanPredmet == false) {
                        odgovor.setPoruka("Doslo je do greske prilikom cuvanja predmeta.");
                        odgovor.setParametar(sacuvanPredmet);
                    } else {
                        odgovor.setParametar(sacuvanPredmet);
                    }
                    break;
                case Operacija.VRATI_ANGAZOVANJA_ZA_PROFESORA:
                    
                    Profesor profesor = (Profesor) zahtev.getParametar();
                    ArrayList<Angazovanje> angazovanjaProfesora = Kontroler.getInstanca().vratiAngazovanjaProfesora(profesor);
                    if (angazovanjaProfesora == null) {
                        odgovor.setPoruka("Doslo je do greske prilikom vracanja angazovanja.");
                        
                    } else {
                        odgovor.setParametar(angazovanjaProfesora);
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
