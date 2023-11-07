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
public class Predmet implements Serializable{
    private String sifraPredmeta;
    private String nzaivPredmeta;
    private String kodPredmeta;
    private int brojEspb;
    private ArrayList<Angazovanje> listaAngazovanja;

    public Predmet() {
    }

    public Predmet(String sifraPredmeta, String nzaivPredmeta, String kodPredmeta, int brojEspb, ArrayList<Angazovanje> listaAngazovanja) {
        this.sifraPredmeta = sifraPredmeta;
        this.nzaivPredmeta = nzaivPredmeta;
        this.kodPredmeta = kodPredmeta;
        this.brojEspb = brojEspb;
        this.listaAngazovanja = listaAngazovanja;
    }

    public ArrayList<Angazovanje> getListaAngazovanja() {
        return listaAngazovanja;
    }

    public void setListaAngazovanja(ArrayList<Angazovanje> listaAngazovanja) {
        this.listaAngazovanja = listaAngazovanja;
    }

    public String getSifraPredmeta() {
        return sifraPredmeta;
    }

    public void setSifraPredmeta(String sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public String getNzaivPredmeta() {
        return nzaivPredmeta;
    }

    public void setNzaivPredmeta(String nzaivPredmeta) {
        this.nzaivPredmeta = nzaivPredmeta;
    }

    public String getKodPredmeta() {
        return kodPredmeta;
    }

    public void setKodPredmeta(String kodPredmeta) {
        this.kodPredmeta = kodPredmeta;
    }

    public int getBrojEspb() {
        return brojEspb;
    }

    public void setBrojEspb(int brojEspb) {
        this.brojEspb = brojEspb;
    }

    @Override
    public String toString() {
        return nzaivPredmeta; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
