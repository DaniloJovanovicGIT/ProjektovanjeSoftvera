/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Angazovanje;
import domen.Korisnik;
import domen.Predmet;
import domen.Profesor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocne.BrojProfesoraPoZvanju;
import pomocne.ProfesorBrojAngazovanja;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    DBBroker db;
    ArrayList<Korisnik> korisnici;
    private static Kontroler instanca;

    private Kontroler() {
        db = new DBBroker();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("Danilo", "Jovanovic", "admin", "admin"));
        korisnici.add(new Korisnik("Nikola", "Disic", "diske", "diske"));
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Korisnik login(Korisnik k) {
        for (Korisnik korisnik : korisnici) {
            if (k.getUsername().equals(korisnik.getUsername()) && k.getPassword().equals(korisnik.getPassword())) {
                return korisnik;
            }
        }
        return null;
    }

    public boolean sacuvajProfesora(Profesor p) {
        boolean sacuvano = false;
        db.otvoriKonkeckiju();
        try {
            db.sacuvajProfesora(p);
            db.commit();
            sacuvano = true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return sacuvano;
    }

    public ArrayList<Profesor> vratiProfesore() {
        ArrayList<Profesor> profesori = null;
        db.otvoriKonkeckiju();
        try {
            profesori = db.vratiProfesore();
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return profesori;
    }

    public boolean sacuvajPredmet(Predmet predmet) {
        boolean sacuvano = false;
        db.otvoriKonkeckiju();
        try {
            db.sacuvajPredmet(predmet);
            for (Angazovanje angazovanje : predmet.getListaAngazovanja()) {
                db.sacuvajAngazovanje(angazovanje);
            }
            db.commit();
            sacuvano = true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return sacuvano;
    }

    public ArrayList<Angazovanje> vratiAngazovanjaProfesora(Profesor profesor) {
        ArrayList<Angazovanje> angazovanja = null;
        db.otvoriKonkeckiju();
        try {
            angazovanja = db.vratiAngazovanjaProfesora(profesor);
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return angazovanja;
    }

    public ArrayList<ProfesorBrojAngazovanja> vratiBrojAngazovanjaProf() {
        ArrayList<ProfesorBrojAngazovanja> profbrojAngazovanja = null;
        db.otvoriKonkeckiju();
        try {
            profbrojAngazovanja = db.vratiProfBrojAngazovanja();
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return profbrojAngazovanja;
    }

    public ArrayList<BrojProfesoraPoZvanju> vratiBrojProfPoZvanju() {
        ArrayList<BrojProfesoraPoZvanju> poZvanu = null;
        db.otvoriKonkeckiju();
        try {
            poZvanu = db.vratiProfBrojPoZvanu();
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return poZvanu;
    }

}
