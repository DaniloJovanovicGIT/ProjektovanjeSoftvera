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
    private Object paramtear;

    public Zahtev() {
    }

    public Zahtev(int operacija, Object paramtear) {
        this.operacija = operacija;
        this.paramtear = paramtear;
    }

    public Object getParamtear() {
        return paramtear;
    }

    public void setParamtear(Object paramtear) {
        this.paramtear = paramtear;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }
    
}
