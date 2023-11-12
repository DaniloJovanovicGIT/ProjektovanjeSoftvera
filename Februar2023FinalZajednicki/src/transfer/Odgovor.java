/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Danilo
 */
public class Odgovor implements Serializable{
    private Object pamatear;
    private String poruka;
    private int operacija;
    
    public Odgovor() {
    }

    public Odgovor(Object pamatear, String poruka, int operacija) {
        this.pamatear = pamatear;
        this.poruka = poruka;
        this.operacija = operacija;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getPamatear() {
        return pamatear;
    }

    public void setPamatear(Object pamatear) {
        this.pamatear = pamatear;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    
    
}
