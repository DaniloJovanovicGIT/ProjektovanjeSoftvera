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
public class Predstava extends Delo implements Serializable{
    private String reditelj;
    private String podela;
    private int size;

    public Predstava() {
    }

    public Predstava(String reditelj, String podela, int size) {
        this.reditelj = reditelj;
        this.podela = podela;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getReditelj() {
        return reditelj;
    }

    public void setReditelj(String reditelj) {
        this.reditelj = reditelj;
    }

    public String getPodela() {
        return podela;
    }

    public void setPodela(String podela) {
        this.podela = podela;
    }
    
    
}
