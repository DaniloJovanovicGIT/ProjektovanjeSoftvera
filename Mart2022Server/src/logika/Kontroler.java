/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DBBroker;
import domen.Festival;
import domen.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    DBBroker db;
    private static Kontroler instance;
    ArrayList<Korisnik> korisnici;

    private Kontroler() {
        db = new DBBroker();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("admin", "admin"));
        korisnici.add(new Korisnik("danilo", "danilo"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public boolean login(Korisnik k) {
        for (Korisnik korisnik : korisnici) {
            if (k.getUsername().equals(korisnik.getUsername()) && k.getLozinka().equals(korisnik.getLozinka())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Festival> vratiSveFestivale() {
        ArrayList<Festival> lista = new ArrayList<>();
        db.otvoriKonekciju();
        lista = db.vratiSveFestivale();
        db.commit();
        db.zatvoriKonekciju();
        return lista;
    }

}
