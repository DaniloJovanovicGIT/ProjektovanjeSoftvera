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
public class Predmet implements Serializable{
    private String sifraPredmeta;
    private String nazivPredmeta;
    private String kodPredmeta;
    private int brojEPSB;

    public Predmet() {
    }

    public Predmet(String sifraPredmeta, String nazivPredmeta, String kodPredmeta, int brojEPSB) {
        this.sifraPredmeta = sifraPredmeta;
        this.nazivPredmeta = nazivPredmeta;
        this.kodPredmeta = kodPredmeta;
        this.brojEPSB = brojEPSB;
    }

    public int getBrojEPSB() {
        return brojEPSB;
    }

    public void setBrojEPSB(int brojEPSB) {
        this.brojEPSB = brojEPSB;
    }

    public String getSifraPredmeta() {
        return sifraPredmeta;
    }

    public void setSifraPredmeta(String sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getKodPredmeta() {
        return kodPredmeta;
    }

    public void setKodPredmeta(String kodPredmeta) {
        this.kodPredmeta = kodPredmeta;
    }

    @Override
    public String toString() {
        return nazivPredmeta; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
