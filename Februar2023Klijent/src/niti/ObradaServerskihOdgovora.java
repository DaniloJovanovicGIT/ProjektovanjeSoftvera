/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Korisnik;
import domen.Poruka;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import konstante.Operacija;
import logika.Kontroler;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
public class ObradaServerskihOdgovora extends Thread {

    boolean kraj = false;

    @Override
    public void run() {
        while (!kraj) {
            Odgovor odgovor = komunikacija.Komunikacija.getKomunikacija().primiOdgovor();
            int operacija = odgovor.getOperacija();
            switch (operacija) {
                case Operacija.LOGIN:
                    Korisnik ulogovaniKorisnik = (Korisnik) odgovor.getOdgovor();
                    Kontroler.getInstance().obradiLogin(ulogovaniKorisnik);
                    break;
                case Operacija.VRATI_ULOGOVANE_KORISNIKE:
                    ArrayList<String> listaKorisnika = (ArrayList<String>) odgovor.getOdgovor();
                    Kontroler.getInstance().popuniCMBKorisnika(listaKorisnika);
                    break;
                case Operacija.POSALJI_PORUKU:
                    komunikacija.Komunikacija.getKomunikacija().posaljiZahtev(new Zahtev(Operacija.VRATI_PORUKE_ZA_KORISNIKA, null));
                    JOptionPane.showMessageDialog(null, "Poruka poslata");
                    break;
                case Operacija.VRATI_PORUKE_ZA_KORISNIKA:
                    ArrayList<Poruka> listaPoruka = (ArrayList<Poruka>) odgovor.getOdgovor();
                    Kontroler.getInstance().popuniPorukeKorisnika(listaPoruka);
                    break;
                case Operacija.KRAJ:
                    kraj = true;
                    JOptionPane.showMessageDialog(null, odgovor.getPoruka());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Fali operacija");
            }
        }
    }

}
