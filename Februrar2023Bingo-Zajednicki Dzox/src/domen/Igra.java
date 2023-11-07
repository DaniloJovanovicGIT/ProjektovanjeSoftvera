/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Danilo
 */
public class Igra implements Serializable{
    private long igraID;
    private Date datumVreme;
    private String zadataKombinacija;
    private int brojPokusaja;
    private String rezultat;
    private ArrayList<Pokusaj> pokusaji;

    public Igra() {
    }

    public Igra(long igraID, Date datumVreme, String zadataKombinacija, int brojPokusaja, String rezultat, ArrayList<Pokusaj> pokusaji) {
        this.igraID = igraID;
        this.datumVreme = datumVreme;
        this.zadataKombinacija = zadataKombinacija;
        this.brojPokusaja = brojPokusaja;
        this.rezultat = rezultat;
        this.pokusaji = pokusaji;
    }

    public ArrayList<Pokusaj> getPokusaji() {
        return pokusaji;
    }

    public void setPokusaji(ArrayList<Pokusaj> pokusaji) {
        this.pokusaji = pokusaji;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getZadataKombinacija() {
        return zadataKombinacija;
    }

    public void setZadataKombinacija(String zadataKombinacija) {
        this.zadataKombinacija = zadataKombinacija;
    }

    public int getBrojPokusaja() {
        return brojPokusaja;
    }

    public void setBrojPokusaja(int brojPokusaja) {
        this.brojPokusaja = brojPokusaja;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public long getIgraID() {
        return igraID;
    }

    public void setIgraID(long igraID) {
        this.igraID = igraID;
    }
    
    
}
