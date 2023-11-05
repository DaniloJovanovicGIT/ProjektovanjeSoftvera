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
public class Operacija implements Serializable{
    public static final int LOGIN = 1;
    public static final int VRATI_ULOGOVANE=2;
    public static final int OSVEZI_ULOGOVANE=3;
    public static final int POSALJI_PORUKU=4;
    public static final int VRATI_POSLATE_PORUKE_ZA_KORISNIKA=5;
    public static final int VRATI_PRIMLJENE_PORUKE_ZA_KORISNIKA=6;
   
}
