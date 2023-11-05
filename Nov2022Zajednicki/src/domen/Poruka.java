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
public class Poruka implements Serializable{
    private String odKoga;
    private String zaKoga;
    private String textPoruke;
    private Date vremeSlanja;

    public Poruka() {
    }

    public Poruka(String odKoga, String zaKoga, String textPoruke, Date vremeSlanja) {
        this.odKoga = odKoga;
        this.zaKoga = zaKoga;
        this.textPoruke = textPoruke;
        this.vremeSlanja = vremeSlanja;
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

    public String getTextPoruke() {
        return textPoruke;
    }

    public void setTextPoruke(String textPoruke) {
        this.textPoruke = textPoruke;
    }

    public Date getVremeSlanja() {
        return vremeSlanja;
    }

    public void setVremeSlanja(Date vremeSlanja) {
        this.vremeSlanja = vremeSlanja;
    }

    
    
    
}
