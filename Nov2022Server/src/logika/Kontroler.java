/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DBBroker;
import domen.Poruka;
import domen.User;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class Kontroler {
    
    DBBroker db;
    private static Kontroler instance;
    private HashMap<String, Socket> mapaUlogovanih;
    
    private Kontroler() {
        mapaUlogovanih = new HashMap<>();
        db = new DBBroker();
    }
    
    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }
    
    public User ulogujKorisnika(User user, Socket socket) {
        db.otovoriKonekciju();
        User ulogovani = null;
        try {
            ulogovani = db.vratiUlogovanog(user);
            db.commit();
            if (ulogovani != null) {
                mapaUlogovanih.put(ulogovani.getUsername(), socket);
                
            }
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return ulogovani;
    }
    
    public ArrayList<String> vratiUlogovane() {
        ArrayList<String> lista = new ArrayList<>();
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            String key = entry.getKey();
            Socket val = entry.getValue();
            lista.add(key);
        }
        return lista;
    }
    
    public HashMap<String, Socket> getMapaUlogovanih() {
        return mapaUlogovanih;
    }
    
    public boolean sacuvajPoruku(Poruka poruka) {
        boolean uspesno = false;
        db.otovoriKonekciju();
        try {
            db.sacuvajPoruku(poruka);
            db.commit();
            uspesno = true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return uspesno;
    }
    
    public ArrayList<Poruka> vratPrimljenePorukeZaKorisnika(User korisnik) {
        ArrayList<Poruka> listaPoruka = new ArrayList<>();
        db.otovoriKonekciju();
        try {
            listaPoruka = db.vratiPrimljenePoruke(korisnik);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.zatvoriKonekciju();
        return listaPoruka;
    }
    
    public ArrayList<Poruka> vratPoslatePorukeZaKorisnika(User k) {
        ArrayList<Poruka> listaPoruka = new ArrayList<>();
        db.otovoriKonekciju();
        try {
            listaPoruka = db.vratiPoslatePoruke(k);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.zatvoriKonekciju();
        return listaPoruka;
    }
    
    public ArrayList<User> vratiKorisnikeIUlogovane() {
        ArrayList<User> listaUsera = new ArrayList<>();
        db.otovoriKonekciju();
        try {
            listaUsera = db.vratiKorisnike();
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        for (User user : listaUsera) {
            if (mapaUlogovanih.containsKey(user.getUsername())) {
                user.setAktivan(true);
            }
        }
        
        return listaUsera;
    }
    
}
