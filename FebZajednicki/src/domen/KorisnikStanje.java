/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Danilo
 */
public class KorisnikStanje implements Serializable {

    private int korisnickID;
    private String korisnickoIme;
    private String korisnickoLozinka;
    private boolean aktivan;

    public KorisnikStanje() {
    }

    public KorisnikStanje(int korisnickID, String korisnickoIme, String korisnickoLozinka, boolean aktivan) {
        this.korisnickID = korisnickID;
        this.korisnickoIme = korisnickoIme;
        this.korisnickoLozinka = korisnickoLozinka;
        this.aktivan = aktivan;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public int getKorisnickID() {
        return korisnickID;
    }

    public void setKorisnickID(int korisnickID) {
        this.korisnickID = korisnickID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickoLozinka() {
        return korisnickoLozinka;
    }

    public void setKorisnickoLozinka(String korisnickoLozinka) {
        this.korisnickoLozinka = korisnickoLozinka;
    }
    
}
