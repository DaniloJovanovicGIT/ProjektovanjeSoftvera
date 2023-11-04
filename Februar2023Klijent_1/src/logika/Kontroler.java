/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Korisnik;
import domen.Poruka;
import forme.GlavnaForma;
import forme.LoginForma;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    public  void popuniPorukeKorisnika(ArrayList<Poruka> listaPoruka) {
        if (gf != null) {
            gf.popuniListuPoruka(listaPoruka);
        }
    }

    LoginForma lf;
    GlavnaForma gf;

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public void setLf(LoginForma lf) {
        this.lf = lf;
    }

    private static Kontroler instance;

    private Kontroler() {

    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void obradiLogin(Korisnik ulogovaniKorisnik) {
        lf.ogbradiLogovanjeKorisnika(ulogovaniKorisnik);
    }

    public void popuniCMBKorisnika(ArrayList<String> listaKorisnika) {
        if (gf != null) {
            gf.popuniCMBKorisnika(listaKorisnika);
        }
    }

}
