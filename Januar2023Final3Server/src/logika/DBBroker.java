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
import pomocne.BrojAngazovanja;
import pomocne.BrojProfesora;

/**
 *
 * @author Danilo
 */
class DBBroker {

    Connection conn;

    void otvoriKonkeciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/januar2023final3", "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void zatvoriKonekciju() {
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

    void sacuvajProfesora(Profesor profesor) throws SQLException {
        String upit = "INSERT INTO profesor ( \n"
                + "	`ime`, \n"
                + "	`prezime`, \n"
                + "	`zvanje`, \n"
                + "	`emailKorisnika`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, profesor.getIme());
        ps.setString(2, profesor.getPrezime());
        ps.setString(3, profesor.getZvanje());
        ps.setString(4, profesor.getEmailKorisnika());
        ps.executeUpdate();
    }

    ArrayList<Profesor> vratiProfesore() throws SQLException {
        ArrayList<Profesor> listaProfesora = new ArrayList<>();
        String upit = "SELECT 	`profesorId`, \n"
                + "	`ime`, \n"
                + "	`prezime`, \n"
                + "	`zvanje`, \n"
                + "	`emailKorisnika`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`januar2023final3`.`profesor` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaProfesora.add(new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return listaProfesora;
    }

    void sacuvajPredmet(Predmet predmet) throws SQLException {
        String upit = "INSERT INTO predmet ( `sifraPredmeta`, \n"
                + "	`nazivPredmeta`, \n"
                + "	`kodPRedmeta`, \n"
                + "	`brojESPB`) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setString(1, predmet.getSifraPredmeta());
        ps.setString(2, predmet.getNazivPredmeta());
        ps.setString(3, predmet.getKodPredmeta());
        ps.setInt(4, predmet.getBrojEPSB());
        ps.executeUpdate();
    }

    void sacuvajAngazovanje(Angazovanje angazovanje) throws SQLException {
        String upit = "INSERT INTO angazovanje ( \n"
                + "	`profesorId`, \n"
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

    int vratiBrojAngazovanjaProfesora(Profesor prof) throws SQLException {
        int broj = -1;
        String upit = "SELECT count(profesorId) FROM angazovanje WHERE profesorId=" + prof.getProfesorID();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            broj = rs.getInt(1);
        }
        return broj;
    }

    ArrayList<Angazovanje> vratiAngazovanjaZaProfesora(Profesor izabrani) throws SQLException {
        ArrayList<Angazovanje> listaAngazovanja = new ArrayList<>();
        String upit = "SELECT 	a.`angazovanjeID`, \n"
                + "	a.`profesorId`, \n"
                + "	a.`sifraPredmeta`, \n"
                + "	a.`datum`, \n"
                + "	a.`emailKorisnika`, p.sifraPredmeta, p.nazivPredmeta, p.kodPredmeta, p.brojESPB\n"
                + "	 \n"
                + "	FROM \n"
                + "	`angazovanje` a JOIN predmet p ON a.sifraPredmeta = p.sifraPredmeta WHERE a.profesorId=" + izabrani.getProfesorID();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            Predmet predmet = new Predmet(rs.getString("p.sifraPredmeta"), rs.getString("p.nazivPredmeta"), rs.getString("p.kodPredmeta"), rs.getInt("p.brojESPB"));
            listaAngazovanja.add(new Angazovanje(rs.getInt(1), izabrani, predmet, rs.getDate(4), rs.getString(5),"sacuvano"));
        }
        return listaAngazovanja;
    }

    ArrayList<BrojAngazovanja> vratiBrojAngazovanja() throws SQLException {
        ArrayList<BrojAngazovanja> listaBrojAngazovanja = new ArrayList<>();
        String upit = "SELECT p.ime,p.prezime,COUNT(a.angazovanjeID) FROM profesor p INNER JOIN angazovanje a ON p.profesorId=a.profesorId GROUP BY p.ime,p.prezime";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaBrojAngazovanja.add(new BrojAngazovanja(rs.getString(1) + " " + rs.getString(2), rs.getInt(3)));
        }
        return listaBrojAngazovanja;
    }

    ArrayList<BrojProfesora> vratibrojProfesora() throws SQLException {
        ArrayList<BrojProfesora> listaBrojProfesora = new ArrayList<>();
        String upit = "SELECT zvanje,COUNT(profesorId) FROM profesor p  GROUP BY zvanje";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            listaBrojProfesora.add(new BrojProfesora(rs.getString(1), rs.getInt(2)));
        }            

        return listaBrojProfesora;
    }

}
