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
public class  TipEmisije implements Serializable{
    private Long tipEmisijeID;
    private String naziv;
    private TipEmisije nadtipEmisije;

    public TipEmisije() {
    }

    public TipEmisije(Long tipEmisijeID, String naziv, TipEmisije nadtipEmisije) {
        this.tipEmisijeID = tipEmisijeID;
        this.naziv = naziv;
        this.nadtipEmisije = nadtipEmisije;
    }

    public TipEmisije getNadtipEmisije() {
        return nadtipEmisije;
    }

    public void setNadtipEmisije(TipEmisije nadtipEmisije) {
        this.nadtipEmisije = nadtipEmisije;
    }

    public Long getTipEmisijeID() {
        return tipEmisijeID;
    }

    public void setTipEmisijeID(Long tipEmisijeID) {
        this.tipEmisijeID = tipEmisijeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv; 
    }
    
}
