/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import domen.Korisnik;
import domen.Poruka;
import forme.LoginForma;
import java.util.ArrayList;
import logika.Kontroler;

/**
 *
 * @author Danilo
 */
public class ObradaOdgovoraServera extends Thread{
   
    
    
    @Override
    public void run() {
        while (true) {            
            Odgovor odgovor = Komunikacija.getInstance().primiOdgovor();
            switch (odgovor.getOperacija()) {
                case Operacija.LOGIN:
                    Korisnik korisnik = (Korisnik) odgovor.getPamatear();
                    Kontroler.getInstace().ulogujKorisnika(korisnik);
                    break;
                case Operacija.VRATI_ULOGOVANE:
                    ArrayList<String> listaUlogovanih = (ArrayList<String>) odgovor.getPamatear();
                    Kontroler.getInstace().popuniUlogovane(listaUlogovanih);
                    break;
                case Operacija.OBAVESTI_LOGIN:
                    ArrayList<String> listaUlog = (ArrayList<String>) odgovor.getPamatear();
                    Kontroler.getInstace().popuniUlogovane(listaUlog);
                    break;
                case Operacija.ODJAVI_SVE:
                    Kontroler.getInstace().odjaviSve();
                    break;
                case Operacija.POSALJI_PORUKU:
                    boolean uspesnoPoslato = (boolean) odgovor.getPamatear();
                    Kontroler.getInstace().obradiPoslataPoruka(uspesnoPoslato);
                    break;
                case Operacija.VRATI_PORUKE_ZA_KORISNIKA:
                    ArrayList<Poruka> listPoruka = (ArrayList<Poruka>) odgovor.getPamatear();
                    Kontroler.getInstace().popuniPoruke(listPoruka);
                    break;
                case Operacija.STIGLA_NOVA_PORUKA:
                    Kontroler.getInstace().obradiNovaPoruka();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
}
