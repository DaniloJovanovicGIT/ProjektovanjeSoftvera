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

    ArrayList<Angazovanje> listaAngazovanja = new ArrayList<>();
    String[] naziviKolona = {"Profesor", "Predmet", "Datum", "Uneo", "Stanje"};

    @Override
    public int getRowCount() {
        return listaAngazovanja.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public String getColumnName(int column) {
        return naziviKolona[column];
    }

    public void setListaAngazovanja(ArrayList<Angazovanje> listaAngazovanja) {
        this.listaAngazovanja = listaAngazovanja;
        fireTableDataChanged();
    }

    public void add(Angazovanje a) {
        this.listaAngazovanja.add(a);
        fireTableDataChanged();
    }

    public ArrayList<Angazovanje> getListaAngazovanja() {
        return listaAngazovanja;
    }
    
    
    
    public void removeRow(int row) {
        Angazovanje a = listaAngazovanja.get(row);
        if (a.getStanje().equals("sacuvano")) {
            a.setStanje("obrisano");
        } else {
            listaAngazovanja.remove(row);
        }
        fireTableDataChanged();
    }

    public Angazovanje getAngazovanje(int row) {
        return listaAngazovanja.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Angazovanje a = listaAngazovanja.get(rowIndex);
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
                return "n/a";
        }
    }

    public void ucitajPromenu() {
        fireTableDataChanged();
    }

}
