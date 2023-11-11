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
public class StavkaProgramskogVodica implements Serializable{
    private int RB;
    private String nazivEmisije;
    private Date datumVremeOd;
    private Date datumVremeDo;
    private Stanica stanica;
    private TipEmisije tipEmisije;

    public StavkaProgramskogVodica() {
    }

    public StavkaProgramskogVodica(int RB, String nazivEmisije, Date datumVremeOd, Date datumVremeDo, Stanica stanica, TipEmisije tipEmisije) {
        this.RB = RB;
        this.nazivEmisije = nazivEmisije;
        this.datumVremeOd = datumVremeOd;
        this.datumVremeDo = datumVremeDo;
        this.stanica = stanica;
        this.tipEmisije = tipEmisije;
    }

    public TipEmisije getTipEmisije() {
        return tipEmisije;
    }

    public void setTipEmisije(TipEmisije tipEmisije) {
        this.tipEmisije = tipEmisije;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public String getNazivEmisije() {
        return nazivEmisije;
    }

    public void setNazivEmisije(String nazivEmisije) {
        this.nazivEmisije = nazivEmisije;
    }

    public Date getDatumVremeOd() {
        return datumVremeOd;
    }

    public void setDatumVremeOd(Date datumVremeOd) {
        this.datumVremeOd = datumVremeOd;
    }

    public Date getDatumVremeDo() {
        return datumVremeDo;
    }

    public void setDatumVremeDo(Date datumVremeDo) {
        this.datumVremeDo = datumVremeDo;
    }

    public Stanica getStanica() {
        return stanica;
    }

    public void setStanica(Stanica stanica) {
        this.stanica = stanica;
    }
    
}
