/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Delo;
import domen.Festival;
import domen.Izvodjenje;
import domen.Korisnik;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    DBBroker db;
    ArrayList<Korisnik> listaKorisnika;
    private static Kontroler instance;

    private Kontroler() {
        this.db = new DBBroker();
        this.listaKorisnika = new ArrayList<>();
        listaKorisnika.add(new Korisnik("danilo", "danilo"));
        listaKorisnika.add(new Korisnik("bane", "bane"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Korisnik login(Korisnik k) {
        for (Korisnik korisnik : listaKorisnika) {
            if (k.getUsername().equals(korisnik.getUsername()) && k.getPassword().equals(korisnik.getPassword())) {
                return korisnik;
            }
        }
        return null;
    }

    public ArrayList<Festival> vratiFestivale() {
        db.otvoriKonekciju();
        ArrayList<Festival> lista = null;
        try {
            lista = db.vratiFestivale();
            for (Festival festival : lista) {
                ArrayList<Izvodjenje> listaIZvodjenja = db.vratiIzvodjenjaZaFestival(festival);
                festival.setListaIzvodjenja(listaIZvodjenja);
            }
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Delo> vratiDela() {
        db.otvoriKonekciju();
        ArrayList<Delo> listaDela = null;
        try {
            listaDela = db.vratiDela();
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.zatvoriKonekciju();
        return listaDela;
    }

    public boolean izmeniFestival(Festival festival) {
        db.otvoriKonekciju();
        boolean izmenjenFestival = false;

        try {
            db.izmeniFestival(festival);
            for (Izvodjenje izvodjenje : festival.getListaIzvodjenja()) {
                if (izvodjenje.getStanje().equals("dodato")) {
                    db.dodaj(izvodjenje);
                }
                if (izvodjenje.getStanje().equals("obrisano")) {
                    db.obrisi(izvodjenje);
                }
            }
            db.commit();
            izmenjenFestival = true;
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return izmenjenFestival;
    }

}
