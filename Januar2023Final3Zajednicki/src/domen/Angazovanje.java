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
public class Angazovanje implements Serializable{
    private int angazovanjeID;
    private Profesor profesor;
    private Predmet predmet;
    private Date datum;
    private String emailKorisnika;
    private String stanje;

    public Angazovanje() {
    }

    public Angazovanje(int angazovanjeID, Profesor profesor, Predmet predmet, Date datum, String emailKorisnika, String stanje) {
        this.angazovanjeID = angazovanjeID;
        this.profesor = profesor;
        this.predmet = predmet;
        this.datum = datum;
        this.emailKorisnika = emailKorisnika;
        this.stanje = stanje;
    }

    public int getAngazovanjeID() {
        return angazovanjeID;
    }

    public void setAngazovanjeID(int angazovanjeID) {
        this.angazovanjeID = angazovanjeID;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getEmailKorisnika() {
        return emailKorisnika;
    }

    public void setEmailKorisnika(String emailKorisnika) {
        this.emailKorisnika = emailKorisnika;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

   
    
}
