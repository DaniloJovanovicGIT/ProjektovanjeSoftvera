/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import domen.Komponenta;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleKomponenti extends AbstractTableModel {

    ArrayList<Komponenta> lista = new ArrayList<>();
    
    public void add(Komponenta k){
        lista.add(k);
        fireTableDataChanged();
    }
    
    public void obrisi(int red){
        lista.remove(red);
        fireTableDataChanged();
    }
    
    public void setLista(ArrayList<Komponenta> lista) {
        this.lista = lista;
    }

    public ArrayList<Komponenta> getLista() {
        return lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "RB";
            case 1:
                return "Komponenta";
            case 2:
                return "Kolicina";
            case 3:
                return "Kontrolisano";

            default:
                return "n/a";

        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return int.class;
            case 3:
                return boolean.class;

            default:
                return String.class;

        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Komponenta k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return 999;
            case 1:
                return k.getNaziv();
            case 2:
                return k.getKolicina();
            case 3:
                return k.isKontrolisano();

            default:
                return "n/a";

        }
    }

}
