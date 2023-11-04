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
public class Delo implements Serializable{
    private long deloID;
    private String naziv;

    public Delo() {
    }

    public Delo(long deloID, String naziv) {
        this.deloID = deloID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getDeloID() {
        return deloID;
    }

    public void setDeloID(long deloID) {
        this.deloID = deloID;
    }
    
}
