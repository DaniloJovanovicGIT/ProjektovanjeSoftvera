/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Danilo
 */
class DBBRoker {
    Connection conn;

    public DBBRoker() {
    }
    
    public void otvoriKonekciju(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftmart22", "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBRoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void zatvorKonekciju(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBRoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit(){
        try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBRoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback(){
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBRoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
