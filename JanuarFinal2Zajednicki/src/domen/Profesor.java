/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Profesor implements Serializable{
    private int profesorID;
    private String ime;
    private String prezime;
    private String zvanje;
    private String emailKorisnika;
    private ArrayList<Angazovanje> angazovanja;
    
    public Profesor() {
    }

    public Profesor(int profesorID, String ime, String prezime, String zvanje, String emailKorisnika, ArrayList<Angazovanje> angazovanja) {
        this.profesorID = profesorID;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.emailKorisnika = emailKorisnika;
        this.angazovanja = angazovanja;
    }

    public int getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(int profesorID) {
        this.profesorID = profesorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public String getEmailKorisnika() {
        return emailKorisnika;
    }

    public void setEmailKorisnika(String emailKorisnika) {
        this.emailKorisnika = emailKorisnika;
    }

    public ArrayList<Angazovanje> getAngazovanja() {
        return angazovanja;
    }

    public void setAngazovanja(ArrayList<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }

    @Override
    public String toString() {
        return ime+" "+prezime; 
    }

   
    
    
}
