/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Korisnik;
import domen.KorisnikStanje;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModelTabeleKorisnic extends AbstractTableModel {

    ArrayList<KorisnikStanje> lista = new ArrayList<>();

    public void setLista(ArrayList<KorisnikStanje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override

    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Username";
            case 2:
                return "Aktivan";
            default:
                return "Aktivan";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==2){
        return Boolean.class;
        }
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KorisnikStanje k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKorisnickID();
            case 1:
                return k.getKorisnickoIme();
            case 2:
                return k.isAktivan();

            default:
                return "n/a";
        }
    }

}
