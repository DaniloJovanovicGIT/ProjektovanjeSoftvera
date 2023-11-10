/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Angazovanje;
import domen.Korisnik;
import domen.Profesor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocne.BrojAngazovanja;
import pomocne.BrojProfesora;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    DBBroker db;
    private static Kontroler instance;
    ArrayList<Korisnik> korisnici;

    private Kontroler() {
        this.db = new DBBroker();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("Danilo", "Jovanovic", "danilo@gmail.com", "danilo"));
        korisnici.add(new Korisnik("Slavica", "Jovanovic", "slavica@gmail.com", "slavica"));
        korisnici.add(new Korisnik("Dragan", "Jovanovic", "dragan@gmail.com", "dragan"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Korisnik login(Korisnik korisnik) {
        for (Korisnik bazaKorisnik : korisnici) {
            if (korisnik.getEmail().equals(bazaKorisnik.getEmail()) && korisnik.getPassword().equals(bazaKorisnik.getPassword())) {
                return bazaKorisnik;
            }
        }
        return null;
    }

    public boolean sacuvajProfesora(Profesor profesor) {
        db.otvoriKonkeciju();
        boolean uspesno = false;
        try {
            db.sacuvajProfesora(profesor);
            db.commit();
            uspesno = true;
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return uspesno;
    }

    public ArrayList<Profesor> vratiProfesore() {
        ArrayList<Profesor> listaProfesora = null;
        db.otvoriKonkeciju();
        try {
            listaProfesora = db.vratiProfesore();
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return listaProfesora;
    }

    public boolean sacuvajAngazovanje(Angazovanje angazovanje) {
        db.otvoriKonkeciju();
        boolean uspesno = false;
        try {
            db.sacuvajPredmet(angazovanje.getPredmet());
            db.sacuvajAngazovanje(angazovanje);
            db.commit();
            uspesno = true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return uspesno;
    }

    public int brojAngazovanjeProfesora(Profesor prof) {
        db.otvoriKonkeciju();
        int broj = -1;
        try {
            broj = db.vratiBrojAngazovanjaProfesora(prof);
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return broj;
    }

    public ArrayList<Angazovanje> vratiAngazovanjaZaProfesora(Profesor izabrani) {
        ArrayList<Angazovanje> listaAngazovana = null;
        db.otvoriKonkeciju();
        try {
            listaAngazovana = db.vratiAngazovanjaZaProfesora(izabrani);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return listaAngazovana;
    }

    public ArrayList<BrojAngazovanja> vratiBrojAngazovanja() {
        ArrayList<BrojAngazovanja> listaBrojAngazovanja = null;
        db.otvoriKonkeciju();
        try {
            listaBrojAngazovanja = db.vratiBrojAngazovanja();
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return listaBrojAngazovanja;
    }

    public ArrayList<BrojProfesora> vratiBrojProfesora() {
        ArrayList<BrojProfesora> listaBrojProfesora = null;
        db.otvoriKonkeciju();
        try {
            listaBrojProfesora = db.vratibrojProfesora();
            db.commit();
        } catch (SQLException ex) {
            
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            db.zatvoriKonekciju();
        }
        return listaBrojProfesora;
    }

}
