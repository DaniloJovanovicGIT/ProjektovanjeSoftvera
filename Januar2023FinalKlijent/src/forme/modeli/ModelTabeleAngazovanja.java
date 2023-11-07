/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Angazovanje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleAngazovanja extends AbstractTableModel {

    ArrayList<Angazovanje> lista = new ArrayList<>();
    String[] kolone = {"Profesor", "Predmet", "Datum", "Uneo"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    public ArrayList<Angazovanje> getLista() {
        return lista;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setLista(ArrayList<Angazovanje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public void add(Angazovanje a) {
        this.lista.add(a);
        fireTableDataChanged();
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
            default:
                return "n/a";
        }
    }

    public void izmeniAngazovanje(Date date, int row) {
        Angazovanje a = lista.get(row);
        a.setDatum(date);
        a.setStanje("izmenjeno");
        fireTableDataChanged();
    }

    public void izbrisiRed(int row) {
        Angazovanje a = lista.get(row);
        a.setStanje("obrisano");
        lista.remove(a);
        fireTableDataChanged();
    }

}
