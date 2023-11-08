/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Izvodjenje;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleIzvodjenja extends AbstractTableModel {

    ArrayList<Izvodjenje> lista = new ArrayList<>();
    String[] kolone = {"Delo", "Vreme pocetka", "Vreke kraja", "Sala", "Stanje"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setLista(ArrayList<Izvodjenje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public void add(Izvodjenje i) {
        lista.add(i);
        fireTableDataChanged();
    }

    public ArrayList<Izvodjenje> getLista() {
        return lista;
    }
    
    

    public void remove(int row) {
        Izvodjenje izvodjenje = lista.get(row);
        if (izvodjenje.getStanje().equals("dodato")) {
            lista.remove(row);
        } else {
            izvodjenje.setStanje("obrisano");
        }

        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Izvodjenje i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getDelo();
            case 1:
                return i.getDatumVremePocketka();
            case 2:
                return i.getDatumVremeKraja();
            case 3:
                return i.getSala();
            case 4:
                return i.getStanje();

            default:
                return i.getStanje();
        }
    }

}
