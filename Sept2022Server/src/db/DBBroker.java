/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import com.mysql.cj.protocol.Resultset;
import domen.Komponenta;
import domen.Pogon;
import domen.PrevoznoSredstvo;
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

    public void otvoriKonkeciju() {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/septembar2022", "root", "");
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

    public ArrayList<Pogon> vratiSvePogone() throws SQLException {
        ArrayList<Pogon> pogoni = new ArrayList<>();
        String upit = "SELECT 	`pogonID`, \n"
                + "	`naziv`, \n"
                + "	`adresa`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`pogon` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            pogoni.add(new Pogon(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return pogoni;
    }

    public void sacuvajKomponentu(Komponenta komponenta, int prevoznoSredstvoID) throws SQLException {
        String upit = "INSERT INTO komponenta (naziv,kolicina,kontrolisano,prevoznoSredstvoID) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, komponenta.getNaziv());
        ps.setInt(2, komponenta.getKolicina());
        ps.setBoolean(3, komponenta.isKontrolisano());
        ps.setInt(4, prevoznoSredstvoID);
        ps.executeUpdate();
    }

    public int sacuvajPrevoznoSredstvo(PrevoznoSredstvo ps) throws SQLException {
        String upit = "INSERT INTO prevoznosredstvo (`model`, `pocetakProizvodnje`,`zavrsetakProizvodnje`,`ocekivaniRadniVek`,`tipPrevoznogSredstva`,`pogonID`, `rukovodilac`) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement p = conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        p.setString(1, ps.getModel());
        p.setDate(2, new java.sql.Date(ps.getPocetakProizvodnje().getTime()));
        p.setDate(3, new java.sql.Date(ps.getZavretakProizvodnje().getTime()));
        p.setInt(4, ps.getOcekivaniRadniVek());
        p.setString(5, ps.getTip());
        p.setInt(6, ps.getPogon().getId());
        p.setString(7, ps.getRukovodilac().getKorisnickoIme());
        int key = p.executeUpdate();
        return key;
    }
}
