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
    private int FestivalID;
    private String naziv;
    private Date datumOd;
    private Date datumDo;
    private String opis;
    private String korisnik;
    private ArrayList<Izvodjenje> listaIzvodjenja;
    
    public Festival() {
    }

    public Festival(int FestivalID, String naziv, Date datumOd, Date datumDo, String opis, String korisnik, ArrayList<Izvodjenje> listaIzvodjenja) {
        this.FestivalID = FestivalID;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.opis = opis;
        this.korisnik = korisnik;
        this.listaIzvodjenja = listaIzvodjenja;
    }

    public ArrayList<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void setListaIzvodjenja(ArrayList<Izvodjenje> listaIzvodjenja) {
        this.listaIzvodjenja = listaIzvodjenja;
    }

    public int getFestivalID() {
        return FestivalID;
    }

    public void setFestivalID(int FestivalID) {
        this.FestivalID = FestivalID;
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
