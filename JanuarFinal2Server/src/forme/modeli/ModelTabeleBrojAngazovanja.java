/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocne.BrojAngazovanja;

/**
 *
 * @author Danilo
 */
public class ModelTabeleBrojAngazovanja extends AbstractTableModel {

    ArrayList<BrojAngazovanja> lista = new ArrayList<>();
    String[] nazivi = {"Profesor", "Broj angazovanja"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        return nazivi[column];
    }

    public void setLista(ArrayList<BrojAngazovanja> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BrojAngazovanja ba = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ba.getIme();
            case 1:
                return ba.getBroj();

            default:
                throw new AssertionError();
        }
    }

}
