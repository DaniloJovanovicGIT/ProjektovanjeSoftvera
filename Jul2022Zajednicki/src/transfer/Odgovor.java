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
    private Object paramtera;
    private String poruka;

    public Odgovor() {
    }

    public Odgovor(Object paramtera, String poruka) {
        this.paramtera = paramtera;
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getParamtera() {
        return paramtera;
    }

    public void setParamtera(Object paramtera) {
        this.paramtera = paramtera;
    }
    
}
