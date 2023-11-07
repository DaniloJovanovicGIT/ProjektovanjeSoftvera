/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocne.BrojProfesoraPoZvanju;
import pomocne.ProfesorBrojAngazovanja;

/**
 *
 * @author Danilo
 */
class DBBroker {

    Connection conn;

    public void otvoriKonkeckiju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/januarfinal", "root", "");
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

    public void sacuvajProfesora(Profesor p) throws SQLException {
        String upit = "INSERT INTO profesor(`ime`, \n"
                + "	`prezime`, \n"
                + "	`zvanje`, \n"
                + "	`emailKorisnika`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, p.getIme());
        ps.setString(2, p.getPrezime());
        ps.setString(3, p.getZvanje());
        ps.setString(4, p.getEmailKorisnika());
        ps.executeUpdate();
    }

    ArrayList<Profesor> vratiProfesore() throws SQLException {
        ArrayList<Profesor> profesori = new ArrayList<>();
        String upit = "	SELECT 	`profesorID`, \n"
                + "		`ime`, \n"
                + "		`prezime`, \n"
                + "		`zvanje`, \n"
                + "		`emailKorisnika`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`profesor` \n"
                + "	";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            profesori.add(new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return profesori;
    }

    void sacuvajAngazovanje(Angazovanje angazovanje) throws SQLException {
        String upit = "INSERT INTO angazovanje(`profesorID`, \n"
                + "	`sifraPredmeta`, \n"
                + "	`datum`, \n"
                + "	`emailKorisnika`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setInt(1, angazovanje.getProfesor().getProfesorID());
        ps.setString(2, angazovanje.getPredmet().getSifraPredmeta());
        ps.setDate(3, new java.sql.Date(angazovanje.getDatum().getTime()));
        ps.setString(4, angazovanje.getEmailKorisnika());
        ps.executeUpdate();
    }

    void sacuvajPredmet(Predmet predmet) throws SQLException {
        String upit = "INSERT INTO predmet(`sifraPredmeta`, \n"
                + "	`nazivPredmeta`, \n"
                + "	`kodPredmeta`, \n"
                + "	`brojESPB`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, predmet.getSifraPredmeta());
        ps.setString(2, predmet.getNzaivPredmeta());
        ps.setString(3, predmet.getKodPredmeta());
        ps.setInt(4, predmet.getBrojEspb());
        ps.executeUpdate();
    }

    ArrayList<Angazovanje> vratiAngazovanjaProfesora(Profesor profesor) throws SQLException {
        ArrayList<Angazovanje> angazovanje = new ArrayList<>();
        String upit = "SELECT 	*"
                + "	 \n"
                + "	FROM \n"
                + "	angazovanje a LEFT JOIN predmet p ON a.sifraPredmeta=p.sifraPredmeta WHERE profesorID='" + profesor.getProfesorID() + "'";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            Predmet p = new Predmet(rs.getString("p.sifraPredmeta"), rs.getString("nazivPredmeta"), rs.getString("kodPredmeta"), rs.getInt("brojESPB"), null);
            angazovanje.add(new Angazovanje(rs.getInt("angazovanjeID"), profesor, p, rs.getDate("datum"), rs.getString("emailKorisnika"), "sacvano"));
        }
        return angazovanje;
    }

    ArrayList<ProfesorBrojAngazovanja> vratiProfBrojAngazovanja() throws SQLException {
        ArrayList<ProfesorBrojAngazovanja> angazovanje = new ArrayList<>();
        String upit = "SELECT p.ime, p.prezime, COUNT(angazovanjeID) AS brojAngazovanja FROM angazovanje a JOIN profesor p ON a.profesorID=p.profesorID GROUP BY p.ime";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            angazovanje.add(new ProfesorBrojAngazovanja(rs.getString(1), rs.getString(2), rs.getInt(3)));
        }
        return angazovanje;
    }

    ArrayList<BrojProfesoraPoZvanju> vratiProfBrojPoZvanu() throws SQLException {
        ArrayList<BrojProfesoraPoZvanju> poZvanu = new ArrayList<>();
        String upit = "SELECT zvanje, COUNT(profesorID) FROM profesor GROUP BY zvanje";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            poZvanu.add(new BrojProfesoraPoZvanju(rs.getInt(2),rs.getString(1)));
        }
        return poZvanu;
    }

}
