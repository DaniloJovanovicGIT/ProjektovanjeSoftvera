/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Danilo
 */
public class Poruka implements Serializable {

    private int porukaID;
    private String odKoga;
    private String zaKoga;
    private Date datum;
    private String poruka;

    public Poruka() {
    }

    public Poruka(int porukaID, String odKoga, String zaKoga, Date datum, String poruka) {
        this.porukaID = porukaID;
        this.odKoga = odKoga;
        this.zaKoga = zaKoga;
        this.datum = datum;
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public int getPorukaID() {
        return porukaID;
    }

    public void setPorukaID(int porukaID) {
        this.porukaID = porukaID;
    }

    public String getOdKoga() {
        return odKoga;
    }

    public void setOdKoga(String odKoga) {
        this.odKoga = odKoga;
    }

    public String getZaKoga() {
        return zaKoga;
    }

    public void setZaKoga(String zaKoga) {
        this.zaKoga = zaKoga;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        String skracena = "";
        if (poruka.length() > 20) {
            skracena = poruka.substring(0, 20);
        } else {
            skracena = poruka;
        }
        return "Od:" + odKoga + " Za:" + zaKoga + " Poruka:" + skracena + "... ";
    }

}
