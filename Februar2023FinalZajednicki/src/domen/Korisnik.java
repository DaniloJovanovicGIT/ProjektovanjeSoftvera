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
public class Korisnik implements Serializable{
    private String korisnickoIme;
    private String korisnickaLozinka;
    private boolean ulogovan;

    public Korisnik() {
    }

    public Korisnik(String korisnickoIme, String korisnickaLozinka, boolean ulogovan) {
        this.korisnickoIme = korisnickoIme;
        this.korisnickaLozinka = korisnickaLozinka;
        this.ulogovan = ulogovan;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaLozinka() {
        return korisnickaLozinka;
    }

    public void setKorisnickaLozinka(String korisnickaLozinka) {
        this.korisnickaLozinka = korisnickaLozinka;
    }

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

   
    
}
