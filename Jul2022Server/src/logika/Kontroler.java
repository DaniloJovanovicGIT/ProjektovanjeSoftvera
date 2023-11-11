/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DBBroker;
import domen.ProgramskiVodic;
import domen.Stanica;
import domen.TipEmisije;
import domen.Urednik;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker db;
    private ArrayList<Urednik> urednici;

    private Kontroler() {
        db = new DBBroker();
        urednici = new ArrayList<>();
        urednici.add(new Urednik("danilo", "danilo"));
        urednici.add(new Urednik("admin", "adnim"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Urednik login(Urednik urednik) {
        for (Urednik urednikBaza : urednici) {
            if (urednik.getUsername().equals(urednikBaza.getUsername()) && urednik.getPassword().equals(urednikBaza.getPassword())) {
                return urednikBaza;
            }
        }
        return null;
    }

    public ArrayList<Stanica> vratiStanice() {
        ArrayList<Stanica> listaStanica = null;
        db.otvoriKonekciju();
        try {
            listaStanica = db.vratiStanice();
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonkeciju();
        }
        return listaStanica;
    }

    public ArrayList<TipEmisije> vratiTipove() {
        ArrayList<TipEmisije> listaTipova = null;
        db.otvoriKonekciju();
        try {
            listaTipova = db.vratiTipove();
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonkeciju();
        }
        return listaTipova;
    }

    public ArrayList<TipEmisije> vratiPodtipove(TipEmisije nadtip) {
        ArrayList<TipEmisije> listaTipova = null;
        db.otvoriKonekciju();
        try {
            listaTipova = db.vratiPodtipove(nadtip);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.zatvoriKonkeciju();
        }
        return listaTipova;
    }

    public boolean sacuvajVodic(ProgramskiVodic pv) {
        db.otvoriKonekciju();
        boolean uspesno = false;
        try {
            long programskiVodicId = db.sacuvajProgramskiVodic(pv);
            db.sacuvajStavke(pv.getListaStavki(), programskiVodicId);
            db.commit();
            uspesno = true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        db.zatvoriKonkeciju();
        }
        return uspesno;
    }

}
