/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DBBroker;
import domen.Administrator;
import domen.Korisnik;
import domen.Poruka;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Odgovor;
import transfer.Operacija;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    private static Kontroler instance;
    private HashMap<String, Socket> mapaUlogovanih;
    private DBBroker db;

    private Kontroler() {
        db = new DBBroker();
        mapaUlogovanih = new HashMap<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public HashMap<String, Socket> getMapaUlogovanih() {
        return mapaUlogovanih;
    }

    public Administrator loginAdministratora(Administrator admin) {
        try {
            db.otvoriKonekciju();
            ArrayList<Administrator> listaAdmina = db.vratiAdministratore();
            db.commit();
            for (Administrator administrator : listaAdmina) {
                if (admin.getUsername().equals(administrator.getUsername()) && admin.getPassword().equals(administrator.getPassword()));
                return administrator;
            }
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return null;
    }

    public boolean sacuvajKorisnika(Korisnik korisnik) {
        db.otvoriKonekciju();
        boolean uspesno = false;
        try {
            boolean jedinstvenoKorisnickoIme = db.daLiJeKorisnickoImeJedinstveno(korisnik.getKorisnickoIme());
            if (jedinstvenoKorisnickoIme) {
                db.sacuvajKorisnika(korisnik);
            } else {
                return uspesno;
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

    public Korisnik loginKorisnika(Korisnik korisnik, Socket socket) {
        db.otvoriKonekciju();
        try {
            ArrayList<Korisnik> listaKorisnika = db.vratiKOrisnike();
            for (Korisnik korisnikBaza : listaKorisnika) {
                if (korisnik.getKorisnickoIme().equals(korisnikBaza.getKorisnickoIme()) && korisnik.getKorisnickaLozinka().equals(korisnikBaza.getKorisnickaLozinka())) {
                    mapaUlogovanih.put(korisnikBaza.getKorisnickoIme(), socket);
                    obavestiSve();
                    return korisnikBaza;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> vratiUlogovane() {
        ArrayList<String> listaUlogovanih = new ArrayList<>();
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            listaUlogovanih.add((String) key);
        }
        return listaUlogovanih;
    }

    private void obavestiSve() {
        ArrayList<String> listaUlogovanih = vratiUlogovane();
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            String key = entry.getKey();
            Socket socket = entry.getValue();
            posaljiOdgovor(new Odgovor(listaUlogovanih, "Novi klijent je povezan", Operacija.OBAVESTI_LOGIN), socket);
        }
    }

    public void posaljiOdgovor(Odgovor odgovor, Socket socket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(odgovor);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void odjaviSve() {
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            String key = entry.getKey();
            Socket socket = entry.getValue();
            posaljiOdgovor(new Odgovor(null, "Server je zatvoren, pokusajte kasnije", Operacija.ODJAVI_SVE), socket);
        }
    }

    public boolean sacuvajPoruku(Poruka poruka) {
        db.otvoriKonekciju();
        boolean uspesno = false;
        try {
            db.sacuvajPoruku(poruka);
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

    public ArrayList<Poruka> vratiPorukeZaKorisnika(Korisnik ulogovaniKorisnik) {
        ArrayList<Poruka> listaPoruka = null;
        db.otvoriKonekciju();
        try {
            listaPoruka = db.vratiPorukeZaKorisnika(ulogovaniKorisnik);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }

        return listaPoruka;
    }

    public void obavestiNovaPruka() {
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            String key = entry.getKey();
            Socket socket = entry.getValue();
            posaljiOdgovor(new Odgovor(null, "Server je zatvoren, pokusajte kasnije", Operacija.STIGLA_NOVA_PORUKA), socket);
        }
    }
}
