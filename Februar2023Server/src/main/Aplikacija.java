/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import domen.Administrator;
import domen.Korisnik;
import domen.Poruka;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import server.PokretanjeServera;

/**
 *
 * @author Danilo
 */
public class Aplikacija {

    PokretanjeServera ps;

    public void pokreniSve() {
        try {
            int brojPokusaja = 3;
            boolean uspesnoLogovanje = false;
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ulogujte se");
            while (!uspesnoLogovanje) {
                System.out.println("Unesite vas email:");
                String email = bf.readLine();
                System.out.println("Unesite vasu lozinuku");
                String lozinka = bf.readLine();

                Administrator ulogovaniAdmin = Kontroler.getInstanca().login(email, lozinka);
                if (ulogovaniAdmin != null) {
                    System.out.println("Uspesno ste se logovali\nUlogovani administrator: " + ulogovaniAdmin.getIme());
                    uspesnoLogovanje = true;
                } else {
                    brojPokusaja--;
                }
                if (brojPokusaja == 0) {
                    System.out.println("Imali ste samo 3 pokusaja!");
                    System.exit(0);
                }
            }

            boolean zaustaviSve = false;
            while (!zaustaviSve) {
                ispisOpcije();
                String izabrano = bf.readLine();

                switch (izabrano) {
                    case "1":
                        if (ps == null) {
                            ps = new PokretanjeServera();
                            ps.start();
                            System.out.println("Server je pokrenut");
                        } else {
                            System.out.println("Server je vec pokrenut!");
                        }
                        break;
                    case "2":
                        if (ps != null) {
                            ps.zaustaviServer();
                            System.out.println("Server zaustavljen");
                        } else {
                            System.out.println("Server nije pokrenut!");
                        }
                        break;
                    case "3":
                        System.out.println("Kreiranje korisnika:");
                        System.out.println("Unesite korisnicko ime:");
                        String korisnickoIme = bf.readLine();
                        while (!proveriDaLiSadrziSvaSlova(korisnickoIme)) {
                            System.out.println("Korisnicko ime mora sadrzati samo slova");
                            korisnickoIme = bf.readLine();
                        }
                        System.out.println("Unesite korisnicku lozinku");
                        String korisnickaLozinka = bf.readLine();
                        while (!(proveriDaLiSadrziMakarJednoSlovo(korisnickaLozinka) && proveriDaLiSadrziMakaraJedanBroj(korisnickaLozinka))) {
                            korisnickaLozinka = bf.readLine();
                        }
                        Kontroler.getInstanca().sacuvajKorisnika(korisnickoIme, korisnickaLozinka);
                        break;
                    case "4":
                        ArrayList<Poruka> listaPoruka = Kontroler.getInstanca().vratiListuPoruka();
                        int brojac =0;
                        for (Poruka poruka : listaPoruka) {
                            brojac++;
                            System.out.println("Poruka:"+poruka.toString()+System.lineSeparator());
                            if(brojac%5==0){
                                bf.readLine();
                            }
                        }
                        break;
                    case "0":
                        System.out.println("Server se gasi");
                        zaustaviSve=true;
                        Kontroler.getInstanca().odjaviSveKlijente();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Greska! \nValidne opcije su 1,2,3,4 i 0\n");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Aplikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ispisOpcije() {
        System.out.println("Izaberite opciju:\n");
        System.out.println("Opcija 1: Pokreni server");
        System.out.println("Opcija 2: Zaustavi server");
        System.out.println("Opcija 3: Unesi korisnika");
        System.out.println("Opcija 4: Prikazi poruke");
        System.out.println("Opcija 0: Ugasi sve");
    }

    private boolean proveriDaLiSadrziSvaSlova(String text) {
        for (Character slovo : text.toCharArray()) {
            if (!Character.isLetter(slovo)) {
                System.out.println("Unesi korisnicko ime ponovo");
                return false;
            }
        }
        return true;
    }

    private boolean proveriDaLiSadrziMakaraJedanBroj(String text) {
        for (Character slovo : text.toCharArray()) {
            if (Character.isDigit(slovo)) {
                return true;
            }
        }
        System.out.println("Lozinka mora imati makar jedan broj.");
        System.out.println("Unesi lozinku ponovo");
        return false;
    }

    private boolean proveriDaLiSadrziMakarJednoSlovo(String text) {
        for (Character slovo : text.toCharArray()) {
            if (Character.isLetter(slovo)) {
                return true;
            }
        }
        System.out.println("Lozinka mora imati bar jedno slovo.");
        System.out.println("Unesi lozinku ponovo");
        return false;
    }
}
