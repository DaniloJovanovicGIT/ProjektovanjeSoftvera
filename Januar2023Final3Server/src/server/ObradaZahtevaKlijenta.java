/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Angazovanje;
import domen.Korisnik;
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
                    Korisnik korisnik = (Korisnik) zahtev.getParamtear();
                    Korisnik ulogovani = Kontroler.getInstance().login(korisnik);
                    if (ulogovani == null) {
                        odgovor.setPoruka("Neuspesno prijavljivanje.");
                    } else {
                        odgovor.setParametar(ulogovani);
                    }
                    break;
                case Operacija.SACUVAJ_PROFESORA:
                    Profesor profesor = (Profesor) zahtev.getParamtear();
                    boolean profesorSacuvan = Kontroler.getInstance().sacuvajProfesora(profesor);
                    if (profesorSacuvan == false) {
                        odgovor.setPoruka("Neuspesno cuvanje profesora.");
                    } else {
                        odgovor.setParametar(profesorSacuvan);
                    }
                    break;
                case Operacija.VRATI_PROFESORE:
                    ArrayList<Profesor> listaProfesora = null;
                    listaProfesora = Kontroler.getInstance().vratiProfesore();
                    if (listaProfesora == null) {
                        odgovor.setPoruka("Neuspesno cuvanje profesora.");
                    } else {
                        odgovor.setParametar(listaProfesora);
                    }
                    break;
                case Operacija.SACUVAJ_ANGAZOVANJE_PREDMET:
                    Angazovanje angazovanje = (Angazovanje) zahtev.getParamtear();
                    boolean angazovanjeSacuvano = Kontroler.getInstance().sacuvajAngazovanje(angazovanje);
                    if (angazovanjeSacuvano == false) {
                        odgovor.setPoruka("Neuspesno cuvanje angazovanja i predmeta.");
                    } else {
                        odgovor.setParametar(angazovanjeSacuvano);
                    }
                    break;
                case Operacija.BROJ_ANGAOVANJA_PROFESORA:
                    Profesor prof = (Profesor) zahtev.getParamtear();
                    int broj = Kontroler.getInstance().brojAngazovanjeProfesora(prof);
                    if (broj == -1) {
                        odgovor.setPoruka("Neuspesno cuvanje angazovanja i predmeta.");
                    } else {
                        odgovor.setParametar(broj);
                    }
                    break;

                case Operacija.VRATI_ANGAZOVANJA_ZA_PROFESORA:
                    Profesor izabrani = (Profesor) zahtev.getParamtear();
                    ArrayList<Angazovanje> listaAngazovnja = null;
                    listaAngazovnja = Kontroler.getInstance().vratiAngazovanjaZaProfesora(izabrani);
                    if (listaAngazovnja == null) {
                        odgovor.setPoruka("Neuspesno vracanje angazpvanja.");
                    } else {
                        odgovor.setParametar(listaAngazovnja);
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
