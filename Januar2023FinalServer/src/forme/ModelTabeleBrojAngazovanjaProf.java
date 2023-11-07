/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocne.ProfesorBrojAngazovanja;

/**
 *
 * @author Danilo
 */
public class ModelTabeleBrojAngazovanjaProf extends AbstractTableModel {

    ArrayList<ProfesorBrojAngazovanja> lista = new ArrayList<>();
    String[] kolone = {"Profesor", "Broj angazovanja"};

    public void setLista(ArrayList<ProfesorBrojAngazovanja> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProfesorBrojAngazovanja pab = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pab.getIme()+" "+pab.getPrezime();
            case 1:
                return pab.getBrojAngazovanja();

            default:
                return pab.getBrojAngazovanja();
        }
    }

}
