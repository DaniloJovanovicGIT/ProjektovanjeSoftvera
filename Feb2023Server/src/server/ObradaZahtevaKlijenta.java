/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Korisnik;
import domen.Poruka;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KomunikacijaSaKlijentom;
import konstante.Operacija;
import logika.Kontroler;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
public class ObradaZahtevaKlijenta extends Thread {

    Socket klijent;
    boolean kraj = false;
    boolean uspesanLogin = false;
    boolean preskociPojedninacnoSlanje = false;

    public ObradaZahtevaKlijenta(Socket klijent) {
        this.klijent = klijent;
    }

    @Override
    public void run() {
        while (!kraj) {

            Zahtev zahtev = KomunikacijaSaKlijentom.getInstance().primiZahtev(klijent);
            Odgovor odgovor = new Odgovor();
            int operacija = zahtev.getOperacija();
            odgovor.setOperacija(operacija);
            uspesanLogin = false;
            preskociPojedninacnoSlanje = false;

            switch (operacija) {
                case Operacija.LOGIN:
                    Korisnik korisnik = (Korisnik) zahtev.getParametar();
                    String korIme = korisnik.getKorisnickoIme();
                    if (Kontroler.getInstanca().getMapaUlogovanihKorisnika().get(korIme) != null) {
                        odgovor.setOdgovor(null);
                    } else {
                        Korisnik ulogovaniKorisnik = Kontroler.getInstanca().ulogujKorisnika(korisnik.getKorisnickoIme(), korisnik.getKorisnickoLozinka());
                        if (ulogovaniKorisnik != null) {
                            Kontroler.getInstanca().getMapaUlogovanihKorisnika().put(korIme, klijent);
                            uspesanLogin = true;
                        }
                        odgovor.setOdgovor(ulogovaniKorisnik);
                    }
                    break;
                case Operacija.VRATI_ULOGOVANE_KORISNIKE:
                    ArrayList<String> listaKorisnika = Kontroler.getInstanca().vratiListuUlogovanihKorisnika();
                    odgovor.setOdgovor(listaKorisnika);
                    break;
                case Operacija.POSALJI_PORUKU:
                    Poruka poruka = (Poruka) zahtev.getParametar();
                    boolean sacuvano = Kontroler.getInstanca().sacuvajPoruku(poruka);
                    odgovor.setOdgovor(sacuvano);
                    break;
                case Operacija.VRATI_PORUKE_ZA_KORISNIKA:
                    Kontroler.getInstanca().posaljiPorukeKorisnicima();
                    preskociPojedninacnoSlanje = true;
                    break;

            }
            if (!preskociPojedninacnoSlanje) {
                KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(klijent, odgovor);
            }
            if (operacija == Operacija.LOGIN && uspesanLogin) {
                for (Map.Entry<String, Socket> en : Kontroler.getInstanca().getMapaUlogovanihKorisnika().entrySet()) {
                    String key = en.getKey();
                    Socket val = en.getValue();
                    Odgovor specijalniOdgovor = new Odgovor();
                    specijalniOdgovor.setOperacija(Operacija.VRATI_ULOGOVANE_KORISNIKE);
                    ArrayList<String> listaKorisnika = Kontroler.getInstanca().vratiListuUlogovanihKorisnika();
                    specijalniOdgovor.setOdgovor(listaKorisnika);
                    KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(val, specijalniOdgovor);

                }

            }
        }
    }

}
