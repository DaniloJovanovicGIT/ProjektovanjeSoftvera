/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forma.modeli;

import domen.Angazovanje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleAngazovanja extends AbstractTableModel {

    ArrayList<Angazovanje> lista = new ArrayList<>();
    String[] nazivi = {"Profesor", "Predmet", "Datum", "Uneo","Stanje"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    public ArrayList<Angazovanje> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Angazovanje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return nazivi.length;
    }

    @Override
    public String getColumnName(int column) {
        return nazivi[column]; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Angazovanje a = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getProfesor();
            case 1:
                return a.getPredmet();
            case 2:
                return sdf.format(a.getDatum());
            case 3:
                return a.getEmailKorisnika();
            case 4:
                return a.getStanje();

            default:
                return a.getEmailKorisnika();
        }
    }

}
