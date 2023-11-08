/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Kontroler {
    private DBBRoker db;
    private static Kontroler instanca;
    ArrayList<Korisnik> korisnici;
    
    
    private Kontroler() {
        db = new DBBRoker();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("danilo", "danilo"));
        korisnici.add(new Korisnik("admin", "admin"));
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Korisnik ulogujKorinsika(Korisnik k) {
        for (Korisnik korisnik : korisnici) {
            if(k.getUsername().equals(korisnik.getUsername())&&k.getLozinka().equals(korisnik.getLozinka())){
                return korisnik;
            }
        }
        return null;
    }

}
