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
public class Pokusaj implements Serializable{
    private Igra rezultatIgre;
    private int pokusajID;
    private String kombinacija;
    private int pogodjenihNaMesu;
    private int pogodenihNisuNaMestu;

    public Pokusaj() {
    }

    public Pokusaj(Igra rezultatIgre, int pokusajID, String kombinacija, int pogodjenihNaMesu, int pogodenihNisuNaMestu) {
        this.rezultatIgre = rezultatIgre;
        this.pokusajID = pokusajID;
        this.kombinacija = kombinacija;
        this.pogodjenihNaMesu = pogodjenihNaMesu;
        this.pogodenihNisuNaMestu = pogodenihNisuNaMestu;
    }

   

    public int getPogodenihNisuNaMestu() {
        return pogodenihNisuNaMestu;
    }

    public void setPogodenihNisuNaMestu(int pogodenihNisuNaMestu) {
        this.pogodenihNisuNaMestu = pogodenihNisuNaMestu;
    }

    public Igra getRezultatIgre() {
        return rezultatIgre;
    }

    public void setRezultatIgre(Igra rezultatIgre) {
        this.rezultatIgre = rezultatIgre;
    }

    

    public String getKombinacija() {
        return kombinacija;
    }

    public void setKombinacija(String kombinacija) {
        this.kombinacija = kombinacija;
    }

    public int getPogodjenihNaMesu() {
        return pogodjenihNaMesu;
    }

    public void setPogodjenihNaMesu(int pogodjenihNaMesu) {
        this.pogodjenihNaMesu = pogodjenihNaMesu;
    }

    public int getPokusajID() {
        return pokusajID;
    }

    public void setPokusajID(int pokusajID) {
        this.pokusajID = pokusajID;
    }
    
}
