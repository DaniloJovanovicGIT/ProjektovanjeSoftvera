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
public class Komponenta implements Serializable{
    private int komponentaID;
    private String naziv;
    private int kolicina;
    private boolean kontrolisano;

    public Komponenta() {
    }

    public Komponenta(int komponentaID, String naziv, int kolicina, boolean kontrolisano) {
        this.komponentaID = komponentaID;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.kontrolisano = kontrolisano;
    }

    public boolean isKontrolisano() {
        return kontrolisano;
    }

    public void setKontrolisano(boolean kontrolisano) {
        this.kontrolisano = kontrolisano;
    }

    public int getKomponentaID() {
        return komponentaID;
    }

    public void setKomponentaID(int komponentaID) {
        this.komponentaID = komponentaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    
}
