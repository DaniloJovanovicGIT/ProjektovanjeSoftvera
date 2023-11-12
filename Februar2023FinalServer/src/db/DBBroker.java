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
public class DBBroker {

    Connection conn;

    public void otvoriKonekciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/februar2023final", "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriKonekciju() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Administrator> vratiAdministratore() throws SQLException {
        ArrayList<Administrator> lista = new ArrayList<>();
        String upit = "SELECT 	`administratorId`, \n"
                + "	`ime`, \n"
                + "	`prezime`, \n"
                + "	`username`, \n"
                + "	`password`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`februar2023final`.`administrator` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            lista.add(new Administrator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return lista;
    }

    public void sacuvajKorisnika(Korisnik korisnik) throws SQLException {
        String upit = "INSERT INTO korisnik (korisnickoIme, korisnickaLozinka) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, korisnik.getKorisnickoIme());
        ps.setString(2, korisnik.getKorisnickaLozinka());
        ps.executeUpdate();
    }

    public boolean daLiJeKorisnickoImeJedinstveno(String korisnickoIme) throws SQLException {
        boolean jeste = true;
        String upit = "select 	`korisnickoIme`, \n"
                + "	`korisnickaLozinka`\n"
                + "	 \n"
                + "	from \n"
                + "	`februar2023final`.`korisnik` WHERE korisnickoIme='" + korisnickoIme + "'";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            jeste = false;
        }
        return jeste;
    }

    public ArrayList<Korisnik> vratiKOrisnike() throws SQLException {
        ArrayList<Korisnik> listaKorisnika = new ArrayList<>();
        String upit = "SELECT 	`korisnickoIme`, \n"
                + "	`korisnickaLozinka`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`februar2023final`.`korisnik` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaKorisnika.add(new Korisnik(rs.getString(1), rs.getString(2), false));
        }
        return listaKorisnika;
    }

    public void sacuvajPoruku(Poruka poruka) throws SQLException {
        String upit = "INSERT INTO poruka ( \n"
                + "	`odKoga`, \n"
                + "	`zaKoga`, \n"
                + "	`tekstPoruke`, \n"
                + "	`vremeSlanja`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, poruka.getOdKoga());
        ps.setString(2, poruka.getZaKoga());
        ps.setString(3, poruka.getTekstPoruke());
        ps.setTimestamp(4, new java.sql.Timestamp(poruka.getVremeSlanja().getTime()));
        ps.executeUpdate();
    }

    public ArrayList<Poruka> vratiPorukeZaKorisnika(Korisnik ulogovaniKorisnik) throws SQLException {
        ArrayList<Poruka> listaPoruka = new ArrayList<>();
        String upit = " SELECT 	`porukaId`, \n"
                + "	`odKoga`, \n"
                + "	`zaKoga`, \n"
                + "	`tekstPoruke`, \n"
                + "	`vremeSlanja`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`februar2023final`.`poruka` WHERE zaKoga='" + ulogovaniKorisnik.getKorisnickoIme() + "' OR zaKoga='svima' ORDER BY vremeSlanja DESC";
        Statement s = conn.createStatement();
        ResultSet rs  = s.executeQuery(upit);
        while (rs.next()) {            
            listaPoruka.add(new Poruka(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5)));
        }
        rs.close();
        s.close();
        return listaPoruka;
    }

}
