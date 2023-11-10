/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocne.BrojAngazovanja;
import pomocne.BrojProfesora;

/**
 *
 * @author Danilo
 */
public class ModelTabeleBrojProfesora extends AbstractTableModel {

    ArrayList<BrojProfesora> lista = new ArrayList<>();
    String[] nazivi = {"Zvanje", "Broj profesora"};

    @Override

    public int getRowCount() {
        return lista.size();
    }

    public void setLista(ArrayList<BrojProfesora> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        return nazivi[column]; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BrojProfesora bp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bp.getZvanje();
            case 1:
                return bp.getBroj();

            default:
                return bp.getBroj();
        }
    }

}
