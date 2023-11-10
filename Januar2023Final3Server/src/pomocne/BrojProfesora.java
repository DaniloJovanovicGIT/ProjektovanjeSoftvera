/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomocne;

/**
 *
 * @author Danilo
 */
public class BrojProfesora {
    private String zvanje;
    private int broj;

    public BrojProfesora() {
    }

    public BrojProfesora(String ime, int broj) {
        this.zvanje = ime;
        this.broj = broj;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String ime) {
        this.zvanje = ime;
    }
    
}
