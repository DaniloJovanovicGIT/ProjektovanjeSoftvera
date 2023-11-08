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
    private int deloID;
    private String naziv;

    public Delo() {
    }

    public Delo(int deloID, String naziv) {
        this.deloID = deloID;
        this.naziv = naziv;
    }
    

    public int getDeloID() {
        return deloID;
    }

    public void setDeloID(int deloID) {
        this.deloID = deloID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
