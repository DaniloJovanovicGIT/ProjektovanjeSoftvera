/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class Komunikacija {
    private static Komunikacija instanca;
    private Socket socket;
    private Komunikacija() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Komunikacija getInstanca() {
        if(instanca==null){
        instanca=new Komunikacija();
        }
        return instanca;
    }
    
    public void posaljiZahtev(Zahtev zahtev){
        try {
            ObjectOutputStream oos =  new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(zahtev);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Odgovor primiOdgovor(){
        try {
            ObjectInputStream ois =  new ObjectInputStream(socket.getInputStream());
            return (Odgovor) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
