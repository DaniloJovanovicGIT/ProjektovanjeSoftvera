/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Poruka;
import domen.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
class ObradaZahtevaKlijenta extends Thread {

    Socket socket;
    boolean uspesnoLogovanje = false;

    public ObradaZahtevaKlijenta(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            Zahtev zahtev = primiZahtev();
            Odgovor odgovor = new Odgovor();
            odgovor.setOperacija(zahtev.getOperacija());
            switch (zahtev.getOperacija()) {
                case Operacija.LOGIN:
                    User user = (User) zahtev.getParametar();

                    User ulogovani = Kontroler.getInstance().ulogujKorisnika(user, socket);
                    odgovor.setParametar(ulogovani);
                    if (ulogovani != null) {
                        uspesnoLogovanje = true;
                    }
                    break;
                case Operacija.VRATI_ULOGOVANE:

                    ArrayList<String> listaUlogovani = Kontroler.getInstance().vratiUlogovane();
                    odgovor.setParametar(listaUlogovani);
                    break;
                case Operacija.POSALJI_PORUKU:
                    Poruka poruka = (Poruka) zahtev.getParametar();
                    boolean uspesnoPoslato = Kontroler.getInstance().sacuvajPoruku(poruka);
                    odgovor.setParametar(uspesnoPoslato);
                    if (uspesnoPoslato) {
                        osveziPorukeSvimUlogovanim();
                    }
                    break;
                case Operacija.VRATI_PRIMLJENE_PORUKE_ZA_KORISNIKA:
                    User korisnik = (User) zahtev.getParametar();
                    ArrayList<Poruka> listaPoruka = Kontroler.getInstance().vratPrimljenePorukeZaKorisnika(korisnik);
                    odgovor.setParametar(listaPoruka);
                    break;
                case Operacija.VRATI_POSLATE_PORUKE_ZA_KORISNIKA:
                    User k = (User) zahtev.getParametar();
                    ArrayList<Poruka> listaPoslatih = Kontroler.getInstance().vratPoslatePorukeZaKorisnika(k);
                    odgovor.setParametar(listaPoslatih);
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(socket, odgovor);
            if (uspesnoLogovanje) {
                obavestiUlogovane();
            }
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

    public void posaljiOdgovor(Socket socket, Odgovor odgovor) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(odgovor);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obavestiUlogovane() {

        HashMap<String, Socket> mapaUlogovanih = Kontroler.getInstance().getMapaUlogovanih();
        ArrayList<String> ulogovani = new ArrayList<>();
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            String key = entry.getKey();
            Socket val = entry.getValue();

            ulogovani.add((String) key);
        }
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            Object key = entry.getKey();
            Socket val = entry.getValue();
            posaljiOdgovor(val, new Odgovor(ulogovani, Operacija.OSVEZI_ULOGOVANE, "Blabla"));
        }
    }

    private void osveziPorukeSvimUlogovanim() {
        HashMap<String, Socket> mapaUlogovanih = Kontroler.getInstance().getMapaUlogovanih();
        for (Map.Entry<String, Socket> entry : mapaUlogovanih.entrySet()) {
            String key = entry.getKey();
            Socket val = entry.getValue();
            ArrayList<Poruka> listaPoslatihPoruka = Kontroler.getInstance().vratPoslatePorukeZaKorisnika(new User("", "", key, "", false));
            posaljiOdgovor(val, new Odgovor(listaPoslatihPoruka, Operacija.VRATI_POSLATE_PORUKE_ZA_KORISNIKA, "Blabla"));  
            ArrayList<Poruka> listaPrimljenihPoruka = Kontroler.getInstance().vratPrimljenePorukeZaKorisnika(new User("", "", key, "", false));
            posaljiOdgovor(val, new Odgovor(listaPrimljenihPoruka, Operacija.VRATI_PRIMLJENE_PORUKE_ZA_KORISNIKA, "Blabla"));
        }
    }

}
