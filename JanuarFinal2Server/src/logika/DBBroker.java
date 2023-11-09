/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Angazovanje;
import domen.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import domen.Predmet;
import pomocne.BrojAngazovanja;
import pomocne.BrojProfesora;

/**
 *
 * @author Danilo
 */
class DBBroker {

    Connection conn;

    public void otvoriKonkeciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/januarfinal2", "root", "");
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

    void sacuvajProfesora(Profesor noviProfesor) throws SQLException {
        String upit = "INSERT INTO profesor (`ime`, \n"
                + "	`prezime`, \n"
                + "	`zvanje`, \n"
                + "	`emailKorisnika`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, noviProfesor.getIme());
        ps.setString(2, noviProfesor.getPrezime());
        ps.setString(3, noviProfesor.getZvanje());
        ps.setString(4, noviProfesor.getEmailKorisnika());
        ps.executeUpdate();
    }

    ArrayList<Profesor> vratiProfesore() throws SQLException {
        ArrayList<Profesor> listaProfesora = new ArrayList<>();
        String upit = "SELECT 	`profesorID`, \n"
                + "	`ime`, \n"
                + "	`prezime`, \n"
                + "	`zvanje`, \n"
                + "	`emailKorisnika`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`januarfinal2`.`profesor` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaProfesora.add(new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), null));
        }
        return listaProfesora;
    }

    ArrayList<Angazovanje> vratiAngazovanjaZaProfesora(Profesor profesor) throws SQLException {
        ArrayList<Angazovanje> listaAngazovanja = new ArrayList<>();
        String upit = "SELECT 	a.`angazovanjeID`, \n"
                + "	a.`profesorID`, \n"
                + "	a.`sifraPredmeta`, \n"
                + "	a.`datum`, \n"
                + "	a.`emailKorisnika`,p.sifraPredmeta,p.nazivPredmeta,p.kodPredmeta,p.brojESPB"
                + "	 \n"
                + "	FROM \n"
                + "	`januarfinal2`.`angazovanje` a JOIN predmet p ON a.sifraPredmeta = p.sifraPredmeta WHERE a.profesorID= " + profesor.getProfesorID();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            Predmet predmet = new Predmet(rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));

            listaAngazovanja.add(new Angazovanje(rs.getInt(1), profesor, predmet, rs.getDate(4), rs.getString(7), "sacuvano"));
        }
        return listaAngazovanja;
    }

    ArrayList<Predmet> vratiPredmete() throws SQLException {
        ArrayList<Predmet> listaPredmeta = new ArrayList<>();
        String upit = "SELECT 	`sifraPredmeta`, \n"
                + "	`nazivPredmeta`, \n"
                + "	`kodPredmeta`, \n"
                + "	`brojESPB`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`januarfinal2`.`predmet`";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaPredmeta.add(new Predmet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        return listaPredmeta;
    }

    void sacuvajAngazovanje(Angazovanje angazovanje) throws SQLException {
        String upit = "INSERT INTO angazovanje (`profesorID`, \n"
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

    void izmeniAngazovanje(Angazovanje angazovanje) throws SQLException {
        String upit = "UPDATE  angazovanje SET `profesorID`=?, \n"
                + "	`sifraPredmeta`=?, \n"
                + "	`datum`=?, \n"
                + "	`emailKorisnika`=? WHERE angazovanjeID =?";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setInt(1, angazovanje.getProfesor().getProfesorID());
        ps.setString(2, angazovanje.getPredmet().getSifraPredmeta());
        ps.setDate(3, new java.sql.Date(angazovanje.getDatum().getTime()));
        ps.setString(4, angazovanje.getEmailKorisnika());
        ps.setInt(5, angazovanje.getAngazovanjeID());
        ps.executeUpdate();
    }

    void obrisiAngazovanje(Angazovanje angazovanje) throws SQLException {
        String upit = "DELETE FROM angazovanje WHERE `angazovanjeID`=?";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setInt(1, angazovanje.getAngazovanjeID());
        ps.executeUpdate();
    }

    ArrayList<BrojAngazovanja> vratiBrojAngaozvanja() throws SQLException {
        ArrayList<BrojAngazovanja> listaBrojAngaozvanja = new ArrayList<>();
        String upit = "SELECT p.ime, p.prezime, COUNT(angazovanjeID) FROM profesor p INNER JOIN angazovanje a ON p.profesorID=a.profesorID GROUP BY p.ime,p.prezime";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaBrojAngaozvanja.add(new BrojAngazovanja(rs.getString(1)+" "+rs.getString(2), rs.getInt(3)));
        }
        return listaBrojAngaozvanja;
    }

    ArrayList<BrojProfesora> vratiBrojProfesora() throws SQLException {
        ArrayList<BrojProfesora> listaBrojProfesora = new ArrayList<>();
        String upit = "SELECT p.zvanje, COUNT(profesorID) FROM profesor p GROUP BY p.zvanje";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaBrojProfesora.add(new BrojProfesora(rs.getString(1), rs.getInt(2)));
        }
        return listaBrojProfesora;
    }
}
