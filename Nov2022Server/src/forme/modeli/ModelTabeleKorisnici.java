/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleKorisnici extends AbstractTableModel {

    ArrayList<User> listaUsera = new ArrayList<>();
    String[] nazivi = {"Username", "Aktivan"};

    public void setListaUsera(ArrayList<User> listaUsera) {
        this.listaUsera = listaUsera;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listaUsera.size();
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = listaUsera.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getUsername();
            case 1:
                return u.isAktivan();

            default:
                return u.isAktivan();
        }
    }

}
