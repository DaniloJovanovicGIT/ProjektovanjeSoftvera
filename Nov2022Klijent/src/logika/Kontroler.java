/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Poruka;
import domen.User;
import form.GlavnaForma;
import form.Login;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    public void obradiLogin(User user) {
        lf.obradiLogin(user);
    }
    private Login lf;
    private GlavnaForma gf;

    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Login getLf() {
        return lf;
    }

    public void setLf(Login lf) {
        this.lf = lf;
    }

    public void vratiUlogovani(ArrayList<String> listaUlogovanih) {
        if (gf != null) {
            gf.obradiUlogovane(listaUlogovanih);
        }
    }

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public void obradiPoslataPoruka(boolean uspesno) {
        gf.obradiPorukaPoslata(uspesno);
    }

    public void obradiPrimljene(ArrayList<Poruka> listaPrimljenih) {
        gf.obradiPrimljene(listaPrimljenih);
    }

    public void obradiPoslate(ArrayList<Poruka> listaPoslatih) {
        gf.obradiPoslate(listaPoslatih);
    }

}
