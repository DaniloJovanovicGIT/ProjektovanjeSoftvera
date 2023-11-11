/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.StavkaProgramskogVodica;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ModeltabeleStavki extends AbstractTableModel {

    ArrayList<StavkaProgramskogVodica> lista = new ArrayList<>();
    String[] kolone = {"RB", "Stanica", "Naziv emisije", "Datum i vreme od", "Datum i vreme do", "Tip emisije", "Podtip emisije"};

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

    public void setLista(ArrayList<StavkaProgramskogVodica> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public void addStavka(StavkaProgramskogVodica stavka) {
        lista.add(stavka);
        fireTableDataChanged();
    }

    public void removeStavka(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaProgramskogVodica spv = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return spv.getRB();
            case 1:
                return spv.getStanica();
            case 2:
                return spv.getNazivEmisije();
            case 3:
                return spv.getDatumVremeOd();
            case 4:
                return spv.getDatumVremeDo();
            case 5:
                return spv.getTipEmisije().getNadtipEmisije();
            case 6:
                return spv.getTipEmisije();

            default:
                throw new AssertionError();
        }
    }

    public ArrayList<StavkaProgramskogVodica> getLista() {
        return lista;
    }

}
