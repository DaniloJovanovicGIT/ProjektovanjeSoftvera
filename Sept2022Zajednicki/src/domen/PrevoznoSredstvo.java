/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Danilo
 */
public class PrevoznoSredstvo implements Serializable{
    private int prevoznoSredstvoID;
    private String model;
    private Date pocetakProizvodnje;
    private Date zavretakProizvodnje;
    private int ocekivaniRadniVek;
    private String tip;
    private Pogon pogon;
    private Rukovodilac rukovodilac;
    private ArrayList<Komponenta> komponente;

    public PrevoznoSredstvo(int prevoznoSredstvoID, String model, Date pocetakProizvodnje, Date zavretakProizvodnje, int ocekivaniRadniVek, String tip, Pogon pogon, Rukovodilac rukovodilac, ArrayList<Komponenta> komponente) {
        this.prevoznoSredstvoID = prevoznoSredstvoID;
        this.model = model;
        this.pocetakProizvodnje = pocetakProizvodnje;
        this.zavretakProizvodnje = zavretakProizvodnje;
        this.ocekivaniRadniVek = ocekivaniRadniVek;
        this.tip = tip;
        this.pogon = pogon;
        this.rukovodilac = rukovodilac;
        this.komponente = komponente;
    }

    public PrevoznoSredstvo() {
    }

    public int getPrevoznoSredstvoID() {
        return prevoznoSredstvoID;
    }

    public void setPrevoznoSredstvoID(int prevoznoSredstvoID) {
        this.prevoznoSredstvoID = prevoznoSredstvoID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getPocetakProizvodnje() {
        return pocetakProizvodnje;
    }

    public void setPocetakProizvodnje(Date pocetakProizvodnje) {
        this.pocetakProizvodnje = pocetakProizvodnje;
    }

    public Date getZavretakProizvodnje() {
        return zavretakProizvodnje;
    }

    public void setZavretakProizvodnje(Date zavretakProizvodnje) {
        this.zavretakProizvodnje = zavretakProizvodnje;
    }

    public int getOcekivaniRadniVek() {
        return ocekivaniRadniVek;
    }

    public void setOcekivaniRadniVek(int ocekivaniRadniVek) {
        this.ocekivaniRadniVek = ocekivaniRadniVek;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Pogon getPogon() {
        return pogon;
    }

    public void setPogon(Pogon pogon) {
        this.pogon = pogon;
    }

    public Rukovodilac getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(Rukovodilac rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public ArrayList<Komponenta> getKomponente() {
        return komponente;
    }

    public void setKomponente(ArrayList<Komponenta> komponente) {
        this.komponente = komponente;
    }
    
    
}
