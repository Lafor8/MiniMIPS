package view;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class FixedColumnPane extends JScrollPane implements ChangeListener, PropertyChangeListener {

	public JTable fixed;
	public JTable main;

	public FixedColumnPane(JTable tableX, int colsToFreeze) {
		super(tableX);
		
		
		this.main = tableX;
		
		TableModel model = main.getModel();
		// create a frozen model
		DefaultTableModel frozenModel = new DefaultTableModel(model.getRowCount(), colsToFreeze);
		// populate the frozen model
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < colsToFreeze; j++) {
				String value = (String) model.getValueAt(i, j);
				frozenModel.setValueAt(value, i, j);
			}
		}
		
		String[] fixedColHeader = { model.getColumnName(0) };
		frozenModel.setColumnIdentifiers(fixedColHeader);
		// create frozen table
		fixed = new JTable(frozenModel);
		// remove the frozen columns from the original table
		for (int j = 0; j < colsToFreeze; j++) {
			main.removeColumn(main.getColumnModel().getColumn(0));
		}

		fixed.setSelectionModel( main.getSelectionModel() );
		main.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// format the frozen table
		JTableHeader header = main.getTableHeader();
		// for(TableColumn tc : )
		// System.err.println(header.getColumnModel().getColumn(0).getHeaderValue());
		fixed.setBackground(header.getBackground());
		fixed.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		fixed.setEnabled(false);
		// set frozen table as row header view
		JViewport viewport = new JViewport();
		viewport.setView(fixed);
		System.out.println("DIMENSIONS: " + fixed.getPreferredSize());
		viewport.setPreferredSize(new Dimension(150, 0));
		setRowHeaderView(fixed);
		setCorner(JScrollPane.UPPER_LEFT_CORNER, fixed.getTableHeader());

		fixed.getColumnModel().getColumn(0).setMinWidth(1000);
		for (int i = 0; i < main.getColumnCount(); i++) {
			main.getColumnModel().getColumn(i).setMaxWidth(50);
		}

		getRowHeader().addChangeListener(this);
	}

	public void resizeTables() {
		fixed.getColumnModel().getColumn(0).setMinWidth(1000);
		for (int i = 0; i < main.getColumnCount(); i++) {
			main.getColumnModel().getColumn(i).setMaxWidth(50);
		}
	}

	public void stateChanged(ChangeEvent e) {
		// Sync the scroll pane scrollbar with the row header

		JViewport viewport = (JViewport) this.getViewport();
		getVerticalScrollBar().setValue(viewport.getViewPosition().y);
	}

	//
	// Implement the PropertyChangeListener
	//
	public void propertyChange(PropertyChangeEvent e) {
		// Keep the fixed table in sync with the main table

		if ("selectionModel".equals(e.getPropertyName())) {
			this.fixed.setSelectionModel(this.main.getSelectionModel());
		}

		if ("model".equals(e.getPropertyName())) {
			this.fixed.setModel(this.main.getModel());
		}
	}
}
