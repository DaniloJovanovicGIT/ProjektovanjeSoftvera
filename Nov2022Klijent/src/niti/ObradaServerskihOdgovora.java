/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Poruka;
import domen.User;
import java.util.ArrayList;
import logika.Kontroler;
import transfer.Komunikacija;
import transfer.Odgovor;
import transfer.Operacija;

/**
 *
 * @author Danilo
 */
public class ObradaServerskihOdgovora extends Thread {

    @Override
    public void run() {
        while (true) {
            Odgovor odgovor = primiServerskiOdgovor();
            switch (odgovor.getOperacija()) {
                case Operacija.LOGIN:
                    System.out.println("usao u login");
                    User user = (User) odgovor.getParametar();
                    Kontroler.getInstance().obradiLogin(user);
                    break;
                case Operacija.VRATI_ULOGOVANE:
                    ArrayList<String> listaUlogovanih = (ArrayList<String>) odgovor.getParametar();
                    Kontroler.getInstance().vratiUlogovani(listaUlogovanih);
                    break;
                case Operacija.OSVEZI_ULOGOVANE:
                    System.out.println("Osvezeni klijenti:");
                   ArrayList<String> osvezenaListaUlogovanih = (ArrayList<String>) odgovor.getParametar();
                  Kontroler.getInstance().vratiUlogovani(osvezenaListaUlogovanih);
                    break;
                    case Operacija.POSALJI_PORUKU:
                   boolean uspesno = (boolean) odgovor.getParametar();
                        Kontroler.getInstance().obradiPoslataPoruka(uspesno);
                    break;
                    case Operacija.VRATI_PRIMLJENE_PORUKE_ZA_KORISNIKA:
                        ArrayList<Poruka> listaPrimljenih  = (ArrayList<Poruka>) odgovor.getParametar();
                        Kontroler.getInstance().obradiPrimljene(listaPrimljenih);
                    break;
                    case Operacija.VRATI_POSLATE_PORUKE_ZA_KORISNIKA:
                        ArrayList<Poruka> listaPoslatih  = (ArrayList<Poruka>) odgovor.getParametar();
                        Kontroler.getInstance().obradiPoslate(listaPoslatih);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private Odgovor primiServerskiOdgovor() {
        return Komunikacija.getInstance().primiOdgovor();
    }

}
