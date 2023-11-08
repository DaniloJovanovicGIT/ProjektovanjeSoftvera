/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Delo;
import domen.Festival;
import domen.Izvodjenje;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo
 */
class DBBroker {

    Connection conn;

    public DBBroker() {
    }

    public void otvoriKonekciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mart2022", "root", "");
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

    ArrayList<Festival> vratiFestivale() throws SQLException {
        ArrayList<Festival> lista = new ArrayList<>();
        String upit = "SELECT 	`FestivalID`, \n"
                + "	`Naziv`, \n"
                + "	`DatumOd`, \n"
                + "	`DatumDo`, \n"
                + "	`Opis`, \n"
                + "	`Korisnik`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`mart2022`.`festival`";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            lista.add(new Festival(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getString(6), null));
        }
        return lista;
    }

    ArrayList<Izvodjenje> vratiIzvodjenjaZaFestival(Festival festival) throws SQLException {
        ArrayList<Izvodjenje> lista = new ArrayList<>();
        String upit = "SELECT * FROM izvodjenje i JOIN delo d ON i.DeloID=d.DeloID WHERE i.FestivalID=" + festival.getFestivalID();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            Delo delo = new Delo(rs.getInt("d.DeloID"), rs.getString("d.Naziv"));
            lista.add(new Izvodjenje(festival, delo, rs.getDate("DatumVremePocetka"), rs.getDate("DatumVremeKraja"), rs.getString("Sala"), "sacuvano"));
        }
        return lista;
    }

    public ArrayList<Delo> vratiDela() throws SQLException {
        ArrayList<Delo> lista = new ArrayList<>();
        String upit = "SELECT 	`DeloID`, \n"
                + "	`Naziv`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`mart2022`.`delo` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            Delo delo = new Delo(rs.getInt("DeloID"), rs.getString("Naziv"));
            lista.add(delo);
        }
        return lista;
    }

    void obrisi(Izvodjenje izvodjenje) throws SQLException {
        String upit = "DELETE FROM izvodjenje WHERE FestivalID=? AND DeloID=?";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setInt(1, izvodjenje.getFestival().getFestivalID());
        ps.setInt(2, izvodjenje.getDelo().getDeloID());
        ps.executeUpdate();
    }

    void dodaj(Izvodjenje izvodjenje) throws SQLException {
        String upit = "INSERT INTO izvodjenje (`FestivalID`, \n"
                + "	`DeloID`, \n"
                + "	`DatumVremePocetka`, \n"
                + "	`DatumVremeKraja`, \n"
                + "	`Sala`) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setInt(1, izvodjenje.getFestival().getFestivalID());
        ps.setInt(2, izvodjenje.getDelo().getDeloID());
        ps.setDate(3,new java.sql.Date(izvodjenje.getDatumVremePocketka().getTime()));
        ps.setDate(4, new java.sql.Date(izvodjenje.getDatumVremeKraja().getTime()));
        ps.setString(5, izvodjenje.getSala());
        ps.executeUpdate();
    }

    void izmeniFestival(Festival festival) throws SQLException {
        String upit = "UPDATE festival SET Naziv=?,DatumOd=?,DatumDo=?,Opis=?, Korisnik=? WHERE FestivalID=" + festival.getFestivalID();
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, festival.getNaziv());
        ps.setDate(2, new java.sql.Date(festival.getDatumOd().getTime()));
        ps.setDate(3, new java.sql.Date(festival.getDatumDo().getTime()));
        ps.setString(4, festival.getOpis());
        ps.setString(5, festival.getKorisnik());
        ps.executeUpdate();
    }
}
