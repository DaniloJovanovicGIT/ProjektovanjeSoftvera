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
public class Koncert implements Serializable{
    private long deloID;
    private String izvodjac;
    private String program;

    public Koncert() {
    }

    public Koncert(long deloID, String izvodjac, String program) {
        this.deloID = deloID;
        this.izvodjac = izvodjac;
        this.program = program;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public long getDeloID() {
        return deloID;
    }

    public void setDeloID(long deloID) {
        this.deloID = deloID;
    }

    public String getIzvodjac() {
        return izvodjac;
    }

    public void setIzvodjac(String izvodjac) {
        this.izvodjac = izvodjac;
    }
    
}
