/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Festival;
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

    public void otvoriKonekciju() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftmart22", "root", "");
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

    public ArrayList<Festival> vratiSveFestivale() {
        try {
            ArrayList<Festival> lista = new ArrayList<>();
            int festivalID = -1;
            String upit = "SELECT 	`FestivalID`, \n"
                    + "	`Naziv`, \n"
                    + "	`DatumOd`, \n"
                    + "	`DatumDo`, \n"
                    + "	`Opis`, \n"
                    + "	`Korisnik`\n"
                    + "	 \n"
                    + "	FROM \n"
                    + "	`prosoftmart22`.`festival` ";
            Statement s  = conn.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {                
                Festival f =new Festival(rs.getLong("FestivalID"),
                        rs.getString("Naziv"),
                        rs.getDate("Naziv"),
                        rs.getDate("DatumDo"), rs.getString("Opis"), rs.getString("Korisnik"), null);
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
