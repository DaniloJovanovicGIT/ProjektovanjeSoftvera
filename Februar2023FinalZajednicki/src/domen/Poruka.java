/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Danilo
 */
public class Poruka implements Serializable{
    private int porukaId;
    private String odKoga;
    private String zaKoga;
    private String tekstPoruke;
    private Date vremeSlanja;

    public Poruka() {
    }

    public Poruka(int porukaId, String odKoga, String zaKoga, String tekstPoruke, Date vremeSlanja) {
        this.porukaId = porukaId;
        this.odKoga = odKoga;
        this.zaKoga = zaKoga;
        this.tekstPoruke = tekstPoruke;
        this.vremeSlanja = vremeSlanja;
    }

    public Date getVremeSlanja() {
        return vremeSlanja;
    }

    public void setVremeSlanja(Date vremeSlanja) {
        this.vremeSlanja = vremeSlanja;
    }

    public int getPorukaId() {
        return porukaId;
    }

    public void setPorukaId(int porukaId) {
        this.porukaId = porukaId;
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

    public String getTekstPoruke() {
        return tekstPoruke;
    }

    public void setTekstPoruke(String tekstPoruke) {
        this.tekstPoruke = tekstPoruke;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String skraceniTeks  = tekstPoruke;
        if(tekstPoruke.length()>20){
        skraceniTeks = tekstPoruke.substring(0, 20);
        }
        return "Od:"+odKoga+" Za:"+zaKoga+" Teks:"+skraceniTeks+" Poslato:"+sdf.format(vremeSlanja);
    }
 
    
}
