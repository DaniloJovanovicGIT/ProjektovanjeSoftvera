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
public class Zahtev implements Serializable{
    private int operacija;
    private Object paramtera;

    public Zahtev() {
    }

    public Zahtev(int operacija, Object paramtera) {
        this.operacija = operacija;
        this.paramtera = paramtera;
    }

    public Object getParamtera() {
        return paramtera;
    }

    public void setParamtera(Object paramtera) {
        this.paramtera = paramtera;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }
    
}
