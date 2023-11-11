/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.ProgramskiVodic;
import domen.Stanica;
import domen.TipEmisije;
import domen.Urednik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import transfer.Odgovor;
import transfer.Operacija;
import transfer.Zahtev;

/**
 *
 * @author Danilo
 */
class ObradaZahtevaKlijenta extends Thread{
    Socket socket;

    public ObradaZahtevaKlijenta() {
    }

    public ObradaZahtevaKlijenta(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {            
            Zahtev zahtev = primiZahtev();
            Odgovor odg = new Odgovor();
            switch (zahtev.getOperacija()) {
                case Operacija.LOGIN:
                    Urednik urednik = (Urednik) zahtev.getParamtera();
                    Urednik ulogovani = Kontroler.getInstance().login(urednik);
                    if(ulogovani==null){
                    odg.setPoruka("Neuspesno prijavljivanje");
                    }else{odg.setParamtera(ulogovani);}
                    break;
                case Operacija.VRATI_STANICE:
                    ArrayList<Stanica> listaStanica = null;
                    listaStanica = Kontroler.getInstance().vratiStanice();
                    if(listaStanica==null){
                    odg.setPoruka("Neuspesno vracanje stanica");
                    }else{odg.setParamtera(listaStanica);}
                    break;
                case Operacija.VRATI_TIPOVE:
                    ArrayList<TipEmisije> listaTipova = null;
                    listaTipova = Kontroler.getInstance().vratiTipove();
                    if(listaTipova==null){
                    odg.setPoruka("Neuspesno vracanje tipova");
                    }else{odg.setParamtera(listaTipova);}
                    break;
                case Operacija.VRATI_PODTIPOVE:
                    TipEmisije nadtip = (TipEmisije) zahtev.getParamtera();
                    ArrayList<TipEmisije> listaPodtipova = null;
                    listaPodtipova = Kontroler.getInstance().vratiPodtipove(nadtip);
                    if(listaPodtipova==null){
                    odg.setPoruka("Neuspesno vracanje tipova");
                    }else{odg.setParamtera(listaPodtipova);}
                    break;
                case Operacija.SACUVAJ_PROGRAMSKIVODIC:
                    ProgramskiVodic pv = (ProgramskiVodic) zahtev.getParamtera();
                    boolean sacuvanVodic = Kontroler.getInstance().sacuvajVodic(pv);
                  
                    if(sacuvanVodic==false){
                    odg.setPoruka("Neuspesno cuvanje vodica");
                    }else{odg.setParamtera(sacuvanVodic);}
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(odg);
        }
    }

    private Zahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void posaljiOdgovor(Odgovor odgovor){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(odgovor);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
