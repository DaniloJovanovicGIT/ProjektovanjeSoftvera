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
public class Predmet implements Serializable {

    private String sifraPredmeta;
    private String nazivPredmeta;
    private String kodPRredmeta;
    private int brojEspb;

    public Predmet() {
    }

    public Predmet(String sifraPredmeta, String nazivPredmeta, String kodPRredmeta, int brojEspb) {
        this.sifraPredmeta = sifraPredmeta;
        this.nazivPredmeta = nazivPredmeta;
        this.kodPRredmeta = kodPRredmeta;
        this.brojEspb = brojEspb;
    }

    public int getBrojEspb() {
        return brojEspb;
    }

    public void setBrojEspb(int brojEspb) {
        this.brojEspb = brojEspb;
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

    public String getKodPRredmeta() {
        return kodPRredmeta;
    }

    public void setKodPRredmeta(String kodPRredmeta) {
        this.kodPRredmeta = kodPRredmeta;
    }

    @Override
    public String toString() {
        return nazivPredmeta;
    }

}
