package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import utilities.MiniMipsUtilities;

import controller.DataMemory;

public class InteractiveForm extends JPanel {
	
	TitledBorder title = BorderFactory.createTitledBorder("Data Segment");
	
	
    public static final String[] columnNames = {
        "Address", "Value", ""
    };

    protected JTable table;
    protected JScrollPane scroller;
    protected InteractiveTableModel tableModel;

    public InteractiveForm() {
        initComponent();
    }

    public void initComponent() {
    	setBorder(title);
        tableModel = new InteractiveTableModel(columnNames);
        tableModel.addTableModelListener(new InteractiveForm.InteractiveTableModelListener());
        table = new JTable();
        table.setModel(tableModel);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyRow()) {
            tableModel.addEmptyRow();
        }

        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        TableColumn hidden = table.getColumnModel().getColumn(InteractiveTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        hidden.setCellRenderer(new InteractiveRenderer(InteractiveTableModel.HIDDEN_INDEX));

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public void highlightLastRow(int row) {
        int lastrow = tableModel.getRowCount();
        if (row == lastrow - 1) {
            table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
        } else {
            table.setRowSelectionInterval(row + 1, row + 1);
        }

        table.setColumnSelectionInterval(0, 0);
    }

    class InteractiveRenderer extends DefaultTableCellRenderer {
        protected int interactiveColumn;

        public InteractiveRenderer(int interactiveColumn) {
            this.interactiveColumn = interactiveColumn;
        }

        public Component getTableCellRendererComponent(JTable table,
           Object value, boolean isSelected, boolean hasFocus, int row,
           int column)
        {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == interactiveColumn && hasFocus) {
                if ((InteractiveForm.this.tableModel.getRowCount() - 1) == row &&
                   !InteractiveForm.this.tableModel.hasEmptyRow())
                {
                    InteractiveForm.this.tableModel.addEmptyRow();
                }

                highlightLastRow(row);
            }

            return c;
        }
    }

    public class InteractiveTableModelListener implements TableModelListener {
        public void tableChanged(TableModelEvent evt) {
            if (evt.getType() == TableModelEvent.UPDATE) {
                int column = evt.getColumn();
                int row = evt.getFirstRow();
                //System.out.println("row: " + row + " column: " + column);
                table.setColumnSelectionInterval(column + 1, column + 1);
                table.setRowSelectionInterval(row, row);
            }
        }
    }
    
    public DataMemory getDataSegementValuesFromUI(){
    	DataMemory newValues = new DataMemory();
    	
    	for(int row = 0; row < table.getRowCount(); row++){
    		if(table.getValueAt(row, 1)!= ""){
        		newValues.setDataToMemory(BigInteger.valueOf(row),  new BigInteger((String) table.getValueAt(row, 1)));
    		}
    	}
    	
    	return newValues;
    }
    
	public void refresh(DataMemory dataMemory){
		System.out.println("GDIGDCOUF");
		TreeMap<BigInteger, BigInteger> map = new TreeMap<>();
		map.putAll(dataMemory.dataMemory);
		int i = 0;
		for (Entry<BigInteger, BigInteger> dataEntry : map.entrySet()) {
				tableModel.setValueAt(MiniMipsUtilities.getPaddedHex(dataEntry.getKey()).substring(4), i,0);
				tableModel.setValueAt(MiniMipsUtilities.getPaddedHex(dataEntry.getValue()), i, 1);
				//System.out.println("KEY " + MiniMipsUtilities.getPaddedHex(dataEntry.getKey()).substring(4) + "ROW" + i + "Column" + 0);
				//System.out.println("VALUE" + MiniMipsUtilities.getPaddedHex(dataEntry.getValue()) + "ROW" + i + "Column" + 1);
				i++;	
			}
		}
}

