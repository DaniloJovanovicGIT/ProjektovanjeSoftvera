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
public class Izvodjenje implements Serializable{

    private long festivalID;
    private long deloID;
    private Date vremePocetka;
    private Date vremeKraja;
    private String sala;

    public Izvodjenje() {
    }

    public Izvodjenje(long festivalID, long deloID, Date vremePocetka, Date vremeKraja, String sala) {
        this.festivalID = festivalID;
        this.deloID = deloID;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public long getFestivalID() {
        return festivalID;
    }

    public void setFestivalID(long festivalID) {
        this.festivalID = festivalID;
    }

    public long getDeloID() {
        return deloID;
    }

    public void setDeloID(long deloID) {
        this.deloID = deloID;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(Date vremeKraja) {
        this.vremeKraja = vremeKraja;
    }
    
}
