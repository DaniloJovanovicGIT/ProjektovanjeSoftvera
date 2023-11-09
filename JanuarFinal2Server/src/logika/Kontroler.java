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
import pomocne.BrojAngazovanja;
import pomocne.BrojProfesora;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    private DBBroker db;
    private static Kontroler instance;
    private ArrayList<Korisnik> korisnici;

    private Kontroler() {
        db = new DBBroker();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("Danilo", "Jovanovic", "admin@gmail.com", "admin"));
        korisnici.add(new Korisnik("Slavica", "Jovanovic", "slavica@gmail.com", "admin"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik login(Korisnik korisnik) {
        for (Korisnik bazaKorisnik : korisnici) {
            if (korisnik.getUsername().equals(bazaKorisnik.getUsername()) && korisnik.getPassword().equals(bazaKorisnik.getPassword())) {
                return bazaKorisnik;
            }
        }
        return null;
    }

    public boolean sacuvajProfesora(Profesor noviProfesor) {
        boolean uspesno = false;
        db.otvoriKonkeciju();
        try {
            db.sacuvajProfesora(noviProfesor);
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

    public ArrayList<Angazovanje> vratiAngazovanjaZaProfesora(Profesor profesor) {
        ArrayList<Angazovanje> listaAngazovanja = null;
        db.otvoriKonkeciju();
        try {
            listaAngazovanja = db.vratiAngazovanjaZaProfesora(profesor);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }

        return listaAngazovanja;
    }

    public ArrayList<Predmet> vratiPredmete() {
        ArrayList<Predmet> listaPredmeta = null;
        db.otvoriKonkeciju();
        try {
            listaPredmeta = db.vratiPredmete();
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            db.zatvoriKonekciju();
        }

        return listaPredmeta;
    }

    public boolean sacuvajAngazovanja(ArrayList<Angazovanje> listaAngazovanja) {
        boolean uspesno = false;
        db.otvoriKonkeciju();
        try {
            for (Angazovanje angazovanje : listaAngazovanja) {
                if (angazovanje.getStanje().equals("novo")) {
                    db.sacuvajAngazovanje(angazovanje);
                }
                if (angazovanje.getStanje().equals("izmenjeno")) {
                    db.izmeniAngazovanje(angazovanje);
                }
                if (angazovanje.getStanje().equals("obrisano")) {
                    db.obrisiAngazovanje(angazovanje);
                }
            }
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

    public ArrayList<BrojAngazovanja> vratiBrojAngazovanja() {
        ArrayList<BrojAngazovanja> listaBrojAngazovanja = null;
        db.otvoriKonkeciju();
        try {
            listaBrojAngazovanja = db.vratiBrojAngaozvanja();
            db.commit();
        } catch (Exception ex) {
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
            listaBrojProfesora = db.vratiBrojProfesora();
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            db.zatvoriKonekciju();
        }

        return listaBrojProfesora;
    }

}
