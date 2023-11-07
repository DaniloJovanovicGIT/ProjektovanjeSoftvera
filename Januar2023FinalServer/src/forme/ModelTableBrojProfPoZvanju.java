/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocne.BrojProfesoraPoZvanju;
import pomocne.ProfesorBrojAngazovanja;

/**
 *
 * @author Danilo
 */
public class ModelTableBrojProfPoZvanju extends AbstractTableModel{
    ArrayList<BrojProfesoraPoZvanju> lista = new ArrayList<>();
    String[] kolone = {"Zvanje", "Broj profesora"};

    public void setLista(ArrayList<BrojProfesoraPoZvanju> lista) {
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
        BrojProfesoraPoZvanju poZvanju = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return poZvanju.getZvanje();
            case 1:
                return poZvanju.getBrojProfesora();

            default:
                return poZvanju.getBrojProfesora();
        }
    }
}
