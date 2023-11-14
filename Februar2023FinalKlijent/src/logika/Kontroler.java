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

    private LoginForma lf;
    private GlavnaForma gf;

    private static Kontroler instace;

    private Kontroler() {
    }

    public static Kontroler getInstace() {
        if (instace == null) {
            instace = new Kontroler();
        }
        return instace;
    }

    public void setLf(LoginForma lf) {
        this.lf = lf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }
    

    public void ulogujKorisnika(Korisnik korisnik) {
        if(lf!=null){
            lf.ulogujKorisnika(korisnik);
        }
    }

    public void popuniUlogovane(ArrayList<String> listaUlogovanih) {
     if(gf!=null)
        gf.popuniUlogovane(listaUlogovanih);
    }

    public void odjaviSve() {
        if(gf!=null)gf.pihvatiOdjavu();
    }

    public void obradiPoslataPoruka(boolean uspesnoPoslato) {
        if(gf!=null)gf.obradiPoslataPoruka(uspesnoPoslato);
    }

    public void popuniPoruke(ArrayList<Poruka> listPoruka) {
       if(gf!=null) gf.obradiPopuniPoruke(listPoruka);
    }

    public void obradiNovaPoruka() {
        if(gf!=null) gf.obradiNovaPoruka();
    }
}
