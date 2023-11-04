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
public class Predstava implements Serializable{
    private long deloID;
    private String reditelj;
    private String podela;
    private String size;

    public Predstava() {
    }

    public Predstava(long deloID, String reditelj, String podela, String size) {
        this.deloID = deloID;
        this.reditelj = reditelj;
        this.podela = podela;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getDeloID() {
        return deloID;
    }

    public void setDeloID(long deloID) {
        this.deloID = deloID;
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
