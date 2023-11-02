/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DBBroker;
import domen.Komponenta;
import domen.Pogon;
import domen.PrevoznoSredstvo;
import domen.Rukovodilac;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    private DBBroker db;

    private static Kontroler instanca;
    ArrayList<Rukovodilac> rukovodioci;

    private Kontroler() {
        db = new DBBroker();
        rukovodioci = new ArrayList<>();
        rukovodioci.add(new Rukovodilac("admin", "admin"));
        rukovodioci.add(new Rukovodilac("danilo", "jovanovic"));
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Rukovodilac login(Rukovodilac r) {
        for (Rukovodilac rukovodilac : rukovodioci) {
            if (r.getKorisnickoIme().equals(rukovodilac.getKorisnickoIme()) && r.getKorisnickoIme().equals(rukovodilac.getKorisnickoIme())) {
                return rukovodilac;
            }
        }
        return null;
    }

    public ArrayList<Pogon> vratiSvePogone() {
        db.otvoriKonkeciju();
        try {
            ArrayList<Pogon> pogoni = db.vratiSvePogone();
            db.commit();
            return pogoni;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return null;
    }

    public boolean sacuvajPrevoznoSredstvo(PrevoznoSredstvo ps) {
        db.otvoriKonkeciju();
        try {
            int prevoznoSredstvoID = db.sacuvajPrevoznoSredstvo(ps);
            for (Komponenta komponenta : ps.getKomponente()) {
                db.sacuvajKomponentu(komponenta, prevoznoSredstvoID);
            }
            db.commit();
            return true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonekciju();
        }
        return false;
    }

}
