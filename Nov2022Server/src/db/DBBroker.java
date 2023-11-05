/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Poruka;
import domen.User;
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

    public DBBroker() {
    }

    public void otovoriKonekciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/novembar2022", "root", "");
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

    public User vratiUlogovanog(User user) throws SQLException {
        String upit = "SELECT 	`ime`, \n"
                + "	`prezime`, \n"
                + "	`username`, \n"
                + "	`password`\n"
                + "	 \n"
                + "	FROM user WHERE username='" + user.getUsername() + "' AND password='" + user.getPassword() + "'";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        User ulogovani = null;
        while (rs.next()) {
            ulogovani = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), true);
        }
        return ulogovani;

    }

    public void sacuvajPoruku(Poruka poruka) throws SQLException {
        System.out.println("Uso da ubavcim poruku bajo");
        String upit = "INSERT INTO poruka(odKoga,zaKoga,textPoruke,vremeSlanja) VALUES(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, poruka.getOdKoga());
        ps.setString(2, poruka.getZaKoga());
        ps.setString(3, poruka.getTextPoruke());
        ps.setTimestamp(4, new java.sql.Timestamp(poruka.getVremeSlanja().getTime()));
        ps.executeUpdate();
    }

    public ArrayList<Poruka> vratiPrimljenePoruke(User korisnik) throws SQLException {
        ArrayList<Poruka> listaPoruka = new ArrayList<>();
        String upit = "SELECT "
                + "	`odKoga`, \n"
                + "	`zaKoga`, \n"
                + "	`textPoruke`, \n"
                + "	`vremeSlanja`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`poruka` WHERE zaKoga='" + korisnik.getUsername() + "' OR zaKoga='svi' ORDER BY vremeSlanja DESC";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);

        while (rs.next()) {
            listaPoruka.add(new Poruka(rs.getString(1), rs.getString(2), rs.getString(3), new Date(rs.getTimestamp(4).getTime())));
        }
        return listaPoruka;
    }

    public ArrayList<Poruka> vratiPoslatePoruke(User k) throws SQLException {
        ArrayList<Poruka> listaPoruka = new ArrayList<>();
        String upit = "SELECT "
                + "	`odKoga`, \n"
                + "	`zaKoga`, \n"
                + "	`textPoruke`, \n"
                + "	`vremeSlanja`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`poruka` WHERE odKoga='" + k.getUsername() + "' ORDER BY vremeSlanja DESC";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);

        while (rs.next()) {
            listaPoruka.add(new Poruka(rs.getString(1), rs.getString(2), rs.getString(3), new Date(rs.getTimestamp(4).getTime())));
        }
        return listaPoruka;
    }

    public ArrayList<User> vratiKorisnike() throws SQLException {
        ArrayList<User> listaUSer = new ArrayList<>();
        String upit = "SELECT 	`ime`, \n"
                + "	`prezime`, \n"
                + "	`username`, \n"
                + "	`password`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`novembar2022`.`user` \n";

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);

        while (rs.next()) {
            listaUSer.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), false));
        }
        return listaUSer;
    }

}
