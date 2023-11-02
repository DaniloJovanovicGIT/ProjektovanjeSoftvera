/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domen.Rukovodilac;

/**
 *
 * @author Danilo
 */
public class Session {

    private static Session instance;
    private Rukovodilac ulogovani;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Rukovodilac getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Rukovodilac ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    
}
