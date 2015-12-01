package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class FixedColumnPane extends JScrollPane {
	public FixedColumnPane(JTable table, int colsToFreeze) {
		super(table);
		TableModel model = table.getModel();
		// create a frozen model
		DefaultTableModel frozenModel = new DefaultTableModel(model.getRowCount(), colsToFreeze);
		// populate the frozen model
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < colsToFreeze; j++) {
				String value = (String) model.getValueAt(i, j);
				frozenModel.setValueAt(value, i, j);
				System.err.println(value);
			}
		}
		String[] fixedColHeader = {model.getColumnName(0)};
		frozenModel.setColumnIdentifiers(fixedColHeader);
		// create frozen table
		JTable frozenTable = new JTable(frozenModel);
		// remove the frozen columns from the original table
		for (int j = 0; j < colsToFreeze; j++) {
			table.removeColumn(table.getColumnModel().getColumn(0));
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// format the frozen table
		JTableHeader header = table.getTableHeader();
		//for(TableColumn tc : )
		//System.err.println(header.getColumnModel().getColumn(0).getHeaderValue());
		frozenTable.setBackground(header.getBackground());
		frozenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		frozenTable.setEnabled(false);
		// set frozen table as row header view
		JViewport viewport = new JViewport();
		viewport.setView(frozenTable);
		viewport.setPreferredSize(frozenTable.getPreferredSize());
		setRowHeaderView(viewport);
		setCorner(JScrollPane.UPPER_LEFT_CORNER, frozenTable.getTableHeader());
	}
}
