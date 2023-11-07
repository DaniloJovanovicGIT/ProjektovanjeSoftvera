/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomocne;

import domen.Profesor;

/**
 *
 * @author Danilo
 */
public class ProfesorBrojAngazovanja {
    private String ime;
    private String prezime;
    private int brojAngazovanja;

    public ProfesorBrojAngazovanja() {
    }

    public ProfesorBrojAngazovanja(String ime, String prezime, int brojAngazovanja) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojAngazovanja = brojAngazovanja;
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

    public int getBrojAngazovanja() {
        return brojAngazovanja;
    }

    public void setBrojAngazovanja(int brojAngazovanja) {
        this.brojAngazovanja = brojAngazovanja;
    }

    
    
}
