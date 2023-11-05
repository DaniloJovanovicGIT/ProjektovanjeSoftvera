/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.modeli;

import domen.Poruka;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModeltabelePoruka extends AbstractTableModel {

    ArrayList<Poruka> listaPoruka = new ArrayList<>();
    String[] nazivi = {"Posiljalac", "Primalac", "Text", "Vreme"};

    @Override
    public int getRowCount() {
        return listaPoruka.size();
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
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Poruka poruka = listaPoruka.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return poruka.getOdKoga();
            case 1:
                return poruka.getZaKoga();
            case 2:
                return poruka.getTextPoruke();
            case 3:
                return sdf.format(poruka.getVremeSlanja());
            default:
                return "n/a";
        }
    }

    public void setListaPoruka(ArrayList<Poruka> listaPoruka) {
        this.listaPoruka = listaPoruka;
        fireTableDataChanged();
    }

    public ArrayList<Poruka> getListaPoruka() {
        return listaPoruka;
    }

}
