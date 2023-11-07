/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomocne;

/**
 *
 * @author Danilo
 */
public class BrojProfesoraPoZvanju {
    private int brojProfesora;
    private String zvanje;

    public BrojProfesoraPoZvanju() {
    }

    public BrojProfesoraPoZvanju(int brojProfesora, String zvanje) {
        this.brojProfesora = brojProfesora;
        this.zvanje = zvanje;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public int getBrojProfesora() {
        return brojProfesora;
    }

    public void setBrojProfesora(int brojProfesora) {
        this.brojProfesora = brojProfesora;
    }
    
}
