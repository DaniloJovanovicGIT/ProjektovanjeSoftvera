/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Danilo
 */
public class ProgramskiVodic implements Serializable{
    private Long ProgramskiVodicID;
    private Date dan;
    private String opis;
    private String urednik;
    private ArrayList<StavkaProgramskogVodica> listaStavki;

    public ProgramskiVodic() {
    }

    public ProgramskiVodic(Long ProgramskiVodicID, Date dan, String opis, String urednik, ArrayList<StavkaProgramskogVodica> listaStavki) {
        this.ProgramskiVodicID = ProgramskiVodicID;
        this.dan = dan;
        this.opis = opis;
        this.urednik = urednik;
        this.listaStavki = listaStavki;
    }

    public ArrayList<StavkaProgramskogVodica> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaProgramskogVodica> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public Long getProgramskiVodicID() {
        return ProgramskiVodicID;
    }

    public void setProgramskiVodicID(Long ProgramskiVodicID) {
        this.ProgramskiVodicID = ProgramskiVodicID;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
        this.dan = dan;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUrednik() {
        return urednik;
    }

    public void setUrednik(String urednik) {
        this.urednik = urednik;
    }
    
}
