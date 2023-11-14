package db;

import domen.Administrator;
import domen.Korisnik;
import domen.Poruka;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBroker {

    private Connection conn;

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
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Administrator> vratiAdministratore() throws SQLException {
        ArrayList<Administrator> lista = new ArrayList<>();
        String upit = "SELECT `administratorId`, `ime`, `prezime`, `username`, `password` FROM `februar2023final`.`administrator`";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(upit)) {

            while (rs.next()) {
                lista.add(new Administrator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        return lista;
    }

    public void sacuvajKorisnika(Korisnik korisnik) throws SQLException {
        String upit = "INSERT INTO korisnik (korisnickoIme, korisnickaLozinka) VALUES (?,?)";
        try (PreparedStatement ps = conn.prepareStatement(upit)) {
            ps.setString(1, korisnik.getKorisnickoIme());
            ps.setString(2, korisnik.getKorisnickaLozinka());
            ps.executeUpdate();
        }
    }

    public boolean daLiJeKorisnickoImeJedinstveno(String korisnickoIme) throws SQLException {
        String upit = "SELECT `korisnickoIme`, `korisnickaLozinka` FROM `februar2023final`.`korisnik` WHERE korisnickoIme=?";
        try (PreparedStatement ps = conn.prepareStatement(upit)) {
            ps.setString(1, korisnickoIme);
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next(); // Return true if there are no rows (username is unique)
            }
        }
    }

    public ArrayList<Korisnik> vratiKorisnike() throws SQLException {
        ArrayList<Korisnik> listaKorisnika = new ArrayList<>();
        String upit = "SELECT `korisnickoIme`, `korisnickaLozinka` FROM `februar2023final`.`korisnik`";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(upit)) {

            while (rs.next()) {
                listaKorisnika.add(new Korisnik(rs.getString(1), rs.getString(2), false));
            }
        }
        return listaKorisnika;
    }

    public void sacuvajPoruku(Poruka poruka) throws SQLException {
        String upit = "INSERT INTO poruka (`odKoga`, `zaKoga`, `tekstPoruke`, `vremeSlanja`) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(upit)) {
            ps.setString(1, poruka.getOdKoga());
            ps.setString(2, poruka.getZaKoga());
            ps.setString(3, poruka.getTekstPoruke());
            ps.setTimestamp(4, new java.sql.Timestamp(poruka.getVremeSlanja().getTime()));
            ps.executeUpdate();
        }
    }

    public ArrayList<Poruka> vratiPorukeZaKorisnika(Korisnik ulogovaniKorisnik) throws SQLException {
    ArrayList<Poruka> listaPoruka = new ArrayList<>();
    String upit = "SELECT `porukaId`, `odKoga`, `zaKoga`, `tekstPoruke`, `vremeSlanja` "
            + "FROM `februar2023final`.`poruka` WHERE zaKoga=? OR zaKoga='svima' ORDER BY vremeSlanja DESC";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/februar2023final", "root", "");
         PreparedStatement ps = conn.prepareStatement(upit)) {

        ps.setString(1, ulogovaniKorisnik.getKorisnickoIme());

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaPoruka.add(new Poruka(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5)));
            }
        }
    }

    return listaPoruka;
}
}
