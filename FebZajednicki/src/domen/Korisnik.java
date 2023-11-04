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
public class Korisnik implements Serializable {

    private int korisnickID;
    private String korisnickoIme;
    private String korisnickoLozinka;

    public Korisnik() {
    }

    @Override
    public String toString() {
        return korisnickoIme;
    }

    public Korisnik(int korisnickID, String korisnickoIme, String korisnickoLozinka) {
        this.korisnickID = korisnickID;
        this.korisnickoIme = korisnickoIme;
        this.korisnickoLozinka = korisnickoLozinka;
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
