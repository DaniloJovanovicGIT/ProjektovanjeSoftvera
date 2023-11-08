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
    private Festival festival;
    private Delo delo;
    private Date datumVremePocketka;
    private Date datumVremeKraja;
    private String sala;
    private String stanje;

    public Izvodjenje() {
    }

    public Izvodjenje(Festival festival, Delo delo, Date datumVremePocketka, Date datumVremeKraja, String sala, String stanje) {
        this.festival = festival;
        this.delo = delo;
        this.datumVremePocketka = datumVremePocketka;
        this.datumVremeKraja = datumVremeKraja;
        this.sala = sala;
        this.stanje = stanje;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public Delo getDelo() {
        return delo;
    }

    public void setDelo(Delo delo) {
        this.delo = delo;
    }

    public Date getDatumVremePocketka() {
        return datumVremePocketka;
    }

    public void setDatumVremePocketka(Date datumVremePocketka) {
        this.datumVremePocketka = datumVremePocketka;
    }

    public Date getDatumVremeKraja() {
        return datumVremeKraja;
    }

    public void setDatumVremeKraja(Date datumVremeKraja) {
        this.datumVremeKraja = datumVremeKraja;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    
    
}
