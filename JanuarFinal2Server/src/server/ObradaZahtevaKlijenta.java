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

    public ObradaZahtevaKlijenta() {
    }

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
                    Korisnik korisnik = (Korisnik) zahtev.getParametar();
                    Korisnik ulogovani = Kontroler.getInstance().login(korisnik);
                    if (ulogovani == null) {
                        odgovor.setPoruka("Neuspesno prijavljivanje");
                    } else {
                        odgovor.setParametar(ulogovani);
                    }
                    break;
                case Operacija.SACUVAJ_PROFESORA:
                    Profesor noviProfesor = (Profesor) zahtev.getParametar();
                    boolean sacuvanProfesor = Kontroler.getInstance().sacuvajProfesora(noviProfesor);
                    if (sacuvanProfesor == false) {
                        odgovor.setPoruka("Neuspesno prijavljivanje");
                    } else {
                        odgovor.setParametar(sacuvanProfesor);
                    }
                    break;
                case Operacija.VRATI_PROFESORE:
                    ArrayList<Profesor> listaProfesora = Kontroler.getInstance().vratiProfesore();
                    if (listaProfesora == null) {
                        odgovor.setPoruka("Neuspesno vracanje profesora");
                    } else {
                        odgovor.setParametar(listaProfesora);
                    }
                    break;
                case Operacija.VRATI_ANGAZOVANJA_ZA_PROFESORA:
                    Profesor profesor = (Profesor) zahtev.getParametar();
                    ArrayList<Angazovanje> listaAgnazovanja = Kontroler.getInstance().vratiAngazovanjaZaProfesora(profesor);
                    if (listaAgnazovanja == null) {
                        odgovor.setPoruka("Neuspesno vracanje profesora");
                    } else {
                        odgovor.setParametar(listaAgnazovanja);
                    }
                    break;
                case Operacija.VRATI_PREDMETE:
                    ArrayList<Predmet> listaPredmeta = Kontroler.getInstance().vratiPredmete();
                    if (listaPredmeta == null) {
                        odgovor.setPoruka("Neuspesno vracanje predmeta");
                    } else {
                        odgovor.setParametar(listaPredmeta);
                    }
                    break;
                case Operacija.SACUVAJ_ANGAZOVANJA:
                    ArrayList<Angazovanje> listaAngazovanja =  (ArrayList<Angazovanje>) zahtev.getParametar();
                    boolean sacuvanaAngazovanja = Kontroler.getInstance().sacuvajAngazovanja(listaAngazovanja);
                    if (sacuvanaAngazovanja == false) {
                        odgovor.setPoruka("Neuspesno sacuvana angazovanja");
                    } else {
                        odgovor.setParametar(sacuvanaAngazovanja);
                    }
                    break;
                default:
                    System.out.println("Nepodrzana operacija");
            }
            System.out.println("Odgovor posalt");
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
