package view;

import java.math.BigInteger;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import utilities.MiniMipsUtilities;

import controller.DataMemory;
import controller.Registers;

public class DataSegmentView extends JPanel{
	
	public DefaultTableModel dataMemoryModel;
	public JTable dataMemoryTable;
	TitledBorder title = BorderFactory.createTitledBorder("Data Segment");
	
	public DataSegmentView(){
		
		setBorder(title);
		String[] columnNames = {"Memory","Data"};
		String[][] columnData = {
				{"", ""}
		};
		//dataMemoryModel = new DefaultTableModel(columnData, columnNames);
		dataMemoryTable = new JTable(columnData, columnNames);
		
		JScrollPane js=new JScrollPane(dataMemoryTable);
		js.setVisible(true);
		add(js);
	}
	
	public void refresh(DataMemory dataMemory){
		
		String[] columnNames = {"Address","Data"};
		dataMemoryModel = new DefaultTableModel(null, columnNames){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		  };
		
		dataMemoryTable = new JTable(dataMemoryModel);
	
		for(int i = 0; i < dataMemory.dataMemory.size(); i++){
			addD(i, dataMemory.getDataFromMemory(BigInteger.valueOf(i)));
		}
		
		JScrollPane js=new JScrollPane(dataMemoryTable);
		js.setVisible(true);
		add(js);
		
	}
	
	public void addD(int index, BigInteger data){
		String[] a = new String[]{Integer.toString(index), MiniMipsUtilities.getPaddedHex128(data)};
		dataMemoryModel.addRow(a);
	}

}
