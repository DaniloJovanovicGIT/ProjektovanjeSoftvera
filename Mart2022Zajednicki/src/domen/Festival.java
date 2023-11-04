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
public class Festival implements Serializable{
    private long festivalID;
    private String naziv;
    private Date datumOd;
    private Date datumDo;
    private String opis;
    private String korisnik;
    private ArrayList<Izvodjenje> listaIzvodjenja;
    public Festival() {
    }

    public Festival(long festivalID, String naziv, Date datumOd, Date datumDo, String opis, String korisnik, ArrayList<Izvodjenje> listaIzvodjenja) {
        this.festivalID = festivalID;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.opis = opis;
        this.korisnik = korisnik;
        this.listaIzvodjenja = listaIzvodjenja;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public ArrayList<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void setListaIzvodjenja(ArrayList<Izvodjenje> listaIzvodjenja) {
        this.listaIzvodjenja = listaIzvodjenja;
    }

    public long getFestivalID() {
        return festivalID;
    }

    public void setFestivalID(long festivalID) {
        this.festivalID = festivalID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    
    
}
