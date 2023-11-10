/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomocne;

/**
 *
 * @author Danilo
 */
public class BrojAngazovanja {
    private String ime;
    private int broj;

    public BrojAngazovanja() {
    }

    public BrojAngazovanja(String ime, int broj) {
        this.ime = ime;
        this.broj = broj;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    
}
