/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Pogon;
import domen.PrevoznoSredstvo;
import domen.Rukovodilac;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.Operacija;
import komunikacija.ServerskiOdgovor;
import logika.Kontroler;

/**
 *
 * @author Danilo
 */
class ObradaZahtevaKlijenta extends Thread {

    Socket socket;

    public ObradaZahtevaKlijenta(Socket klijent) {
        socket = klijent;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev zahtev = primiZahtev();
            ServerskiOdgovor odgovor = new ServerskiOdgovor();
            switch (zahtev.getOperacija()) {
                case Operacija.LOGIN:

                    Rukovodilac r = (Rukovodilac) zahtev.getParametar();

                    Rukovodilac ulogovani = Kontroler.getInstanca().login(r);

                    odgovor.setParametar(ulogovani);

                    break;
                case Operacija.VRATI_POGONE:

                    ArrayList<Pogon> listaPogona = Kontroler.getInstanca().vratiSvePogone();

                    odgovor.setParametar(listaPogona);

                    break;
                case Operacija.SACUVAJ_PREVOZNO_SREDSTVO:

                    PrevoznoSredstvo ps = (PrevoznoSredstvo) zahtev.getParametar();
                    boolean sacuvano = Kontroler.getInstanca().sacuvajPrevoznoSredstvo(ps);
                    odgovor.setParametar(sacuvano);

                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(odgovor);

        }
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private KlijentskiZahtev primiZahtev() {

        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahtevaKlijenta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
