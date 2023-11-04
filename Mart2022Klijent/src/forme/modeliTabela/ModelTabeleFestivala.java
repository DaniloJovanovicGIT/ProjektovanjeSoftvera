/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeliTabela;

import domen.Festival;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleFestivala extends AbstractTableModel {

    ArrayList<Festival> lista = new ArrayList<>();
    String[] nazivi = {"Naziv", "Datum od", "Datum do", "Opis"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public void setLista(ArrayList<Festival> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return nazivi[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Festival f = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return f.getNaziv();
            case 1:
                return f.getDatumOd();
            case 2:
                return f.getDatumDo();
            case 3:
                return f.getOpis();

            default:
                return f.getOpis();
        }
    }

}
