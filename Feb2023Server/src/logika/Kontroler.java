/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DbBroker;
import domen.Administrator;
import domen.Korisnik;
import domen.KorisnikStanje;
import domen.Poruka;
import java.net.Socket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KomunikacijaSaKlijentom;
import konstante.Operacija;
import transfer.Odgovor;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    private static Kontroler instanca;
    HashMap<String, Socket> mapaUlogovanihKorisnika;
    DbBroker db;

    public Kontroler() {
        db = new DbBroker();
        mapaUlogovanihKorisnika = new HashMap<>();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Administrator login(String email, String lozinka) {
        db.otvoriKonekciju();
        Administrator a = db.login(email, lozinka);
        db.zatvoriKonekciju();
        return a;
    }

    public HashMap<String, Socket> getMapaUlogovanihKorisnika() {
        return mapaUlogovanihKorisnika;
    }

    public void sacuvajKorisnika(String korisnickoIme, String korisnickaLozinka) {
        boolean sacuvano = false;
        try {
            db.otvoriKonekciju();
            db.sacuvajKorisnika(korisnickoIme, korisnickaLozinka);
            db.commit();
            sacuvano = true;
            System.out.println("Korisnik sacuvan!");
        } catch (SQLException ex) {
            try {
                db.rollback();
                sacuvano = false;
                System.out.println("Korisnik nije sacuvan!");

            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }

    }

    public Korisnik ulogujKorisnika(String korisnickoIme, String korisnickoLozinka) {
        db.otvoriKonekciju();
        Korisnik a = db.ulogujKorisnika(korisnickoIme, korisnickoLozinka);
        db.zatvoriKonekciju();
        return a;
    }

    public void odjaviSveKlijente() {
        Odgovor krajOdgovor = new Odgovor(true, null, "Server je zavrsio sa radom!", Operacija.KRAJ);

        for (Map.Entry<String, Socket> entry : mapaUlogovanihKorisnika.entrySet()) {
            String korisnickoIme = entry.getKey();
            Socket socket = entry.getValue();
            komunikacija.KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(socket, krajOdgovor);
        }
    }

    public ArrayList<String> vratiListuUlogovanihKorisnika() {
        ArrayList<String> listaKorisnika = new ArrayList<>();
        for (Map.Entry<String, Socket> entry : mapaUlogovanihKorisnika.entrySet()) {
            String korisnickoIme = entry.getKey();
            listaKorisnika.add(korisnickoIme);
        }
        return listaKorisnika;
    }

    public boolean sacuvajPoruku(Poruka poruka) {
        boolean sacuvano = false;
        try {
            db.otvoriKonekciju();
            db.sacuvajPoruku(poruka);
            db.commit();
            sacuvano = true;
        } catch (SQLException ex) {
            try {
                db.rollback();
                sacuvano = false;

            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return sacuvano;
    }

    public void posaljiPorukeKorisnicima() {
        db.otvoriKonekciju();
        try {
            for (Map.Entry<String, Socket> entry : mapaUlogovanihKorisnika.entrySet()) {
                String korisnickoIme = entry.getKey();
                Socket soke = entry.getValue();
                ArrayList<Poruka> lista = db.vratiListuPorukaZaKorisnika(korisnickoIme);
                Odgovor odgovor = new Odgovor(true, lista, "Poruke vracene", Operacija.VRATI_PORUKE_ZA_KORISNIKA);
                KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(soke, odgovor);
            }
        } catch (Exception e) {
            try {
                db.rollback();
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        db.commit();
        db.zatvoriKonekciju();
    }

    public ArrayList<Poruka> vratiListuPoruka() {
        ArrayList<Poruka> lista = new ArrayList<>();
        db.otvoriKonekciju();
        lista = db.vratiListuPoruka();
        return lista;
    }

    public boolean daLiPostojiKorisnickoIme(String user) {
        db.otvoriKonekciju();
        boolean korisnikPostoji = db.daLiJeZauzetoIme(user);
        db.commit();
        db.zatvoriKonekciju();
        return korisnikPostoji;
    }

    public ArrayList<KorisnikStanje> vratiKorisnikeStanje() {
        ArrayList<KorisnikStanje> listaKorisnikStanje = new ArrayList<>();
        ArrayList<Korisnik> listaKroisnik = new ArrayList<>();
        db.otvoriKonekciju();
        try {
            listaKroisnik = db.vratiKorisnike();
            db.commit();
        } catch (SQLException ex) {
            try {
                db.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Korisnik korisnik : listaKroisnik) {
            if (mapaUlogovanihKorisnika.get(korisnik.getKorisnickoIme()) != null) {
                listaKorisnikStanje.add(new KorisnikStanje(korisnik.getKorisnickID(), korisnik.getKorisnickoIme(), korisnik.getKorisnickoLozinka(), true));
            } else {
                listaKorisnikStanje.add(new KorisnikStanje(korisnik.getKorisnickID(), korisnik.getKorisnickoIme(), korisnik.getKorisnickoLozinka(), false));
            }
        }

        db.zatvoriKonekciju();
        return listaKorisnikStanje;
    }
}
