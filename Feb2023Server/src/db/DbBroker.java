/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Administrator;
import domen.Korisnik;
import domen.Poruka;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
public class DbBroker {

    Connection konekcija;

    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/februar2023", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() throws SQLException {
        konekcija.rollback();
    }

    public Administrator login(String email, String lozinka) {
        try {
            String upit = "SELECT * from administrator WHERE email = '" + email + "' AND lozinka = '" + lozinka + "'";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                return new Administrator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sacuvajKorisnika(String korisnickoIme, String korisnickaLozinka) throws SQLException {
        String upit = "INSERT INTO korisnik (korisnickoIme, korisnickaLozinka) VALUES (?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setString(1, korisnickoIme);
        ps.setString(2, korisnickaLozinka);
        ps.executeUpdate();
    }

    public boolean daLiJeZauzetoIme(String korisnickoIme) {
        try {
            String upit = "SELECT * FROM korisnik WHERE korisnickoIme='" + korisnickoIme + "'";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Korisnik ulogujKorisnika(String korisnickoIme, String korisnickoLozinka) {
        try {
            String upit = "SELECT * from korisnik WHERE korisnickoIme = '" + korisnickoIme + "' AND korisnickaLozinka = '" + korisnickoLozinka + "'";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                return new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sacuvajPoruku(Poruka poruka) throws SQLException {
        String upit = "INSERT INTO poruka (odKoga, zaKoga,datum,poruka) VALUES (?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setString(1, poruka.getOdKoga());
        ps.setString(2, poruka.getZaKoga());
        ps.setTimestamp(3, new Timestamp(poruka.getDatum().getTime()));
        ps.setString(4, poruka.getPoruka());
        ps.executeUpdate();
    }

    public ArrayList<Poruka> vratiListuPorukaZaKorisnika(String korisnickoIme) throws SQLException {
        ArrayList<Poruka> lista = new ArrayList<>();
        String upit = "SELECT 	`porukaID`, \n"
                + "	`odKoga`, \n"
                + "	`zaKoga`, \n"
                + "	`datum`, \n"
                + "	`poruka`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`februar2023`.`poruka` WHERE zaKoga='" + korisnickoIme
                + "' OR zaKoga='svima' OR odKoga = '" + korisnickoIme + "' ORDER BY datum DESC";
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            lista.add(new Poruka(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5)));
        }
        return lista;
    }

    public ArrayList<Poruka> vratiListuPoruka() {
        ArrayList<Poruka> lista = new ArrayList<>();

        try {
            String upit = "SELECT 	`porukaID`, \n"
                    + "	`odKoga`, \n"
                    + "	`zaKoga`, \n"
                    + "	`datum`, \n"
                    + "	`poruka`\n"
                    + "	 \n"
                    + "	FROM \n"
                    + "	`februar2023`.`poruka` ORDER BY datum DESC";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                lista.add(new Poruka(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5)));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Korisnik> vratiKorisnike() throws SQLException {
        ArrayList<Korisnik> lista = new ArrayList<>();
        String upit = "SELECT 	`korisnikID`, \n"
                + "	`korisnickoIme`, \n"
                + "	`korisnickaLozinka`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`februar2023`.`korisnik`";
        Statement s = konekcija.createStatement();
        ResultSet rs  = s.executeQuery(upit);
        while (rs.next()) {            
            Korisnik k = new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3));
            lista.add(k);
        }
        return lista;
    }

}
