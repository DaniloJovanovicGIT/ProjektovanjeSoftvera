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
public class Stanica implements Serializable{
    private Long stanicaID;
    private String naziv;
    private String tip;
    private String kontakt;

    public Stanica() {
    }

    public Stanica(Long stanicaID, String naziv, String tip, String kontakt) {
        this.stanicaID = stanicaID;
        this.naziv = naziv;
        this.tip = tip;
        this.kontakt = kontakt;
    }

    @Override
    public String toString() {
        return naziv; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Long getStanicaID() {
        return stanicaID;
    }

    public void setStanicaID(Long stanicaID) {
        this.stanicaID = stanicaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
    
}
