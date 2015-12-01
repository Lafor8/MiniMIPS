package view;

import java.util.Collection;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class InteractiveTableModel extends AbstractTableModel {
    public static final int TITLE_INDEX = 0;
    public static final int ARTIST_INDEX = 1;
    //public static final int ALBUM_INDEX = 2;
    public static final int HIDDEN_INDEX = 2;

    protected String[] columnNames;
    protected Vector dataVector;

    public InteractiveTableModel(String[] columnNames) {
        this.columnNames = columnNames;
        dataVector = new Vector();
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        if (column == HIDDEN_INDEX) return false;
        else return true;
    }

    public Class getColumnClass(int column) {
        switch (column) {
            case TITLE_INDEX:
            case ARTIST_INDEX:
            default:
               return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        DataSegmentObject record = (DataSegmentObject)dataVector.get(row);
        switch (column) {
            case TITLE_INDEX:
               return record.getTitle();
            case ARTIST_INDEX:
               return record.getArtist();
            default:
               return new Object();
        }
    }

    public void setValueAt(Object value, int row, int column) {
        DataSegmentObject record = (DataSegmentObject)dataVector.get(row);
        switch (column) {
            case TITLE_INDEX:
               record.setTitle(value.toString());
               break;
            case ARTIST_INDEX:
               record.setArtist(value.toString());
               break;
            default:
               System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public boolean hasEmptyRow() {
        if (dataVector.size() == 0) return false;
        DataSegmentObject audioRecord = (DataSegmentObject)dataVector.get(dataVector.size() - 1);
        if (audioRecord.getTitle().trim().equals("") &&
           audioRecord.getArtist().trim().equals(""))
        {
           return true;
        }
        else return false;
    }

    public void addEmptyRow() {
        dataVector.add(new DataSegmentObject());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }
}