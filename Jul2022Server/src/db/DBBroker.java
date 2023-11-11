/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.ProgramskiVodic;
import domen.Stanica;
import domen.StavkaProgramskogVodica;
import domen.TipEmisije;
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

    public void zatvoriKonkeciju() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjul22", "root", "");
            conn.setAutoCommit(false);
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

    public ArrayList<Stanica> vratiStanice() throws SQLException {
        ArrayList<Stanica> lista = new ArrayList<>();
        String upit = "select 	`StanicaID`, \n"
                + "	`Naziv`, \n"
                + "	`Tip`, \n"
                + "	`Kontakt`\n"
                + "	 \n"
                + "	from \n"
                + "	`prosoftjul22`.`stanica` ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            lista.add(new Stanica(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        return lista;
    }

    public ArrayList<TipEmisije> vratiTipove() throws SQLException {
        ArrayList<TipEmisije> lista = new ArrayList<>();
        String upit = "SELECT 	`TipEmisijeID`, \n"
                + "	`Naziv`, \n"
                + "	`NadtipEmisijeID`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`prosoftjul22`.`tipemisije` WHERE NadtipEmisijeID IS NULL";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            lista.add(new TipEmisije(rs.getLong(1), rs.getString(2), null));
        }
        return lista;
    }

    public ArrayList<TipEmisije> vratiPodtipove(TipEmisije nadtip) throws SQLException {
        ArrayList<TipEmisije> lista = new ArrayList<>();
        String upit = "SELECT 	`TipEmisijeID`, \n"
                + "	`Naziv`, \n"
                + "	`NadtipEmisijeID`\n"
                + "	 \n"
                + "	FROM \n"
                + "	`prosoftjul22`.`tipemisije` WHERE NadtipEmisijeID=" + nadtip.getTipEmisijeID();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            lista.add(new TipEmisije(rs.getLong(1), rs.getString(2), nadtip));
        }
        return lista;
    }

    public long sacuvajProgramskiVodic(ProgramskiVodic pv) throws SQLException {
        String upitVodic = "INSERT INTO programskivodic (`Dan`, \n"
                + "	`Opis`, \n"
                + "	`Urednik`) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upitVodic, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, new java.sql.Date(pv.getDan().getTime()));
        ps.setString(2, pv.getOpis());
        ps.setString(3, pv.getUrednik());
        long programskiVodicID = ps.executeUpdate();
        return programskiVodicID;

    }

    public void sacuvajStavke(ArrayList<StavkaProgramskogVodica> listaStavki, long programskiVodicId) throws SQLException {
        String upitStavka = "INSERT INTO stavkaprogramskogvodica (`ProgramskiVodicID`, \n"
                + "	`RB`, \n"
                + "	`NazivEmisije`, \n"
                + "	`DatumVremeOd`, \n"
                + "	`DatumVremeDo`, \n"
                + "	`StanicaID`, \n"
                + "	`TipEmisijeID`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(upitStavka);

        for (StavkaProgramskogVodica stavka : listaStavki) {
            ps.setLong(1, programskiVodicId);
            ps.setInt(2, stavka.getRB());
            ps.setString(3, stavka.getNazivEmisije());
            ps.setDate(4, new java.sql.Date(stavka.getDatumVremeOd().getTime()));
            ps.setDate(5, new java.sql.Date(stavka.getDatumVremeDo().getTime()));
            ps.setLong(6, stavka.getStanica().getStanicaID());
            ps.setLong(7, stavka.getTipEmisije().getTipEmisijeID());
            ps.executeUpdate();
        }
    }

}
